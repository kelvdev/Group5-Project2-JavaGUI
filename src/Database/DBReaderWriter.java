package Database;// Helper class to read and write to the database with ease

import DataObjects.*;

import java.util.ArrayList;

// TODO: implement Database.DBReaderWriter methods to do specific tasks such as addPatient() or updatePatient();
public class DBReaderWriter {

    // TODO: ensure that DBReaderWriter is connected to the database using DBConnector
    public DBReaderWriter(){

    }

    // TODO: implement a INSERT INTO Patient VALUE(S) using this method. Return 1 if delete successful, else 0
    public int createPatient(Patient patient){
        return 0;
    }

    // TODO: implement a UPDATE Patient using this method. Return 1 if delete successful, else 0
    public int updatePatient(Patient patient){
        return 0;
    }

    // TODO: implement a DELETE FROM Patient WHERE using this method. Return 1 if delete successful, else 0
    public int deletePatient(int THC){
        return 0;
    }

    // TODO: implement a SELECT FROM Patient WHERE using this method and return a Patient object
    public Patient getPatient(){

        // return a populated patient
        Patient patient;

        return null;
    }

    // TODO: implement a INSERT INTO Visit VALUE(S) using this method. Return 1 if delete successful, else 0
    public int createVisit(){
        return 0;
    }

    // TODO: implement a DELETE FROM Visit VALUE(S) using this method. Return 1 if delete successful, else 0
    public int deleteVisit(){
        return 0;
    }

    // TODO: implement a SELECT FROM Visit WHERE visitID (parameter) = visitID (database value) using this method and return a Visit object
    public Visit getVisit(int visitID){

        // return a populated visit
        Visit visit;

        return null;
    }

    // TODO: implement an SQL call using the SQL visitorsCount function and return a visit count
    public int getAllPatientVisitsOnDate(String date){
        // return visit count
        return 0;
    }

    // TODO: implement a INSERT INTO THI VALUE(S) using this method. Return 1 if create successful, else 0
    public int createPatientTHI(){
        return 0;
    }

    // TODO: implement a UPDATE THI SET WHERE using this method. Return 1 if update successful, else 0
    public int updatePatientTHI(){
        return 0;
    }

    // TODO: implement a DELETE FROM THI VALUE(S) using this method. Return 1 if delete successful, else 0
    public int deletePatientTHI(){
        return 0;
    }

    // TODO: implement a SELECT FROM THI WHERE THC (parameter) = THC (database value) using this method and return a THI object
    public THI getTHI(int THC){

        // return a populated visit
        THI THI;

        return null;
    }

    // TODO: implement an SQL call using the SQL THIScore procedure and return a THI determination result string

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

    public ArrayList<Object> getPatientFullInformation(){
        return null;
    }
}
