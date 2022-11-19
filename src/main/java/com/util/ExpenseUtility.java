package com.util;

import com.model.Expense;
import jakarta.persistence.Query;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExpenseUtility {

    private DatabasesUtil databasesUtil;

    public ExpenseUtility() {

    }

    public void printExpensesList(List<Expense> expenses){
        for(Expense expense : expenses)
            System.out.println(expense);
    }

    public void printExpensesMap( Map<String, Double> map){
        for(String  name : map.keySet()) {
            String key = name;
            String value = map.get(name).toString();
            System.out.println(key + " " + value);
        }
    }
    public List<Expense> ExpensesListDescByValue(List<Expense> expenses) {

        expenses.sort(Comparator.comparingDouble(Expense::getValue).reversed());

        return expenses;
    }

    public  Map<String, Double> AveregeGroupedByCategory(List<Expense> expenses){
        Map<String, Double> averegeGrouped = expenses.stream().collect(Collectors.
                groupingBy(Expense::getCategory, Collectors.averagingDouble(Expense:: getValue)));

        return averegeGrouped;
    }

}
