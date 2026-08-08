package services;
import java.util.Scanner;

public class MenuImple implements IMenu {

        private Scanner scanner = new Scanner(System.in);
        private AthleteService athleteService;
        private ResultatService resultatService;
        private StatistiqueService statistiqueService;

        public MenuImple(){
            athleteService = new AthleteService();
            resultatService = new ResultatService();
            statistiqueService = new StatistiqueService();
        }

        @Override
        public boolean menuAdmin() {
            int choix;
            boolean deconnexion = false;
            do {
                System.out.println("\n JEUX OLYMPIQUES DE LA JEUNESSE 2026 \n");
                System.out.println("====== Menu Admin ======");
                System.out.println("1. Gestion des utilisateurs");
                System.out.println("2. Gestion des pays");
                System.out.println("3. Gestion des disciplines");
                System.out.println("4. Gestion des athlètes");
                System.out.println("5. Gestion des compétitions");
                System.out.println("6. Gestion des résultats");
                System.out.println("7. Statistiques");
                System.out.println("8. Tableau medailles");
                System.out.println("9. Déconnexion");
                System.out.println("10. Quitter");
                System.out.print("Votre choix : ");
                choix = scanner.nextInt();

                switch (choix) {
                    case 1:
                        UtilisateurService utilisateurService = new UtilisateurService();
                        utilisateurService.menuUtilisateur();
                        break;

                    case 2:
                        new paysService().menuPays();
                        break;

                    case 3:
                        new DisciplineService().menuDiscipline();
                        break;

                    case 4:
                        athleteService.menuAthlete();
                        break;

                    case 5:
                        new CompetitionService().menuCompetition();
                        break;

                    case 6:
                        resultatService.menuResultat();
                        break;

                    case 7:
                        statistiqueService.afficherStatistiques();
                        break;

                    case 8:
                        resultatService.tableauMedaille();
                        break;

                    case 9:
                        System.out.println("Déconnexion réussie...");
                        deconnexion = true;
                        break;

                    case 10:
                        System.out.println("Au revoir !");
                        return false;
                    default:
                        System.out.println("Choix invalide.");
                }
            } while (!deconnexion && choix != 10);
            return deconnexion;
        }

        @Override
        public boolean menuUtilisateur() {
            int choix;
            boolean deconnexion = false;
            do {
                System.out.println("\n JEUX OLYMPIQUES DE LA JEUNESSE 2026 \n");
                System.out.println("====== Menu Utilisateur ======");
                System.out.println("1. Gestion des pays");
                System.out.println("2. Gestion des disciplines");
                System.out.println("3. Gestion des athlètes");
                System.out.println("4. Gestion des compétitions");
                System.out.println("5. Gestion des résultats");
                System.out.println("6. Statistiques");
                System.out.println("7. Tableau medailles");
                System.out.println("8. Déconnexion");
                System.out.println("9. Quitter");
                System.out.print("Votre choix : ");
                choix = scanner.nextInt();

                switch (choix) {
                    case 1:
                        new paysService().menuPays();
                        break;

                    case 2:
                        new DisciplineService().menuDiscipline();
                        break;

                    case 3:
                        athleteService.menuAthlete();
                        break;

                    case 4:
                        new CompetitionService().menuCompetition();
                        break;

                    case 5:
                        resultatService.menuResultat();
                        break;

                    case 6:
                        statistiqueService.afficherStatistiques();
                        break;
                     
                    case 7:
                        resultatService.tableauMedaille();
                        break;
                        
                    case 8:
                        System.out.println("Déconnexion réussie...");
                        deconnexion = true;
                        break;

                    case 9:
                        System.out.println("Fin programme");
                        return false;

                    default:
                        System.out.println("Choix invalide.");
                }
            } while (!deconnexion && choix != 9);
            return deconnexion;
        }

}
