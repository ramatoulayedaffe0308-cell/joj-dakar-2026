package services;


import DAO.UtilisateurDAO;
import models.Utilisateur;

import java.util.List;
import java.util.Scanner;

public class UtilisateurService {

    private UtilisateurDAO utilisateurDAO;
    private Scanner scanner;

    public UtilisateurService() {
        utilisateurDAO = new UtilisateurDAO();
        scanner = new Scanner(System.in);
    }

    public void menuUtilisateur() {
        int choix;
        do {
            System.out.println("\n===== GESTION DES UTILISATEURS ===== \n");
            System.out.println("1. Ajouter utilisateur");
            System.out.println("2. Modifier utilisateur");
            System.out.println("3. Supprimer utilisateur");
            System.out.println("4. Rechercher utilisateur");
            System.out.println("5. Afficher utilisateurs");
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
                    System.out.println("Retour...");
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        } while (choix != 6);
    }

    private void ajouter() {
        scanner.nextLine();
        System.out.print("Nom complet : ");
        String nom = scanner.nextLine();
        System.out.print("Login : ");
        String login = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String password = scanner.nextLine();
        System.out.print("Role (ADMIN/USER) : ");
        String role = scanner.nextLine();

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNomComplet(nom);
        utilisateur.setLogin(login);
        utilisateur.setPassword(password);
        utilisateur.setRole(role);

        if(utilisateurDAO.ajouter(utilisateur)) {
            System.out.println("Utilisateur ajouté avec succès");
        } else {
            System.out.println("Erreur lors de l'ajout");
        }
    }

    private void afficher() {
        List<Utilisateur> utilisateurs =
                utilisateurDAO.afficherTous();
        for(Utilisateur u : utilisateurs) {
            System.out.println(u);
        }
    }

    private void rechercher() {
        System.out.print("Id utilisateur : ");
        int id = scanner.nextInt();
        Utilisateur u = utilisateurDAO.rechercher(id);
        if(u != null) {
            System.out.println(u);
        } else {
            System.out.println("Utilisateur introuvable");
        }
    }

    private void supprimer() {
        System.out.print("Id utilisateur : ");
        int id = scanner.nextInt();
        if(utilisateurDAO.supprimer(id)) {
            System.out.println("Utilisateur supprimé");
        } else {
            System.out.println("Erreur suppression");
        }
    }

    private void modifier() {
        System.out.print("Id utilisateur : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nouveau nom complet : ");
        String nom = scanner.nextLine();
        System.out.print("Nouveau login : ");
        String login = scanner.nextLine();
        System.out.print("Nouveau mot de passe : ");
        String password = scanner.nextLine();
        System.out.print("Nouveau rôle : ");
        String role = scanner.nextLine();
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(id);
        utilisateur.setNomComplet(nom);
        utilisateur.setLogin(login);
        utilisateur.setPassword(password);
        utilisateur.setRole(role);

        if(utilisateurDAO.modifier(utilisateur)) {
            System.out.println("Modification réussie");
        } else {
            System.out.println("Erreur modification");
        }
    }

}
