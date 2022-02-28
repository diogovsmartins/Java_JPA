package Teste;

import DAO.CategoriaDao;
import Util.JPAUtil;
import modelo.Categoria;

import javax.persistence.EntityManager;

public class TestingJpaObjectStatus {

    public static void main(String[] args) {
        Categoria categoria=new Categoria("eletronicos");
        //categoria ainda não foi presistido pela JPA então está no estado de Transient
        EntityManager em=JPAUtil.getEntityManager();
        CategoriaDao categoriaDao=new CategoriaDao(em);

        em.getTransaction().begin();
        categoriaDao.cadastrar(categoria);
        /*nesse momento foi chamado o metodo persist pelo metodo cadastrar, a partir daqui ela esta como managed
        a JPA cuida dele a partir daqui e olha pro estado e atributos dele e vai atualizar ele no banco de dados,
        inserir caso não esteja cadastrado lá ou se o metodo remove for chamado ele vai ir para o estado de
        removed e depois de commitado ele vai ser deletado. Então qualquer mudança aqui a JPA vai mudar os estados de acordo
        com oque foi pedido.
        */
        em.flush();
        //depois do flush/commit ele insere no banco de dados a ultima versão conhecida pela JPA do objeto
        em.clear();
        /*após utilizar o metodo clear ou close o objeto ja se encontra no estado detached, onde a JPA não mais está cuidando do estado dele
        ou seja, qualquer mudança a partir daqui a JPA não vai conhecer e não vai atualizar no banco de dados mesmo que
        seja feito outro commit por exemplo
        */

        categoria=categoriaDao.atualizar(categoria);
        /*É possivel retornar o objeto pro estado de Managed utilizando o objeto para criar uma nova referencia
        * pra ele mesmo após ser passado de parametro para o metodo .merge do EntityManager e apartir dai a JPA
        * volta a cuidar do estado do objeto e qualquer mudança que será feita nele, então se for realizado um
        * commit ou flush ele vai ser atualizado lá no banco de dados. */
        categoria.setNome("moveis");
        em.flush();
        em.remove(categoria);
        em.flush();
        em.close();

    }
}
