package tech.devinhouse.newsapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.devinhouse.newsapi.model.Revisor;
import tech.devinhouse.newsapi.repository.RevisorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RevisorService {

    @Autowired
    private RevisorRepository revisorRepository;

    public List<Revisor> getAllRevisores() {
        return revisorRepository.findAll();
    }

    public Optional<Revisor> getRevisorById(Long id) {
        return revisorRepository.findById(id);
    }

    public Revisor saveRevisor(Revisor revisor) {
        return revisorRepository.save(revisor);
    }

    public void deleteRevisor(Long id) {
        revisorRepository.deleteById(id);
    }
}
