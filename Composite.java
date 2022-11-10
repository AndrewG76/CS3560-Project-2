public interface Composite {
    public void setID(String inputID);
    public String getID();
    public void accept(Visitor v);
}
