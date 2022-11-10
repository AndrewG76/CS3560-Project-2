public class Admin {
    private static Admin instance = null;

    private static Admin getInstance(){
        if(instance == null){
            instance = new Admin();
        }
        return instance;
    }
}
