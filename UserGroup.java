import java.util.*;

public class UserGroup implements Component{
    private String id;
    private List<Component> childUsersAndGroups;
    private UserGroup parentGroup;
    
    public UserGroup(String inputID){
        id = inputID;
        childUsersAndGroups = new ArrayList<Component>();
        parentGroup = null;
    }

    public UserGroup(String inputID, UserGroup inputParent){
        id = inputID;
        parentGroup = inputParent;
        childUsersAndGroups = new ArrayList<Component>();
    }

    public void setID(String inputID){
        id = inputID;
    }

    public String getID(){
        return id;
    }

    public List<Component> getChildUsersAndGroups(){
        return childUsersAndGroups;
    }

    public UserGroup getParent(){
        return parentGroup;
    }

    public void accept(Visitor inputVisitor){
        inputVisitor.atGroup(this);
        for(int i = 0; i < childUsersAndGroups.size(); i++){
            childUsersAndGroups.get(i).accept(inputVisitor);
        }
    }

    public void addToGroup(Component inputComponent){
        childUsersAndGroups.add(inputComponent);
    }
}
