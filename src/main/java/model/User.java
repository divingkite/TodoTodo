package model;

/**
 * Created by hitesh.ag on 10/08/17.
 */
public class User {

    private String userName;
    private String password;
    private String emailId;
    private String phone;

    public User(String username, String password,
                String email, String phone){
        this.userName = username;
        this.password = password;
        this.emailId = email;
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    synchronized public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    synchronized public void setPassword(String password) {
        this.password = password;
    }

    synchronized public String getEmailId() {
        return emailId;
    }

    synchronized public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    synchronized public String getPhone() {
        return phone;
    }

    synchronized public void setPhone(String phone) {
        this.phone = phone;
    }
}
