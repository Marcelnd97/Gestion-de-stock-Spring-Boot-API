package com.damo.gestionDeStock.service.impl;

import com.damo.gestionDeStock.dto.ChangerMotDePasseUtilisateurDto;
import com.damo.gestionDeStock.dto.UtilisateurDto;
import com.damo.gestionDeStock.handlers.exception.EntityNotFoundException;
import com.damo.gestionDeStock.handlers.exception.ErrorCodes;
import com.damo.gestionDeStock.handlers.exception.InvalidEntityException;
import com.damo.gestionDeStock.handlers.exception.InvalideOperationException;
import com.damo.gestionDeStock.model.Utilisateur;
import com.damo.gestionDeStock.repository.UtilisateurRepository;
import com.damo.gestionDeStock.service.UtilisateurService;
import com.damo.gestionDeStock.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtiilisateurServiceImpl implements UtilisateurService {

    private PasswordEncoder passwordEncoder;
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtiilisateurServiceImpl(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder){
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UtilisateurDto save(UtilisateurDto utilisDto) {

        List<String> errors = UtilisateurValidator.validator(utilisDto);
        if (!errors.isEmpty()) {
            log.error("Utilisateur is not valid {}", utilisDto);
            throw new InvalidEntityException("L'utilisateur n'est pas valide",
                    ErrorCodes.UTILISATEUR_NOT_FOUND, errors);
        }
        if (userAlreadyExists(utilisDto.getEmail())){
            throw new InvalidEntityException("Un autre utilisateur avec le meme email existe dejà",
                    ErrorCodes.UTILISATEUR_NOT_FOUND,
                    Collections.singletonList("Un autre utilisateur avec le meme email existe dejà dans la BDD"));
        }
        utilisDto.setMotDePasse(passwordEncoder.encode(utilisDto.getMotDePasse()));

        return UtilisateurDto.fromEntity(utilisateurRepository.save(UtilisateurDto.toEntity(utilisDto)));
    }

    private Boolean userAlreadyExists(String email){
        Optional<Utilisateur> user = utilisateurRepository.findUtilisateurByEmail(email);
        return user.isPresent();
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if (id == null){
            log.error("Users ID is not valid");
            return  null;
        }

        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);

        return Optional.of(UtilisateurDto.fromEntity(utilisateur.get())).orElseThrow(()->
                new EntityNotFoundException("Aucun utilisateur avec l'ID =" + id + "n'est trouver dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id == null){
            log.error("Users ID is not valid");
            return;
        }
        utilisateurRepository.findById(id);
    }

    @Override
    public UtilisateurDto findByMail(String mail){
        return utilisateurRepository.findUtilisateurByEmail(mail)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Aucun utilisateur avec l'email :" + mail + "n'a été trouvé dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND)
                );
    }

    @Override
    public UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto changerMotDePasseDto) {
       validate(changerMotDePasseDto);
       Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(changerMotDePasseDto.getId());
       if (utilisateurOptional.isEmpty()){
           log.error("Aucun utilisateur n'a été trouvé avec l'ID" + changerMotDePasseDto.getId());
           throw new InvalideOperationException("Aucun utilisateur n'a été trouvé avec l'ID" + changerMotDePasseDto.getId(),
                   ErrorCodes.UTILISATEUR_NOT_FOUND);
       }
       Utilisateur utilisateur = utilisateurOptional.get();
       utilisateur.setMotDePasse(passwordEncoder.encode(changerMotDePasseDto.getMotDePasse()));

        return UtilisateurDto.fromEntity(
                utilisateurRepository.save(utilisateur));
    }

    private void validate(ChangerMotDePasseUtilisateurDto changerMotDePasseDto){
        if (changerMotDePasseDto == null){
            log.warn("Impossible de modifier le mot de pass avec un objet null");
            throw new InvalideOperationException("Aucune information n'a été fourni pour pouvoir changer le mot de passe",
                    ErrorCodes.USER_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
        if (changerMotDePasseDto.getId() == null){
            log.warn("Impossible de modifier le mot de passe avec l'ID null");
            throw new InvalideOperationException("ID utilisateur null:: impossible de modifier le mot de passe",
                    ErrorCodes.USER_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
        if (!StringUtils.hasLength(changerMotDePasseDto.getMotDePasse()) || !StringUtils.hasLength(changerMotDePasseDto.getConfirmeMotDePasse())){
            log.warn("Impossible de modifier le mot de passe avec un mot de passe null");
            throw new InvalideOperationException("Mot de passe utilisateur null:: impossible de modifier le mot de passe",
                    ErrorCodes.USER_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
        if (!changerMotDePasseDto.getMotDePasse().equals(changerMotDePasseDto.getConfirmeMotDePasse())){
            log.warn("Impossible de modifier le mot de passe avec deux mot de passe différent");
            throw new InvalideOperationException("Mot de passe utilisateur non conforme:: impossible de modifier le mot de passe",
                    ErrorCodes.USER_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
    }
}
