package com.util;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that helps us to work with multiple connection of databases
 */
public class DatabasesUtil {
    private List<DatabaseUtil> databases = new ArrayList<>();

    public DatabasesUtil(List<DatabaseUtil> databaes) {
        this.databases = databaes;
    }

    public DatabasesUtil() {

    }


    /**
     * Helper for setting up a new database and adding it to databases
     * @param persistName
     */
    public void AddPersist(String persistName) {
        DatabaseUtil db = new DatabaseUtil(persistName);
        db.init();
        this.AddDb(db);
    }


    /**
     * Overload
     * @param persistName
     * @param dbName setting up the dbName
     */
    public void AddPersist(String persistName, String dbName) {
        DatabaseUtil db = new DatabaseUtil(persistName);
        db.setName(dbName);
        db.init();
        this.AddDb(db);
    }

    public void AddDb(DatabaseUtil db) {
        this.databases.add(db);
    }

    public List<DatabaseUtil> getDatabases() {
        return databases;
    }

    /***
     * Search in our connections for a specific database
     * @param dbName is the name of the data base that we looking for
     * @return the database founded in our list of collection otherwise return new con.
     */
    public DatabaseUtil getDatabase(String dbName) {
        for(DatabaseUtil db : this.databases) {
            if(db.getName() == dbName) {
                return db;
            }
        }
        return new DatabaseUtil();
    }

    public void closeAll(){
        for(DatabaseUtil db : this.databases)
            db.close();
    }
    public void setDatabaes(List<DatabaseUtil> databaes) {
        this.databases = databaes;
    }

}
