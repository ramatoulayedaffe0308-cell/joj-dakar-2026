package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static Connection getConnection() {
        String URL ="jdbc:mysql://localhost:3306/joj_dakar2026";
        String Username="root";
        String password="";
        Connection connect=null;
        try{
            connect= DriverManager.getConnection(URL,Username,password);

        }catch (SQLException e){
            //System.out.println("Erreur de connexion :");
            e.printStackTrace();
        }
        return connect;
    }
}
