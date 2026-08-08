package models;

public class Pays {

    private int idPays;
    private String nomPays;
    private String continent;

    public Pays() {}

    public Pays(int idPays, String nomPays, String continent) {
        this.idPays = idPays;
        this.nomPays = nomPays;
        this.continent = continent;
    }

    public int getIdPays() {
        return idPays;
    }

    public String getNomPays() {
        return nomPays;
    }

    public String getContinent() {
        return continent;
    }

    public void setIdPays(int idPays) {
        this.idPays = idPays;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    @Override
    public String toString() {
        return "Pays{" +
                "idPays=" + idPays +
                ", nomPays='" + nomPays + '\'' +
                ", continent='" + continent + '\'' +
                '}';
    }
}

