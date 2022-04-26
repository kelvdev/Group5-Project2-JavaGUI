package Database;// Helper class to read and write to the database with ease

import DataObjects.*;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import java.sql.*;
// TODO: implement Database.DBReaderWriter methods to do specific tasks such as addPatient() or updatePatient();
public class DBReaderWriter {

    // USE THIS CONNECTION AS THE DATABASE TO READ/WRITE/DELETE/UPDATE TO
    private Connection connection;

    public DBReaderWriter(DBConnector dbConnector){
        connection = dbConnector.getConnection();
    }

    // TODO: implement a INSERT INTO Patient VALUE(S) using this method. Return true if create successful, else false
    // Patient THC should be NULL as database will allocate a THC for the patient
    // Enrique
    public boolean createPatient(Patient patient)
    {

        return true;
    }

    // TODO: implement a UPDATE Patient to add demographics information using this method. Return true if update successful, else false
    /* NOTE: Only update the sections that correspond to demographics for a patient.
     DO NOT OVERRIDE THE ENTIRE PATIENT INFO IN THE DATABASE
     only select these attributes from the patient to update:
     */
    // Enrique
    public boolean addDemographicsInformation(Patient patient){
        int thc = patient.getTHC();
        return true;
    }

    // TODO: implement a UPDATE Patient using this method. Return true if update successful, else false
    // Robert
    public boolean updatePatient(Patient patient){
        int thc = patient.getTHC();
        return false;
    }

    // TODO: implement a DELETE FROM Patient WHERE using this method. Return true if delete successful, else false
    // Huy
    public boolean deletePatient(int THC)
    {
        String query = "DELETE FROM Patient WHERE THC = ?";
        
        String serverName = "jdbc:mysql://localhost:3306/team5";
        String user = "root";
        String password = "myawesomepassword";

        
        try
        {
            Connection con = DriverManager.getConnection(serverName, user, password);
            //Statement stmt = con.createStatement();
            PreparedStatement prepareStmt = con.prepareStatement(query);

            prepareStmt.setInt(1, THC);

            prepareStmt.execute();
            
            int deleted = prepareStmt.executeUpdate();

            con.close();

            if (deleted == 0) 
            {
                System.out.printf("Nothing to delete!\n");
                return false;
            }
            else
            {
                System.out.printf("%d row(s) deleted", deleted);
                return true;
            }
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        return false;
    }

    // TODO: implement a SELECT FROM Patient WHERE using this method and return a Patient object
    // Huy
    public Patient getPatient(int THC)
    {

        // return a populated patient

        String query = "SELECT * FROM Patient WHERE THC = ?";

        String serverName = "jdbc:mysql://localhost:3306/team5";
        String user = "root";
        String password = "myawesomepassword";

        try 
        {
            Connection con = DriverManager.getConnection(serverName, user, password);
            //Statement stmt = con.createStatement();
            PreparedStatement prepareStmt = con.prepareStatement(query);

            prepareStmt.setInt(1, THC);
            
            ResultSet rs = prepareStmt.executeQuery(query);

            System.out.printf("THC|Country|State|Zip|WStatus|Occupation|Surname|FirstName|SSN|DoB|Insurance|TinBackround|HBackground|tIndComments|hIndComments\n");
            
            while (rs.next()) 
            {
                int thc = rs.getInt("THC");
                int countryID = rs.getInt("Country");
                int stateID = rs.getInt("State");
                int zipID = rs.getInt("ZIP");
                int wStatusID = rs.getInt("WStatus");
                String occupation = rs.getString("Occup");
                String surname = rs.getString("Surname");
                String firstName = rs.getString("First_name");
                String ssn = rs.getString("SSN");
                String dob = rs.getString("DOB");
                String insurance = rs.getString("Insurance");
                String tinBackground = rs.getString("Tin_background");
                String hBackground = rs.getString("H_background");
                String tIndComments = rs.getString("T_Ind_comments");
                String hIndComments = rs.getString("H_Ind_comments");
               
                System.out.printf(thc + "|" + countryID + "|" + stateID + "|" + zipID + "|" + wStatusID + "|" + surname
                                + "|" + firstName + "|" + ssn + "|" + dob + "|" + insurance + "|" + occupation
                                + "|" + tinBackground + "|" + hBackground + "|" + tIndComments + "|" + hIndComments + "\n");
                
                
                Patient patient = new Patient(thc, countryID, stateID, zipID, wStatusID, surname, firstName, ssn, dob, insurance, occupation,
                tinBackground, hBackground, tIndComments, hIndComments);

                return patient;
            }
        } 
        catch(Exception e) 
        {
        System.out.println("SQL exception occured" + e);
        }
    
        return null;
    }

    // TODO: implement a INSERT INTO Visit VALUE(S) using this method. Return true if create successful, else false
    // Enrique
    public boolean createVisit(int THC, String visitComments){
        return false;
    }

    // TODO: implement a DELETE FROM Visit VALUE(S) using this method. Return true if delete successful, else false
    // Huy
    public boolean deleteVisit(int id)
    {
        String query = "DELETE FROM Patient WHERE visitID = ?";
        
        String serverName = "jdbc:mysql://localhost:3306/team5";
        String user = "root";
        String password = "myawesomepassword";

        try
        {
            Connection con = DriverManager.getConnection(serverName, user, password);
            //Statement stmt = con.createStatement();
            PreparedStatement prepareStmt = con.prepareStatement(query);

            prepareStmt.setInt(1, id);

            prepareStmt.execute();
            
            int deleted = prepareStmt.executeUpdate();

            con.close();

            if (deleted == 0) 
            {
                System.out.printf("Nothing to delete!\n");
                return false;
            }
            else
            {
                System.out.printf("%d row(s) deleted", deleted);
                return true;
            }
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return false;
    }

    // TODO: implement a SELECT FROM Visit WHERE visitID (parameter) = visitID (database value) using this method and return a Visit object
    // Rob
    public Visit getVisit(int visitID){

        // return a populated visit
        Visit visit;

        return null;
    }

    // TODO: implement an SQL call using the SQL visitorsCount function and return a visit count
    // Huy
    public boolean getAllPatientVisitsOnDate(String date)
    {
        // return visit count
        String query = "DELETE FROM Patient WHERE visitID = ?";
        
        String serverName = "jdbc:mysql://localhost:3306/team5";
        String user = "root";
        String password = "myawesomepassword";


        try 
        {
            Connection con = DriverManager.getConnection(serverName, user, password);

            CallableStatement statement = con.prepareCall("{? = call team5.VisitorsCount(?)}");

            statement.registerOutParameter(1, Types.INTEGER);
            statement.setString(2, date);
            statement.execute();

            System.out.print("Number of visitors on " + date + ": " + statement.getInt(1));
        }
        catch(Exception e) 
        {
            System.out.println("SQL exception occured" + e);
        }
        return false;
    }

    // TODO: implement a INSERT INTO THI VALUE(S) using this method. Return true if create successful, else false
    // Enrique
    public boolean createPatientTHI(THI thi){
        return false;
    }

    // TODO: implement a UPDATE THI SET WHERE using this method. Return true if update successful, else false
    // Rob
    public boolean updatePatientTHI(int VisitID){
        return false;
    }

    // TODO: implement a DELETE FROM THI VALUE(S) using this method. Return true if delete successful, else false
    // Huy
    public boolean deletePatientTHI(int VisitID)
    {
        String query = "DELETE FROM Patient WHERE visitID = ?";
        
        String serverName = "jdbc:mysql://localhost:3306/team5";
        String user = "root";
        String password = "myawesomepassword";

        try
        {
            Connection con = DriverManager.getConnection(serverName, user, password);
            //Statement stmt = con.createStatement();
            PreparedStatement prepareStmt = con.prepareStatement(query);

            prepareStmt.setInt(1, VisitID);

            prepareStmt.execute();
            
            int deleted = prepareStmt.executeUpdate();

            con.close();

            if (deleted == 0) 
            {
                System.out.printf("Nothing to delete!\n");
                return false;
            }
            else
            {
                System.out.printf("%d row(s) deleted", deleted);
                return true;
            }
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return false;
    }

    // TODO: implement a SELECT FROM THI WHERE visitID (parameter) = visitID (database value) using this method and return a THI object
    // Huy
    public THI getTHI(int visitIDKey)
    {

        // return a populated visit
        
        String query = "SELECT * FROM THI WHERE visitID = ?";

        String serverName = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "myawesomepassword";

        try 
        {
            Connection con = DriverManager.getConnection(serverName, user, password);
            //Statement stmt = con.createStatement();
            PreparedStatement prepareStmt = con.prepareStatement(query);

            prepareStmt.setInt(1, visitIDKey);

            ResultSet rs = prepareStmt.executeQuery(query);

            System.out.printf("VisitID, Sc_T, Sc_F, Sc_E, Sc_C, F1, F2, E3, F4," +
                                " C5, E6, F7, C8, F9, E10, C11, F12, F13, E14, F15, E16," +
                                " E17, F18, C19, F20, E21, E22, C23, F24, E25\n");
            
            while (rs.next()) 
            {
                int visitID = rs.getInt("Visit_ID");
                int Sc_T = rs.getInt("Sc_T");
                int Sc_F = rs.getInt("Sc_F");
                int Sc_E = rs.getInt("Sc_E");
                int Sc_C = rs.getInt("Sc_C");
                int F1 = rs.getInt("F1");
                int F2 = rs.getInt("F2");
                int E3 = rs.getInt("E3");
                int F4 = rs.getInt("F4");
                int C5 = rs.getInt("C5");
                int E6 = rs.getInt("E6");
                int F7 = rs.getInt("F7");
                int C8 = rs.getInt("C8");
                int F9 = rs.getInt("F9");
                int E10 = rs.getInt("E10");
                int C11 = rs.getInt("C11");
                int F12 = rs.getInt("F12");
                int F13 = rs.getInt("F13");
                int E14 = rs.getInt("E14");
                int F15 = rs.getInt("F15");
                int E16 = rs.getInt("E16");
                int E17 = rs.getInt("E17");
                int F18 = rs.getInt("F18");
                int C19 = rs.getInt("C19");
                int F20 = rs.getInt("F20");
                int E21 = rs.getInt("E21");
                int E22 = rs.getInt("E22");
                int C23 = rs.getInt("C23");
                int F24 = rs.getInt("F24");
                int E25 = rs.getInt("E25");
               
                System.out.printf(visitID + "|" + Sc_T + "|" + Sc_F + "|" + Sc_E + "|" + Sc_C + "|" + F1 + "|" + F2 + "|" + E3 + "|" + F4 + "|" +
                                C5 + "|" + E6 + "|" + F7 + "|" + C8 + "|" + F9 + "|" + E10 + "|" + C11 + "|" + F12 + "|" + F13 + "|" + E14 + "|" + F15 + "|" + E16 + "|" +
                                E17 + "|" + F18 + "|" + C19 + "|" + F20 + "|" + E21 + "|" + E22 + "|" + C23 + "|" + F24 + "|" + E25 + "\n");
                
                THI _THI = new THI(visitID, Sc_T, Sc_F, Sc_E, Sc_C, F1, F2, E3, F4,
                                C5, E6, F7, C8, F9, E10, C11, F12, F13, E14, F15, E16,
                                E17, F18, C19, F20, E21, E22, C23, F24, E25);

                return _THI;
            }
        } 
        catch(Exception e) 
        {
            System.out.println("SQL exception occured" + e);
        }

        return null;
    }

    // TODO: implement an SQL call using the SQL THIScore procedure and return a THI determination result string
    // Huy
    public String getResultTHI(int visitorID)
    {
        String serverName = "jdbc:mysql://localhost:3306/team5";
        String user = "root";
        String password = "myawesomepassword";

        try 
        {
            Connection con = DriverManager.getConnection(serverName, user, password);
            CallableStatement statement = con.prepareCall("{call team5.THIScore(?, ?, ?)}");

            statement.registerOutParameter(1, Types.INTEGER);
            statement.registerOutParameter(2, Types.INTEGER);
            statement.registerOutParameter(3, Types.VARCHAR);
 
            statement.setInt(1, visitorID);
 
            statement.execute();

            int id = statement.getInt(1);
            int score = statement.getInt(2);
            String desc = statement.getString(3);

            System.out.printf("VisitorID|Score|ScoreDesc\n");
            System.out.printf(id + "|" + score + "|" + desc + "\n");

            return desc;
        }
        catch(Exception e) 
        {
            System.out.println("SQL exception occured" + e);
        }
        
        return "";
    }

    /* TODO: implement a 3 table join which will return the following values in order in an ArrayList
    *         > (int) patientTHC
    *         > (String) patient full name
    *         > (String) patient dob
    *         > (String) patient insurance
    *         > (String) patient address
    *         > (int) patient THI score (set this value to -1 if THI has not been completed)
    *         > (int) patient visit count
     * */

    // Kelvin
    public ArrayList<Object> getPatientFullInformation(int THC){
        return null;
    }
}
