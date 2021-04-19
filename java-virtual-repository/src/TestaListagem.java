import java.sql.*;

public class TestaListagem {

    public static void main(String[] args) throws SQLException {
        // Separando a criação da conexão, para quando eu qusier trocar a senha do banco, por exemplo, basta mudar em CriaConexao
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperaConexao();
        //

//        Statement stm = connection.createStatement(); //as consultas SQL são chamadas de statement no mundo java

        PreparedStatement stm = connection.prepareStatement("SELECT id,nome,descricao FROM PRODUTO"); //as consultas SQL são chamadas de statement no mundo java
        stm.execute();

        //boolean resultado = stm.execute("SELECT id,nome,descricao FROM PRODUTO");
        //o execute vai retonar true quando o resultado da consulta SQL for uma lista, como um select, e false quando não for, como um delete ou update
        //System.out.println(resultado);

        //armazenando os valores da minha consulta SQL
        ResultSet rst = stm.getResultSet();
        //le cada linha da tabela selecionada, realiza os operações, e vai para a proxima
        while(rst.next()){
            Integer id = rst.getInt("id"); //posso selecionar pela posicao da coluna ou pelo nome (label)
            System.out.println(id);
            String nome = rst.getString("nome");
            System.out.println(nome);
            String descricao = rst.getString("descricao");
            System.out.println(descricao);
        }
        connection.close();
    }
}
