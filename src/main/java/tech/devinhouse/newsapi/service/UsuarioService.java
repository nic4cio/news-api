package tech.devinhouse.newsapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.devinhouse.newsapi.model.Usuario;
import tech.devinhouse.newsapi.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        LOGGER.debug("Getting all usuarios");
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        LOGGER.debug("Getting usuario by id: {}", id);
        return usuarioRepository.findById(id);
    }

    public Usuario saveUsuario(Usuario usuario) {
        LOGGER.info("Saving usuario: {}", usuario);
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        LOGGER.warn("Deleting usuario with id: {}", id);
        usuarioRepository.deleteById(id);
    }
}
