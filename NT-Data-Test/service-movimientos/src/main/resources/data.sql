insert into tbl_tipos_movimiento(id,descripcion)
values (1,'Credito');
    insert into tbl_tipos_movimiento (id,descripcion)
values (2,'Debito');

insert into tbl_movimientos (id,tipo_movimiento_id,fecha,numero_cuenta,valor,saldo)
values (11,1,'2022-08-25','000004',10,90);
insert into tbl_movimientos (id,tipo_movimiento_id,fecha,numero_cuenta,valor,saldo)
values (12,1,'2022-08-24','000004',10,100);

insert into tbl_movimientos (id,tipo_movimiento_id,fecha,numero_cuenta,valor,saldo)
values (13,1,'2022-08-26','000004',10,110);

insert into tbl_movimientos (id,tipo_movimiento_id,fecha,numero_cuenta,valor,saldo)
values (14,2,'2018-08-28','000005',25,150);
