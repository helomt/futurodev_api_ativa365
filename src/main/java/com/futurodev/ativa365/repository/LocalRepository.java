package com.futurodev.ativa365.repository;

import com.futurodev.ativa365.model.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocalRepository extends JpaRepository<Local, Long> {

    Page<Local> findByDeletedFalseAndOwnerId(Pageable pageable, Long id);

    Optional<Local> findByIdAndDeletedFalseAndOwnerEmail(Long id, String email);

    Page<Local> findByDeletedFalse(Pageable pageable);
}
