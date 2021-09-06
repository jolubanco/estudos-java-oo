package br.com.alura.loja.imposto;

import br.com.alura.loja.orcamento.Orcamento;

import java.math.BigDecimal;

public class CalculadoraDeImpostos {

    //pattern Strategy: Sempre que temos um método comum para varias classes, podemos substituir os
    //if's criando uma interface e utilizando o polimorfismo. Cada classe tem a sua implementacao específica
    //O padrão Strategy visa resolver a existência de diversos algoritmos para uma ação, resultando na possibilidade de vários ifs.
    //Este padrão pode ser utilizado quando há diversos possíveis algoritmos para uma ação (como calcular imposto, por exemplo).
    // Nele, nós separamos cada um dos possíveis algoritmos em classes separadas.
    public BigDecimal calcular(Orcamento orcamento, Imposto imposto){
        return imposto.calcular(orcamento);
    }
}
