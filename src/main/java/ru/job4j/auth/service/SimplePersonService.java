package ru.job4j.auth.service;

import ru.job4j.auth.model.Person;
import ru.job4j.auth.repository.PersonRepository;
import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@ThreadSafe
@Service
@AllArgsConstructor
public class SimplePersonService implements PersonService, UserDetailsService {

    private final PersonRepository personRepository;

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public boolean deleteById(int id) {
        return personRepository.deleteById(id);
    }

    @Override
    public boolean update(Person person) {
        return personRepository.save(person).getId() != 0;
    }

    @Override
    public Optional<Person> findById(int id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var person = personRepository.findByUsername(username);
        if (person.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return new User(person.get().getLogin(), person.get().getPassword(), emptyList());
    }

    @Override
    public Optional<Person> findByLogin(String login) {
        return personRepository.findByUsername(login);
    }
}