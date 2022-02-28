package Teste;

import DAO.CategoriaDao;
import DAO.ProdutoDao;
import Util.JPAUtil;
import modelo.Categoria;
import modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class SelectComJPQL {
    public static void main(String[] args) {
        Categoria categoria=new Categoria("perifericos");
        Produto produto=new Produto("teclado", "teclado mecanico",
                new BigDecimal("30"),categoria);
        EntityManager em=JPAUtil.getEntityManager();
        CategoriaDao categoriaDao=new CategoriaDao(em);
        ProdutoDao produtoDao=new ProdutoDao(em);

        em.getTransaction().begin();
        categoriaDao.cadastrar(categoria);
        produtoDao.cadastrar(produto);
        em.flush();

        //busca por id do produto
        System.out.println(produtoDao.buscarPorId(1).getPreco());
        //busca todos os produtos
        List<Produto>produtos=produtoDao.buscarTodos();
        produtos.stream().forEach(p-> System.out.println("nome: "+produto.getNome()+
                " Descricao: "+produto.getDescricao()));

        //busca produtos por um parametro, nesse caso pelo nome
        List<Produto> buscaComParametro=produtoDao.buscarProdutoComParametro("teclado");
        buscaComParametro.forEach(produto1-> System.out.println(produto1.getNome()));
        //busca produto pelo nome da categoria
        List<Produto> buscaCategoria=produtoDao.buscarProdutoPorNomeCategoria("perifericos");
        buscaCategoria.forEach(produto1-> System.out.println(produto1.getPreco()));
        //busca um atributo de um produto que tenha o nome especificado
        System.out.println(produtoDao.buscarAtributoDeProdutoPorNome("teclado"));
        em.flush();
        em.close();

    }
}
