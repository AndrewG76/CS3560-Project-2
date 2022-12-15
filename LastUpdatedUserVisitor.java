public class LastUpdatedUserVisitor implements Visitor{
    private String id;
    private long latestUpdateTime;
    
    public LastUpdatedUserVisitor(){
        id = "Unavailable because there are no users created yet. Please try again with users created first.";
        latestUpdateTime = -1;
    }

    public String getLastUpdatedUser(){
        return id;
    }

    @Override
    public void atUser(User inputUser){
        if(inputUser.getLastUpdateTime() > latestUpdateTime){
            latestUpdateTime = inputUser.getLastUpdateTime();
            id = inputUser.getID();
        }
    }

    @Override
    public void atGroup(UserGroup inputGroup){
        //nothing happens here at groups
    }
}
