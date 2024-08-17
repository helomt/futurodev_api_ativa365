package com.futurodev.ativa365.service;

import com.futurodev.ativa365.client.ViaCepClient;
import com.futurodev.ativa365.exceptions.CepNotFoundException;
import com.futurodev.ativa365.exceptions.PersonCpfAlreadyExistsException;
import com.futurodev.ativa365.exceptions.PersonEmailAlreadyExistsException;
import com.futurodev.ativa365.exceptions.PersonNotFoundException;
import com.futurodev.ativa365.model.Person;
import com.futurodev.ativa365.model.transport.CreatePersonForm;
import com.futurodev.ativa365.model.transport.PersonDTO;
import com.futurodev.ativa365.model.transport.ViaCepDTO;
import com.futurodev.ativa365.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PersonService  implements UserDetailsService {
    private final PersonRepository personRepository;
    private  final PasswordEncoder passwordEncoder;
    private final ViaCepClient viaCepClient;

    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder, ViaCepClient viaCepClient) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
        this.viaCepClient = viaCepClient;
    }

    @Transactional
    public PersonDTO createPerson(CreatePersonForm form) throws PersonEmailAlreadyExistsException, PersonCpfAlreadyExistsException, CepNotFoundException {
        if(this.personRepository.existsByEmail(form.email())){
            throw new PersonEmailAlreadyExistsException(form.email());
        }
        if(this.personRepository.existsByCpf(form.cpf())){
            throw new PersonCpfAlreadyExistsException(form.cpf());
        }
        ViaCepDTO address = this.viaCepClient.search(form.cep()).getBody();
        if(address.cep() == null){
            throw new CepNotFoundException(form.cep());
        } else {
            //String encodedPassword = this.passwordEncoder.encode(form.password());
            Person persistedPerson = this.personRepository.save(new Person(form, this.passwordEncoder, Objects.requireNonNull(address)));
            return new PersonDTO(persistedPerson);
        }
    }

    //Puramente para consultas
    public Page<PersonDTO> listPaginatedPerson(Pageable pageable){
        return this.personRepository.findDistinctByDeletedFalse(pageable).map(PersonDTO::new);
    }

    @Transactional
    public void deletePerson (Long id) throws PersonNotFoundException {
        Person personForDelete = this.personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        personForDelete.markAsDeleted();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.personRepository.findByEmailAndDeletedFalse(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public PersonDTO getUserInSession(String email) throws PersonNotFoundException {
        return this.personRepository.findByEmailAndDeletedFalse(email).map(PersonDTO:: new)
                .orElseThrow(() -> new PersonNotFoundException(email));
    }
}
