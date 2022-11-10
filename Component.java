public interface Component {
    public void accept(Visitor inputVisitor);
    public String getID();
}
