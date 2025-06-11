package com.profecarlos.tallerapirest.restapi.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.profecarlos.tallerapirest.restapi.model.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByNombre(String nombre);

    List<Usuario> findByRol(String rol); // Corregido aquí

    Optional<Usuario> findByEmail(String email); // Útil si quieres login más adelante
}
