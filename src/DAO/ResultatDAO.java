package DAO;

import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultatDAO {

        private Connection connection;
        public ResultatDAO() {
            connection = Database.getConnection();
        }

        // Ajouter
        public boolean ajouter(Resultat resultat) {
            String sql = "INSERT INTO resultat(idAthlete,idCompetition,score,rang) VALUES(?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, resultat.getAthlete().getIdAthlete());
                ps.setInt(2, resultat.getCompetition().getIdCompetition());
                ps.setString(3, resultat.getScore());
                ps.setInt(4, resultat.getRang());

                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                System.out.println("Erreur ajout : " + e.getMessage());
            }
            return false;
        }

        // Modifier
        public boolean modifier(Resultat resultat) {
            String sql = "UPDATE resultat SET idAthlete=?, idCompetition=?, score=?, rang=? WHERE idResultat=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, resultat.getAthlete().getIdAthlete());
                ps.setInt(2, resultat.getCompetition().getIdCompetition());
                ps.setString(3, resultat.getScore());
                ps.setInt(4, resultat.getRang());
                ps.setInt(5, resultat.getIdResultat());

                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                System.out.println("Erreur modification : " + e.getMessage());
            }
            return false;
        }

        // Supprimer
        public boolean supprimer(int id) {
            String sql = "DELETE FROM resultat WHERE idResultat=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);

                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                System.out.println("Erreur suppression : " + e.getMessage());
            }
            return false;
        }

        // Rechercher
        public Resultat rechercher(int id) {
            String sql = "SELECT r.*, " +
                            "a.nom, a.prenom, a.sexe, a.dateNaissance, " +
                            "p.idPays, p.nomPays, p.continent, " +
                            "da.idDiscipline AS idDisciplineAthlete, " +
                            "da.nomDiscipline AS nomDisciplineAthlete, " +
                            "da.description AS descriptionDisciplineAthlete, " +
                            "c.nomCompetition, c.dateCompetition, c.lieu, " +
                            "dc.idDiscipline AS idDisciplineCompetition, " +
                            "dc.nomDiscipline AS nomDisciplineCompetition, " +
                            "dc.description AS descriptionDisciplineCompetition " +

                            "FROM resultat r " +

                            "JOIN athlete a ON r.idAthlete = a.idAthlete " +
                            "JOIN pays p ON a.idPays = p.idPays " +
                            "JOIN discipline da ON a.idDiscipline = da.idDiscipline " +
                            "JOIN competition c ON r.idCompetition = c.idCompetition " +
                            "JOIN discipline dc ON c.idDiscipline = dc.idDiscipline " +

                            "WHERE r.idResultat=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    Resultat resultat = new Resultat();
                    resultat.setIdResultat(rs.getInt("idResultat"));

                    resultat.setScore(rs.getString("score"));

                    resultat.setRang(rs.getInt("rang"));

                    Athlete athlete = new Athlete();
                    athlete.setIdAthlete(rs.getInt("idAthlete"));

                    athlete.setNom(rs.getString("nom"));

                    athlete.setPrenom(rs.getString("prenom"));

                    athlete.setSexe(rs.getString("sexe"));

                    Date dateNaissance = rs.getDate("dateNaissance");

                    if (dateNaissance != null) {
                        athlete.setDateNaissance(dateNaissance.toLocalDate());
                    }

                    Pays pays = new Pays();

                    pays.setIdPays(rs.getInt("idPays"));

                    pays.setNomPays(rs.getString("nomPays"));

                    pays.setContinent(rs.getString("continent"));

                    athlete.setPays(pays);

                    Discipline disciplineAthlete = new Discipline();
                    disciplineAthlete.setIdDiscipline(rs.getInt("idDisciplineAthlete"));
                    disciplineAthlete.setNomDiscipline(rs.getString("nomDisciplineAthlete"));
                    disciplineAthlete.setDescription(rs.getString("descriptionDisciplineAthlete"));
                    athlete.setDiscipline(disciplineAthlete);

                    Competition competition = new Competition();
                    competition.setIdCompetition(rs.getInt("idCompetition"));
                    competition.setNomCompetition(rs.getString("nomCompetition"));
                    Date dateCompetition = rs.getDate("dateCompetition");

                    if (dateCompetition != null) {
                        competition.setDateCompetition(dateCompetition.toLocalDate());
                    }
                    competition.setLieu(rs.getString("lieu"));

                    Discipline disciplineCompetition = new Discipline();
                    disciplineCompetition.setIdDiscipline(rs.getInt("idDisciplineCompetition"));
                    disciplineCompetition.setNomDiscipline(rs.getString("nomDisciplineCompetition"));
                    disciplineCompetition.setDescription(rs.getString("descriptionDisciplineCompetition"));

                    competition.setDiscipline(disciplineCompetition);
                    resultat.setAthlete(athlete);
                    resultat.setCompetition(competition);

                    return resultat;
                }
            } catch (SQLException e) {
                System.out.println(
                        "Erreur recherche résultat : " + e.getMessage()
                );
            }
            return null;
        }


        // Afficher tous
        public List<Resultat> afficherTous() {
            List<Resultat> liste = new ArrayList<>();

            String sql = "SELECT r.idResultat, r.idAthlete, r.idCompetition, r.score, r.rang, " +
                    "a.nom, a.prenom, a.sexe, a.dateNaissance, " +
                    "p.idPays, p.nomPays, p.continent, " +
                    "da.idDiscipline AS idDisciplineAthlete, " +
                    "da.nomDiscipline AS nomDisciplineAthlete, " +
                    "da.description AS descriptionDisciplineAthlete, " +
                    "c.nomCompetition, c.dateCompetition, c.lieu, " +
                    "dc.idDiscipline AS idDisciplineCompetition, " +
                    "dc.nomDiscipline AS nomDisciplineCompetition, " +
                    "dc.description AS descriptionDisciplineCompetition " +

                    "FROM resultat r " +

                    "JOIN athlete a ON r.idAthlete = a.idAthlete " +
                    "JOIN pays p ON a.idPays = p.idPays " +
                    "JOIN discipline da ON a.idDiscipline = da.idDiscipline " +
                    "JOIN competition c ON r.idCompetition = c.idCompetition " +
                    "JOIN discipline dc ON c.idDiscipline = dc.idDiscipline " +

                    "ORDER BY r.idResultat";

            try (Statement st = connection.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    Resultat resultat = new Resultat();
                    resultat.setIdResultat(rs.getInt("idResultat"));
                    resultat.setScore(rs.getString("score"));
                    resultat.setRang(rs.getInt("rang"));

                    Athlete athlete = new Athlete();
                    athlete.setIdAthlete(rs.getInt("idAthlete"));
                    athlete.setNom(rs.getString("nom"));
                    athlete.setPrenom(rs.getString("prenom"));
                    athlete.setSexe(rs.getString("sexe"));
                    Date dateNaissance = rs.getDate("dateNaissance");
                    if (dateNaissance != null) {
                        athlete.setDateNaissance(
                                dateNaissance.toLocalDate()
                        );
                    }

                    Pays pays = new Pays();
                    pays.setIdPays(rs.getInt("idPays"));
                    pays.setNomPays(rs.getString("nomPays"));
                    pays.setContinent(rs.getString("continent"));
                    athlete.setPays(pays);

                    Discipline disciplineAthlete = new Discipline();

                    disciplineAthlete.setIdDiscipline(rs.getInt("idDisciplineAthlete"));
                    disciplineAthlete.setNomDiscipline(rs.getString("nomDisciplineAthlete"));
                    disciplineAthlete.setDescription(rs.getString("descriptionDisciplineAthlete"));
                    athlete.setDiscipline(disciplineAthlete);

                    Competition competition = new Competition();
                    competition.setIdCompetition(rs.getInt("idCompetition"));
                    competition.setNomCompetition(rs.getString("nomCompetition"));
                    Date dateCompetition = rs.getDate("dateCompetition");
                    if (dateCompetition != null) {
                        competition.setDateCompetition(
                                dateCompetition.toLocalDate()
                        );
                    }
                    competition.setLieu(rs.getString("lieu"));

                    Discipline disciplineCompetition = new Discipline();
                    disciplineCompetition.setIdDiscipline(rs.getInt("idDisciplineCompetition"));
                    disciplineCompetition.setNomDiscipline(rs.getString("nomDisciplineCompetition"));
                    disciplineCompetition.setDescription(rs.getString("descriptionDisciplineCompetition"));
                    competition.setDiscipline(disciplineCompetition);

                    resultat.setAthlete(athlete);
                    resultat.setCompetition(competition);

                    liste.add(resultat);
                }
            } catch (SQLException e) {
                System.out.println(
                        "Erreur affichage résultats : " + e.getMessage()
                );
            }
            return liste;
        }

    // Tableau des médailles
    public void tableauMedaille() {
        String sql =
                "SELECT p.nomPays, " +
                        "SUM(CASE WHEN r.rang = 1 THEN 1 ELSE 0 END) AS Ors, " +
                        "SUM(CASE WHEN r.rang = 2 THEN 1 ELSE 0 END) AS Argents, " +
                        "SUM(CASE WHEN r.rang = 3 THEN 1 ELSE 0 END) AS Bronzes, " +
                        "COUNT(r.idResultat) AS Total " +
                        "FROM resultat r " +
                        "JOIN athlete a ON r.idAthlete = a.idAthlete " +
                        "JOIN pays p ON a.idPays = p.idPays " +
                        "GROUP BY p.idPays, p.nomPays " +
                        "ORDER BY Ors DESC, Argents DESC, Bronzes DESC";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n======================================");
            System.out.println("       TABLEAU DES MEDAILLES");
            System.out.println("======================================\n");

            System.out.printf("%-15s %-8s %-8s %-8s %-8s%n",
                    "Pays",
                    "Or",
                    "Argent",
                    "Bronze",
                    "Total"
            );
            while (rs.next()) {
                System.out.printf("%-15s %-8d %-8d %-8d %-8d%n",
                        rs.getString("nomPays"),
                        rs.getInt("Ors"),
                        rs.getInt("Argents"),
                        rs.getInt("Bronzes"),
                        rs.getInt("Total")
                );
            }
        } catch (SQLException e) {
            System.out.println(
                    "Erreur tableau des médailles : " + e.getMessage()
            );
        }
    }

    public List<Resultat> classementCompetition(int idCompetition) {
        List<Resultat> liste = new ArrayList<>();
        String sql =
                "SELECT r.*, a.nom, a.prenom, c.nomCompetition " +
                        "FROM resultat r " +
                        "JOIN athlete a ON r.idAthlete = a.idAthlete " +
                        "JOIN competition c ON r.idCompetition = c.idCompetition " +
                        "WHERE r.idCompetition = ? " +
                        "ORDER BY r.rang ASC";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCompetition);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Resultat resultat = new Resultat();
                resultat.setIdResultat(rs.getInt("idResultat"));
                resultat.setScore(rs.getString("score"));
                resultat.setRang(rs.getInt("rang"));

                Athlete athlete = new Athlete();
                athlete.setIdAthlete(rs.getInt("idAthlete"));
                athlete.setNom(rs.getString("nom"));
                athlete.setPrenom(rs.getString("prenom"));

                Competition competition = new Competition();
                competition.setIdCompetition(rs.getInt("idCompetition"));
                competition.setNomCompetition(rs.getString("nomCompetition"));
                resultat.setAthlete(athlete);
                resultat.setCompetition(competition);

                liste.add(resultat);
            }
        } catch (SQLException e) {
            System.out.println(
                    "Erreur classement : " + e.getMessage()
            );
        }
        return liste;
    }

}
