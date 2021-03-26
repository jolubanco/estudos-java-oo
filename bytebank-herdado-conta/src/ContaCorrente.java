//herança herda atributos e métodos, mas NÃO herda os construtores
public class ContaCorrente extends Conta implements Tributavel {

    public ContaCorrente(int agencia, int numero) {
        super(agencia, numero);
        //super() chamando o construtor padrão da classe mãe, super(agencia, numero) chama o construtor definido em conta com os mesmos parametros
    }

    @Override
    public void deposita(double valor) {
        super.saldo += valor;
    }

    //@Overrride chama-se "annotation" e com isso o compilador sabe que eu quero sobescrever o método
    //Funciona sem tambem, mas se por acaso eu mudar o nome do método na super classe, esse método não sera mais um sobescrita
    //e sim um método novo, com o @Override isso não ocorre
    @Override
    public boolean saca(double valor) {
        double valorASacar = valor + 0.2;
        return super.saca(valorASacar);
    }

    @Override
    public double getValorImposto() {
        return super.saldo * 0.01;
    }
}
