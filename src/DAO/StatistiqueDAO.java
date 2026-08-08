package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class StatistiqueDAO {

    private Connection connection;
    public StatistiqueDAO(){
        connection = Database.getConnection();
    }

    private int compter(String table){
        String sql = "SELECT COUNT(*) FROM " + table;

        try(Statement st = connection.createStatement()){
            ResultSet rs = st.executeQuery(sql);

            if(rs.next()){
                return rs.getInt(1);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int nombrePays(){
        return compter("pays");
    }

    public int nombreAthletes(){
        return compter("athlete");
    }

    public int nombreDisciplines(){
        return compter("discipline");
    }

    public int nombreCompetitions(){
        return compter("competition");
    }

    public int nombreResultats(){
        return compter("resultat");
    }

}