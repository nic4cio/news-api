package tech.devinhouse.newsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.devinhouse.newsapi.dto.RevisorDTO;
import tech.devinhouse.newsapi.model.Revisor;
import tech.devinhouse.newsapi.service.RevisorService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/revisores")
public class RevisorController {

    @Autowired
    private RevisorService revisorService;

    @GetMapping
    public ResponseEntity<List<RevisorDTO>> getAllRevisores() {
        List<Revisor> revisores = revisorService.getAllRevisores();
        List<RevisorDTO> revisorDTOs = revisores.stream()
                .map(revisor -> RevisorDTO.fromEntity(revisor))
                .collect(Collectors.toList());
        return ResponseEntity.ok(revisorDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RevisorDTO> getRevisorById(@PathVariable Long id) {
        Optional<Revisor> revisorOptional = revisorService.getRevisorById(id);
        return revisorOptional.map(revisor -> ResponseEntity.ok(RevisorDTO.fromEntity(revisor)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RevisorDTO> createRevisor(@RequestBody RevisorDTO revisorDTO) {
        Revisor revisor = RevisorDTO.toEntity(revisorDTO);
        Revisor createdRevisor = revisorService.saveRevisor(revisor);
        return new ResponseEntity<>(RevisorDTO.fromEntity(createdRevisor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RevisorDTO> updateRevisor(@PathVariable Long id, @RequestBody RevisorDTO revisorDTO) {
        Optional<Revisor> revisorOptional = revisorService.getRevisorById(id);

        if (revisorOptional.isPresent()) {
            Revisor existingRevisor = revisorOptional.get();
            existingRevisor.setIdUsuario(revisorDTO.getIdUsuario());
            existingRevisor.setNome(revisorDTO.getNome());
            existingRevisor.setSalario(revisorDTO.getSalario());
            existingRevisor.setNivelCargo(revisorDTO.getNivelCargo());

            Revisor updatedRevisor = revisorService.saveRevisor(existingRevisor);
            return ResponseEntity.ok(RevisorDTO.fromEntity(updatedRevisor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRevisor(@PathVariable Long id) {
        revisorService.deleteRevisor(id);
        return ResponseEntity.noContent().build();
    }
}
