package ru.softlab.zikova.efr.processes;


import org.springframework.beans.factory.annotation.Autowired;
import ru.softlab.efr.common.bpm.support.ActionRequestAdapter;
import ru.softlab.efr.common.bpm.support.ExecutionContext;
import ru.softlab.zikova.efr.entity.ClientEntity;
import ru.softlab.zikova.efr.exchange.model.Client;
import ru.softlab.zikova.efr.repository.ClientRepository;


public class SimpleUserTask implements ActionRequestAdapter<Client> {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void process(ExecutionContext executionContext, Client client) {
        clientRepository.save(new ClientEntity(null, "Adam", "Sendler", true));
    }
}
