package com.chat.client;

import com.chat.common.data.Message;

/**
 * The ClientSvc interface defines the contract for a client service in the chat application.
 * It provides methods for sending messages and starting the client.
 */
public interface ClientSvc {

    /**
     * Sends a message to the server.
     *
     * @param message the message to be sent
     */
    void sendMessage(Message message);

    /**
     * Starts the client and establishes a connection with the server.
     *
     * @return a String indicating the status of the client
     */
    String start();
}