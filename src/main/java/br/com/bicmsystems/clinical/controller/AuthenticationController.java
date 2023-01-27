package br.com.bicmsystems.clinical.controller;

import br.com.bicmsystems.clinical.domain.user.dto.AuthenticationData;
import br.com.bicmsystems.clinical.domain.user.model.UserModel;
import br.com.bicmsystems.clinical.infra.security.TokenJWTData;
import br.com.bicmsystems.clinical.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authentication(@RequestBody @Valid AuthenticationData data) {
        var token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authenticate = manager.authenticate(token);
        var tokenJWT = tokenService.generateToken((UserModel) authenticate.getPrincipal());
        return ResponseEntity.ok(new TokenJWTData(tokenJWT));
    }

}
