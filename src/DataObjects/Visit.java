package DataObjects;

// Class to be used for representing an individual visit information from the database
public class Visit {

    private int visitID, thc, visitNR;
    private String date, comments;

    public Visit(int visitID, int thc, int visitNR, String date, String comments){
        this.visitID = visitID;
        this.thc = thc;
        this.visitNR = visitNR;
        this.date = date;
        this.comments = comments;
    }

    public int getVisitID(){
        return visitID;
    }

    public int getTHC(){
        return thc;
    }

    public int getVisitNR(){
        return visitNR;
    }

    public String getDate(){
        return date;
    }

    public String getComments(){
        return comments;
    }

}
