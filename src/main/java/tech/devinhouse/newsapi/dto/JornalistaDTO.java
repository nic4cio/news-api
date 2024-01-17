package tech.devinhouse.newsapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tech.devinhouse.newsapi.model.Jornalista;

@Data
public class JornalistaDTO {

    private Long id;

    @NotNull(message = "O usuário não pode ser nulo")
    private UsuarioDTO usuario;

    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    private Double salario;

    private String nivelCargo;

    private Integer numeroPublicacoes;

    public static JornalistaDTO fromEntity(Jornalista jornalista) {
        JornalistaDTO dto = new JornalistaDTO();
        dto.setId(jornalista.getId());
        dto.setUsuario(UsuarioDTO.fromEntity(jornalista.getUsuario()));
        dto.setNome(jornalista.getNome());
        dto.setSalario(jornalista.getSalario());
        dto.setNivelCargo(jornalista.getNivelCargo());
        dto.setNumeroPublicacoes(jornalista.getNumeroPublicacoes());
        return dto;
    }

    public static Jornalista toEntity(JornalistaDTO dto) {
        Jornalista jornalista = new Jornalista();
        jornalista.setId(dto.getId());
        jornalista.setUsuario(UsuarioDTO.toEntity(dto.getUsuario()));
        jornalista.setNome(dto.getNome());
        jornalista.setSalario(dto.getSalario());
        jornalista.setNivelCargo(dto.getNivelCargo());
        jornalista.setNumeroPublicacoes(dto.getNumeroPublicacoes());
        return jornalista;
    }
}
