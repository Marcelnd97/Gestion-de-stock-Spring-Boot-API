package com.damo.gestionDeStock.controller;

import com.damo.gestionDeStock.dto.auth.AuthenticationRequest;
import com.damo.gestionDeStock.dto.auth.AuthenticationResponse;
//import com.damo.gestionDeStock.model.auth.ExtendedUser;
import com.damo.gestionDeStock.model.auth.ExtendedUser;
import com.damo.gestionDeStock.service.auth.AppUserDetailsService;
//import com.damo.gestionDeStock.utils.JwtUtil;
import com.damo.gestionDeStock.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.damo.gestionDeStock.utils.Constants.AUTHENTICATION_ENDPOINT;

@RestController
@RequestMapping(AUTHENTICATION_ENDPOINT)
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        final UserDetails userDetails = appUserDetailsService.loadUserByUsername(request.getLogin());
        final String jwt = jwtUtil.generateToken((ExtendedUser) userDetails);
        return  ResponseEntity.ok(AuthenticationResponse.builder().accessToken("dummy accessToken").build());
    }

}
