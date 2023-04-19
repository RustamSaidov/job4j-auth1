package ru.job4j.auth.service;

import ru.job4j.auth.model.Person;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.List;
import java.util.Optional;

public interface PersonService {

    Person save(Person person);

    boolean deleteById(int id);

    boolean update(Person person);

    Optional<Person> findById(int id);

    List<Person> findAll();

    UserDetails loadUserByUsername(String username);

    Optional<Person> findByLogin(String username);
}