package DAO;

import models.Athlete;
import models.Discipline;
import models.Pays;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AthleteDAO {

    private Connection connection;
    public AthleteDAO() {
        connection = Database.getConnection();
    }

    // Ajouter
    public boolean ajouter(Athlete athlete) {
        String sql = "INSERT INTO athlete(nom, prenom, sexe, dateNaissance, idPays, idDiscipline) VALUES(?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, athlete.getNom());
            ps.setString(2, athlete.getPrenom());
            ps.setString(3, athlete.getSexe());
            ps.setDate(4, Date.valueOf(athlete.getDateNaissance()));
            ps.setInt(5, athlete.getPays().getIdPays());
            ps.setInt(6, athlete.getDiscipline().getIdDiscipline());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erreur ajout : " + e.getMessage());
        }
        return false;
    }

    // Modifier
    public boolean modifier(Athlete athlete) {
        String sql = "UPDATE athlete SET nom=?, prenom=?, sexe=?, dateNaissance=?, idPays=?, idDiscipline=? WHERE idAthlete=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, athlete.getNom());
            ps.setString(2, athlete.getPrenom());
            ps.setString(3, athlete.getSexe());
            ps.setDate(4, Date.valueOf(athlete.getDateNaissance()));
            ps.setInt(5, athlete.getPays().getIdPays());
            ps.setInt(6, athlete.getDiscipline().getIdDiscipline());
            ps.setInt(7, athlete.getIdAthlete());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erreur modification : " + e.getMessage());
        }
        return false;
    }

    // Supprimer
    public boolean supprimer(int id) {
        String sql = "DELETE FROM athlete WHERE idAthlete=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erreur suppression : " + e.getMessage());
        }
        return false;
    }

    // Rechercher
    public Athlete rechercher(int id) {
        String sql =
                "SELECT a.*, " +
                        "p.nomPays, p.continent, " +
                        "d.nomDiscipline, d.description " +
                        "FROM athlete a " +
                        "JOIN pays p ON a.idPays = p.idPays " +
                        "JOIN discipline d ON a.idDiscipline = d.idDiscipline " +
                        "WHERE a.idAthlete=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Athlete athlete = new Athlete();
                athlete.setIdAthlete(rs.getInt("idAthlete"));
                athlete.setNom(rs.getString("nom"));
                athlete.setPrenom(rs.getString("prenom"));
                athlete.setSexe(rs.getString("sexe"));
                athlete.setDateNaissance(rs.getDate("dateNaissance").toLocalDate());
                Pays pays = new Pays();
                pays.setIdPays(rs.getInt("idPays"));
                pays.setNomPays(rs.getString("nomPays"));

                Discipline discipline = new Discipline();
                discipline.setIdDiscipline(rs.getInt("idDiscipline"));
                discipline.setNomDiscipline(rs.getString("nomDiscipline"));

                athlete.setPays(pays);
                athlete.setDiscipline(discipline);

                return athlete;
            }
        }catch (SQLException e) {
            System.out.println("Erreur recherche : " + e.getMessage());
        }
        return null;
    }

    // Afficher tous
    public List<Athlete> afficherTous() {
        List<Athlete> liste = new ArrayList<>();

        String sql =
                "SELECT a.*, " +
                        "p.nomPays, p.continent, " +
                        "d.nomDiscipline, d.description " +
                        "FROM athlete a " +
                        "JOIN pays p ON a.idPays = p.idPays " +
                        "JOIN discipline d ON a.idDiscipline = d.idDiscipline";

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Athlete athlete = new Athlete();
                athlete.setIdAthlete(rs.getInt("idAthlete"));
                athlete.setNom(rs.getString("nom"));
                athlete.setPrenom(rs.getString("prenom"));
                athlete.setSexe(rs.getString("sexe"));
                athlete.setDateNaissance(rs.getDate("dateNaissance").toLocalDate());
                Pays pays = new Pays();
                pays.setIdPays(rs.getInt("idPays"));
                pays.setNomPays(rs.getString("nomPays"));
                pays.setContinent(rs.getString("continent"));

                Discipline discipline = new Discipline();
                discipline.setIdDiscipline(rs.getInt("idDiscipline"));
                discipline.setNomDiscipline(rs.getString("nomDiscipline"));
                discipline.setDescription(rs.getString("description"));

                athlete.setPays(pays);
                athlete.setDiscipline(discipline);

                liste.add(athlete);
            }
        } catch (SQLException e) {
            System.out.println("Erreur affichage : " + e.getMessage());
        }
        return liste;
    }
}