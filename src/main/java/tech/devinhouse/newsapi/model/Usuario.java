package tech.devinhouse.newsapi.model;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;
}
