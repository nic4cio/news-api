package tech.devinhouse.newsapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tech.devinhouse.newsapi.model.Noticia;

import java.util.Date;

@Data
public class NoticiaDTO {

    private Long id;

    @NotBlank(message = "O título não pode estar em branco")
    private String titulo;

    @NotBlank(message = "O texto da notícia não pode estar em branco")
    private String textoNoticia;

    private Date dataCriacao;

    private Date dataAtualizacao;


    public static NoticiaDTO fromEntity(Noticia noticia) {
        NoticiaDTO dto = new NoticiaDTO();
        dto.setId(noticia.getId());
        dto.setTitulo(noticia.getTitulo());
        dto.setTextoNoticia(noticia.getTextoNoticia());
        dto.setDataCriacao(noticia.getDataCriacao());
        dto.setDataAtualizacao(noticia.getDataAtualizacao());

        return dto;
    }

    public static Noticia toEntity(NoticiaDTO dto) {
        Noticia noticia = new Noticia();
        noticia.setId(dto.getId());
        noticia.setTitulo(dto.getTitulo());
        noticia.setTextoNoticia(dto.getTextoNoticia());
        noticia.setDataCriacao(dto.getDataCriacao());
        noticia.setDataAtualizacao(dto.getDataAtualizacao());

        return noticia;
    }
}
