package br.unipe.borracheiro.borracheiro.model;

/**
 * Created by -Vinícius on 04/06/2017.
 */

public class LoginResult {
    private String success;
    private String message;
    private String token;


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String toString() {
        return success + " - " + token;
    }
}
