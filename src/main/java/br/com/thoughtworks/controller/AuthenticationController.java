package br.com.thoughtworks.controller;

import br.com.thoughtworks.config.security.TokenService;
import br.com.thoughtworks.controller.DTO.TokenDTO;
import br.com.thoughtworks.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> authenticate(@RequestBody @Valid LoginForm loginForm) {
        UsernamePasswordAuthenticationToken token = loginForm.parseUsernamePasswordAuthenticationToken();
        try {
            Authentication auth = authenticationManager.authenticate(token);
            String generatedToken = tokenService.generateToken(auth);
            return ResponseEntity.ok(new TokenDTO(generatedToken, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
