package models;

public class Resultat {

    private int idResultat;
    private Athlete athlete;
    private Competition competition;
    private String score;
    private int rang;

    public Resultat() {
    }

    public Resultat(int idResultat, Athlete athlete, Competition competition, String score, int rang) {
        this.idResultat = idResultat;
        this.athlete = athlete;
        this.competition = competition;
        this.score = score;
        this.rang = rang;
    }

    public int getIdResultat() {
        return idResultat;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public Competition getCompetition() {
        return competition;
    }

    public String getScore() {
        return score;
    }

    public int getRang() {
        return rang;
    }

    public void setIdResultat(int idResultat) {
        this.idResultat = idResultat;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    @Override
    public String toString() {
        return "Resultat{" +
                "idResultat=" + idResultat +
                ", athlete=" + athlete +
                ", competition=" + competition +
                ", score='" + score + '\'' +
                ", rang=" + rang +
                '}';
    }
}
