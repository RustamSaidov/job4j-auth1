package ru.job4j.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.job4j.auth.model.Person;
import ru.job4j.auth.model.PersonCredentials;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    Person save(Person person);

    boolean deleteById(int id);

    boolean update(PersonCredentials personDTO);

    Optional<Person> findById(int id);

    List<Person> findAll();

    UserDetails loadUserByUsername(String username);

    Optional<Person> findByLogin(String login);
}