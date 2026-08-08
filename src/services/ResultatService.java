package services;

import DAO.AthleteDAO;
import DAO.CompetitionDAO;
import DAO.ResultatDAO;
import models.Athlete;
import models.Competition;
import models.Resultat;

import java.util.List;
import java.util.Scanner;

public class ResultatService implements IResultat{

    private ResultatDAO resultatDAO;
    private AthleteDAO athleteDAO;
    private CompetitionDAO competitionDAO;
    private Scanner scanner;

    public ResultatService() {
        resultatDAO = new ResultatDAO();
        athleteDAO = new AthleteDAO();
        competitionDAO = new CompetitionDAO();
        scanner = new Scanner(System.in);
    }

    @Override
    public void menuResultat() {
        int choix;
        do {
            System.out.println("\n===== GESTION DES RESULTATS =====\n");
            System.out.println("1. Enregistrer résultat");
            System.out.println("2. Modifier résultat");
            System.out.println("3. Supprimer résultat");
            System.out.println("4. Rechercher résultat");
            System.out.println("5. Classement compétition");
            System.out.println("6. Afficher résultats");
            System.out.println("7. Retour");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    ajouter();
                    break;
                case 2:
                    modifier();
                    break;
                case 3:
                    supprimer();
                    break;
                case 4:
                    rechercher();
                    break;
                case 5:
                    classement();
                    break;
                case 6:
                    afficher();
                    break;
                case 7:
                    System.out.println("Retour");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 7);
    }

    private void ajouter() {
        Resultat resultat = new Resultat();

        List<Athlete> athletes = athleteDAO.afficherTous();
        System.out.println("\nListe des athlètes :");
        for (Athlete a : athletes) {
            System.out.println(a);
        }
        System.out.print("Id athlète : ");
        int idAthlete = scanner.nextInt();
        resultat.setAthlete(athleteDAO.rechercher(idAthlete));

        List<Competition> competitions = competitionDAO.afficherTous();
        System.out.println("\nListe des compétitions :");
        for (Competition c : competitions) {
            System.out.println(c);
        }

        System.out.print("Id compétition : ");
        int idCompetition = scanner.nextInt();
        resultat.setCompetition(competitionDAO.rechercher(idCompetition));
        scanner.nextLine();

        System.out.print("Score : ");
        resultat.setScore(scanner.nextLine());

        System.out.print("Rang : ");
        resultat.setRang(scanner.nextInt());

        if (resultatDAO.ajouter(resultat)) {
            System.out.println("Résultat enregistré avec succès.");
        } else {
            System.out.println("Erreur lors de l'enregistrement.");
        }
    }

    private void modifier() {
        System.out.print("Id du résultat : ");
        int id = scanner.nextInt();
        Resultat resultat = resultatDAO.rechercher(id);

        if (resultat == null) {
            System.out.println("Résultat introuvable.");
            return;
        }

        System.out.println("Modification du score");
        scanner.nextLine();

        System.out.print("Nouveau score : ");
        resultat.setScore(scanner.nextLine());

        System.out.print("Nouveau rang : ");
        resultat.setRang(scanner.nextInt());

        if (resultatDAO.modifier(resultat)) {
            System.out.println("Modification réussie.");
        } else {
            System.out.println("Erreur modification.");
        }
    }

    private void supprimer() {
        System.out.print("Id du résultat : ");
        int id = scanner.nextInt();

        if (resultatDAO.supprimer(id)) {
            System.out.println("Suppression réussie.");
        } else {
            System.out.println("Suppression impossible.");
        }
    }

    private void rechercher() {
        System.out.print("Id du résultat : ");
        int id = scanner.nextInt();
        Resultat resultat = resultatDAO.rechercher(id);

        if (resultat != null) {
            System.out.println(resultat);
        } else {
            System.out.println("Résultat introuvable.");
        }
    }

    private void afficher() {
        List<Resultat> liste = resultatDAO.afficherTous();

        if (liste.isEmpty()) {
            System.out.println("Aucun résultat.");
        } else {
            for (Resultat resultat : liste) {
                System.out.println(resultat);
            }
        }
    }

    private void classement() {
        System.out.print("ID compétition : ");
        int idCompetition = scanner.nextInt();

        List<Resultat> liste = resultatDAO.classementCompetition(idCompetition);
        if (liste.isEmpty()) {
            System.out.println(
                    "Aucun résultat pour cette compétition."
            );
            return;
        }
        System.out.println("\n===== CLASSEMENT DE LA COMPÉTITION =====");
        for (Resultat r : liste) {
            System.out.println("Rang : " + r.getRang()
                            + " | Athlète : "
                            + r.getAthlete().getNom()
                            + " "
                            + r.getAthlete().getPrenom()
                            + " | Score : "
                            + r.getScore()
            );
        }
    }

    public void tableauMedaille(){
        resultatDAO.tableauMedaille();
    }

}