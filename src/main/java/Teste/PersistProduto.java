package Teste;

import DAO.CategoriaDao;
import DAO.ProdutoDao;
import Util.JPAUtil;
import modelo.Categoria;
import modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PersistProduto {
    public static void main(String[] args) {
        Categoria categoria=new Categoria("Garrafas");
        Produto produto=new Produto("Garrafa de água", "Garrafa de água CI&T vermelha", new BigDecimal("60"), categoria);

        EntityManager em=JPAUtil.getEntityManager();
        ProdutoDao produtoDao=new ProdutoDao(em);
        CategoriaDao categoriaDao=new CategoriaDao(em);
        em.getTransaction().begin();
        categoriaDao.cadastrar(categoria);
        produtoDao.cadastrar(produto);
        em.getTransaction().commit();
        em.close();
    }
}
