package tech.devinhouse.newsapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tech.devinhouse.newsapi.model.Revisor;

@Data
public class RevisorDTO {

    private Long id;

    @NotNull(message = "O ID do usuário não pode ser nulo")
    private Integer idUsuario;

    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @NotNull(message = "O salário não pode ser nulo")
    private Double salario;

    @NotBlank(message = "O nível de cargo não pode estar em branco")
    private String nivelCargo;

    public static RevisorDTO fromEntity(Revisor revisor) {
        RevisorDTO dto = new RevisorDTO();
        dto.setId(revisor.getId());
        dto.setIdUsuario(revisor.getIdUsuario());
        dto.setNome(revisor.getNome());
        dto.setSalario(revisor.getSalario());
        dto.setNivelCargo(revisor.getNivelCargo());
        return dto;
    }

    public static Revisor toEntity(RevisorDTO dto) {
        Revisor revisor = new Revisor();
        revisor.setId(dto.getId());
        revisor.setIdUsuario(dto.getIdUsuario());
        revisor.setNome(dto.getNome());
        revisor.setSalario(dto.getSalario());
        revisor.setNivelCargo(dto.getNivelCargo());
        return revisor;
    }
}
