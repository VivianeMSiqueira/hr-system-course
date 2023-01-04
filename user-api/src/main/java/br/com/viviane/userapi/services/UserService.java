package br.com.viviane.userapi.services;

import br.com.viviane.userapi.domain.User;

import java.util.List;

public interface UserService {

    User findById(Long id);
    List<User> findAll();
}
