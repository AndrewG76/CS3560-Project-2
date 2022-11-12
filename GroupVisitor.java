public class GroupVisitor implements Visitor{
    private int groupCount;

    public GroupVisitor(){
        groupCount = 0;
    }

    public int getGroupCount(){
        return groupCount;
    }

    public void atUser(User inputUser){
        //Because this is groupVisitor, it has no functionality needed at users
    }

    public void atGroup(UserGroup inputGroup){
        groupCount++;
    }
}
