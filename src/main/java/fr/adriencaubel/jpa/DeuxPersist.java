package fr.adriencaubel.jpa;

import java.util.ArrayList;
import java.util.List;

import fr.adriencaubel.jpa.entities.Cours;
import fr.adriencaubel.jpa.entities.Promotion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * Si on n'utilise pas le CascadeType.ALL, nous devons d'abord perister les cours puis seulement la promotion ensuite
 */
public class DeuxPersist {

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

            // Persister les cours dans la base de données
            em.persist(coursJava);
            em.persist(architectureLogicielle);

            // Créer une liste de cours
            List<Cours> coursList = new ArrayList<>();
            coursList.add(coursJava);
            coursList.add(architectureLogicielle);

            // Créer l'objet Promotion et lui associer les cours
            Promotion promotionINFO3 = new Promotion("INFO3");
            promotionINFO3.setListesCours(coursList);

            // Persister la promotion dans la base de données
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