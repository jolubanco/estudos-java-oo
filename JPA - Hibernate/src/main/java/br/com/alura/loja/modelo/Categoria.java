package br.com.alura.loja.modelo;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

	@EmbeddedId
	private CategoriaId id; //definindo chave primaria composta
	
	public Categoria() {
	}
	
	public Categoria(String nome) {
		this.id = new CategoriaId(nome,"XPTO");
	}

	public String getNome() {
		return this.id.getNome();
	}

}
