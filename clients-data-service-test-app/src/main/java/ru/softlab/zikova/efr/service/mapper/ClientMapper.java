package ru.softlab.zikova.efr.service.mapper;

import org.springframework.stereotype.Service;
import ru.softlab.zikova.efr.entity.ClientEntity;
import ru.softlab.zikova.efr.exchange.model.Client;

/**
 * Сервис парсинга сущностей Client, ClientEntity
 *
 * @author vasilenko
 * @since 11.11.2022
 */
@Service
public class ClientMapper {


    /**
     * Метод парсинга ClientEntity в Client
     * @param entity сущность ClientEntity
     * @return сущность Client
     * */
    public Client parseClientEntityInClient(ClientEntity entity){
        return ((entity==null)? new Client(): new Client(
                entity.getId(),
                entity.getName(),
                entity.getSurname(),
                entity.getIsActive()
        ));
    }

    /**
     * Метод парсинга Client в ClientEntity
     * @param client сущность Client
     * @return сущность ClientEntity
     * */
    public ClientEntity parseClientInClientEntity(Client client){
        return ((client==null)? ClientEntity.builder().build(): ClientEntity.builder()
                .id(client.getId())
                .name(client.getName())
                .surname(client.getSurname())
                .isActive(client.isIsActive())
                .build());
    }
}
