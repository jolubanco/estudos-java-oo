public class TestaProduto {
    public static void main(String[] args) {
        Produto produto = new Produto();
        produto.setNome("Bis");
        produto.setPreco(4.0);

        System.out.println(produto.getNome());
    }
}
