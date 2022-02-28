package modelo;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Categoria(String nome){
        this.nome=nome;
    }

   public Categoria(){

   }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
