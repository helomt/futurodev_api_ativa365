package com.futurodev.ativa365.repository;

import com.futurodev.ativa365.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
