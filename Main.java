import java.sql.DriverManager;

import java.sql.SQLException;
import org.mariadb.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main{
    public static void main(String[] args) throws SQLException {
        PreparedStatement preparedStatement = null;
        java.sql.Connection connection = null;
        String url = "jdbc:mariadb://localhost:3306/teste";
        String user = "root";
        String password = "123456";

        try{
            connection = DriverManager.getConnection(url, user, password);
        }catch(SQLException e){
            e.printStackTrace();
        }

        if(connection != null){
            System.out.println("conexão bem sucedida");
        }else{
            System.out.println("errorrrrr");
        }

        //teste1 
        String nome = "welton";
        int idade = 17;

        String sql = "INSERT INTO pessoa (nome,idade) VALUES (?,?)";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, nome);
        preparedStatement.setInt(2, idade); 

        int rowsAffected = preparedStatement.executeUpdate();

        if(rowsAffected > 0){
            System.out.println("inserido");
        }else{
            System.out.println("erro");
        }

        //teste2
        String sql2 = "SELECT * FROM pessoa";

        preparedStatement = connection.prepareStatement(sql2);

        ResultSet rs = preparedStatement.executeQuery(); 

        while(rs.next()){
            System.out.println("NOME: " + rs.getString("nome") + " IDADE: " + rs.getInt("idade"));
        }

        
    }
}
