package tech.devinhouse.newsapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.devinhouse.newsapi.model.Jornalista;
import tech.devinhouse.newsapi.repository.JornalistaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class JornalistaService {

    @Autowired
    private JornalistaRepository jornalistaRepository;

    public List<Jornalista> getAllJornalistas() {
        return jornalistaRepository.findAll();
    }

    public Optional<Jornalista> getJornalistaById(Long id) {
        return jornalistaRepository.findById(id);
    }

    public Jornalista saveJornalista(Jornalista jornalista) {
        return jornalistaRepository.save(jornalista);
    }

    public void deleteJornalista(Long id) {
        jornalistaRepository.deleteById(id);
    }
}
