public class Admin {
    private static Admin instance = null;

    private static Admin getInstance(){
        if(instance == null){
            synchronized(Admin.class){ //Synchronized allows for the usage of multi-threading processes for live, up-to-date announcements like with the Tweets
                if(instance == null){
                    instance = new Admin();
                }
            }
        }
        return instance;
    }
}
