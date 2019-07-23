package com.example.entrega.unit;

import com.example.entrega.component.MapperClientServiceImpl;
import com.example.entrega.dao.ClientDao;
import com.example.entrega.model.ClientEntity;
import com.example.entrega.service.ClientServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


//TODO: hacer mas test, no me ha dado tiempo



@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {
    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientDao clientDao;
    @Mock
    private MapperClientServiceImpl mapperClientService;

    @Test
    public void saveClientEntityTest() {
        // GIVEN
        final ClientEntity clientEntity = new ClientEntity();
        clientEntity.setName("prueba1");
        clientEntity.setId(1);
        clientEntity.setDni("12345678A");

        final ClientEntity clientEntity2 = new ClientEntity();
        clientEntity2.setName("prueba1");
        clientEntity2.setId(1);
        clientEntity2.setDni("12345678A");

        // WHEN
        Mockito.when( clientDao.save(clientEntity) ).thenReturn(clientEntity2);


        // THEN
        Assert.assertNotNull(clientService.save(clientEntity));
        Assert.assertEquals(clientEntity, clientService.save(clientEntity));

    }

    @Test
    public void saveClientEntitiesTestAllOk() {
        // GIVEN

        final ClientEntity clientEntity = new ClientEntity();
        clientEntity.setName("prueba1");
        clientEntity.setId(1);
        clientEntity.setDni("12345678A");

        final ClientEntity clientEntity2 = new ClientEntity();
        clientEntity2.setName("prueba1");
        clientEntity2.setId(1);
        clientEntity2.setDni("12345678A");

        List<ClientEntity> clientEntities = new ArrayList<>();
        clientEntities.add(clientEntity);
        clientEntities.add(clientEntity2);

        // WHEN

        // THEN
        Assert.assertNotNull(clientService.save(clientEntities));
        Assert.assertEquals(clientEntities, clientService.save(clientEntities) );

    }

}










