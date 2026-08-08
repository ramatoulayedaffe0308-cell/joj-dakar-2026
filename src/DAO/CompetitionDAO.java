package DAO;

import models.Competition;
import models.Discipline;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CompetitionDAO {

    private Connection connection;
    public CompetitionDAO(){
        connection = Database.getConnection();
    }

    public boolean ajouter(Competition competition){
        String sql =
                "INSERT INTO competition(nomCompetition,dateCompetition,lieu,idDiscipline) VALUES(?,?,?,?)";
        try(
                PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setString(1, competition.getNomCompetition());

            ps.setDate(2, Date.valueOf(competition.getDateCompetition()));

            ps.setString(3, competition.getLieu());

            ps.setInt(4, competition.getDiscipline().getIdDiscipline());

            return ps.executeUpdate()>0;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean modifier(Competition competition) {
        String sql =
                "UPDATE competition SET " +
                        "nomCompetition=?, dateCompetition=?, lieu=?, idDiscipline=? " +
                        "WHERE idCompetition=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, competition.getNomCompetition());

            if (competition.getDateCompetition() != null) {
                ps.setDate(2, Date.valueOf(competition.getDateCompetition()));
            } else {
                ps.setNull(2, Types.DATE);
            }
            ps.setString(3, competition.getLieu());
            ps.setInt(4, competition.getDiscipline().getIdDiscipline());
            ps.setInt(5, competition.getIdCompetition());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(
                    "Erreur modification compétition : " + e.getMessage()
            );
        }
        return false;
    }

    public boolean supprimer(int id){
        String sql = "DELETE FROM competition WHERE idCompetition=?";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);

            return ps.executeUpdate()>0;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Competition rechercher(int id) {
        String sql =
                "SELECT c.idCompetition, c.nomCompetition, c.dateCompetition, c.lieu, " +
                        "d.idDiscipline, d.nomDiscipline, d.description " +
                        "FROM competition c " +
                        "JOIN discipline d ON c.idDiscipline = d.idDiscipline " +
                        "WHERE c.idCompetition = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Competition c = new Competition();
                    c.setIdCompetition(rs.getInt("idCompetition"));
                    c.setNomCompetition(rs.getString("nomCompetition"));
                    Date date = rs.getDate("dateCompetition");
                    if (date != null) {
                        c.setDateCompetition(date.toLocalDate());
                    }
                    c.setLieu(rs.getString("lieu"));

                    Discipline d = new Discipline();
                    d.setIdDiscipline(rs.getInt("idDiscipline"));
                    d.setNomDiscipline(rs.getString("nomDiscipline"));
                    d.setDescription(rs.getString("description"));
                    c.setDiscipline(d);

                    return c;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur recherche compétition : " + e.getMessage());
        }
        return null;
    }


    public List<Competition> afficherTous() {
        List<Competition> liste = new ArrayList<>();

        String sql =
                "SELECT c.idCompetition, c.nomCompetition, c.dateCompetition, c.lieu, " +
                        "d.idDiscipline, d.nomDiscipline, d.description " +
                        "FROM competition c " +
                        "JOIN discipline d ON c.idDiscipline = d.idDiscipline " +
                        "ORDER BY c.idCompetition";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Competition c = new Competition();
                c.setIdCompetition(rs.getInt("idCompetition"));
                c.setNomCompetition(rs.getString("nomCompetition"));
                Date date = rs.getDate("dateCompetition");
                if (date != null) {
                    c.setDateCompetition(date.toLocalDate());
                }
                c.setLieu(rs.getString("lieu"));

                Discipline d = new Discipline();
                d.setIdDiscipline(rs.getInt("idDiscipline"));
                d.setNomDiscipline(rs.getString("nomDiscipline"));
                d.setDescription(rs.getString("description"));
                c.setDiscipline(d);

                liste.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Erreur affichage compétitions : " + e.getMessage());
        }
        return liste;
    }

}