package DAO;

import models.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {

    private Connection connection;
    public UtilisateurDAO(){
        connection = Database.getConnection();
    }

    //ajout
    public boolean ajouter(Utilisateur utilisateur) {

        String sql = "INSERT INTO utilisateur(nomComplet, login, password, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, utilisateur.getNomComplet());
            ps.setString(2, utilisateur.getLogin());
            ps.setString(3, utilisateur.getPassword());
            ps.setString(4, utilisateur.getRole());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
            return false;
        }
    }

    //auth
    public Utilisateur connecter(String login, String password) {

        String sql = "SELECT * FROM utilisateur WHERE login = ? AND password = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setIdUtilisateur(rs.getInt("idUtilisateur"));
                utilisateur.setNomComplet(rs.getString("nomComplet"));
                utilisateur.setLogin(rs.getString("login"));
                utilisateur.setPassword(rs.getString("password"));
                utilisateur.setRole(rs.getString("role"));

                return utilisateur;
            }

        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return null;
    }

    //rechercher
    public Utilisateur rechercher(int id) {

        String sql = "SELECT * FROM utilisateur WHERE idUtilisateur = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setIdUtilisateur(rs.getInt("idUtilisateur"));
                utilisateur.setNomComplet(rs.getString("nomComplet"));
                utilisateur.setLogin(rs.getString("login"));
                utilisateur.setPassword(rs.getString("password"));
                utilisateur.setRole(rs.getString("role"));

                return utilisateur;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //supprimer
    public boolean supprimer(int id) {

        String sql = "DELETE FROM utilisateur WHERE idUtilisateur = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //modif
    public boolean modifier(Utilisateur utilisateur) {

        String sql = """
            UPDATE utilisateur
            SET nomComplet = ?, login = ?, password = ?, role = ?
            WHERE idUtilisateur = ?
            """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, utilisateur.getNomComplet());
            ps.setString(2, utilisateur.getLogin());
            ps.setString(3, utilisateur.getPassword());
            ps.setString(4, utilisateur.getRole());
            ps.setInt(5, utilisateur.getIdUtilisateur());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //affichage
    public List<Utilisateur> afficherTous() {

        List<Utilisateur> liste = new ArrayList<>();
        String sql = "SELECT * FROM utilisateur";

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setIdUtilisateur(rs.getInt("idUtilisateur"));
                utilisateur.setNomComplet(rs.getString("nomComplet"));
                utilisateur.setLogin(rs.getString("login"));
                utilisateur.setPassword(rs.getString("password"));
                utilisateur.setRole(rs.getString("role"));

                liste.add(utilisateur);
            }

        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return liste;
    }
}
