package com.dao;

import com.model.Expense;

import java.util.List;

public interface ExpenseDAOInterface {

    default Expense create(Expense entity) {
        return null;
    }

    default Expense findById(int id){
        return null;
    }

    default Expense update(Expense entity) {
        return null;
    }

    default void delete(Expense entity) {

    }

    default void deleteAll() {

    }

    default List<Expense> getAll() {
        return null;
    }

    default List<Expense> getEntertainment() {
        return null;
    }

    default List<Expense> getFood() {
        return null;
    }

    default List<Expense> getHome() {
        return null;
    }
}
