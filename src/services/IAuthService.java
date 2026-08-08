package services;
import models.Utilisateur;

public interface IAuthService {

    Utilisateur seConnecter(String login, String password);
}
