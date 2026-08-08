package models;
import java.time.LocalDate;


public class Competition {

    private int idCompetition;
    private String nomCompetition;
    private LocalDate dateCompetition;
    private String lieu;
    private Discipline discipline;

    public Competition() {
    }

    public Competition(int idCompetition, String nomCompetition, LocalDate dateCompetition, String lieu, Discipline discipline) {
        this.idCompetition = idCompetition;
        this.nomCompetition = nomCompetition;
        this.dateCompetition = dateCompetition;
        this.lieu = lieu;
        this.discipline = discipline;
    }

    public int getIdCompetition() {
        return idCompetition;
    }

    public String getNomCompetition() {
        return nomCompetition;
    }

    public LocalDate getDateCompetition() {
        return dateCompetition;
    }

    public String getLieu() {
        return lieu;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setIdCompetition(int idCompetition) {
        this.idCompetition = idCompetition;
    }

    public void setNomCompetition(String nomCompetition) {
        this.nomCompetition = nomCompetition;
    }

    public void setDateCompetition(LocalDate dateCompetition) {
        this.dateCompetition = dateCompetition;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "idCompetition=" + idCompetition +
                ", nomCompetition='" + nomCompetition + '\'' +
                ", dateCompetition=" + dateCompetition +
                ", lieu='" + lieu + '\'' +
                ", discipline=" + discipline +
                '}';
    }
}
