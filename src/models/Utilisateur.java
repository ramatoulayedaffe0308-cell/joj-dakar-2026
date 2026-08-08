package models;

public class Utilisateur {

    private int idUtilisateur;
    private String nomComplet;
    private String login;
    private String password;
    private String role;

    public Utilisateur() {}

    public Utilisateur(int idUtilisateur, String nomComplet, String login, String password, String role) {
        this.idUtilisateur = idUtilisateur;
        this.nomComplet = nomComplet;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }
    public String getNomComplet() {
        return nomComplet;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public String getRole() {
        return role;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "idUtilisateur=" + idUtilisateur +
                ", nomComplet='" + nomComplet + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
