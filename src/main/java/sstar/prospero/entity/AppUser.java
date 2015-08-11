package sstar.prospero.entity;

/**
 * Created by Sergey.Tarasenko on 04.05.2015.
 */
public class AppUser {
    private String userLogin;
    private String fio;
    private String password;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
