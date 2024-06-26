package lucadipietro.U5_W2_D3.entities;

import jakarta.persistence.*;
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
    @ManyToOne
    @JoinColumn(name = "autore_id")
    private Autore autore;

    public BlogPost(String categoria, String titolo, String contenuto) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
    }
}
