import models.Utilisateur;
import services.AuthService;
import services.MenuImple;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AuthService auth = new AuthService();
        boolean continuer = true;

        while(continuer){
            System.out.println("\n===== CONNEXION JOJ DAKAR 2026 =====");

            System.out.print("Login : ");
            String login = scanner.nextLine();

            System.out.print("Mot de passe : ");
            String password = scanner.nextLine();

            Utilisateur utilisateur = auth.seConnecter(login, password);

            if(utilisateur != null){
                System.out.println();
                System.out.println("Connexion réussie !");
                System.out.println("Bienvenue " + utilisateur.getNomComplet()
                );
                MenuImple menu = new MenuImple();

                if(utilisateur.getRole().equalsIgnoreCase("ADMIN")){
                    continuer=menu.menuAdmin();
                }else{
                    continuer=menu.menuUtilisateur();
                }
            }else{
                System.out.println("Login ou mot de passe incorrect.");
            }
        }
        scanner.close();
        System.out.println("\nProgramme terminé.");
    }

}