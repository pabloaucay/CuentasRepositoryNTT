package com.aucsoft.servicemovimientos.service;

import com.aucsoft.servicemovimientos.Model.Cuenta;
import com.aucsoft.servicemovimientos.client.CuentaClient;
import com.aucsoft.servicemovimientos.entity.Movimiento;
import com.aucsoft.servicemovimientos.entity.TipoMovimiento;
import com.aucsoft.servicemovimientos.exception.SaldoInsuficienteException;
import com.aucsoft.servicemovimientos.exception.ValorRequeridoException;
import com.aucsoft.servicemovimientos.repository.MovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImplement implements MovimientoService{
    private final MovimientoRepository movimientoRepository;
    @Autowired
    CuentaClient cuentaClient;
    @Override
    public List<Movimiento> getMovimientosByNumeroCuenta(String numeroCuenta, Date inicio, Date fin) {
        List<Movimiento> movimientos=movimientoRepository.findMovimientosByFechaBetweenAndNumeroCuenta(inicio,fin,numeroCuenta);

        Cuenta cuenta= cuentaClient.getCuentaByNumeroCuenta(numeroCuenta).getBody();
        List<Movimiento>rMovimientos = movimientos.stream().map(movimientoItem-> {
            movimientoItem.setCuenta(cuenta);
            return movimientoItem;
        }).collect(Collectors.toList());

        return  rMovimientos;
    }

    @Override
    public List<Movimiento> getMovimientosByNumeroCuenta(String numeroCuenta) {
        List<Movimiento> movimientos=movimientoRepository.findBynumeroCuenta(numeroCuenta);
        if (movimientos.isEmpty()){
            return null;
        }
        Cuenta cuenta= cuentaClient.getCuentaByNumeroCuenta(numeroCuenta).getBody();
        List<Movimiento>rMovimientos = movimientos.stream().map(movimientoItem-> {
            movimientoItem.setCuenta(cuenta);
            return movimientoItem;
        }).collect(Collectors.toList());

        return  rMovimientos;
    }

    @Override
    public Movimiento getlastMovimientoByNumeroCuenta(String numeroCuenta) {
        Movimiento movimiento=movimientoRepository.findTopBynumeroCuentaOrderByFechaDesc(numeroCuenta);
        if(movimiento==null){
            return null;
        }
        movimiento.setCuenta( cuentaClient.getCuentaByNumeroCuenta(numeroCuenta).getBody());
        return  movimiento;
    }

    @Override
    public List<Movimiento> getMovimientoByIdCliente(Long idCliente, Date inicio, Date fin) {
        List<Cuenta> cuentas=cuentaClient.getCuentaByIdentificacion(idCliente).getBody();
        if(cuentas.isEmpty()){
            return null;
        }
        List<Movimiento> movimientos= new ArrayList<Movimiento>();
        for (int i=0;i<cuentas.size();i++){
            List<Movimiento> movimientByCuenta=getMovimientosByNumeroCuenta(cuentas.get(i).getNumeroCuenta(),inicio,fin);
            if (!movimientByCuenta.isEmpty()){
                movimientos.addAll(movimientByCuenta);
            }
        }
        if (movimientos.isEmpty()){
            return null;
        }
        return movimientos;
    }

    @Override
    public Movimiento createMovimiento(Movimiento movimiento) throws SaldoInsuficienteException, ValorRequeridoException {
        if(movimiento.getValor()==0L){
            throw new ValorRequeridoException();
        }
        movimiento.setFecha(new Date());
        Movimiento lastMovimientoDB= getlastMovimientoByNumeroCuenta(movimiento.getNumeroCuenta());
        Long saldoNuevo=0L;
        if(movimiento.getValor()>0){
            movimiento.setTipoMovimiento(TipoMovimiento.builder().id(1).build());
        }else {
            movimiento.setTipoMovimiento(TipoMovimiento.builder().id(2).build());
        }
        if(lastMovimientoDB!=null){
            saldoNuevo=lastMovimientoDB.getSaldo()+ movimiento.getValor();
            if(saldoNuevo<0){
                throw new SaldoInsuficienteException();
            }
        }else {
            saldoNuevo= cuentaClient.getCuentaByNumeroCuenta(movimiento.getNumeroCuenta()).getBody().getSaldoInicial()+movimiento.getValor();
        }

        movimiento.setSaldo(saldoNuevo.longValue());
        return movimientoRepository.save(movimiento);
    }
}
