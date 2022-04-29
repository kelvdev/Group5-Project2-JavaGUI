import DataObjects.Patient;

import java.sql.*;

public class Test {

    Connection connection;

    public Test(){

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost/Team5", "root", "myawesomepassword");
        } catch(SQLException sqle){
            sqle.getSQLState();
        }
    }

    public void addPatient(Patient patient) throws SQLException {
        String sqlStatement = "INSERT INTO Patient VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement createPatientStatement = connection.prepareStatement(sqlStatement);

        createPatientStatement.setInt(1, 1);
        createPatientStatement.setString(2, patient.getCountryID());
        createPatientStatement.setString(3, patient.getStateID());
        createPatientStatement.setInt(4, patient.getZipID());
        createPatientStatement.setInt(5, patient.getWStatusID());
        createPatientStatement.setString(6, patient.getOccupation());
        createPatientStatement.setString(7, patient.getSurname());
        createPatientStatement.setString(8, patient.getFirstName());
        createPatientStatement.setString(9, patient.getSSN());
        createPatientStatement.setString(10, patient.getDob());
        createPatientStatement.setString(11, patient.getInsurance());
        createPatientStatement.setString(12, patient.getTinBackground());
        createPatientStatement.setString(13, patient.getHBackground());
        createPatientStatement.setString(14, patient.getTIndComments());
        createPatientStatement.setString(15, patient.getHIndComments());

        createPatientStatement.executeUpdate();
    }

    public void getPatient(){


    }

    public void updatePatient(Patient patient) throws SQLException {
        String sqlStatement = "UPDATE Patient" +
                "SET THC = ?";
        PreparedStatement createPatientStatement = connection.prepareStatement(sqlStatement);
    }

}
