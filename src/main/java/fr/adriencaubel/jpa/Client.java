package fr.adriencaubel.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Client {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");     
		EntityManager entityManager = entityManagerFactory.createEntityManager();   
		EntityTransaction transaction = entityManager.getTransaction();
	    try {
	        transaction.begin();
	        transaction.commit();
	    }
	    catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        throw e;
	    }
	    finally {
	        entityManager.close();
	    }
	}
}
