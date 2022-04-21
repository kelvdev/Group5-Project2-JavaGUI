package Database;// Helper class to read and write to the database with ease

import DataObjects.*;

import java.sql.Connection;
import java.util.ArrayList;

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
    public boolean deletePatient(int THC){
        return false;
    }

    // TODO: implement a SELECT FROM Patient WHERE using this method and return a Patient object
    // Huy
    public Patient getPatient(int THC){

        // return a populated patient
        Patient patient;

        return null;
    }

    // TODO: implement a INSERT INTO Visit VALUE(S) using this method. Return true if create successful, else false
    // Enrique
    public boolean createVisit(int THC, String visitComments){
        return false;
    }

    // TODO: implement a DELETE FROM Visit VALUE(S) using this method. Return true if delete successful, else false
    // Huy
    public boolean deleteVisit(){
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

    // TODO: implement a DELETE FROM THI VALUE(S) using this method. Return true if delete successful, else false
    // Huy
    public boolean deletePatientTHI(int VisitID){
        return false;
    }

    // TODO: implement a SELECT FROM THI WHERE visitID (parameter) = visitID (database value) using this method and return a THI object
    // Huy
    public THI getTHI(int visitID){

        // return a populated visit
        THI THI;

        return null;
    }

    // TODO: implement an SQL call using the SQL THIScore procedure and return a THI determination result string
    // Huy
    public String getResultTHI(int visitorID){
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
