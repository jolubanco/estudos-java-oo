public class TesteFuncionario {
    public static void main(String[] args) {

        Cliente cliente = new Cliente();

        Gerente nico = new Gerente();
        nico.setNome("Nico");
        nico.setSalario(2600);
        nico.setCpf("2656853534");

        System.out.println(nico.getNome());
        System.out.println(nico.getBonificacao());
    }
}
