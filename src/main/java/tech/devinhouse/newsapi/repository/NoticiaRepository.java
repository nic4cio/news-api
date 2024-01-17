package tech.devinhouse.newsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.devinhouse.newsapi.model.Noticia;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
}
