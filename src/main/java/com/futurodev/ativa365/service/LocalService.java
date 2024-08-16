package com.futurodev.ativa365.service;

import com.futurodev.ativa365.exceptions.PersonNotFoundException;
import com.futurodev.ativa365.model.Local;
import com.futurodev.ativa365.model.Person;
import com.futurodev.ativa365.model.transport.CreateLocalForm;
import com.futurodev.ativa365.model.transport.LocalDTO;
import com.futurodev.ativa365.repository.LocalRepository;
import com.futurodev.ativa365.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LocalService {

    private final LocalRepository localRepository;
    private final PersonRepository personRepository;


    public LocalService(LocalRepository localRepository, PersonRepository personRepository) {
        this.localRepository = localRepository;
        this.personRepository = personRepository;
    }

    @Transactional
    public LocalDTO createLocal(CreateLocalForm form, UserDetails userInSession) throws PersonNotFoundException {
        Person owner = this.personRepository.findByEmailAndDeletedFalse(userInSession.getUsername())
                .orElseThrow(() -> new PersonNotFoundException(userInSession.getUsername()));
        Local persistedLocal = this.localRepository.save(new Local(form, owner));
        return new LocalDTO(persistedLocal);
    }
}
