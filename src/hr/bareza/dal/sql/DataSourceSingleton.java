/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.bareza.dal.sql;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javax.sql.DataSource;

/**
 *
 * @author Bareza
 */
public class DataSourceSingleton {
    
    private static final String SERVER_NAME= "MARIN\\SQLEXPRESS";
    private static final String DATABASE_NAME= "Java1";
    private static final String USERNAME= "sas";
    private static final String PASSWORD= "rDVOUli9TXOYA6qsSKMY";

    private static DataSource instance;

    public DataSourceSingleton() {
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = createInstance();
        }
        return instance;
    }
    
    private static DataSource createInstance() {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setDatabaseName(DATABASE_NAME);
        dataSource.setServerName(SERVER_NAME);
        dataSource.setUser(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }
    
}
