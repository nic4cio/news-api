package tech.devinhouse.newsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.devinhouse.newsapi.model.Jornalista;

@Repository
public interface JornalistaRepository extends JpaRepository<Jornalista, Long> {
}
