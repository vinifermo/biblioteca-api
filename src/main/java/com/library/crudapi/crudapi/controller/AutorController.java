package com.library.crudapi.crudapi.controller;

import com.library.crudapi.crudapi.dto.request.AutorRequestDTO;
import com.library.crudapi.crudapi.dto.response.AutorResponseDTO;
import com.library.crudapi.crudapi.entity.Autor;
import com.library.crudapi.crudapi.service.AutorService;
import com.library.crudapi.crudapi.util.FilterPageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/autor")
public class AutorController {

    private final AutorService autorService;

    @GetMapping
    public Page<Autor> findByPage(@RequestParam(value = "filter", defaultValue = "") String filter, FilterPageable filterPageable) {
        return autorService.findByPage(filter.toUpperCase(), filterPageable.listByPage());
    }

    @GetMapping("/{id}")
    public AutorResponseDTO findById(@PathVariable UUID id) {
        Autor autor = autorService.findById(id);
        AutorResponseDTO autorResponseDTO = new AutorResponseDTO(autor);

        return autorResponseDTO;
    }

        @PostMapping
        public ResponseEntity<AutorResponseDTO> create(@Valid @RequestBody AutorRequestDTO autorRequestDTO) {
            Autor autorSalvo = autorService.create(autorRequestDTO);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(autorSalvo.getId())
                    .toUri();
            log.info("Criado novo autor com id: {}", autorSalvo.getId());

            return ResponseEntity.created(location).build();
        }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable UUID id, @Valid @RequestBody AutorRequestDTO autorRequestDTO) {
        autorService.update(id, autorRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        autorService.delete(id);
    }
}