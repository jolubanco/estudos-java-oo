public class CriaConta {
    public static void main(String[] args) {
        Conta primeiraConta = new Conta(); //variável primeiraConta é do tipo Conta
        primeiraConta.saldo = 200;
        System.out.println("Primeira conta tem R$ " + primeiraConta.saldo);


        primeiraConta.saldo += 100;
        System.out.println(primeiraConta.saldo);

        Conta segundaConta = new Conta();
        segundaConta.saldo = 50;
        System.out.println("Segunda conta tem R$ " + segundaConta.saldo);

        System.out.println(primeiraConta.agencia);
        System.out.println(primeiraConta.numero);

        if(primeiraConta == segundaConta){
            System.out.println("mesmissima conta");
        } else {
            System.out.println("contas diferentes");
        }
    }
}
