package com.main;

import com.dao.ExpenseDAOImpl;
import com.model.Expense;
import com.util.DatabaseUtil;
import com.util.DatabasesUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class Main {

    static Scanner input = new Scanner(System.in);

    public static EntityManager createEm(String unitName) {
        return Persistence.createEntityManagerFactory(unitName).createEntityManager();
    }

    public static void main(String[] args) {

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF); // Sets logging level

        System.out.println("Entertainment connection: " + createEm("entertainment").isOpen());
        System.out.println("Home connection: " + createEm("home").isOpen());
        System.out.println("Food connection: " + createEm("food").isOpen());




//        dbE.init();
//        dbH.init();
//        dbF.init();
//        Expense expE = new Expense("aadjod", 4);
//        Expense expH = new Expense("aadjod", 5);
//        Expense expF= new Expense("aadjod", 6);
//
//        dbE.executeTransaction(entityManager -> entityManager.persist(expE));
//        dbF.executeTransaction(entityManager -> entityManager.persist(expF));
//        dbH.executeTransaction(entityManager -> entityManager.persist(expH));

        DatabasesUtil databases = new DatabasesUtil();
        {
            DatabaseUtil dbE = new DatabaseUtil("entertainment");
            dbE.init();
            databases.AddDb(dbE);
        }

        {
            DatabaseUtil dbF = new DatabaseUtil("food");
            dbF.init();
            databases.AddDb(dbF);
        }

        {
            DatabaseUtil dbH = new DatabaseUtil("home");
            dbH.init();
            databases.AddDb(dbH);
        }

        ExpenseDAOImpl expenseOperation = new ExpenseDAOImpl(databases);
        List<Expense> expenses = expenseOperation.getAll();
        expenseOperation.printExpensesList(expenses);



    }
}