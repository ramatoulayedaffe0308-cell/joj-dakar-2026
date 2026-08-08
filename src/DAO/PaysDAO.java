package DAO;
import models.Pays;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaysDAO {

        private Connection connection;
        public PaysDAO() {
            connection = Database.getConnection();
        }

        // Ajouter
        public boolean ajouter(Pays pays) {

            String sql = "INSERT INTO pays(nomPays, continent) VALUES(?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, pays.getNomPays());
                ps.setString(2, pays.getContinent());

                return ps.executeUpdate() > 0;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }

        // Modifier
        public boolean modifier(Pays pays) {

            String sql = "UPDATE pays SET nomPays=?, continent=? WHERE idPays=?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, pays.getNomPays());
                ps.setString(2, pays.getContinent());
                ps.setInt(3, pays.getIdPays());

                return ps.executeUpdate() > 0;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }

        // Supprimer
        public boolean supprimer(int id) {

            String sql = "DELETE FROM pays WHERE idPays=?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);

                return ps.executeUpdate() > 0;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }

        // Rechercher
        public Pays rechercher(int id) {

            String sql = "SELECT * FROM pays WHERE idPays=?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    Pays pays = new Pays();
                    pays.setIdPays(rs.getInt("idPays"));
                    pays.setNomPays(rs.getString("nomPays"));
                    pays.setContinent(rs.getString("continent"));
                    return pays;
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            return null;
        }

        //Liste
        public List<Pays> afficherTous() {

            List<Pays> liste = new ArrayList<>();
            String sql = "SELECT * FROM pays";

            try (Statement st = connection.createStatement()) {
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    Pays pays = new Pays();
                    pays.setIdPays(rs.getInt("idPays"));
                    pays.setNomPays(rs.getString("nomPays"));
                    pays.setContinent(rs.getString("continent"));

                    liste.add(pays);
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return liste;
        }

        public void afficherListePays() {
            String sql = "SELECT * FROM pays";

            try (Statement st = connection.createStatement()) {
                ResultSet rs = st.executeQuery(sql);
                System.out.println("\n===== LISTE DES PAYS ===== \n");

                while (rs.next()) {
                    System.out.println(
                            rs.getInt("idPays")
                                    + " - "
                                    + rs.getString("nomPays")
                                    + rs.getString("continent")
                    );
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
}
