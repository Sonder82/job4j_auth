package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.model.Person;
import ru.job4j.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
@AllArgsConstructor
public class SimplePersonService implements PersonService, UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(SimplePersonService.class.getName());

    private final PersonRepository personRepository;

    private BCryptPasswordEncoder encoder;

    @Override
    public Optional<Person> save(Person person) {
        Optional<Person> result = Optional.empty();
        try {
            person.setPassword(encoder.encode(person.getPassword()));
            result = Optional.of(personRepository.save(person));
        } catch (Exception exception) {
            LOG.error("Error message: " + exception.getMessage(), exception);
        }
        return result;
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
    public boolean deleteById(int id) {
        var result = false;
        var optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            personRepository.delete(optionalPerson.get());
            result = true;
        }
        return result;
    }

    @Override
    public boolean update(Person person) {
        boolean result = false;
        var optionalPerson = personRepository.findById(person.getId());
        if (optionalPerson.isPresent()) {
            optionalPerson.get().setLogin(person.getLogin());
            optionalPerson.get().setPassword(encoder.encode(person.getPassword()));
            personRepository.save(person);
            result = true;
        }
        return result;
    }

    @Override
    public Person findByLogin(String login) {
        return personRepository.findByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Person user = personRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User(user.getLogin(), user.getPassword(), emptyList());
    }
}
