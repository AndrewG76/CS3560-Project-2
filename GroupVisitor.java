public class GroupVisitor implements Visitor{
    private int groupCount;

    public GroupVisitor(){
        groupCount = 0;
    }

    public int getGroupCount(){
        return groupCount;
    }

    public void atUser(User inputUser){

    }

    public void atGroup(UserGroup inputUser){
        groupCount++;
    }
}
