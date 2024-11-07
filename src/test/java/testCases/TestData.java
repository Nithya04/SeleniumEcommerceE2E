package testCases;

public class TestData {

    private static TestData instance;
    private String user_email;
    private String password;
    
    // Private constructor to prevent instantiation from outside the class
    private TestData() {}
	
 // Public method to provide access to the instance of the class
    public static TestData getInstance() {
        if (instance == null) {
            instance = new TestData();
        }
        return instance;
    }
    
 // Getter and Setter methods for user_email and password
    public String getUserEmail() {
        return user_email;
    }

    public void setUserEmail(String user_email) {
        this.user_email = user_email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
