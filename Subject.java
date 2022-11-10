import java.util.*;

public class Subject extends Observable{
    private String id;

    public Subject(String inputID){
        id = inputID;
    }

    public void setID(String inputID){
        id = inputID;
    }

    public String getID(String inputID){
        return id;
    }
}
