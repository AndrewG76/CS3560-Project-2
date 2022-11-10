import java.util.*;

public class UserGroup implements Composite{
    private String id;
    private List<Composite> members;
    
    public UserGroup(String inputID){
        id = inputID;
        members = new ArrayList<Composite>();
    }

    public void setID(String inputID){
        id = inputID;
    }

    public String getID(){
        return id;
    }

    public List<Composite> getMembers(){
        return members;
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    public void addToGroup(Composite inputComposite){
        members.add(inputComposite);
    }
}
