package services;


import DAO.PaysDAO;
import models.Pays;

import java.util.Scanner;

public class paysService {

    private PaysDAO paysDAO;
    private Scanner scanner;

    public paysService(){
        paysDAO = new PaysDAO();
        scanner = new Scanner(System.in);
    }

    public void menuPays(){
        int choix;
        do{
            System.out.println("\n ===== GESTION DES PAYS ===== \n");
            System.out.println("1. Ajouter pays");
            System.out.println("2. Modifier pays");
            System.out.println("3. Supprimer pays");
            System.out.println("4. Rechercher pays");
            System.out.println("5. Liste des pays");
            System.out.println("6. Retour");
            System.out.print("Votre Choix : ");
            choix = scanner.nextInt();

            switch(choix){
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
                    System.out.println("Retour menu principal");
                    break;

                default:
                    System.out.println("Choix incorrect");

            }
        }while(choix !=6);
    }

    private void ajouter(){
        scanner.nextLine();
        System.out.print("Nom du pays : ");
        String nom = scanner.nextLine();
        System.out.print("Continent : ");
        String continent = scanner.nextLine();
        Pays pays = new Pays();
        pays.setNomPays(nom);
        pays.setContinent(continent);

        if(paysDAO.ajouter(pays))
            System.out.println("Pays ajouté avec succès");
        else
            System.out.println("Erreur ajout");
    }

    private void afficher(){
        for(Pays p : paysDAO.afficherTous()){
            System.out.println(p);
        }
    }

    private void rechercher(){
        System.out.print("Id pays : ");
        int id=scanner.nextInt();
        Pays p=paysDAO.rechercher(id);
        if(p!=null)
            System.out.println(p);
        else
            System.out.println("Pays introuvable");
    }

    private void supprimer(){
        System.out.print("Id pays : ");
        int id=scanner.nextInt();
        if(paysDAO.supprimer(id))
            System.out.println("Supprimé");
        else
            System.out.println("Erreur suppression");
    }

    private void modifier(){
        System.out.print("Id pays : ");
        int id=scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nouveau nom : ");
        String nom=scanner.nextLine();
        System.out.print("Nouveau continent : ");
        String continent=scanner.nextLine();
        Pays p=new Pays();
        p.setIdPays(id);
        p.setNomPays(nom);
        p.setContinent(continent);

        if(paysDAO.modifier(p))
            System.out.println("Modification réussie");
        else
            System.out.println("Erreur");
    }

}
