package Database;
/* class to initiate connection to the MYSQL database
*  Once connected to database, use Database.DBReaderWriter to do specific task read and writes to the database
* */


import java.sql.*;

public class DBConnector {

        private Connection databaseConnection;

        public DBConnector(String serverName, String user, String password){
            try {
                //DriverManager.registerDriver(new );
                databaseConnection = DriverManager.getConnection(serverName, user, password);
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            } catch (SQLException sqlException){
                System.out.println("Unable to establish a connection!");
                System.out.println(sqlException.getMessage());
                databaseConnection = null;
            }
        }

        // return the connection to the database
        public Connection getConnection(){
            return databaseConnection;
        }

        public void closeConnection() {
            try {
                databaseConnection.close();
            } catch (SQLException sqlE){
                System.out.println(sqlE.getMessage());
            }
        }

}
