package br.com.viviane.userapi.services.impl;

import br.com.viviane.userapi.domain.User;
import br.com.viviane.userapi.repositories.UserRepository;
import br.com.viviane.userapi.services.UserService;
import br.com.viviane.userapi.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final Environment env;
    private final UserRepository repository;
    @Override
    public User findById(Long id) {
        log.info("PAYROL_SERVICE ::: Get request on " + env.getProperty("local.server.port") + " port");
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
}
