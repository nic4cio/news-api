package tech.devinhouse.newsapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Revisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    private String nome;

    private Double salario;

    private String nivelCargo;

}