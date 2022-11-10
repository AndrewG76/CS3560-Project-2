public interface Component {
    public String getID();
    public void accept(Visitor inputVisitor);
}
