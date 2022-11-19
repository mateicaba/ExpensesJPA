package com.util;
import jakarta.persistence.*;

import java.util.function.Consumer;

public class DatabaseUtil {

    private String persistName;
    private String name;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;



    public DatabaseUtil() {

    }

    public DatabaseUtil(String persistName){
        this.persistName = persistName;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public boolean executeTransaction(Consumer<EntityManager> action) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            action.accept(entityManager);
            entityTransaction.commit();
        } catch (RuntimeException e) {
            System.err.println("Transaction error: " + e.getLocalizedMessage());
            entityTransaction.rollback();
            return false;
        }

        return true;
    }

    public boolean init() {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory(this.persistName);
            entityManager = entityManagerFactory.createEntityManager();
        } catch (Exception e) {
            System.err.println("Error at initialing DatabaseManager: " + e.getMessage().toString());
            return false;
        }

        return true;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
