package tech.devinhouse.newsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.devinhouse.newsapi.dto.JornalistaDTO;
import tech.devinhouse.newsapi.model.Jornalista;
import tech.devinhouse.newsapi.service.JornalistaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/jornalistas")
public class JornalistaController {

    @Autowired
    private JornalistaService jornalistaService;

    @GetMapping
    public ResponseEntity<List<JornalistaDTO>> getAllJornalistas() {
        List<Jornalista> jornalistas = jornalistaService.getAllJornalistas();
        List<JornalistaDTO> jornalistaDTOs = jornalistas.stream()
                .map(JornalistaDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(jornalistaDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JornalistaDTO> getJornalistaById(@PathVariable Long id) {
        Optional<Jornalista> jornalistaOptional = jornalistaService.getJornalistaById(id);
        return jornalistaOptional.map(jornalista -> ResponseEntity.ok(JornalistaDTO.fromEntity(jornalista)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<JornalistaDTO> createJornalista(@RequestBody JornalistaDTO jornalistaDTO) {
        Jornalista jornalista = JornalistaDTO.toEntity(jornalistaDTO);
        Jornalista createdJornalista = jornalistaService.saveJornalista(jornalista);
        return new ResponseEntity<>(JornalistaDTO.fromEntity(createdJornalista), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JornalistaDTO> updateJornalista(@PathVariable Long id, @RequestBody JornalistaDTO jornalistaDTO) {
        Optional<Jornalista> jornalistaOptional = jornalistaService.getJornalistaById(id);

        if (jornalistaOptional.isPresent()) {
            Jornalista existingJornalista = jornalistaOptional.get();
            existingJornalista.setNome(jornalistaDTO.getNome());
            existingJornalista.setSalario(jornalistaDTO.getSalario());

            Jornalista updatedJornalista = jornalistaService.saveJornalista(existingJornalista);
            return ResponseEntity.ok(JornalistaDTO.fromEntity(updatedJornalista));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJornalista(@PathVariable Long id) {
        jornalistaService.deleteJornalista(id);
        return ResponseEntity.noContent().build();
    }
}
