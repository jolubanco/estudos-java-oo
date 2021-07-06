package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Funcionario;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BonusServiceTest {

    @Test
    void bonusDeveriaSerZeroParaFuncionarioComSalarioMuitoAlto(){
        BonusService service = new BonusService();
        //quanto esperamos uma exception do método que será testado
        //preciso informar qual exception estou esperando. Precisamos passar uma função lambda () ->
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularBonus(new Funcionario("João", LocalDate.now(), new BigDecimal("25000"))));

        //outra forma de lidar com a exception
//        try {
//            service.calcularBonus(new Funcionario("João", LocalDate.now(), new BigDecimal("25000")));
//            fail("Não deu a exception");
//        } catch (Exception e){
//            assertEquals("Funcionário com salário maior que R$ 10000 não pode receber bônus!",e.getMessage());
//            //verifica se a mensagem de erro está sendo enviada corretamente
//        }
    }

    @Test
    void bonusDeveriaSer10PorCentoDoSalario(){
        BonusService service = new BonusService();
        BigDecimal bonus = service.calcularBonus(new Funcionario("João", LocalDate.now(), new BigDecimal("2500")));
        assertEquals(new BigDecimal("250.00"),bonus);
    }

    @Test
    void bonusDeveriaSer10PorCentoParaSalarioDeExatamente10000(){
        BonusService service = new BonusService();
        BigDecimal bonus = service.calcularBonus(new Funcionario("João", LocalDate.now(), new BigDecimal("10000")));
        assertEquals(new BigDecimal("1000.00"),bonus);
    }


}
