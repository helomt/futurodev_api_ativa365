package com.futurodev.ativa365.service;

import com.futurodev.ativa365.exceptions.PersonCpfAlreadyExistsException;
import com.futurodev.ativa365.exceptions.PersonEmailAlreadyExistsException;
import com.futurodev.ativa365.model.Person;
import com.futurodev.ativa365.model.transport.CreatePersonForm;
import com.futurodev.ativa365.model.transport.PersonDTO;
import com.futurodev.ativa365.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public PersonDTO createPerson(CreatePersonForm form) throws PersonEmailAlreadyExistsException, PersonCpfAlreadyExistsException {
        if(this.personRepository.existsByEmail(form.email())){
            throw new PersonEmailAlreadyExistsException(form.email());
        }
        if(this.personRepository.existsByCpf(form.cpf())){
            throw new PersonCpfAlreadyExistsException(form.cpf());
        }
        Person persistedPerson = this.personRepository.save(new Person(form));
        return new PersonDTO(persistedPerson);
    }

    public Page<PersonDTO> listPaginatedPerson(Pageable pageable){
        return this.personRepository.findAll(pageable).map(PersonDTO::new);
    }


}
