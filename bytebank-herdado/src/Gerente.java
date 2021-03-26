//gerente é um Funcionário. Gerente herda da classe Funcionário, assina o contrato autenticavel, é um autenticavel
//posso assinar varios contratos, ao contrario da herenca que nao pode se multipla
public class Gerente extends Funcionario implements Autenticavel{

    private AutenticacaoUtil autenticador;

    public Gerente(){
        this.autenticador = new AutenticacaoUtil();
    }

    public double getBonificacao(){
        System.out.println("Chamando o método de bonificação do GERENTE");
        return super.getSalario();
    }

    @Override
    public void setSenha(int senha) {
        this.autenticador.setSenha(senha);
    }

    @Override
    public boolean autentica(int senha) {
        return this.autenticador.autentica(senha);
    }


    ////usando super. inves do this, por boas praticas, para indicar que o atributo está na classe mãe e não 'nesta' classe
    //super.getBonificacao() acessa o método da classe mãe. Reaproveitando a implementação padrão e caso mude la, muda aqui automaticamente

}
