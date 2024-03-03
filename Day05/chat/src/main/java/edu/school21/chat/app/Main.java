package edu.school21.chat.app;

import edu.school21.chat.models.Chatroom;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Date date = new Date(System.currentTimeMillis());
        Chatroom chatroom = new Chatroom();
        System.out.print(chatroom.getId());
    }
}
