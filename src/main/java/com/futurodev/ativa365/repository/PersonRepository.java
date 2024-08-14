package com.futurodev.ativa365.repository;

import com.futurodev.ativa365.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    Page<Person> findDistinctByDeletedFalse(Pageable pageable);
}
