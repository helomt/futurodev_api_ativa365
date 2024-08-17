package com.futurodev.ativa365.service;

import com.futurodev.ativa365.exceptions.LocalNotFoundException;
import com.futurodev.ativa365.exceptions.PersonNotFoundException;
import com.futurodev.ativa365.model.Local;
import com.futurodev.ativa365.model.Person;
import com.futurodev.ativa365.model.transport.CreateLocalForm;
import com.futurodev.ativa365.model.transport.LocalDTO;
import com.futurodev.ativa365.model.transport.UpdateLocalForm;
import com.futurodev.ativa365.repository.LocalRepository;
import com.futurodev.ativa365.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LocalService {

    private final LocalRepository localRepository;
    private final PersonRepository personRepository;


    public LocalService(LocalRepository localRepository, PersonRepository personRepository) {
        this.localRepository = localRepository;
        this.personRepository = personRepository;
    }

    public LocalDTO getSingleLocal(Long id, UserDetails userInSession) throws LocalNotFoundException {
        return this.localRepository.findByIdAndDeletedFalseAndOwnerEmail(id, userInSession.getUsername())
                .map(LocalDTO::new)
                .orElseThrow(() -> new LocalNotFoundException(id));
    }

    public Page<LocalDTO> listPaginatedLocals(Pageable pageable, UserDetails userInSession) throws PersonNotFoundException {
        Person userInSessionEntity = this.personRepository.findByEmailAndDeletedFalse(userInSession.getUsername())
                .orElseThrow(() -> new PersonNotFoundException(userInSession.getUsername()));
        Long id = userInSessionEntity.getId();
        return this.localRepository.findByDeletedFalseAndOwnerId(pageable, id).map(LocalDTO::new);
    }

    @Transactional
    public LocalDTO createLocal(CreateLocalForm form, UserDetails userInSession) throws PersonNotFoundException {
        Person owner = this.personRepository.findByEmailAndDeletedFalse(userInSession.getUsername())
                .orElseThrow(() -> new PersonNotFoundException(userInSession.getUsername()));
        Local persistedLocal = this.localRepository.save(new Local(form, owner));
        return new LocalDTO(persistedLocal);
    }

    @Transactional
    public void deleteLocal(Long id, UserDetails userInSession) throws LocalNotFoundException {
        Local localForDelete = this.localRepository.findByIdAndDeletedFalseAndOwnerEmail(id, userInSession.getUsername())
                .orElseThrow(() -> new LocalNotFoundException(id));

        localForDelete.markAsDeleted();

    }

    @Transactional
    public LocalDTO updateLocal(Long id, UpdateLocalForm form, UserDetails userInSession) throws LocalNotFoundException {
        Local localForUpdate = this.localRepository.findByIdAndDeletedFalseAndOwnerEmail(id, userInSession.getUsername())
                .orElseThrow(() -> new LocalNotFoundException(id));

        localForUpdate.updateAvailableAttributes(form);
        return new LocalDTO(localForUpdate);
    }

    public Page<LocalDTO> listPaginatedLocalsNoOwner(Pageable pageable) {
        return this.localRepository.findByDeletedFalse(pageable).map(LocalDTO::new);
    }
}
