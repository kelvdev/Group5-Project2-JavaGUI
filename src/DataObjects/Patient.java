package DataObjects;

// Class to be used for representing an individual patient information from the database
public class Patient {

    private int thc, zipID, wStatusID;
    private String surname, firstName, countryID, stateID, ssn, dob, insurance, occupation, tinBackground,
    hBackground, tIndComments, hIndComments;

    public Patient(){

    }

    public Patient(int thc, String countryID, String stateID, int zipID, int wStatusID,
                   String surname, String firstName, String ssn, String dob, String insurance, String occupation,
                   String tinBackground, String hBackground, String tIndComments, String hIndComments){

        this.thc = thc;
        this.countryID = countryID;
        this.stateID = stateID;
        this.zipID = zipID;
        this.wStatusID = wStatusID;
        this.occupation = occupation;
        this.surname = surname;
        this.firstName = firstName;
        this.ssn = ssn;
        this.dob = dob;
        this.insurance = insurance;
        this.tinBackground = tinBackground;
        this.hBackground = hBackground;
        this.tIndComments = tIndComments;
        this.hIndComments = hIndComments;

    }

    public int getTHC(){
        return thc;
    }

    public String getCountryID(){
        return countryID;
    }

    public String getStateID(){
        return stateID;
    }

    public int getZipID(){
        return zipID;
    }

    public int getWStatusID(){
        return wStatusID;
    }

    public String getOccupation(){
        return occupation;
    }

    public String getSurname(){
        return surname;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getSSN(){
        return ssn;
    }

    public String getDob(){
        return dob;
    }

    public String getInsurance(){
        return insurance;
    }

    public String getTinBackground(){
        return tinBackground;
    }

    public String getHBackground(){
        return hBackground;
    }

    public String getTIndComments(){
        return tIndComments;
    }

    public String getHIndComments(){
        return hIndComments;
    }

}
