package com.sam.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sam.api.entities.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long>{

}
