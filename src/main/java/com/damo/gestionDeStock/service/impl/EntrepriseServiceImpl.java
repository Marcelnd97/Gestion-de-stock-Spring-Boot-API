package com.damo.gestionDeStock.service.impl;


import com.damo.gestionDeStock.dto.EntrepriseDto;
import com.damo.gestionDeStock.dto.RolesDto;
import com.damo.gestionDeStock.dto.UtilisateurDto;
import com.damo.gestionDeStock.exception.EntityNotFoundException;
import com.damo.gestionDeStock.exception.ErrorCodes;
import com.damo.gestionDeStock.exception.InvalidEntityException;
import com.damo.gestionDeStock.model.Entreprise;
import com.damo.gestionDeStock.repository.EntrepriseRepository;
import com.damo.gestionDeStock.repository.RolesRepository;
import com.damo.gestionDeStock.service.EntrepriseService;
import com.damo.gestionDeStock.service.UtilisateurService;
import com.damo.gestionDeStock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(rollbackOn = Exception.class)
@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {


    private EntrepriseRepository entrepriseRepository;

    private UtilisateurService utilisateurService;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository,
                                 UtilisateurService utilisateurService,
                                 RolesRepository rolesRepository,
                                 PasswordEncoder passwordEncoder) {

        this.entrepriseRepository = entrepriseRepository;
        this.utilisateurService = utilisateurService;
        this.rolesRepository = rolesRepository;
    }



    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {

        List<String> errors = EntrepriseValidator.validator(entrepriseDto);

        if (!errors.isEmpty()){
            log.error("Entreprise is not valid {} ", entrepriseDto);
            throw  new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_FOUND);
        }

        EntrepriseDto savedEntreprise = EntrepriseDto.fromEntity(
                entrepriseRepository.save(EntrepriseDto.toEntity(entrepriseDto)));
        UtilisateurDto utilisateur = UtilisateurDto.fromEntreprise(savedEntreprise);
        UtilisateurDto savedUser = utilisateurService.save(utilisateur);


        RolesDto rolesDto = RolesDto.builder()
                .roleName("ADMIN")
                .utilisateur(savedUser)
                .build();
        rolesRepository.save(RolesDto.toEntity(rolesDto));

        return savedEntreprise;
    }

    private UtilisateurDto fromEntreprise(EntrepriseDto entrepDto){

        return UtilisateurDto.builder()
                .adresse(entrepDto.getAdresse())
                .nom(entrepDto.getNom())
                .prenom(entrepDto.getCodeFiscale())
                .mail(entrepDto.getEmail())
                .motDePasse(generateRandomPassword())
                .entreprise(entrepDto)
                .datenaissance(Instant.now())
                .photo(entrepDto.getPhoto())
                .build();
    }

    private String generateRandomPassword(){
        return "som3R@nd0mP@$$word";
    }

    @Override
    public EntrepriseDto findById(Integer id) {

        if(id == null){
            log.error("Entreprise ID is not valid");
            return  null;
        }

        Optional<Entreprise> entreprise = entrepriseRepository.findById(id);

        return Optional.of(EntrepriseDto.fromEntity(entreprise.get())).orElseThrow(() ->
                new EntityNotFoundException("Aucune entreprise avec l'ID =" + id + "n'est trouver dans la BDD",
                        ErrorCodes.ENTREPRISE_NOT_FOUND));
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {

        if (id == null){
            log.error("Entreprise ID is not valid");
            return;
        }
        entrepriseRepository.deleteById(id);

    }
}
