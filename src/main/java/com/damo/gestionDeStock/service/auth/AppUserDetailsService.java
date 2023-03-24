package com.damo.gestionDeStock.service.auth;


import com.damo.gestionDeStock.dto.UtilisateurDto;
import com.damo.gestionDeStock.model.auth.ExtendedUser;
import com.damo.gestionDeStock.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurService service;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        UtilisateurDto utilisateur = service.findByMail(mail);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        utilisateur.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));

        return new ExtendedUser(utilisateur.getMail(),utilisateur.getMotDePasse(), utilisateur.getEntreprise().getId(), authorities);
    }
}
