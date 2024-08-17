package com.futurodev.ativa365.controller;

import com.futurodev.ativa365.exceptions.LocalNotFoundException;
import com.futurodev.ativa365.exceptions.PersonNotFoundException;
import com.futurodev.ativa365.model.transport.CreateLocalForm;
import com.futurodev.ativa365.model.transport.LocalDTO;
import com.futurodev.ativa365.model.transport.UpdateLocalForm;
import com.futurodev.ativa365.service.LocalService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/local")
public class LocalController {

    private final LocalService localService;


    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    @PostMapping
    public ResponseEntity<LocalDTO> createLocal(@RequestBody @Valid CreateLocalForm form,
                                                UriComponentsBuilder uriComponentsBuilder,
                                                @AuthenticationPrincipal UserDetails userInSession) throws PersonNotFoundException {
        LocalDTO response =  this.localService.createLocal(form, userInSession);

        URI uri = uriComponentsBuilder.path("/local/{id}").buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<LocalDTO>> listPaginatedLocals(@PageableDefault(size = 10, sort = {"name"})Pageable pageable,
                                                              @AuthenticationPrincipal UserDetails userInSession) throws PersonNotFoundException {
       Page<LocalDTO> response = this.localService.listPaginatedLocals(pageable, userInSession);
       return response.hasContent() ? ResponseEntity.ok(response): ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalDTO> getSingleLocal(@PathVariable("id") Long id,
                                                   @AuthenticationPrincipal UserDetails userInSession) throws LocalNotFoundException {
        LocalDTO response = this.localService.getSingleLocal(id, userInSession);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LocalDTO> deleteLocal(@PathVariable("id") Long id,
                                                @AuthenticationPrincipal UserDetails userInSession) throws LocalNotFoundException {
        this.localService.deleteLocal(id, userInSession);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalDTO> updateLocal(@PathVariable("id") Long id,
                                                @RequestBody @Valid UpdateLocalForm form,
                                                @AuthenticationPrincipal UserDetails userInSession) throws LocalNotFoundException{
        LocalDTO response = this.localService.updateLocal(id, form, userInSession);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/all")
    public ResponseEntity<Page<LocalDTO>> listPaginatedLocalsNoOwner(@PageableDefault(size = 10, sort = {"name"})Pageable pageable) {
        Page<LocalDTO> response = this.localService.listPaginatedLocalsNoOwner(pageable);
        return response.hasContent() ? ResponseEntity.ok(response): ResponseEntity.noContent().build();
    }


}
