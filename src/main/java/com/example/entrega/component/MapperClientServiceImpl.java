package com.example.entrega.component;

import com.example.entrega.dto.ClientDto;
import com.example.entrega.model.ClientEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapperClientServiceImpl implements MapperClientService {

    @Override
    public ClientEntity map(ClientDto clientDto) {
        final ClientEntity clientEntity = new ClientEntity();

        clientEntity.setDni(clientDto.getDni());
        clientEntity.setId(clientDto.getId());
        clientEntity.setName(clientDto.getName());

        return clientEntity;
    }

    @Override
    public ClientDto map(ClientEntity clientEntity) {
        final ClientDto clientDto = new ClientDto();

        clientDto.setDni(clientEntity.getDni());
        clientDto.setId(clientEntity.getId());
        clientDto.setName(clientEntity.getName());

        return clientDto;
    }

    @Override
    public List<ClientDto> map(List<ClientEntity> clientEntities) {
        List<ClientDto> clientsDto = new ArrayList<>();
        for (ClientEntity b : clientEntities) {
            ClientDto clientDto = map(b);
            clientsDto.add(clientDto);
        }
        return clientsDto;
    }

    @Override
    public List<ClientEntity> map2(List<ClientDto> clientDtos) {
        List<ClientEntity> clientEntities = new ArrayList<>();
        for (ClientDto b : clientDtos) {
            ClientEntity clientEntity = map(b);
            clientEntities.add(clientEntity);
        }
        return clientEntities;
    }
}
