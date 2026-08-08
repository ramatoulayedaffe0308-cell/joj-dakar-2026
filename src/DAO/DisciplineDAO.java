package DAO;
import models.Discipline;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplineDAO {

    private Connection connection;
    public DisciplineDAO(){
        connection = Database.getConnection();
    }

    // Ajouter
    public boolean ajouter(Discipline discipline){
        String sql = "INSERT INTO discipline(nomDiscipline,description) VALUES(?,?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, discipline.getNomDiscipline());
            ps.setString(2, discipline.getDescription());

            return ps.executeUpdate()>0;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    // Modifier
    public boolean modifier(Discipline discipline){
        String sql = "UPDATE discipline SET nomDiscipline=?, description=? WHERE idDiscipline=?";

        try(PreparedStatement ps=connection.prepareStatement(sql)){
            ps.setString(1, discipline.getNomDiscipline());
            ps.setString(2, discipline.getDescription());
            ps.setInt(3, discipline.getIdDiscipline());

            return ps.executeUpdate()>0;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    // Supprimer
    public boolean supprimer(int id){
        String sql = "DELETE FROM discipline WHERE idDiscipline=?";

        try(PreparedStatement ps=connection.prepareStatement(sql)){
            ps.setInt(1,id);

            return ps.executeUpdate()>0;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    // Rechercher
    public Discipline rechercher(int id){
        String sql = "SELECT * FROM discipline WHERE idDiscipline=?";

        try(PreparedStatement ps=connection.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                Discipline d=new Discipline();
                d.setIdDiscipline(
                        rs.getInt("idDiscipline")
                );

                d.setNomDiscipline(
                        rs.getString("nomDiscipline")
                );

                d.setDescription(
                        rs.getString("description")
                );

                return d;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Afficher
    public List<Discipline> afficherTous(){
        List<Discipline> liste=new ArrayList<>();

        String sql="SELECT * FROM discipline";

        try(Statement st=connection.createStatement()){
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                Discipline d=new Discipline();

                d.setIdDiscipline(
                        rs.getInt("idDiscipline")
                );

                d.setNomDiscipline(
                        rs.getString("nomDiscipline")
                );

                d.setDescription(
                        rs.getString("description")
                );

                liste.add(d);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return liste;
    }

    public void afficherListeDisciplines() {
        String sql = "SELECT * FROM discipline";

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            System.out.println("\n===== LISTE DES DISCIPLINES =====");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("idDiscipline")
                                + " - "
                                + rs.getString("nomDiscipline")
                                + " : "
                                + rs.getString("description")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}