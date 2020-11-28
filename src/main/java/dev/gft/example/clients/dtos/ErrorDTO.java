package dev.gft.example.clients.dtos;

import java.io.Serializable;

public class ErrorDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7689690798087155824L;
    private String message;
    private Integer code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
