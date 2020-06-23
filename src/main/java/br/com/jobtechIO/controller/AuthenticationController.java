package br.com.jobtechIO.controller;

import br.com.jobtechIO.configuration.security.TokenService;
import br.com.jobtechIO.domain.dto.request.LoginForm;

import br.com.jobtechIO.domain.dto.response.TokenDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Api(tags = { " Authentication " }, value = "end-point to obtain authentication token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authManeger;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> authentication(@RequestBody @Valid LoginForm form){

        UsernamePasswordAuthenticationToken dataLogin =  form.convert();
        try {
            Authentication authenticate = authManeger.authenticate(dataLogin);
            String token = tokenService.generateToken(authenticate);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        }catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }

    }
}
