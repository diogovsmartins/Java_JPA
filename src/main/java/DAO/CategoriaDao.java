package DAO;
import modelo.Categoria;
import modelo.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaDao {
    private EntityManager em;

    public CategoriaDao (EntityManager em){
        this.em=em;
    }

    public void cadastrar(Categoria categoria){
        this.em.persist(categoria);
    }

    public Categoria atualizar(Categoria categoria){
       return this.em.merge(categoria);
    }

    public void remover(Categoria categoria){
        categoria=em.merge(categoria);
        this.em.remove(categoria);
    }

    public Categoria buscarPorId(long id){
        return em.find(Categoria.class, id);
    }

    public List<Categoria> buscarTodos(){
        String jpql="SELECT p FROM Categoria p";
        return   em.createQuery(jpql, Categoria.class).getResultList();
    }
}
