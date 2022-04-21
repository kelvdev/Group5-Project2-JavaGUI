package Database;// Helper class to read and write to the database with ease

import DataObjects.*;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

// TODO: implement Database.DBReaderWriter methods to do specific tasks such as addPatient() or updatePatient();
public class DBReaderWriter {

    // TODO: ensure that DBReaderWriter is connected to the database using DBConnector
    // Kelvin
    public DBReaderWriter(DBConnector dbConnector){

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
        
        String serverName = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "myawesomepassword";

        try
        {
            DBConnector con = new DBConnector(serverName, user, password);
            //Statement stmt = con.createStatement();
            PreparedStatement prepareDeletePatient = DBConnector.prepareStatement(query);

            prepareDeletePatient.setInt(1, THC);

            prepareDeletePatient.execute();
            
            int deleted = PreparedStatement.executeUpdate();

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

        String serverName = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "myawesomepassword";

        try 
        {
            DBConnector con = new DBConnector(serverName, user, password);
            //Statement stmt = con.createStatement();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.printf("THC|CountryID|StateID|ZipID|WStatusID|Occupation|Surname|FirstName|SSN|DoB|Insurance|TinBackround|HBackground|tIndComments|hIndComments\n");
            
            while (rs.next()) 
            {
                int thc = rs.getInt("thc");
                int countryID = rs.getInt("countryID");
                int stateID = rs.getString("stateID");
                int zipID = rs.getInt("zipID");
                int wStatusID = rs.getInt("wStatusID");
                String surname = rs.getString("surname");
                String firstName = rs.getString("firstName");
                String ssn = rs.getString("ssn");
                String dob = rs.getString("dob");
                String insurance = rs.getString("insurance");
                String occupation = rs.getString("occupation");
                String tinBackground = rs.getString("tinBackground");
                String hBackground = rs.getString("hBackground");
                String tIndComments = rs.getString("tIndComments");
                String hIndComments = rs.getString("hIndComments");
               
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
        
        String serverName = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "myawesomepassword";

        try
        {
            DBConnector con = new DBConnector(serverName, user, password);
            //Statement stmt = con.createStatement();
            PreparedStatement prepareDeletePatient = DBConnector.prepareStatement(query);

            prepareDeletePatient.setInt(1, id);

            prepareDeletePatient.execute();
            
            int deleted = PreparedStatement.executeUpdate();

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
        
        String serverName = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "myawesomepassword";

        try
        {
            DBConnector con = new DBConnector(serverName, user, password);
            //Statement stmt = con.createStatement();
            PreparedStatement prepareDeletePatient = DBConnector.prepareStatement(query);

            prepareDeletePatient.setInt(1, VisitID);

            prepareDeletePatient.execute();
            
            int deleted = PreparedStatement.executeUpdate();

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
    public THI getTHI(int visitID){

        // return a populated visit
        
        String query = "SELECT * FROM THI WHERE visitID = ?";

        String serverName = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "myawesomepassword";

        try 
        {
            DBConnector con = new DBConnector(serverName, user, password);
            //Statement stmt = con.createStatement();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.printf("VisitID, Sc_T, Sc_F, Sc_E, Sc_C, F1, F2, E3, F4,
                                C5, E6, F7, C8, F9, E10, C11, F12, F13, E14, F15, E16,
                                E17, F18, C19, F20, E21, E22, C23, F24, E25\n");
            
            while (rs.next()) 
            {
                int thc = rs.getInt("thc");
                int visitID = rs.getInt("visitID");
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
        String serverName = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "myawesomepassword";

        try 
        {
            DBConnector con = new DBConnector(serverName, user, password);
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
