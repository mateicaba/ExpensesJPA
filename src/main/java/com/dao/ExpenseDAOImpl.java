package com.dao;


import com.model.Expense;
import com.util.DatabaseUtil;
import com.util.DatabasesUtil;
import jakarta.persistence.Query;

import java.util.*;


public class ExpenseDAOImpl implements ExpenseDAOInterface {

    private final DatabasesUtil databasesUtil;

    public ExpenseDAOImpl(DatabasesUtil databases){ this.databasesUtil = databases;}


    @Override
    public Expense findById(int id) {
        return ExpenseDAOInterface.super.findById(id);
    }

    @Override
    public Expense update(Expense entity) {
        return ExpenseDAOInterface.super.update(entity);
    }

    @Override
    public void delete(Expense entity) {
        ExpenseDAOInterface.super.delete(entity);
    }

    @Override
    public void deleteAll() {
        ExpenseDAOInterface.super.deleteAll();
    }

    @Override
    public List<Expense> getAll() {
        List<Expense> expenses = new ArrayList<>();
        for(DatabaseUtil databasesUtil : databasesUtil.getDatabases()){
            Query query = databasesUtil.getEntityManager().createNativeQuery("SELECT * FROM expense;", Expense.class);

            for(Expense expense : (List<Expense>)query.getResultList()){
                expense.setCategory(databasesUtil.getName());
                expenses.add(expense);
            }
        }
        return expenses;
    }
    @Override
    public List<Expense> getEntertainment() {
        return ExpenseDAOInterface.super.getEntertainment();
    }

    @Override
    public List<Expense> getFood() {
        return ExpenseDAOInterface.super.getFood();
    }

    @Override
    public List<Expense> getHome() {
        return ExpenseDAOInterface.super.getHome();
    }

    @Override
    public Expense create(Expense entity){
        return ExpenseDAOInterface.super.create(entity);
    }

    public void create(Expense entity, String db) {
        databasesUtil.getDatabase(db)
                .executeTransaction(entityManager -> entityManager.persist(entity));
    }
}
