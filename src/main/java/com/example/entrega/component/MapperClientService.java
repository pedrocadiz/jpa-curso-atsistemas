package com.example.entrega.component;


import com.example.entrega.dto.ClientDto;
import com.example.entrega.model.ClientEntity;

import java.util.List;

public interface MapperClientService {
    ClientEntity map(ClientDto clientDto);
    ClientDto map(ClientEntity clientEntity);
    List<ClientDto> map(List<ClientEntity> clientEntities);
    List<ClientEntity> map2(List<ClientDto> clientDtos);
}
