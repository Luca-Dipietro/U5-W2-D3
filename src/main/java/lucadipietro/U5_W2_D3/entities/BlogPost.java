package lucadipietro.U5_W2_D3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "blog_posts")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BlogPost {
    @Id
    @GeneratedValue
    private UUID id;
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;

    public BlogPost(String categoria, String titolo, String contenuto) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
    }
}
