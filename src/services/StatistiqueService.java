package services;
import DAO.StatistiqueDAO;

public class StatistiqueService implements IStatistiqueService {

    private StatistiqueDAO statistiqueDAO;
    public StatistiqueService(){
        statistiqueDAO = new StatistiqueDAO();
    }


    @Override
    public void afficherStatistiques(){

        System.out.println("\n==============================");
        System.out.println("       STATISTIQUES JOJ 2026");
        System.out.println("==============================");

        System.out.println(
                "Nombre de pays : " + statistiqueDAO.nombrePays()
        );

        System.out.println(
                "Nombre d'athlètes : " + statistiqueDAO.nombreAthletes()
        );

        System.out.println(
                "Nombre de disciplines : " + statistiqueDAO.nombreDisciplines()
        );

        System.out.println(
                "Nombre de compétitions : " + statistiqueDAO.nombreCompetitions()
        );

        System.out.println(
                "Nombre de résultats : " + statistiqueDAO.nombreResultats()
        );
    }

}
