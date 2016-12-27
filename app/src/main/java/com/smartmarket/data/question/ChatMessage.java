package com.smartmarket.data.question;

/**
 * Created by glauco on 27/12/16.
 */

public class ChatMessage {

    public static final int MY_MESSAGE = 0;
    public static final int BUYER_MESSAGE = 1;

    private int type;
    private String message;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
