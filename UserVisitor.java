public class UserVisitor implements Visitor{
    private int userCount;

    public UserVisitor(){
        userCount = 0;
    }

    public int getUserCount(){
        return userCount;
    }

    public void atUser(User inputUser){
        userCount++;
    }

    public void atGroup(UserGroup inputGroup){
        //This method is dealing with just users so nothing really happens here at groups
    }
}
