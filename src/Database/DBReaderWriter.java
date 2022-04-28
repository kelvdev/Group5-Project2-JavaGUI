package Database;// Helper class to read and write to the database with ease

import DataObjects.*;

import java.sql.*;
import java.util.ArrayList;

public class DBReaderWriter {

    // USE THIS CONNECTION AS THE DATABASE TO READ/WRITE/DELETE/UPDATE TO
    private Connection connection;

    public DBReaderWriter(DBConnector dbConnector){
        connection = dbConnector.getConnection();
    }

    public Connection getConnection(){
        return connection;
    }

    // TODO: implement a INSERT INTO Patient VALUE(S) using this method. Return THC of newly created patient
    // Patient THC should be NULL as database will allocate a THC for the patient
    // Enrique
    public int createPatient(Patient patient)
    {
        String query = "INSERT INTO" +
                " Patient (Country, State, ZIP, WStatus, Occup, Surname, First_name, SSN, DOB," +
                " Insurance, Tin_background, H_background, T_Ind_comments, H_Ind_comments)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement prepareStmt = this.connection.prepareStatement(query);

            prepareStmt.setString(1, patient.getCountryID());
            prepareStmt.setString(2, patient.getStateID());
            prepareStmt.setInt(3, patient.getZipID());
            prepareStmt.setInt(4, patient.getWStatusID());
            prepareStmt.setString(5, patient.getOccupation());
            prepareStmt.setString(6, patient.getSurname());
            prepareStmt.setString(7, patient.getFirstName());
            prepareStmt.setString(8, patient.getSSN());
            prepareStmt.setString(9, patient.getDob());
            prepareStmt.setString(10, patient.getInsurance());
            prepareStmt.setString(11, patient.getTinBackground());
            prepareStmt.setString(12, patient.getHBackground());
            prepareStmt.setString(13, patient.getTIndComments());
            prepareStmt.setString(14, patient.getHIndComments());

            prepareStmt.executeUpdate();

            return getMaxTHC();
        }
        catch(SQLException e)
        {
            System.out.println("SQL exception occured" + e.getSQLState());
            System.out.println(e.getMessage());
        }

        return -1;

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

    // DELETE FROM Patient WHERE using this method. Return true if delete successful, else false
    // Huy
    public boolean deletePatient(int THC)
    {
        String query = "DELETE FROM Patient WHERE THC = ?";

        try
        {
            //Statement stmt = con.createStatement();
            PreparedStatement prepareStmt = this.connection.prepareStatement(query);

            prepareStmt.setInt(1, THC);

            prepareStmt.execute();

            int deleted = prepareStmt.executeUpdate();

            this.connection.close();

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

    // SELECT FROM Patient WHERE using this method and return a Patient object
    // Huy
    public Patient getPatient(int THC)
    {
        // return a populated patient

        String query = "SELECT * FROM Patient WHERE THC = ?";

        try
        {
            //Statement stmt = con.createStatement();
            PreparedStatement prepareStmt = this.connection.prepareStatement(query);

            prepareStmt.setInt(1, THC);

            ResultSet rs = prepareStmt.executeQuery();

            System.out.printf("THC|CountryID|StateID|ZipID|WStatusID|Occupation|Surname|FirstName|SSN|DoB|Insurance|TinBackround|HBackground|tIndComments|hIndComments\n");

            while (rs.next())
            {
                int thc = rs.getInt("thc");
                String countryID = rs.getString("country");
                String stateID = rs.getString("state");
                int zipID = rs.getInt("zip");
                int wStatusID = rs.getInt("wStatus");
                String surname = rs.getString("surname");
                String firstName = rs.getString("first_Name");
                String ssn = rs.getString("ssn");
                String dob = rs.getString("dob");
                String insurance = rs.getString("insurance");
                String occupation = rs.getString("occup");
                String tinBackground = rs.getString("tin_background");
                String hBackground = rs.getString("h_background");
                String tIndComments = rs.getString("t_ind_comments");
                String hIndComments = rs.getString("h_ind_comments");

                System.out.printf(thc + "|" + countryID + "|" + stateID + "|" + zipID + "|" + wStatusID + "|" + surname
                        + "|" + firstName + "|" + ssn + "|" + dob + "|" + insurance + "|" + occupation
                        + "|" + tinBackground + "|" + hBackground + "|" + tIndComments + "|" + hIndComments + "\n");


                Patient patient = new Patient(thc, countryID, stateID, zipID, wStatusID, surname, firstName, ssn, dob, insurance, occupation,
                        tinBackground, hBackground, tIndComments, hIndComments);

                return patient;
            }
        }
        catch(SQLException e)
        {
            System.out.println("SQL exception occured" + e.getSQLState());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

        return null;
    }

    // TODO: implement a INSERT INTO Visit VALUE(S) using this method. Return visit ID of the newly created visit
    // Enrique
    public int createVisit(int THC, String visitComments){
        return 0;
    }

    // DELETE FROM Visit VALUE(S) using this method. Return true if delete successful, else false
    // Huy
    public boolean deleteVisit(int id)
    {
        String query = "DELETE FROM Patient WHERE visitID = ?";

        try
        {
            //Statement stmt = con.createStatement();
            PreparedStatement prepareStmt = this.connection.prepareStatement(query);

            prepareStmt.setInt(1, id);

            prepareStmt.execute();

            int deleted = prepareStmt.executeUpdate();

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
    public Visit getVisit(int visitID) {

        // return a populated visit
        Visit visit;

        return null;
    }

    // TODO: implement an SQL call using the SQL visitorsCount function and return a visit count
    // Huy
    public boolean getAllPatientVisitsOnDate(String date){
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

    // DELETE FROM THI VALUE(S) using this method. Return true if delete successful, else false
    // Huy
    public boolean deletePatientTHI(int VisitID)
    {
        String query = "DELETE FROM Patient WHERE visitID = ?";

        try
        {
            //Statement stmt = con.createStatement();
            PreparedStatement prepareStmt = this.connection.prepareStatement(query);

            prepareStmt.setInt(1, VisitID);

            prepareStmt.execute();

            int deleted = prepareStmt.executeUpdate();

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

    // SELECT FROM THI WHERE visitID (parameter) = visitID (database value) using this method and return a THI object
    // Huy
    public THI getTHI(int visitIDKey){

        // return a populated visit

        String query = "SELECT * FROM THI WHERE visitID = ?";

        try
        {
            Connection con = this.connection;
            //Statement stmt = con.createStatement();
            PreparedStatement prepareStmt = con.prepareStatement(query);

            prepareStmt.setInt(1, visitIDKey);

            ResultSet rs = prepareStmt.executeQuery();

            System.out.printf("VisitID, Sc_T, Sc_F, Sc_E, Sc_C, F1, F2, E3, F4," +
                    " C5, E6, F7, C8, F9, E10, C11, F12, F13, E14, F15, E16," +
                    " E17, F18, C19, F20, E21, E22, C23, F24, E25\n");

            while (rs.next())
            {
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

    // SQL call using the SQL THIScore procedure and return a THI determination result string
    // Huy
    public String getResultTHI(int visitorID)
    {

        try
        {
            CallableStatement statement = this.connection.prepareCall("{call team5.THIScore(?, ?, ?)}");

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
     *         > (String) patient name
     *         > (String) patient dob
     *         > (String) patient insurance
     *         > (int) patient THI score (set this value to -1 if THI has not been completed)
     *         > (int) patient visit count
     * */

    // Kelvin
    public Object[][] getAllPatientsFullInformation(){

        ArrayList<Object[]> patientFullInfoList = new ArrayList<>();

        try {
            PreparedStatement fullInformationStatement = this.connection.prepareStatement("SELECT" +
                            " THC, Patient.first_Name, Patient.surname, Patient.dob, Patient.insurance\n" +
                            "FROM Patient;");

            ResultSet rs = fullInformationStatement.executeQuery();

            while(rs.next()){

                System.out.println(rs.getInt("THC") + " " +
                        rs.getString("first_Name") + " " +
                        rs.getString("surname") + " " +
                        rs.getString("dob") + " " +
                        rs.getString("insurance"));

                patientFullInfoList.add(new Object[]{
                        rs.getInt("THC"),
                        rs.getString("first_Name") + " " + rs.getString("surname"),
                        rs.getString("dob"),
                        rs.getString("insurance")
                });
            }

            Object[][] patientFinalArray = new Object[patientFullInfoList.size()][4];

            for(int i = 0; i < patientFinalArray.length; i++){
                patientFinalArray[i] = patientFullInfoList.get(i);
            }

            return patientFinalArray;
        } catch (SQLException sqlE){
            return null;
        }
    }

    // Kelvin

    public int getMaxTHC(){

        try{
            PreparedStatement getMaxTHCStatement = connection.prepareStatement("SELECT MAX(THC) FROM Patient");
            ResultSet resultSet = getMaxTHCStatement.executeQuery();

            resultSet.next();
            return resultSet.getInt("MAX(THC)");
        }catch (SQLException sqlE){
            System.out.println("GET MAX THC FAILED " + sqlE.getMessage());
            sqlE.printStackTrace();
        }

        return -1;
    }


}