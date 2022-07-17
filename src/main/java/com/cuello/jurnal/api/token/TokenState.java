package com.cuello.jurnal.api.token;

public class TokenState {
    private boolean isValid;
    private String error;
    private boolean isExpired;

    public TokenState(boolean isValid, String error) {
        this.isValid = isValid;
        this.error = error;
    }

    public boolean isValid() {
        return this.isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public String getError() {
        return this.error;
    }

    public void setIsExpired(boolean isExpired) {
        this.isExpired = isExpired;
    }

    public boolean isExpired() {
        return this.isExpired;
    }
}
