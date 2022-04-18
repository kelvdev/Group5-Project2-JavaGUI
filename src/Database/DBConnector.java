package Database;/* class to initiate connection to the MYSQL database
*  Once connected to database, use Database.DBReaderWriter to do specific task read and writes to the database
* */

    // TODO: IMPLEMENT Database.DBConnector methods to connect to MYSql database and return a connection status
    public class DBConnector {

        String serverName, user, password;

        // TODO: Establish connection in the constructor
        public DBConnector(String serverName, String user, String password){
            this.serverName = serverName;
            this.user = user;
            this.password = password;
        }



        // TODO: create a return method to get the current database that the application is connected to
        public void getDatabase(){

        }

}
