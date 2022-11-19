package com.util;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that helps us to work with multiple connection of databases
 */
public class DatabasesUtil {
    private List<DatabaseUtil> databaes = new ArrayList<>();

    public DatabasesUtil(List<DatabaseUtil> databaes) {
        this.databaes = databaes;
    }

    public DatabasesUtil() {

    }

    public void AddPersist(String persistName) {
        DatabaseUtil db = new DatabaseUtil(persistName);
        db.init();
        this.AddDb(db);
    }

    public void AddDb(DatabaseUtil db) {
        this.databaes.add(db);
    }

    public List<DatabaseUtil> getDatabases() {
        return databaes;
    }

    /***
     * Search in our connections for a specific database
     * @param dbName is the name of the data base that we looking for
     * @return the database founded in our list of collection otherwise return new con.
     */
    public DatabaseUtil getDatabase(String dbName) {
        for(DatabaseUtil db : this.databaes) {
            if(db.getName() == dbName) {
                return db;
            }
        }
        return new DatabaseUtil();
    }
    public void setDatabaes(List<DatabaseUtil> databaes) {
        this.databaes = databaes;
    }

}
