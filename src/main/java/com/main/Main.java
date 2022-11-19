package com.main;

import com.dao.ExpenseDAOImpl;
import com.model.Expense;
import com.util.*;

import java.util.*;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF); // Sets logging level

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
                    break;
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

                    switch (option2) {

                        case 1: {
                            Expense expense = new Expense();
                            expense.setDescription(input2.nextLine());
                            expense.setValue(input2.nextDouble());
                            ExpenseDAOImpl expenseOperation = new ExpenseDAOImpl(databases);
                            expenseOperation.create(expense,"entertainment");
                            break;
                        }

                        case 2: {
                            Expense expense = new Expense();
                            expense.setDescription(input2.nextLine());
                            expense.setValue(input2.nextDouble());
                            ExpenseDAOImpl expenseOperation = new ExpenseDAOImpl(databases);
                            expenseOperation.create(expense,"food");
                            break;
                        }

                        case 3: {
                            Expense expense = new Expense();
                            expense.setDescription(input2.nextLine());
                            expense.setValue(input2.nextDouble());
                            ExpenseDAOImpl expenseOperation = new ExpenseDAOImpl(databases);
                            expenseOperation.create(expense,"home");
                            break;
                        }
                        case 4: {
                            break;
                        }
                    }
                    break;
                }
                case 4: {
                    databases.closeAll();
                    run = false;
                    break;
                }
            }
        }
    }
}