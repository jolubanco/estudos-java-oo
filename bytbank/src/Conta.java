public class Conta {
    double saldo;
    int agencia;
    int numero;
    String titular;

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












}
