package com.sam.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sam.api.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	boolean existsByEmailIgnoreCase(String email);
}
