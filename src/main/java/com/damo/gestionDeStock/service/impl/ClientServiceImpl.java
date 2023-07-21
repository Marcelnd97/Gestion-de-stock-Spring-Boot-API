package com.damo.gestionDeStock.service.impl;

import com.damo.gestionDeStock.dto.ClientDto;
import com.damo.gestionDeStock.handlers.exception.EntityNotFoundException;
import com.damo.gestionDeStock.handlers.exception.ErrorCodes;
import com.damo.gestionDeStock.handlers.exception.InvalidEntityException;
import com.damo.gestionDeStock.handlers.exception.InvalideOperationException;
import com.damo.gestionDeStock.model.Client;
import com.damo.gestionDeStock.model.CommandeClient;
import com.damo.gestionDeStock.repository.ClientRepository;
import com.damo.gestionDeStock.repository.CommandeClientRepository;
import com.damo.gestionDeStock.service.ClientService;
import com.damo.gestionDeStock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {


    private ClientRepository clientRepository;
    private CommandeClientRepository commandeClientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, CommandeClientRepository commandeClientRepository){
        this.clientRepository = clientRepository;
        this.commandeClientRepository = commandeClientRepository;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {

        List<String> errors = ClientValidator.validator(clientDto);

        if (!errors.isEmpty()) {
            log.error("Client is not valid {}", clientDto);
            throw new InvalidEntityException("Le client n'est pas valide",
                    ErrorCodes.CLIENT_NOT_VALID, errors);
        }

        return ClientDto.fromEntity(clientRepository.save(ClientDto.toEntity(clientDto)));
    }

    @Override
    public ClientDto findById(Integer id) {

        if (id == null){
            log.error("Client ID is not valid");
            return  null;
        }

        Optional<Client> client = clientRepository.findById(id);

        return Optional.of(ClientDto.fromEntity(client.get())).orElseThrow(()->
                new EntityNotFoundException("Aucun client avec l'ID =" + id + "n'est trouver dans la BDD",
                ErrorCodes.CLIENT_NOT_FOUND));
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("Category ID is null");
            return;
        }
        List<CommandeClient> commandeClients = commandeClientRepository.findAllByClientId(id);
        if (!commandeClients.isEmpty()) {
            throw new InvalideOperationException("Impossible de supprimer un client qui a deja des commande clients",
                    ErrorCodes.CLIENT_ALREADY_IN_USE);
        }
        clientRepository.deleteById(id);
    }

}
