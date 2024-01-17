package tech.devinhouse.newsapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String textoNoticia;

    @ManyToOne
    @JoinColumn(name = "id_revisor")
    private Revisor revisor;

    @ManyToOne
    @JoinColumn(name = "id_jornalista")
    private Jornalista jornalista;

    private Date dataCriacao;

    private Date dataAtualizacao;
}
