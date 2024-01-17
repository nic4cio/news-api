package tech.devinhouse.newsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.devinhouse.newsapi.model.Revisor;

@Repository
public interface RevisorRepository extends JpaRepository<Revisor, Long> {
}
