package tech.devinhouse.newsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.devinhouse.newsapi.dto.NoticiaDTO;
import tech.devinhouse.newsapi.model.Noticia;
import tech.devinhouse.newsapi.service.NoticiaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/noticias")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    @GetMapping
    public ResponseEntity<List<NoticiaDTO>> getAllNoticias() {
        List<Noticia> noticias = noticiaService.getAllNoticias();
        List<NoticiaDTO> noticiaDTOs = noticias.stream()
                .map(noticia -> NoticiaDTO.fromEntity(noticia))
                .collect(Collectors.toList());
        return ResponseEntity.ok(noticiaDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticiaDTO> getNoticiaById(@PathVariable Long id) {
        Optional<Noticia> noticiaOptional = noticiaService.getNoticiaById(id);
        return noticiaOptional.map(noticia -> ResponseEntity.ok(NoticiaDTO.fromEntity(noticia)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<NoticiaDTO> createNoticia(@RequestBody NoticiaDTO noticiaDTO) {
        Noticia noticia = NoticiaDTO.toEntity(noticiaDTO);
        Noticia createdNoticia = noticiaService.saveNoticia(noticia);
        return new ResponseEntity<>(NoticiaDTO.fromEntity(createdNoticia), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoticiaDTO> updateNoticia(@PathVariable Long id, @RequestBody NoticiaDTO noticiaDTO) {
        Optional<Noticia> noticiaOptional = noticiaService.getNoticiaById(id);

        if (noticiaOptional.isPresent()) {
            Noticia existingNoticia = noticiaOptional.get();
            existingNoticia.setDataCriacao(noticiaDTO.getDataCriacao());
            existingNoticia.setDataAtualizacao(noticiaDTO.getDataAtualizacao());
            existingNoticia.setTitulo(noticiaDTO.getTitulo());
            existingNoticia.setTextoNoticia(noticiaDTO.getTextoNoticia());

            Noticia updatedNoticia = noticiaService.saveNoticia(existingNoticia);
            return ResponseEntity.ok(NoticiaDTO.fromEntity(updatedNoticia));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoticia(@PathVariable Long id) {
        noticiaService.deleteNoticia(id);
        return ResponseEntity.noContent().build();
    }
}
