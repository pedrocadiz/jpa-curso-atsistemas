package com.example.entrega.service;

import com.example.entrega.dao.ClientDao;
import com.example.entrega.model.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{
    @Autowired private ClientDao clientDao;

    @Override
    public ClientEntity save(ClientEntity clientEntity){
        return clientDao.save(clientEntity);
    }

    @Override
    public List<ClientEntity> save(List<ClientEntity> clientEntities) {
        for (ClientEntity b : clientEntities){
            save(b);
        }
        return clientEntities;
    }

    @Override
    public List<ClientEntity> showAll() {
        return clientDao.findAll();
    }

    @Override
    public ClientEntity findClientEntityById(Integer id) {
        return clientDao.findClientEntityById(id);
    }

    @Override
    public Boolean equals(ClientEntity clientEntity1, ClientEntity clientEntity2) {

        if(!clientEntity1.getId().equals(clientEntity2.getId())) return false;
        if(!clientEntity1.getDni().equals(clientEntity2.getDni())) return false;
        if(!clientEntity1.getName().equals(clientEntity2.getName())) return false;

        return true;
    }

    @Override
    public void delete(ClientEntity clientEntity) {
        clientDao.delete(clientEntity);
    }
}
