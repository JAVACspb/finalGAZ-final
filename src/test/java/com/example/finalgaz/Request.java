package com.example.finalgaz;

import com.example.casegpn.validator.RequestValid;

import javax.validation.constraints.Size;

public class Request {

    public String userId;
    public String groupId;

    public Request(String userId, String groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }
}
