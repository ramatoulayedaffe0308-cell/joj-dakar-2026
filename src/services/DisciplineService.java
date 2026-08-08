package services;
import DAO.DisciplineDAO;
import models.Discipline;
import java.util.Scanner;

public class DisciplineService {

    private DisciplineDAO disciplineDAO;
    private Scanner scanner;

    public DisciplineService() {
        disciplineDAO = new DisciplineDAO();
        scanner = new Scanner(System.in);
    }

    public void menuDiscipline() {
        int choix;
        do {
            System.out.println("\n===== GESTION DES DISCIPLINES ===== \n");
            System.out.println("1. Ajouter discipline");
            System.out.println("2. Modifier discipline");
            System.out.println("3. Supprimer discipline");
            System.out.println("4. Rechercher discipline");
            System.out.println("5. Afficher disciplines");
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
                    System.out.println("Retour au menu principal...");
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        } while (choix != 6);
    }

    private void ajouter() {
        scanner.nextLine();
        System.out.print("Nom de la discipline : ");
        String nom = scanner.nextLine();
        System.out.print("Description : ");
        String description = scanner.nextLine();
        Discipline discipline = new Discipline();
        discipline.setNomDiscipline(nom);
        discipline.setDescription(description);

        if (disciplineDAO.ajouter(discipline)) {
            System.out.println("Discipline ajoutée avec succès.");
        } else {
            System.out.println("Échec de l'ajout.");
        }
    }

    private void modifier() {
        System.out.print("Id discipline : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nouveau nom : ");
        String nom = scanner.nextLine();
        System.out.print("Nouvelle description : ");
        String description = scanner.nextLine();
        Discipline discipline = new Discipline();
        discipline.setIdDiscipline(id);
        discipline.setNomDiscipline(nom);
        discipline.setDescription(description);

        if (disciplineDAO.modifier(discipline)) {
            System.out.println("Modification réussie.");
        } else {
            System.out.println("Échec de la modification.");
        }
    }

    private void supprimer() {
        System.out.print("Id discipline : ");
        int id = scanner.nextInt();

        if (disciplineDAO.supprimer(id)) {
            System.out.println("Suppression réussie.");
        } else {
            System.out.println("Échec de la suppression.");
        }
    }

    private void rechercher() {
        System.out.print("Id discipline : ");
        int id = scanner.nextInt();
        Discipline discipline = disciplineDAO.rechercher(id);

        if (discipline != null) {
            System.out.println(discipline);
        } else {
            System.out.println("Discipline introuvable.");
        }
    }

    private void afficher() {
        for (Discipline d : disciplineDAO.afficherTous()) {
            System.out.println(d);
        }
    }

}