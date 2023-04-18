package ru.job4j.auth.repository;

import ru.job4j.auth.model.Person;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    List<Person> findAll();

    boolean deleteById(int id);

    default Person findByUsername(String username){
        List<Person> list = findAll();
        return list.stream()
                .filter(p -> p.getLogin().equals("нужный_username"))
                .findFirst().get();
    }
}
