package com.aucsoft.servicecuentas.entity;

import com.aucsoft.servicecuentas.Model.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@Table(name="tbl_cuentas")
@AllArgsConstructor
@NoArgsConstructor @Builder
public class Cuenta {
    @Id
    @NotEmpty(message = "El campo no puede ser vacio")
    @Column(name = "numero_cuenta",unique = true,nullable = false)
    @Size(min=6,max = 6, message = "La logitud de la debe ser 6")
    private String numeroCuenta;
    @Column(name = "id_cliente",nullable = false)
    private Long idCliente;
    @NotNull(message = "No puede ser vacio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_cuenta_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private TipoCuenta tipoCuenta;
    @PositiveOrZero(message = "El saldo inicial debe ser mayor o igual a 0")
    @Column(name = "saldo_inicial")
    private Long saldoInicial;
    @Column(columnDefinition = "boolean default true")
    private boolean estado;
    @Transient
    private Cliente cliente;

}
