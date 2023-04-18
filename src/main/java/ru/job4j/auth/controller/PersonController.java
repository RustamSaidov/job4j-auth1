package ru.job4j.auth.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.job4j.auth.model.Person;
import ru.job4j.auth.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService persons;
    private final BCryptPasswordEncoder encoder;

    public PersonController( PersonService persons, BCryptPasswordEncoder encoder) {
        this.persons = persons;
        this.encoder = encoder;
    }

    @GetMapping("/all")
    public List<Person> findAll() {
        return persons.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
        var person = this.persons.findById(id);
        return new ResponseEntity<Person>(
                person.orElse(new Person()),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

//    @PostMapping("/sign-up")
//    public ResponseEntity<Person> create(@RequestBody Person person) {
//        return new ResponseEntity<Person>(this.persons.save(person),
//                HttpStatus.CREATED
//        );
//    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
        persons.save(person);
    }



    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        try {
            this.persons.save(person);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        try {
            this.persons.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
