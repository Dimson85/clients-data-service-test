package ru.softlab.zikova.efr.service;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.softlab.efr.services.auth.Right;
import ru.softlab.efr.services.authorization.annotations.HasRight;
import ru.softlab.zikova.efr.controller.ClientApi;
import ru.softlab.zikova.efr.entity.ClientEntity;
import ru.softlab.zikova.efr.exchange.model.Client;
import ru.softlab.zikova.efr.repository.ClientRepository;
import ru.softlab.zikova.efr.service.mapper.ClientMapper;

/**
 * Сервис работы с Client
 *
 * @author vasilenko
 * @since 11.11.2022
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ClientService implements ClientApi.Delegate {
    /**
     * @serial clientRepository Репозиторий работы с сущностью ClientEntity
     */
    private final ClientRepository clientRepository;
    /**
     * @serial clientMapper Сервис преобразования сущностей Client, ClientEntity
     */
    private final ClientMapper clientMapper;


    /**
     * @param id идентификатор для получения сущности Client
     */
    @Override
    public ResponseEntity<Void> deleteClient(Long id) throws Exception {
        return ResponseEntity.noContent().build();
    }

    /**
     * Метод поиска сущности Client
     *
     * @param id идентификатор для получения сущности Client
     * @return сущность Client
     */
    @Override
    public ResponseEntity<Client> findClientById(Long id) {
        ClientEntity entity = findClientEntityById(id);
        Client client = clientMapper.parseClientEntityInClient(entity);
        return ResponseEntity.ok(client);
    }

    /**
     * Метод сохранения сущности Client в БД
     *
     * @param client сущность для сохранения в БД
     * @return сущность Client
     */
    @Override
    public ResponseEntity<Client> saveClient(Client client) {
        ClientEntity entity = clientMapper.parseClientInClientEntity(client);
        saveClientEntityInDb(entity);
        client.setId(entity.getId());
        return ResponseEntity.ok(client);
    }

    /**
     * Метод обновления данных сущности Client в БД
     *
     * @param client сущность с обновленными данными
     * @return сущность Client
     */
    @Override
    @HasRight(Right.ACCESS_PRIVATE_BANKING_CLIENTS)
    public ResponseEntity<Client> updateClient(Client client) {
        setDataInClient(client);
        return ResponseEntity.ok(client);
    }

    /**
     * Метод обновления данных сущности Client
     *
     * @param client сохраняемая сущность
     */
    private void setDataInClient(Client client) {
        ClientEntity entity = findClientEntityById(client.getId());
        entity.setName(client.getName());
        entity.setSurname(client.getSurname());
        entity.setIsActive(client.isIsActive());
        saveClientEntityInDb(entity);
    }

    /**
     * Метод поиска сущности ClientEntity по Id
     *
     * @param id идентификатор для получения сущности ClientEntity
     * @return сущность ClientEntity
     */
    public ClientEntity findClientEntityById(Long id) {
        return clientRepository.findClientEntitiesById(id)
                .orElseThrow(() -> new NotFoundException("Client is empty in DB"));
    }

    /**
     * @param clientEntity сущность для сохранения в БД
     */
    private void saveClientEntityInDb(ClientEntity clientEntity) {
        clientRepository.save(clientEntity);
    }
}
