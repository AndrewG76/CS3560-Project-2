import java.util.*;

public class UserGroup implements Component{
    private String id;
    private List<Component> components;
    
    public UserGroup(String inputID){
        id = inputID;
        components = new ArrayList<Component>();
    }

    public void setID(String inputID){
        id = inputID;
    }

    public String getID(){
        return id;
    }

    public List<Component> getMembers(){
        return components;
    }

    public void accept(Visitor v){
        v.atGroup(this);
        for(int i = 0; i < components.size(); i++){
            components.get(i).accept(v);
        }
    }

    public void addToGroup(Component inputComponent){
        components.add(inputComponent);
    }

    public void addGroup(UserGroup inputGroup){

    }

    public void visit(Component inputComponent){

    }
}
