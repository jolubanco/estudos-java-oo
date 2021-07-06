package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Desempenho;
import br.com.alura.tdd.modelo.Funcionario;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReajusteServiceTest {

    private ReajusteService service;
    private Funcionario funcionario;

    //instacioamos as classes como atributos e criarmos esse metodo para inicializar apenas uma vez, para nao repetir codigo
    @BeforeEach //como precisariamos chamar esse método em cada um dos testes, podemos utilizar o recurso de indicar para o JUtil que ele deve iniciar esse método antes de rodar os testes
    public void inicializar(){
        System.out.println("Inicializar");
        this.service = new ReajusteService();
        this.funcionario = new Funcionario("Ana", LocalDate.now(),new BigDecimal("1000.00"));
    }

    @AfterEach
    public void finalizar(){
        System.out.println("Fim");
    }

    @BeforeAll //os métodos ALL precisam ser estáticos
    public static void antesDeTodos(){
        System.out.println("Antes de todos");
    }
    @AfterAll
    public static void depoisDeTodos(){
        System.out.println("Depois de todos");
    }

    @Test
    public void reajusteDeveriaSerDeTresPorCentoQuandoODesempenhoForADesejar(){
        service.concederReajuste(funcionario, Desempenho.A_DESEJAR);
        assertEquals(new BigDecimal("1030.00"),funcionario.getSalario());
    }

    @Test
    public void reajusteDeveriaSerDeQuinzePorCentoQuandoODesempenhoForADesejar(){
        service.concederReajuste(funcionario, Desempenho.BOM);
        assertEquals(new BigDecimal("1150.00"),funcionario.getSalario());
    }

    @Test
    public void reajusteDeveriaSerDeVintePorCentoQuandoODesempenhoForADesejar(){
        service.concederReajuste(funcionario, Desempenho.OTIMO);
        assertEquals(new BigDecimal("1200.00"),funcionario.getSalario());
    }
}
