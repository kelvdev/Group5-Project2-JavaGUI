package Database;// Helper class to read and write to the database with ease

import DataObjects.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DBReaderWriter {

    // USE THIS CONNECTION AS THE DATABASE TO READ/WRITE/DELETE/UPDATE TO
    private Connection connection;

    public DBReaderWriter(DBConnector dbConnector) {
        connection = dbConnector.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    // INSERT INTO Patient VALUE(S) using this method. Return THC of newly created patient
    // Patient THC should be NULL as database will allocate a THC for the patient
    // Enrique
    public int createPatient(Patient patient) {
        String query = "INSERT INTO" +
                " Patient (Country, State, ZIP, WStatus, Occup, Surname, First_name, SSN, DOB," +
                " Insurance, T_Ind_comments, H_Ind_comments)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            prepareStmt.setString(11, patient.getTIndComments());
            prepareStmt.setString(12, patient.getHIndComments());

            prepareStmt.executeUpdate();

            return getMaxTHC();
        } catch (SQLException e) {
            System.out.println("SQL exception occured" + e.getSQLState());
            System.out.println(e.getMessage());
        }

        return -1;

    }

    // UPDATE Patient to add demographics information using this method. Return true if update successful, else false
    /* NOTE: Only update the sections that correspond to demographics for a patient.
     DO NOT OVERRIDE THE ENTIRE PATIENT INFO IN THE DATABASE
     only select these attributes from the patient to update:
     */
    // Enrique
    public boolean addDemographicsInformation(Patient patient) {
        int thc = patient.getTHC();

        String query = "UPDATE Patient" +
                " SET occup = ?, WStatus = ?, T_Ind_comments = ?, H_Ind_comments = ?" +
                " WHERE THC = ?";

        try {
            PreparedStatement prepareStmt = this.connection.prepareStatement(query);

            System.out.println(patient.getOccupation() +
                    patient.getWStatusID() +
                    patient.getTIndComments() +
                    patient.getHIndComments() +
                    patient.getTHC());

            prepareStmt.setString(1, patient.getOccupation());
            prepareStmt.setInt(2, patient.getWStatusID());
            prepareStmt.setString(3, patient.getTIndComments());
            prepareStmt.setString(4, patient.getHIndComments());
            prepareStmt.setInt(5, thc);

            prepareStmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("SQL exception occured" + e.getSQLState());
            System.out.println(e.getMessage());
        }

        return false;

    }

    // UPDATE Patient using this method. Return true if update successful, else false
    // Robert
    public boolean updatePatient(Patient patient) {
        int thc = patient.getTHC();
        return false;
    }

    // DELETE FROM Patient WHERE using this method. Return true if delete successful, else false
    // Huy
    public boolean deletePatient(int THC) {
        String query = "DELETE FROM Patient WHERE THC = ?";

        try {
            //Statement stmt = con.createStatement();
            PreparedStatement prepareStmt = this.connection.prepareStatement(query);

            prepareStmt.setInt(1, THC);

            int deleted = prepareStmt.executeUpdate();

            //this.connection.close(); Don't know if we need to

            if (deleted == 0) {
                System.out.printf("Nothing to delete!\n");
                return false;
            } else {
                System.out.printf("%d row(s) deleted", deleted);
                return true;
            }
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        return false;
    }

    // SELECT FROM Patient WHERE using this method and return a Patient object
    // Huy
    public Patient getPatient(int THC) {
        // return a populated patient
        String query = "SELECT * FROM Patient WHERE THC = ?";
        try {
            //Statement stmt = con.createStatement();
            PreparedStatement prepareStmt = this.connection.prepareStatement(query);

            prepareStmt.setInt(1, THC);

            ResultSet rs = prepareStmt.executeQuery();

            System.out.printf("THC|CountryID|StateID|ZipID|WStatusID|Occupation|Surname|FirstName|SSN|DoB|Insurance|TinBackround|HBackground|tIndComments|hIndComments\n");

            while (rs.next()) {
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
                String tinBackground = "";
                String hBackground = "";
                String tIndComments = rs.getString("t_ind_comments");
                String hIndComments = rs.getString("h_ind_comments");

                System.out.printf(thc + "|" + countryID + "|" + stateID + "|" + zipID + "|" + wStatusID + "|" + surname
                        + "|" + firstName + "|" + ssn + "|" + dob + "|" + insurance + "|" + occupation
                        + "|" + tinBackground + "|" + hBackground + "|" + tIndComments + "|" + hIndComments + "\n");
                Patient patient = new Patient(thc, countryID, stateID, zipID, wStatusID, surname, firstName, ssn, dob, insurance, occupation,
                        tinBackground, hBackground, tIndComments, hIndComments);
                return patient;
            }
        } catch (SQLException e) {
            System.out.println("SQL exception occured" + e.getSQLState());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    // INSERT INTO Visit VALUE(S) using this method. Return visit ID of the newly created visit
    // Enrique
    public int createVisit(int THC, String visitComments) {

        LocalDateTime dateTime = LocalDateTime.now();
        String dateString = dateTime.getYear() + "-" + dateTime.getMonthValue() + "-" + dateTime.getDayOfMonth();
        String query = "INSERT INTO" + " Visit (THC, Visit_nr, Date, Comments)" + " VALUES (?, 0, ?, ?)";

        try {
            PreparedStatement prepareStmt = this.connection.prepareStatement(query);

            prepareStmt.setInt(1, THC);
            prepareStmt.setString(2, dateString);
            prepareStmt.setString(3, visitComments);

            prepareStmt.executeUpdate();

            System.out.println("Create visit passed execUpdate");

            return getMaxVisitID();
        } catch (SQLException e) {
            System.out.println("SQL exception occured" + e.getSQLState());
            System.out.println(e.getMessage());
        }

        return 1;
    }

    // DELETE FROM Visit VALUE(S) using this method. Return true if delete successful, else false
    // Huy
    public boolean deleteVisit(int id) {
        String query = "DELETE FROM Patient WHERE visitID = ?";

        try {
            //Statement stmt = con.createStatement();
            PreparedStatement prepareStmt = this.connection.prepareStatement(query);

            prepareStmt.setInt(1, id);

            prepareStmt.execute();

            int deleted = prepareStmt.executeUpdate();

            if (deleted == 0) {
                System.out.printf("Nothing to delete!\n");
                return false;
            } else {
                System.out.printf("%d row(s) deleted", deleted);
                return true;
            }
            //this.connection.close(); Don't know if we need to

        } catch (Exception e) {
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

    // SQL call using the SQL visitorsCount function and return a visit count
    // Huy
    public int getAllPatientVisitsOnDate(String date) {
        // return visit count
        try {
            CallableStatement statement = this.connection.prepareCall("{? = call team5.VisitorsCount(?)}");

            statement.registerOutParameter(1, Types.INTEGER);
            statement.setString(2, date);
            statement.execute();

            System.out.print("Number of visitors on " + date + ": " + statement.getInt(1));
            //this.connection.close(); Don't know if we need to

            return statement.getInt(1);
        } catch (Exception e) {
            System.out.println("SQL exception occured" + e);
        }
        return -1;
    }

    // INSERT INTO THI VALUE(S) using this method. Return true if create successful, else false
    // Enrique
    public boolean createPatientTHI(THI thi) {

        String query = "INSERT INTO" +
                " THI (Visit_ID, SC_T, Sc_F, Sc_E, Sc_C, F1, F2, E3, F4, C5, E6, F7, C8, F9, E10," +
                " C11, F12, F13, E14, F15, E16, E17, F18, C19, F20, E21, E22, C23, F24, E25)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement prepareStmt = this.connection.prepareStatement(query);

            prepareStmt.setInt(1, thi.getVisitID());
            prepareStmt.setInt(2, thi.getSc_T());
            prepareStmt.setInt(3, thi.getSc_F());
            prepareStmt.setInt(4, thi.getSc_E());
            prepareStmt.setInt(5, thi.getSc_C());
            prepareStmt.setInt(6, thi.getF1());
            prepareStmt.setInt(7, thi.getF2());
            prepareStmt.setInt(8, thi.getE3());
            prepareStmt.setInt(9, thi.getF4());
            prepareStmt.setInt(10, thi.getC5());
            prepareStmt.setInt(11, thi.getE6());
            prepareStmt.setInt(12, thi.getF7());
            prepareStmt.setInt(13, thi.getC8());
            prepareStmt.setInt(14, thi.getF9());
            prepareStmt.setInt(15, thi.getE10());
            prepareStmt.setInt(16, thi.getC11());
            prepareStmt.setInt(17, thi.getF12());
            prepareStmt.setInt(18, thi.getF13());
            prepareStmt.setInt(19, thi.getE14());
            prepareStmt.setInt(20, thi.getF15());
            prepareStmt.setInt(21, thi.getE16());
            prepareStmt.setInt(22, thi.getE17());
            prepareStmt.setInt(23, thi.getF18());
            prepareStmt.setInt(24, thi.getC19());
            prepareStmt.setInt(25, thi.getF20());
            prepareStmt.setInt(26, thi.getE21());
            prepareStmt.setInt(27, thi.getE22());
            prepareStmt.setInt(28, thi.getC23());
            prepareStmt.setInt(29, thi.getF24());
            prepareStmt.setInt(30, thi.getE25());

            prepareStmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("Error creating THI");
            System.out.println("SQL exception occured" + e.getSQLState());
            System.out.println(e.getMessage());
        }

        return false;
    }

    // DELETE FROM THI VALUE(S) using this method. Return true if delete successful, else false
    // Huy
    public boolean deletePatientTHI(int VisitID) {
        String query = "DELETE FROM Patient WHERE visitID = ?";

        try {
            //Statement stmt = con.createStatement();
            PreparedStatement prepareStmt = this.connection.prepareStatement(query);

            prepareStmt.setInt(1, VisitID);

            prepareStmt.execute();

            int deleted = prepareStmt.executeUpdate();

            if (deleted == 0) {
                System.out.printf("Nothing to delete!\n");
                return false;
            } else {
                System.out.printf("%d row(s) deleted", deleted);
                return true;
            }
            //this.connection.close(); Don't know if we need to

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return false;
    }

    // SELECT FROM THI WHERE visitID (parameter) = visitID (database value) using this method and return a THI object
    // Huy
    public THI getTHI(int visitIDKey) {

        // return a populated visit

        String query = "SELECT * FROM THI WHERE visitID = ?";

        try {
            Connection con = this.connection;
            //Statement stmt = con.createStatement();
            PreparedStatement prepareStmt = con.prepareStatement(query);

            prepareStmt.setInt(1, visitIDKey);

            ResultSet rs = prepareStmt.executeQuery(query);

            System.out.printf("VisitID, Sc_T, Sc_F, Sc_E, Sc_C, F1, F2, E3, F4," +
                    " C5, E6, F7, C8, F9, E10, C11, F12, F13, E14, F15, E16," +
                    " E17, F18, C19, F20, E21, E22, C23, F24, E25\n");

            while (rs.next()) {
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

                //this.connection.close(); Don't know if we need to

                return _THI;
            }
        } catch (Exception e) {
            System.out.println("SQL exception occured" + e);
        }

        return null;
    }

    // SQL call using the SQL THIScore procedure and return a THI determination result string
    // Huy
    public String getResultTHI(int visitorID) {

        try {
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
            //this.connection.close(); Don't know if we need to

            return desc;
        } catch (Exception e) {
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
    public Object[][] getAllPatientsFullInformation() {

        ArrayList<Object[]> patientFullInfoList = new ArrayList<>();

        try {
            PreparedStatement fullInformationStatement = this.connection.prepareStatement("SELECT" +
                    " THC, Patient.first_Name, Patient.surname, Patient.dob, Patient.insurance" +
                    " FROM Patient" +
                    " ORDER BY Patient.surname;");

            ResultSet rs = fullInformationStatement.executeQuery();

            while (rs.next()) {

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

            for (int i = 0; i < patientFinalArray.length; i++) {
                patientFinalArray[i] = patientFullInfoList.get(i);
            }

            return patientFinalArray;
        } catch (SQLException sqlE) {
            return null;
        }
    }

    // Kelvin
    public int getMaxTHC() {

        try {
            PreparedStatement getMaxTHCStatement = connection.prepareStatement("SELECT MAX(THC) FROM Patient");
            ResultSet resultSet = getMaxTHCStatement.executeQuery();

            resultSet.next();
            return resultSet.getInt("MAX(THC)");
        } catch (SQLException sqlE) {
            System.out.println("GET MAX THC FAILED " + sqlE.getMessage());
            sqlE.printStackTrace();
        }

        return -1;
    }

    //Enrique
    public int getMaxVisitID() {

        try {
            PreparedStatement getMaxVisitIDStatement = connection.prepareStatement("SELECT MAX(Visit_ID) FROM Visit");
            ResultSet resultSet = getMaxVisitIDStatement.executeQuery();

            resultSet.next();
            return resultSet.getInt("MAX(Visit_ID)");
        } catch (SQLException sqlE) {
            System.out.println("GET MAX Visit_ID FAILED " + sqlE.getMessage());
            sqlE.printStackTrace();
        }

        return -1;
    }

    //Kelvin
    public int getAllTHICollected() {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT COUNT(*) FROM THI");
            ResultSet rs = preparedStatement.executeQuery();

            rs.next();

            return rs.getInt("COUNT(*)");

        } catch (SQLException sqle) {

        }

        return 0;
    }

    //Kelvin
    public int getRegisteredPatientCount() {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT COUNT(*) FROM Patient");
            ResultSet rs = preparedStatement.executeQuery();

            rs.next();

            return rs.getInt("COUNT(*)");

        } catch (SQLException sqle) {

        }

        return 0;
    }

    //Kelvin

    public Object[][] getInsuranceAnalytics() {

        ArrayList<Object[]> insuranceFullInfoList = new ArrayList<>();

        try {
            PreparedStatement fullInformationStatement =
                    this.connection.prepareStatement(
                            "SELECT Insurance, COUNT(THC) FROM Patient GROUP BY Insurance ORDER BY Insurance"
                    );

            ResultSet rs = fullInformationStatement.executeQuery();

            while (rs.next()) {

                insuranceFullInfoList.add(new Object[]{
                        rs.getString("insurance"),
                        rs.getInt("COUNT(THC)")
                });
            }

            Object[][] insuranceFinalArray = new Object[insuranceFullInfoList.size()][2];

            for (int i = 0; i < insuranceFinalArray.length; i++) {
                insuranceFinalArray[i] = insuranceFullInfoList.get(i);
            }

            return insuranceFinalArray;
        } catch (SQLException sqlE) {
            System.out.println(sqlE.getMessage());
            return null;
        }
    }

}