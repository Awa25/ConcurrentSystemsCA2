package com.chat.server;

/**
 * Represents a server service for starting and stopping the server.
 */
public interface ServerSvc {
    /**
     * Starts the server.
     */
    void start();

    /**
     * Stops the server.
     */
    void stop();
}
