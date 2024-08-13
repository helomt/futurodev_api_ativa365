package com.futurodev.ativa365.service;

import com.futurodev.ativa365.model.Person;
import com.futurodev.ativa365.model.transport.CreatePersonForm;
import com.futurodev.ativa365.model.transport.PersonDTO;
import com.futurodev.ativa365.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonDTO createPerson(CreatePersonForm form){
        Person persistedPerson = this.personRepository.save(new Person(form));
        return new PersonDTO(persistedPerson);
    }


}
