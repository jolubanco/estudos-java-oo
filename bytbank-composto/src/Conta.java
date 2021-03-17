public class Conta {
    private double saldo; //faz com que o atributo não possa ser acessado diretamente, fica privado. Apenas utilizando algum método
    int agencia;
    int numero;
    Cliente titular; //Cliente é o nome da classe Cliente, para linkar a conta com o cliente. Agora recebe a posição do objeto cliente

    //o void significa que o método não retornará nada
    //sempre precisamos definir o tivo da variável que o método recebe
    public void deposita(double valor){
        this.saldo += valor;
    }
    //boolean irá ternonar um verdadeiro ou falso
    public boolean saca(double valor){
        if(this.saldo >= valor){
            this.saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    public boolean transfere(double valor, Conta destino){
        if(this.saldo >= valor){
            this.saca(valor);
            destino.deposita(valor);
            return true; //return para a execução do método
        } else {
            return false;
        }
    }

    //convenção chamar de get
    public double getSaldo() {
        return this.saldo;
    }












}
