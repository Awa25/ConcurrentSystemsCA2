package com.chat;

import com.chat.ui.ChatClient;
import javax.swing.JOptionPane;

/**
 * The Main class serves as the entry point for the client-side of the chat
 * application. It prompts the user to enter their username and starts the
 * ChatClient.
 */
public class Main {

    /**
     * The main method is the entry point of the application. It prompts the
     * user to enter their username and starts the ChatClient.
     *
     * @param args command line arguments (not used in this application)
     */
    public static String userName;

    public static void main(String[] args) {
        
        userName = JOptionPane.showInputDialog(null, "Enter your userName");
        ChatClient.start();
    }
}