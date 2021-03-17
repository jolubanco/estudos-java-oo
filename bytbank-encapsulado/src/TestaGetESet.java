public class TestaGetESet {
    public static void main(String[] args) {
        Conta conta = new Conta(1337,24226);

        System.out.println(conta.getAgencia());

        Cliente paulo = new Cliente();
        //conta.titular = paulo; não compila pois titular é privado agora
        paulo.setNome("paulo silveira");

        conta.setTitular(paulo); //atribuindo a referencia paulo ao campo titular da conta

        System.out.println(conta.getTitular().getNome());

        conta.getTitular().setProfissao("programador");

    }
}
