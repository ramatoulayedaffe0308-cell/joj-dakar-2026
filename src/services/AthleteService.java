package services;

import DAO.AthleteDAO;
import DAO.DisciplineDAO;
import DAO.PaysDAO;
import models.Athlete;
import models.Discipline;
import models.Pays;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AthleteService implements IAthleteService{

        private AthleteDAO athleteDAO;
        private PaysDAO paysDAO;
        private DisciplineDAO disciplineDAO;
        private Scanner scanner;

        public AthleteService() {

            athleteDAO = new AthleteDAO();
            paysDAO = new PaysDAO();
            disciplineDAO = new DisciplineDAO();
            scanner = new Scanner(System.in);

        }

        @Override
        public void menuAthlete() {
            int choix;
            do {
                System.out.println("\n===== GESTION DES ATHLETES ===== \n");
                System.out.println("1. Ajouter athlète");
                System.out.println("2. Modifier athlète");
                System.out.println("3. Supprimer athlète");
                System.out.println("4. Rechercher athlète");
                System.out.println("5. Afficher athlètes");
                System.out.println("6. Retour");
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
                        afficher();
                        break;
                    case 6:
                        System.out.println("Retour");
                        break;
                    default:
                        System.out.println("Choix invalide.");
                }
            } while (choix != 6);
        }

        private void ajouter() {
            scanner.nextLine();
            Athlete athlete = new Athlete();

            System.out.print("Votre Nom : ");
            athlete.setNom(scanner.nextLine());

            System.out.print("Votre Prénom : ");
            athlete.setPrenom(scanner.nextLine());

            System.out.print("Sexe (M/F) : ");
            athlete.setSexe(scanner.nextLine());

            System.out.print("Date de naissance (AAAA-MM-JJ) : ");
            athlete.setDateNaissance(LocalDate.parse(scanner.nextLine()));

            List<Pays> pays = paysDAO.afficherTous();
            for (Pays p : pays) {
                System.out.println(p);
            }

            System.out.print("Id du pays : ");
            int idPays = scanner.nextInt();

            athlete.setPays(paysDAO.rechercher(idPays));

            List<Discipline> disciplines = disciplineDAO.afficherTous();
            for (Discipline d : disciplines) {
                System.out.println(d);
            }

            System.out.print("Id discipline : ");
            int idDiscipline = scanner.nextInt();

            athlete.setDiscipline(disciplineDAO.rechercher(idDiscipline));

            if (athleteDAO.ajouter(athlete)) {
                System.out.println("Athlète ajouté avec succès.");
            } else {
                System.out.println("Erreur lors de l'ajout.");
            }
        }

        private void modifier() {
            System.out.print("Id de l'athlète : ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Athlete athlete = athleteDAO.rechercher(id);
            if (athlete == null) {
                System.out.println("Athlète introuvable.");
                return;
            }

            System.out.print("Nom : ");
            athlete.setNom(scanner.nextLine());

            System.out.print("Prénom : ");
            athlete.setPrenom(scanner.nextLine());

            System.out.print("Sexe : ");
            athlete.setSexe(scanner.nextLine());

            System.out.print("Date de naissance (AAAA-MM-JJ) : ");
            athlete.setDateNaissance(LocalDate.parse(scanner.nextLine()));

            for (Pays p : paysDAO.afficherTous()) {
                System.out.println(p);
            }

            System.out.print("Id du pays : ");
            athlete.setPays(paysDAO.rechercher(scanner.nextInt()));

            for (Discipline d : disciplineDAO.afficherTous()) {
                System.out.println(d);
            }

            System.out.print("Id discipline : ");
            athlete.setDiscipline(disciplineDAO.rechercher(scanner.nextInt()));

            if (athleteDAO.modifier(athlete)) {
                System.out.println("Modification réussie.");
            } else {
                System.out.println("Erreur de modification.");
            }
        }

        private void supprimer() {
            System.out.print("Id de l'athlète : ");
            int id = scanner.nextInt();
            if (athleteDAO.supprimer(id)) {
                System.out.println("Suppression réussie.");
            } else {
                System.out.println("Suppression impossible.");
            }
        }

        private void rechercher() {
            System.out.print("Id de l'athlète : ");
            int id = scanner.nextInt();
            Athlete athlete = athleteDAO.rechercher(id);
            if (athlete != null) {
                System.out.println(athlete);
            } else {
                System.out.println("Athlète introuvable.");
            }
        }

        private void afficher() {
            List<Athlete> liste = athleteDAO.afficherTous();
            if (liste.isEmpty()) {
                System.out.println("Aucun athlète.");
            } else {
                for (Athlete athlete : liste) {
                    System.out.println(athlete);
                }
            }
        }

}
