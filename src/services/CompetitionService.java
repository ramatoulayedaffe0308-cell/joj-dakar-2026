package services;

import DAO.CompetitionDAO;
import DAO.DisciplineDAO;
import models.Competition;
import models.Discipline;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class CompetitionService implements Icompetition {

    private CompetitionDAO competitionDAO;
    private DisciplineDAO disciplineDAO;
    private Scanner scanner;

    public CompetitionService(){
        competitionDAO = new CompetitionDAO();
        disciplineDAO = new DisciplineDAO();
        scanner = new Scanner(System.in);
    }

    @Override
    public void menuCompetition(){
        int choix;
        do{
            System.out.println("\n===== GESTION DES COMPETITIONS ===== \n");
            System.out.println("1. Ajouter compétition");
            System.out.println("2. Modifier compétition");
            System.out.println("3. Supprimer compétition");
            System.out.println("4. Rechercher compétition");
            System.out.println("5. Afficher compétitions");
            System.out.println("6. Quitter");
            System.out.print("Votre choix : ");
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
                    System.out.println("Retour menu principale");
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        }while(choix != 6);
    }

    private void ajouter(){
        scanner.nextLine();
        Competition competition = new Competition();

        System.out.print("Nom compétition : ");
        competition.setNomCompetition(scanner.nextLine());

        System.out.print("Date compétition (AAAA-MM-JJ) : ");

        competition.setDateCompetition(
                LocalDate.parse(scanner.nextLine())
        );

        for(Discipline d : disciplineDAO.afficherTous()){
            System.out.println(d);
        }
        System.out.print("Choisir discipline : ");
        int id = scanner.nextInt();

        Discipline discipline =
                disciplineDAO.rechercher(id);
        competition.setDiscipline(discipline);

        System.out.println("\nLieux disponibles :");
        System.out.println("1. Dakar");
        System.out.println("2. Diamniadio");
        System.out.println("3. Saly");

        System.out.print("Choisir lieu : ");
        int lieu = scanner.nextInt();

        switch(lieu){
            case 1:
                competition.setLieu("Dakar");
                break;
            case 2:
                competition.setLieu("Diamniadio");
                break;
            case 3:
                competition.setLieu("Saly");
                break;
            default:
                System.out.println("Lieu incorrect");
                return;
        }
        if(competitionDAO.ajouter(competition)){
            System.out.println("Compétition ajoutée avec succès.");
        }else{
            System.out.println("Erreur ajout.");
        }
    }

    private void supprimer(){
        System.out.print("ID compétition : ");
        int id = scanner.nextInt();
        if(competitionDAO.supprimer(id)){
            System.out.println("Suppression réussie.");
        }else{
            System.out.println("Erreur suppression.");
        }
    }

    private void afficher(){
        List<Competition> liste =
                competitionDAO.afficherTous();
        if(liste.isEmpty()){
            System.out.println("Aucune compétition.");
        }else{
            for(Competition c : liste){
                System.out.println(c);
            }
        }
    }

    private void rechercher() {
        System.out.print("ID compétition : ");
        int id = scanner.nextInt();
        Competition competition = competitionDAO.rechercher(id);

        if (competition != null) {
            System.out.println(competition);
        } else {
            System.out.println("Compétition introuvable.");
        }
    }

    private void modifier() {
        System.out.print("ID compétition : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Competition competition = competitionDAO.rechercher(id);

        if (competition == null) {
            System.out.println("Compétition introuvable.");
            return;
        }

        System.out.print("Nouveau nom : ");
        competition.setNomCompetition(scanner.nextLine());
        System.out.print("Nouvelle date (AAAA-MM-JJ) : ");
        competition.setDateCompetition(LocalDate.parse(scanner.nextLine()));

        System.out.println("\nDisciplines disponibles :");
        List<Discipline> disciplines = disciplineDAO.afficherTous();
        for (Discipline d : disciplines) {System.out.println(d);}

        System.out.print("Nouvel ID discipline : ");
        int idDiscipline = scanner.nextInt();
        Discipline discipline = disciplineDAO.rechercher(idDiscipline);
        if (discipline == null) {
            System.out.println("Discipline introuvable.");
            return;
        }

        competition.setDiscipline(discipline);
        System.out.println("\nLieux disponibles :");
        System.out.println("1. Dakar");
        System.out.println("2. Diamniadio");
        System.out.println("3. Saly");
        System.out.print("Choisir lieu : ");
        int choixLieu = scanner.nextInt();
        switch (choixLieu) {
            case 1:
                competition.setLieu("Dakar");
                break;
            case 2:
                competition.setLieu("Diamniadio");
                break;
            case 3:
                competition.setLieu("Saly");
                break;
            default:
                System.out.println("Lieu incorrect.");
                return;
        }
        if (competitionDAO.modifier(competition)) {
            System.out.println("Modification réussie.");
        } else {
            System.out.println("Erreur lors de la modification.");
        }
    }
    
}