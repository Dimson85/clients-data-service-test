package ru.softlab.zikova.efr.controller.unit;

import lombok.RequiredArgsConstructor;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.softlab.zikova.efr.config.ControllerTestConfig;
import ru.softlab.zikova.efr.controller.ClientApi;
import ru.softlab.zikova.efr.entity.ClientEntity;
import ru.softlab.zikova.efr.service.ClientService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ControllerTestConfig.class})
public class ClientControllerUnitTest extends ControllerBaseTest {
    @Autowired
    private ClientService clientService;

    @After
    public void tearDown() {
        Mockito.reset(clientService);
    }

    @Test
    public void getClient_Success() throws Exception {
        ClientEntity clientEntity = new ClientEntity(1L, "Chip", "Dale", true);
        when(clientService.findClientEntityById(1L))
                .thenReturn(clientEntity);
        mockMvc.perform(
                        get(ClientApi.FIND_CLIENT_BY_ID_PATH, 1L)
                )
                .andExpect(status().isOk());
    }

}
