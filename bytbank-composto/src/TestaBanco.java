public class TestaBanco {
    public static void main(String[] args) {
        Cliente paulo = new Cliente();
        paulo.nome = "Paulo Silveira";
        paulo.cpf = "222.222.222-22";
        paulo.profissao = "programador";

        Conta contaDoPaulo = new Conta();
        contaDoPaulo.deposita(100);

        //associa o cliente paulo a conta contaDoPaulo
        contaDoPaulo.titular = paulo; //conectando a conta com o cliente, passando a referencia do cliente

        System.out.println(contaDoPaulo.titular); //mostra a posição do objeto
        System.out.println(contaDoPaulo.titular.nome); //mostra o nome do cliente que possui essa conta
    }
}
