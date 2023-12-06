package com.chat.client;

import com.chat.client.impl.Client;
import com.chat.common.data.Message;

import java.io.IOException;

/**
 * The MsgBroker class is responsible for receiving messages from the server and updating the ChatClient.
 * It runs as a separate thread to continuously listen for incoming messages.
 */
public class MsgBroker extends Thread {
    
    private Client client;

    /**
     * Constructs a new MsgBroker with the given Client instance.
     *
     * @param client the client instance associated with this MsgBroker
     */
    public MsgBroker(Client client) {
        
        this.client = client;
    }

    
    /**
     * The run method is executed when the thread starts.
     * It continuously listens for incoming messages from the server and updates the ChatClient accordingly.
     */
    public void run() {
        
        while(true) {
            
            try {
                Message msg = (Message) client.getObjectInputStream().readObject();
                client.getChatClient().appendText(msg);
            } 
            
            catch(IOException e) {
                client.display(new Message(e, "Server has closed the connection"));
                if(client.getChatClient() != null)
                    client.getChatClient().connectionFailed();
                break;
            } 
            
            catch(ClassNotFoundException e2) {
                e2.printStackTrace();
            }
        }
    }
}
