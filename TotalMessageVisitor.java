public class TotalMessageVisitor implements Visitor{
    private int messageCount;

    public TotalMessageVisitor(){
        messageCount = 0;
    }

    public int getMessageCount(){
        return messageCount;
    }

    public void atUser(User inputUser){
        messageCount += inputUser.getTweets().size();
    }

    public void atGroup(UserGroup inputGroup){
        //Because we're observing the users' sent messages, groups don't need to be taken into consideration
    }
}
