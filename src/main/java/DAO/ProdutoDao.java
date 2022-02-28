package DAO;

import modelo.Categoria;
import modelo.Produto;
import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDao {
    private EntityManager em;

    public ProdutoDao (EntityManager em){
        this.em=em;
    }

    public void cadastrar(Produto produto){
        this.em.persist(produto);
    }

    public Produto atualizar(Produto produto){
        return this.em.merge(produto);
    }

    public void remover(Produto produto){
        produto=em.merge(produto);
        this.em.remove(produto);
    }

    public Produto buscarPorId(long id){
        return em.find(Produto.class, id);
    }

    public List<Produto> buscarTodos(){
        String jpql="SELECT p FROM Produto p";
      return   em.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> buscarProdutoComParametro(String nome){
        String jpql="SELECT p FROM Produto p WHERE p.nome=:nome";
        return   em.createQuery(jpql, Produto.class).setParameter("nome",nome)
                .getResultList();
    }

    public List<Produto> buscarProdutoPorNomeCategoria(String nome){
        String jpql="SELECT p FROM Produto p WHERE p.categoria.nome=:nome";
        return   em.createQuery(jpql, Produto.class).setParameter("nome",nome)
                .getResultList();
    }

    public String buscarAtributoDeProdutoPorNome(String nome){
        String jpql="SELECT p.nome FROM Produto p WHERE p.nome=:nome";
        return   em.createQuery(jpql, String.class).setParameter("nome",nome)
                .getSingleResult();
    }
}
