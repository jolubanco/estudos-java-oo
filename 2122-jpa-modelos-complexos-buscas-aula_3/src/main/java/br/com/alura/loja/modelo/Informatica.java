package br.com.alura.loja.modelo;

public class Informática extends Produto{

    private String marca;
    private Integer modelo;

    public Informática(){}

    public Informática(String marca, Integer modelo){
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }
}
