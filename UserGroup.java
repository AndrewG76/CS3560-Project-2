import java.util.*;

public class UserGroup implements Component{
    private String id;
    private UserGroup parentGroup;
    private List<Component> childUsersAndGroups;
    private Set<String> ids;
    
    public UserGroup(String inputID){ //This constructor exists to be utilized for the root group which is always present
        id = inputID;
        childUsersAndGroups = new ArrayList<Component>();
        parentGroup = this;
        ids = new HashSet<String>();
    }

    public UserGroup(String inputID, UserGroup inputParent){ //Then this is the general constructor that the regular groups will be using as a recursive structure
        id = inputID;
        parentGroup = inputParent;
        childUsersAndGroups = new ArrayList<Component>();
        ids = new HashSet<String>();
    }

    public UserGroup getParent(){
        return parentGroup;
    }

    public String getID(){
        return id;
    }

    public Set<String> getIDs(){
        return ids;
    }

    public List<Component> getChildUsersAndGroups(){
        return childUsersAndGroups;
    }

    public UserGroup getRoot(){ //Method used for traversing parent
        UserGroup root = parentGroup;
        while(parentGroup.getID() != "root"){
            root = parentGroup.getParent();
        }
        return root;
    }

    public void setID(String inputID){
        id = inputID;
    }

    @Override
    public String toString(){ //Used to display on the scroll panel the classification
        return "[Group] " + id;
    }

    public void accept(Visitor inputVisitor){
        inputVisitor.atGroup(this);
        for(Component child : childUsersAndGroups){
            child.accept(inputVisitor);
        }
    }

    public boolean addToGroup(Component inputComponent){
        if(getRoot().getIDs().contains(inputComponent.getID())){ //Verification for whether or not this group id exists within our list of previous group ids
            return false;
        }
        childUsersAndGroups.add(inputComponent);
        getRoot().getIDs().add(inputComponent.getID());
        return true;
    }

    public boolean equals(UserGroup inputGroup){
        return id.toLowerCase().equals((inputGroup.getID().toLowerCase()));
    }
}
