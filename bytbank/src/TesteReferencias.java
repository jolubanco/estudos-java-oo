public class TesteReferencias {
    public static void main(String[] args) {
        Conta primeiraConta = new Conta();
        primeiraConta.saldo = 300;

        System.out.println("Saldo da primeira conta: " + primeiraConta.saldo);

        Conta segundaConta = primeiraConta; //criando uma nova referencia para o mesmo objeto

        System.out.println("Saldo da segunda conta: " + segundaConta.saldo);

        segundaConta.saldo += 100;
        System.out.println(segundaConta.saldo);
        System.out.println(primeiraConta.saldo);

        if(primeiraConta == segundaConta){
            System.out.println("são as mesmissima conta");
        }
    }
}
