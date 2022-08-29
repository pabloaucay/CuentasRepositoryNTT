package com.aucsoft.servicemovimientos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="tbl_tipos_movimiento")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoMovimiento {
    @Id
    private int id;
    private String descripcion;
}