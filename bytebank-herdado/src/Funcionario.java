//quando eu digo que a classe é asbstract signigica que eu nao quero nenhum objeto que seja do tipo dela
//por exemplo não faz sentido dizer que uma pessoa é apenas um funcionario, ela tem um cargo como gerente, desing
//então não faria sentido criar um objeto funcionário.
// classes abstratas não podem ser instaciados como objetos

public abstract class Funcionario {

    private String nome;
    private String cpf;
    private double salario; //usando private e acessando os valores atraves do método get em outras classes é mais comum.
    //protected double salario; //protected significa público para as classes filhas, está entre private e public

    public abstract double getBonificacao(); //significa que esse método não tem corpo, sem implementação. Implem. nos filhos
    //força as filhas a implementarem o metodo getBonificacao()

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
