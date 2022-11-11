public class UserFinderVisitor implements Visitor{
    private User target;
    private String targetID;
    
    public UserFinderVisitor(String inputTargetID){
        targetID = inputTargetID;
        target = null;
    }

    public User getTarget(){
        return target;
    }

    public void atUser(User inputUser){
        if(targetID.toLowerCase().equals((inputUser.getID().toLowerCase()))){
            target = inputUser;
        }
    }

    public void atGroup(UserGroup inputGroup){
        
    }


}
