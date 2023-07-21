package com.damo.gestionDeStock.controller.api;

import com.damo.gestionDeStock.dto.auth.AuthenticationRequest;
import com.damo.gestionDeStock.dto.auth.AuthenticationResponse;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.damo.gestionDeStock.utils.Constants.AUTHENTICATION_ENDPOINT;

@Api("authentication")
public interface AuthenticationApi {

    @PostMapping(AUTHENTICATION_ENDPOINT + "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);

}


