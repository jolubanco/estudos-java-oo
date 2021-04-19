import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.recuperaConexao();

        PreparedStatement stm = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?");
        stm.setInt(1,2);
        stm.execute();

        Integer linhasModificadas = stm.getUpdateCount(); //retorna quantas linhas foram modificados apos o statement ser executado

        System.out.println("Quantidade de linhas que foram modificadas: " + linhasModificadas);
    }
}
