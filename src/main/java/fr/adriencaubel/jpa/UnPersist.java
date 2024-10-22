package fr.adriencaubel.jpa;

import java.util.ArrayList;
import java.util.List;

import fr.adriencaubel.jpa.entities.Cours;
import fr.adriencaubel.jpa.entities.Promotion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class UnPersist {

    public static void main(String[] args) {
        // Créer une instance de l'EntityManagerFactory avec l'unité de persistance configurée
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager em = emf.createEntityManager();

        // Créer une transaction
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            // Créer les objets Cours
            Cours coursJava = new Cours("Java", 40, "Programmation Java avancée");
            Cours architectureLogicielle = new Cours("Architecture Logicielle", 45, "Principes de l'architecture logicielle");
            Cours uml = new Cours("UML", 130, "UML Design");

            
            // Créer une liste de cours
            List<Cours> coursList = new ArrayList<>();
            coursList.add(coursJava);
            coursList.add(architectureLogicielle);
            coursList.add(uml);


            // Créer l'objet Promotion et lui associer les cours
            Promotion promotionINFO3 = new Promotion("INFO3");
            promotionINFO3.setListesCours(coursList);

            // Persister la promotion (les cours seront persistés automatiquement grâce à CascadeType.PERSIST)
            em.persist(promotionINFO3);

            // Valider la transaction
            transaction.commit();
            System.out.println("Promotion et cours créés avec succès !");
            
        } catch (Exception e) {
            // Si une erreur survient, annuler la transaction
            transaction.rollback();
            e.printStackTrace();
        } finally {
            // Fermer l'EntityManager
            em.close();
            emf.close();
        }
    }
}
