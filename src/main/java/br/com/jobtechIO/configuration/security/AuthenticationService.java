package br.com.jobtechIO.configuration.security;

import br.com.jobtechIO.domain.entities.Candidate;
import br.com.jobtechIO.exceptions.GenericBadRequestException;
import br.com.jobtechIO.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private CandidateRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Candidate> candidate = repository.findByEmail(username);

        if (candidate.isPresent()) {
           return candidate.get();
        } else {
            return candidate.orElseThrow(() -> new UsernameNotFoundException("Invalid access"));
        }
    }
}
