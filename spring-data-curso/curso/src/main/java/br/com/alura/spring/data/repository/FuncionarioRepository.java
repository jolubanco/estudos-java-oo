package br.com.alura.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import br.com.alura.spring.data.orm.FuncionarioProjecao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Funcionario;

@Repository //utilizamos o PagingAndSortingRepository para fazer a paginação das nossas consultas, faz com que retornem em blocos e poupa processamento e tempo
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>, JpaSpecificationExecutor<Funcionario> {

	List<Funcionario> findByNome(String nome);
	//utilizando JPQL
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome "
			+ "AND f.salario >= :salario AND f.dataContratacao = :data")
	List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate data);

	//utilizando Native Query, SQL.
	@Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data",
			nativeQuery = true)
	List<Funcionario> findDataContratacaoMaior(LocalDate data);

	@Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
	List<FuncionarioProjecao> findFuncionarioSalario();
	//criamos a interface FuncionarioProjecao para que assim possamos receber apenas alguns atributos do objeto funcionario
	//isto serve para não precisarmos buscar o objeto inteiro de maneira desnecessario
}