package modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity //anotação que define que essa classe esta mapeando uma entidade do banco de dados
@Table(name = "produtos")
public class Produto {

    @Id //define a chave primária da entidade que está sendo mapeada
    @GeneratedValue(strategy = GenerationType.IDENTITY) //define quem vai gerar o identificador, BD ou aplicação
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private LocalDate dataDeCadastro= LocalDate.now();
    @ManyToOne
    private Categoria categoria;

    /*Lembretes: Uma entidade precisa ter o mesmo nome que a tabela que está mapeando, do contrário é preciso
    utilizar a anotação @Table(name ="nomeDaTabela") pra identificar que essa entidade é o espelho da que existe no BD
    o mesmo vale para os atributos, eles precisam ser do mesmo tipo ou um tipo que caiba dentro do que está especificado
    e os nomes também precisam ser iguais do contrário também tem que utilizar uma anotação chamada
    @Column(name="nome da coluna").
    * */

    public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria){
        this.nome=nome;
        this.descricao=descricao;
        this.preco=preco;
        this.categoria=categoria;
    }

    public Produto(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
