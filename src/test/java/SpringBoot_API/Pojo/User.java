package SpringBoot_API.Pojo;

public class User {
    private String mobilephone;
    private String pwd;

    public User() {
    }

    public User(String mobilephone, String pwd) {
        this.mobilephone = mobilephone;
        this.pwd = pwd;
    }

    public String getUsername() {
        return mobilephone;
    }

    public void setUsername(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
