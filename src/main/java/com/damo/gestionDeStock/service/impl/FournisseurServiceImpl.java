package com.damo.gestionDeStock.service.impl;

import com.damo.gestionDeStock.dto.FournisseurDto;
import com.damo.gestionDeStock.handlers.exception.EntityNotFoundException;
import com.damo.gestionDeStock.handlers.exception.ErrorCodes;
import com.damo.gestionDeStock.handlers.exception.InvalidEntityException;
import com.damo.gestionDeStock.handlers.exception.InvalideOperationException;
import com.damo.gestionDeStock.model.CommandeFournisseur;
import com.damo.gestionDeStock.model.Fournisseur;
import com.damo.gestionDeStock.repository.CommandeFournisseurRepository;
import com.damo.gestionDeStock.repository.FournisseurRepository;
import com.damo.gestionDeStock.service.FournisseurService;
import com.damo.gestionDeStock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {


    private FournisseurRepository fournisseurRepository;
    private CommandeFournisseurRepository commandeFournisseurRepository;

    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository, CommandeFournisseurRepository commandeFournisseurRepository){
        this.fournisseurRepository = fournisseurRepository;
        this.commandeFournisseurRepository = commandeFournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto fournisDto) {

        List<String> errors = FournisseurValidator.validator(fournisDto);

        if (!errors.isEmpty()){
            log.error("Fournisseur is not valid {}", fournisDto);
            throw  new InvalidEntityException("Le fournisseur n'est pas valide",
                    ErrorCodes.FOURNISSEUR_NOT_VALID);
        }

        return FournisseurDto.fromEntity(fournisseurRepository.save(FournisseurDto.toEntity(fournisDto)));
    }

    @Override
    public FournisseurDto findById(Integer id) {
        if(id == null){
            log.error("Fournisseur ID is not valid");
            return  null;
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);

        return Optional.of(FournisseurDto.fromEntity(fournisseur.get())).orElseThrow(() ->
                new EntityNotFoundException("Aucun fournisseur avec l'ID =" + id + "n'est trouver dans la BDD",
                        ErrorCodes.FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("Fournisseur ID is not valid");
            return;
        }
        List<CommandeFournisseur> commandeFournisseur = commandeFournisseurRepository.findAllByFournisseurId(id);
        if (!commandeFournisseur.isEmpty()) {
            throw new InvalideOperationException("Impossible de supprimer un fournisseur qui a deja des commandes",
                    ErrorCodes.FOURNISSEUR_ALREADY_IN_USE);
        }

        fournisseurRepository.deleteById(id);

    }
}
