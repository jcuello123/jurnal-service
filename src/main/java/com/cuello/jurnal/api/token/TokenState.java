package com.cuello.jurnal.api.token;

public class TokenState {
    private boolean isValid;
    private boolean isExpired;

    public TokenState(boolean isValid, boolean isExpired) {
        this.isValid = isValid;
        this.isExpired = isExpired;
    }

    public boolean isValid() {
        return this.isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public void setIsExpired(boolean isExpired) {
        this.isExpired = isExpired;
    }

    public boolean isExpired() {
        return this.isExpired;
    }
}
