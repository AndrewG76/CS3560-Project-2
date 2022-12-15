import java.util.*;

public class UserIDCheckVisitor implements Visitor{
    private List<String> badUserIDs;
    private List<String> badGroupIDs;

    public UserIDCheckVisitor(){
        badUserIDs = new ArrayList<String>();
        badGroupIDs = new ArrayList<String>();
    }

    @Override
    public void atUser(User inputUser){
        String temporaryID = inputUser.getID();
        if(temporaryID.split(" ").length > 1){
            badUserIDs.add(temporaryID);
        }
    }

    @Override
    public void atGroup(UserGroup inputUserGroup){
        String temporaryID = inputUserGroup.getID();
        if(temporaryID.split(" ").length > 1){
            badGroupIDs.add(temporaryID);
        }
    }

    public String getBadIDs(){
        String badIDs = "Here are the invalid user IDs...\n";
        if(badUserIDs.size() == 0){
            badIDs += "None.\n";
        }
        else{
            for(String id : badUserIDs){
                badIDs += id + "\n";
            }
        }
        badIDs += "Here are the invalid group IDs...:\n";
        if(badGroupIDs.size() == 0){
            badIDs += "None.\n";
        }
        else{
            for(String id : badGroupIDs){
                badIDs += id + "\n";
            }
        }
        return badIDs;
    }
}
