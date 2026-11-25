package dev.rlucassmd.meusOculosApi.dto;

import java.io.Serial;
import java.io.Serializable;

public class UserInsertDTO extends UserDTO implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
