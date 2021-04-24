package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDAO {

    private EntityManager em;

    public ProdutoDAO(EntityManager em){
        this.em = em;
    }

    public void cadastrar(Produto produto){
        this.em.persist(produto);
    }

    public Produto buscarPorId(Long id){
       return em.find(Produto.class,id); //busca uma UNICA entidade pelo id
    }

    //JPQL
    public List<Produto> buscarTodos(){
        String jpql = "SELECT p FROM Produto p"; //PARECE SQL MAS NÃO É, EM VEZ DO NOME DA TABELA, PASSO O NOME DA ENTIDADE
        return em.createQuery(jpql, Produto.class).getResultList(); // o createQuery(jpql) só monta a query, para disparar no banco de dados precisa do getResultList
    }

    public List<Produto> buscarPorNome(String nome){
        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nomeParametro";
        return em.createQuery(jpql, Produto.class).setParameter("nomeParametro",nome).getResultList();
    }

    public List<Produto> buscarPorNomeDaCategoria(String nome){
        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nomeParametro"; //faz o JOIN automatiicamento, do SQL
        return em.createQuery(jpql, Produto.class).setParameter("nomeParametro",nome).getResultList();
    }
    //retornando apenas um atributo da entidade
    public BigDecimal buscarPrecoDoProdutoComNome(String nome){
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nomeParametro"; //faz o JOIN automatiicamento, do SQL
        return em.createQuery(jpql, BigDecimal.class).setParameter("nomeParametro",nome).getSingleResult();
        //getSingleResult devolve apenas um unico resultado na consulta, e nao uma lista
    }
}
