package br.com.alura.loja.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity //mapeando as classes com o banco de dados
@Table(name = "produtos") // ensinando pra JPA que o nome da tabela no banco de dados é 'produtos' e não 'Produto'. Caso o nome seja o mesmo não precisa
public class Produto {

    //os nomes das colunas no banco são os mesmos dos atributos
    @Id //faz com que o id, logo abixo, seja a chave primaria da minha tabela no banco de dados
    @GeneratedValue(strategy = GenerationType.IDENTITY) // faz que não precisemos passar a chave primaria, o banco que vai gerar automaticamente
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private LocalDate dataCadastro = LocalDate.now(); //pega a data atual
//    @Enumerated(EnumType.STRING) // configurando para cadastrar como uma string, e não como a posição no enum
    @ManyToOne // para dizer para a JPA qual a cardinalidade do relacionamento das duas tabelas
    private Categoria categoria;

    //a JPA exige que as entitidades tenham tambem um construtor padrão
    public Produto(){
    }

    public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
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
