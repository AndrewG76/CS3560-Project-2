import java.util.*;

public class UserGroup implements Component{
    private String id;
    private List<Component> childUsersAndGroups;
    private Set<String> ids;
    private UserGroup parentGroup;
    
    public UserGroup(String inputID){
        id = inputID;
        childUsersAndGroups = new ArrayList<Component>();
        parentGroup = this;
        ids = new HashSet<String>();
    }

    public UserGroup(String inputID, UserGroup inputParent){
        id = inputID;
        parentGroup = inputParent;
        childUsersAndGroups = new ArrayList<Component>();
        ids = new HashSet<String>();
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

    public String toString(){
        return "[Group] " + id;
    }

    public void accept(Visitor inputVisitor){
        inputVisitor.atGroup(this);
        for(int i = 0; i < childUsersAndGroups.size(); i++){
            childUsersAndGroups.get(i).accept(inputVisitor);
        }
    }

    public boolean addToGroup(Component inputComponent){
        if(getRoot().getIDs().contains(inputComponent.getID())){
            return false;
        }
        childUsersAndGroups.add(inputComponent);
        getRoot().getIDs().add(inputComponent.getID());
        return true;
    }

    public Set<String> getIDs(){
        return ids;
    }

    public boolean equals(UserGroup inputGroup){
        return this.id.toLowerCase().equals((inputGroup.getID().toLowerCase()));
    }

    public UserGroup getRoot(){
        UserGroup root = parentGroup;
        while(parentGroup.getID() != "root"){
            root = parentGroup.getParent();
        }
        return root;
    }
}
