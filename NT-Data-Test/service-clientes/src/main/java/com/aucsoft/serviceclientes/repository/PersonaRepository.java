package com.aucsoft.serviceclientes.repository;

import com.aucsoft.serviceclientes.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona,String> {
    public List<Persona> findByIdentificacion(String identificacion);
    public List<Persona> findByNombre (String nombre);
}
