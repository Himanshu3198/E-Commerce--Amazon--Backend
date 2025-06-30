package org.ecommerce.Exception;


import org.springframework.stereotype.Component;

@Component
public class ErrorResponse {

    private String errorCode;
    private String message;
    private String help;

    public ErrorResponse(String errorCode, String message, String help) {
        this.errorCode = errorCode;
        this.message = message;
        this.help = help;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }
}
