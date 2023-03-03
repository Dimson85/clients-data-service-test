package ru.softlab.zikova.efr.controller.integration;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.softlab.efr.test.infrastructure.transport.rest.MockRestRule;
import ru.softlab.zikova.efr.config.IntegrationTestApplicationConfig;
import ru.softlab.zikova.efr.controller.ClientApi;
import ru.softlab.zikova.efr.entity.ClientEntity;
import ru.softlab.zikova.efr.exchange.model.Client;
import ru.softlab.zikova.efr.repository.ClientRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ru.softlab.efr.test.infrastructure.transport.rest.matchers.MockRestResultMatchers.status;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = IntegrationTestApplicationConfig.class)
public class ClientControllerIntegrationTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    @Rule
    public MockRestRule mockRestRule;

    @Test
    public void getId() throws Exception {
        ClientEntity clientEntity =
                new ClientEntity(null, "Mike", "Jobs", true);
        clientRepository.save(clientEntity);
        mockRestRule.init()
                .path(ClientApi.FIND_CLIENT_BY_ID_PATH)
                .variable("id", clientEntity.getId())
                .get(Client.class)
                .andExpect(status().isOk());
    }

    @Test
    public void saveAndUpdateClient() throws Exception {
        Client clientEntity = new Client(null, "Bob", "Mag", true);
        Client clientSave = saveClient(clientEntity);
        clientSave.setName("Mike");
        Client clientUpdate = (Client) mockRestRule
                .init()
                .setTimeoutInSeconds(60L)
                .path(ClientApi.UPDATE_CLIENT_PATH)
                .put(clientSave, Client.class)
                .andExpect(status().isOk())
                .andReturnBody();
        assertEquals(clientUpdate.getName(), "Mike");
    }

    @Test
    public void deleteClient() throws Exception {
        Client clientEntity = new Client(null, "Bob", "Mag", true);
        Client clientSave = saveClient(clientEntity);

        mockRestRule.init()
                .setTimeoutInSeconds(60L)
                .path(ClientApi.DELETE_CLIENT_PATH)
                .variable("id", clientSave.getId())
                .delete()
                .andExpect(status().isNoContent());

    }

    @Test
    public void testFindClientByIdNotFound() throws Exception {
        mockRestRule.init()
                .path(ClientApi.FIND_CLIENT_BY_ID_PATH)
                .variable("id", -1)
                .get(ClientEntity.class)
                .andExpect(status().isNotFound());
    }

    private Client saveClient(Client clientEntity) throws Exception {
        Client clientSave = (Client) mockRestRule
                .init()
                .setTimeoutInSeconds(60L)
                .path(ClientApi.SAVE_CLIENT_PATH)
                .post(clientEntity, Client.class)
                .andExpect(status().isOk())
                .andReturnBody();
        assertNotNull(clientSave.getId());
        assertEquals(clientSave.getName(), clientEntity.getName());
        return clientSave;
    }
}
