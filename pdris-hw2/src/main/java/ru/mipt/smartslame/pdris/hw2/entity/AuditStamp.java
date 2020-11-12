package ru.mipt.smartslame.pdris.hw2.entity;

import ru.mipt.smartslame.pdris.hw2.enums.AuthResult;
import ru.mipt.smartslame.pdris.hw2.enums.AuthResultReason;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditStamp {
    private AuthResult authResult;
    private AuthResultReason authResultReason;
    private String date;

    public AuditStamp(AuthResult authResult, AuthResultReason authResultReason) {
        this.authResult = authResult;
        this.authResultReason = authResultReason;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.date = formatter.format(new Date());
    }

    public AuthResult getAuthResult() {
        return authResult;
    }

    public AuthResultReason getAuthResultReason() {
        return authResultReason;
    }

    public String getDate() {
        return date;
    }
}