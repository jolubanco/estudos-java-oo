import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {
    public static void main(String[] args) throws SQLException {

        // Separando a criação da conexão, para quando eu qusier trocar a senha do banco, por exemplo, basta mudar em CriaConexao
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperaConexao();
        //

        System.out.println("Fechando Conexão");
        connection.close();

    }
}
