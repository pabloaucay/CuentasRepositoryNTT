package com.aucsoft.servicemovimientos.entity;

import com.aucsoft.servicemovimientos.Model.Cuenta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Data
@Table(name="tbl_movimientos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_movimiento_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private TipoMovimiento tipoMovimiento;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @NotNull(message = "No puede ser vacio")
    @Column(name = "numero_cuenta")
    private String numeroCuenta;
    private Long valor;
    private Long saldo;
    @Transient
    private Cuenta cuenta;

}
