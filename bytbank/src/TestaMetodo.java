public class TestaMetodo {
    public static void main(String[] args) {
        Conta contaDoPaulo = new Conta();
        contaDoPaulo.saldo = 150;
        contaDoPaulo.deposita(50);
        System.out.println("Saldo da conta do Paulo: " + contaDoPaulo.saldo);

        boolean conseguiuRetirar = contaDoPaulo.saca(50);
        System.out.println("Saldo da conta do Paulo: " + contaDoPaulo.saldo);
        System.out.println(conseguiuRetirar); //return true or false

        Conta contaDaMarcela = new Conta();
        contaDaMarcela.deposita(1000);

        boolean sucessoTransferencia = contaDaMarcela.transfere(100, contaDoPaulo);
        if(sucessoTransferencia){
            System.out.println("Transferencia feita com sucesso!");
        } else {
            System.out.println("Houve algum problema");
        }

        System.out.println("Saldo da conta da Marcela: " + contaDaMarcela.saldo);
        System.out.println("Saldo da conta do Paulo: " + contaDoPaulo.saldo);

        contaDoPaulo.titular = "Paulo Silveira";
        System.out.println(contaDoPaulo.titular);

    }
}
