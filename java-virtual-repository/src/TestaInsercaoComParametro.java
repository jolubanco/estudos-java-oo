import java.sql.*;

public class TestaInsercaoComParametro {
    public static void main(String[] args) throws SQLException {

        //SE APENAS CRIARMOS O STATEMENT, IRA DELETA A NOSSA TABELA PRODUTO
        //para corrigir usamos PreparedStatement

        ConnectionFactory factory = new ConnectionFactory();
        try(Connection connection = factory.recuperaConexao()) {
            //MUITO IMPORTANTE DESABILITAR
            connection.setAutoCommit(false); //com esse parametro, eu controlo quem será adiiconado no banco, no caso só sera adicionado caso os dois dados
            //foram adicionados, se um der errado entao nao adiciona nenhum!

            //em vez de criar o statament, nós preparamos eles utilizando a jbdc para tratar entradas que poderia dar erro na consulta SQL
            //com o try () não preciso fechar as conexões, elas sao fechadas automaticamente
            try (
                    PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO (nome,descricao) " +
                            "VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);) {

                //setando a posição das entradas que deixamos no statement SQL
                adicionarVariavel("SmartTV", "45 polegadas", stm);
                adicionarVariavel("Radio", "Radio de bateria", stm);

                connection.commit(); //permito a inserção no banco
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Rollback Executado");
                connection.rollback(); //desfaz todas as alterações no banco do tipo, INSERT, UPDATE, DELETE
            }
        }
    }

    private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
        stm.setString(1, nome);
        stm.setString(2, descricao);

//        como o statement já foi preparado, basta executar
        stm.execute();

        try(ResultSet rst = stm.getGeneratedKeys()) {
            while (rst.next()) {
                Integer id = rst.getInt(1);
                System.out.println("O id criado foi: " + id);
            }
        }
    }


}
