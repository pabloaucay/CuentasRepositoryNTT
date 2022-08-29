package com.aucsoft.servicemovimientos.repository;

import com.aucsoft.servicemovimientos.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento,String> {
    public List<Movimiento> findBynumeroCuenta(String numeroCuenta);
    public Movimiento findTopBynumeroCuentaOrderByFechaDesc(String numeroCuenta);

    public List<Movimiento> findMovimientosByFechaBetweenAndNumeroCuenta(Date startDate, Date endDate, String numeroCuenta);

}
