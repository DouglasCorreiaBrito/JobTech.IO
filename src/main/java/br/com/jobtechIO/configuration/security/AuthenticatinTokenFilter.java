package br.com.jobtechIO.configuration.security;

import br.com.jobtechIO.domain.entities.Candidate;
import br.com.jobtechIO.repository.CandidateRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticatinTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private CandidateRepository repository;

    public AuthenticatinTokenFilter(TokenService tokenService, CandidateRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {


        String token = recoveryToken(httpServletRequest);
        boolean isValid = tokenService.tokenIsValid(token);
        if (isValid){
            authenticateClient(token);
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void authenticateClient(String token) {
        Integer idClient = tokenService.getIdClient(token);
        Candidate candidate = repository.findById(idClient).get();
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(candidate,null,candidate.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private String recoveryToken(HttpServletRequest httpServletRequest) {
         String token = httpServletRequest.getHeader("Authorization");
         if (token == null || token.isEmpty() || !token.startsWith("Bearer ")){
             return null;
         }
         return token.substring(7,token.length());
    }
}
