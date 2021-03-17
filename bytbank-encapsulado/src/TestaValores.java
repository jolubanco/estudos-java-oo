public class TestaValores {
    public static void main(String[] args) {
        Conta conta = new Conta(1337,24226);

        System.out.println("o número da agência é " + conta.getAgencia());

        Conta conta2 = new Conta(1337,16549);
        Conta conta3 = new Conta(0001,10105);

        System.out.println("O total de contas é " + Conta.getTotal());
    }
}
