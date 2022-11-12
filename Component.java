//Individual users and then the groups of users and groups will be implementing this

public interface Component {
    public void accept(Visitor inputVisitor);
    public String getID();
    public String toString();
    public UserGroup getParent();
}
