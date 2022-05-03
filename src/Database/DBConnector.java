package Database;
/* class to initiate connection to the MYSQL database
*  Once connected to database, use Database.DBReaderWriter to do specific task read and writes to the database
* */


import java.sql.*;
import java.util.Scanner;

public class DBConnector {

        private Connection databaseConnection;
        private String serverName, username, password;

        public DBConnector(String serverName, String user, String password){
            this.serverName = serverName;
            this.username = user;
            this.password = password;

            establishConnection(this.serverName, this.username, this.password);
        }

        // attempt to establish a connection with database
        public void establishConnection(String serverName, String user, String password){
            try {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                databaseConnection = DriverManager.getConnection(serverName, user, password);
                System.out.println(
                        "Connection attempt to server location \"" + this.serverName +
                                "\" for username \"" + this.username +
                                "\" for password \"" + this.password +
                                "\" passed!"
                );
            } catch (SQLException sqlException){
                System.out.println("Unable to establish a connection!");
                System.out.println(sqlException.getMessage());
                promptDatabaseConnection();
            }
        }

        private void promptDatabaseConnection() {
            System.out.println(
                    "Connection attempt to server location \"" + this.serverName +
                            "\" for username \"" + this.username +
                            "\" for password \"" + this.password +
                            "\" failed!\n"
            );

            System.out.println("Please enter [servername] [username] [password]." +
                    " Example \"jdbc:mysql://localhost/team5 root myawesomepassword\" (quotes excluded) \nEnter -1 to quit app");

            Scanner scanner = new Scanner(System.in);

            try {
                this.serverName = scanner.next();

                if (serverName.compareTo("-1") == 0) {
                    System.out.println("EXITING APP");
                    System.exit(-1);
                }

                this.username = scanner.next();
                this.password = scanner.next();

                establishConnection(this.serverName, this.username, this.password);

            } catch (Exception e){
                System.out.println(e.getMessage());
                databaseConnection = null;
            }
        }

    public void closeConnection() {
        try {
            databaseConnection.close();
        } catch (SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }
    }

    // return the connection to the database
    public Connection getConnection(){
        return databaseConnection;
    }

}
