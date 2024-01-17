package tech.devinhouse.newsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tech.devinhouse.newsapi.dto.UsuarioDTO;
import tech.devinhouse.newsapi.model.Usuario;
import tech.devinhouse.newsapi.service.UsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        List<UsuarioDTO> usuarioDTOs = usuarios.stream()
                .map(usuario -> UsuarioDTO.fromEntity(usuario))
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuarioDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioService.getUsuarioById(id);
        return usuarioOptional.map(usuario -> ResponseEntity.ok(UsuarioDTO.fromEntity(usuario)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioDTO.toEntity(usuarioDTO);
        Usuario createdUsuario = usuarioService.saveUsuario(usuario);
        return new ResponseEntity<>(UsuarioDTO.fromEntity(createdUsuario), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioOptional = usuarioService.getUsuarioById(id);

        if (usuarioOptional.isPresent()) {
            Usuario existingUsuario = usuarioOptional.get();
            existingUsuario.setUsername(usuarioDTO.getUsername());  // Substitua isso pelos campos relevantes
            existingUsuario.setPassword(usuarioDTO.getPassword());  // Substitua isso pelos campos relevantes

            Usuario updatedUsuario = usuarioService.saveUsuario(existingUsuario);
            return ResponseEntity.ok(UsuarioDTO.fromEntity(updatedUsuario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}


