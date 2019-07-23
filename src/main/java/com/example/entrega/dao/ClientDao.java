package com.example.entrega.dao;

import com.example.entrega.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientDao extends JpaRepository<ClientEntity, Integer> {
    List<ClientEntity> findAll();
    ClientEntity findClientEntityById(Integer id);

}
