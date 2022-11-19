package com.main;

import com.dao.ExpenseDAOImpl;
import com.model.Expense;
import com.util.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;


import java.util.*;
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
           databases.AddPersist("entertainment","entertainment");
        }
        {
           databases.AddPersist("food","food");
        }

        {
            databases.AddPersist("home","home");
        }

//        ExpenseDAOImpl expenseOperation = new ExpenseDAOImpl(databases);
//        List<Expense> expenses = expenseOperation.getAll();
//        expenseOperation.printExpensesList(expenses);

        MENU(databases);


    }

    private static void MENU(DatabasesUtil databases) {

        System.out.println("Products menu:\n");
        System.out.println(" 1. Show all expenses, ordered descending;");
        System.out.println(" 2. Show average expenses in each category");
        System.out.println(" 3. Add new expense");
        System.out.println(" 4. Exit");

        boolean run = true;
        while (run) {
            Scanner input = new Scanner(System.in);
            System.out.println("\nChoose:");
            int option = input.nextInt();

            switch (option) {

                case 1: {
                    ExpenseUtility utility = new ExpenseUtility();
                    ExpenseDAOImpl expenseOperation = new ExpenseDAOImpl(databases);
                    List<Expense> expenses = expenseOperation.getAll();

                    utility.printExpensesList(utility.ExpensesListDescByValue(expenses));
                }

                case 2: {
                    ExpenseUtility utility = new ExpenseUtility();
                    ExpenseDAOImpl expenseOperation = new ExpenseDAOImpl(databases);
                    List<Expense> expenses = expenseOperation.getAll();

                    utility.printExpensesMap(utility.AveregeGroupedByCategory(expenses));
                    break;
                }

                case 3: {
                    System.out.println("Select the category you want to insert in:\n");
                    System.out.println(" 1. Entertainment expenses;");
                    System.out.println(" 2. Food expenses");
                    System.out.println(" 3. Home expenses");
                    System.out.println(" 4. Back");

                    Scanner input2 = new Scanner(System.in);
                    System.out.println("\nChoose:");
                    int option2 = input.nextInt();

                    switch (option) {

                        case 1: {
                            ExpenseDAOImpl operations = new ExpenseDAOImpl(databases);
                            DatabaseUtil dbE = databases.getDatabase("entertainment");
                            dbE.executeTransaction(entityManager -> entityManager.persist(new Expense("aadjod", 4)));
                        }

                        case 2: {
                            ExpenseDAOImpl operations = new ExpenseDAOImpl(databases);
                            DatabaseUtil dbF = databases.getDatabase("food");
                            dbF.executeTransaction(entityManager -> entityManager.persist(new Expense("aadjod", 5)));
                        }

                        case 3: {
                            DatabaseUtil dbH = databases.getDatabase("home");
                            dbH.executeTransaction(entityManager -> entityManager.persist(new Expense("aadjod", 6)));
                        }
                        case 4: {
                            break;
                        }
                    }
                }
                case 4: {
                    run = false;
                    break;
                }
            }
        }
    }
}