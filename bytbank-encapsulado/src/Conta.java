public class Conta {
    private double saldo; //faz com que o atributo não possa ser acessado diretamente, fica privado. Apenas utilizando algum método
    private int agencia;
    private int numero;
    private Cliente titular; //Cliente é o nome da classe Cliente, para linkar a conta com o cliente. Agora recebe a posição do objeto cliente
    private static int total; //static faz com que o atributo seja da classe e não do objeto
    //só podemos fazer Conta.total para atributos estáticos e não para atributos dos objetos

    public Conta(int agencia, int numero) {
        Conta.total++; //toda vez que instancia uma conta, a variavel total é acrescida em 1 (podemos usar apenas total++)
        this.agencia = agencia;
        this.numero = numero;
        System.out.println("Estou criando uma conta " + this.numero);
    }


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

    public int getNumero() {
        return this.numero;
    }

    //método para alterar, normalmente não retornam nada, ou seja, definimos como void
    public void setNumero(int numero){
        if(numero <= 0){
            System.out.println("Não pode valor menor ou igual a 0");
        }
        this.numero = numero;
    }

    public int getAgencia() {
        return this.agencia;
    }

    public void setAgencia(int agencia) {
        if(agencia <= 0){
            System.out.println("Não pode valor menor ou igual a 0");
            return;
        }
        this.agencia = agencia;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public Cliente getTitular() {
        return titular;
    }

    //método estático, para consultar o total basta fazer Conta.getTotal(). Sem o static não funciona, pois métodos são para objetos não para a classe
    public static int getTotal(){
        return Conta.total;
    }
}
