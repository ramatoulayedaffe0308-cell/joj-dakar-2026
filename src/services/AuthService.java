package services;
import DAO.UtilisateurDAO;
import models.Utilisateur;

public class AuthService implements IAuthService{

    private UtilisateurDAO utilisateurDAO;
    public AuthService() {
        utilisateurDAO = new UtilisateurDAO();
    }

    @Override
    public Utilisateur seConnecter(String login, String password) {
        return utilisateurDAO.connecter(login, password);
    }
}
