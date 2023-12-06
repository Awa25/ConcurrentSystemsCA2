package com.chat.common.data;

import com.chat.common.utils.Type;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Message class represents a message exchanged in the chat application.
 * It implements the Serializable interface to allow for object serialization.
 */
public class Message implements Serializable {
    
    public static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private String msg;
    private Type type;
    private String username;
    private String time;

    
    /**
     * Sets the message content.
     *
     * @param msg the message content
     */
    public void setMsg(String msg) {
        
        this.msg = msg;
    }

    
    /**
     * Gets the timestamp of the message.
     *
     * @return the timestamp of the message
     */
    public String getTime() {
        
        return time;
    }

    
    /**
     * Sets the timestamp of the message.
     *
     * @param time the timestamp of the message
     */
    public void setTime(Date time) {
        
        this.time = sdf.format(time);
    }

    
    /**
     * Gets the username associated with the message.
     *
     * @return the username of the message sender
     */
    public String getUsername() {
        
        if (username == null || username.isEmpty())
            return "";
        return username;
    }

    
    /**
     * Gets the content of the message.
     *
     * @return the content of the message
     */
    public String getMsg() {
        
        return msg;
    }

    
    /**
     * Gets the type of the message.
     *
     * @return the type of the message
     */
    public Type getType() {
        
        return type;
    }

    
    /**
     * Sets the username associated with the message.
     *
     * @param username the username of the message sender
     */
    public void setUsername(String username) {
        
        this.username = username;
    }

    
    /**
     * Constructs a new Message object with the current timestamp.
     */
    public Message() {
        
        this.time = sdf.format(new Date());
    }

    
    /**
     * Constructs a new Message object with the specified message and type.
     *
     * @param msg  the message content
     * @param type the type of the message
     */
    public Message(String msg, Type type) {
        
        this();
        this.setMsg(msg);
        this.type = type;
    }

    
    /**
     * Constructs a new Message object with the specified message and username.
     * The type of the message is set to INFO by default.
     *
     * @param msg      the message content
     * @param username the username of the message sender
     */
    public Message(String msg, String username) {
        
        this();
        this.setMsg(msg);
        this.type = Type.INFO;
        this.username = username;
    }

    
    /**
     * Constructs a new Message object with the specified exception and message.
     * The type of the message is set to ERROR.
     *
     * @param e   the exception associated with the message
     * @param msg the message content
     */
    public Message(Exception e, String msg) {
        
        this();
        this.type = Type.ERROR;
        this.setMsg(msg + ":" + e.getMessage());
    }

    
    /**
     * Constructs a new Message object with the specified message.
     * The type of the message is set to INFO.
     *
     * @param msg the message content
     */
    public Message(String msg) {
        this();
        this.type = Type.INFO;
        this.setMsg(msg);
    }

    
    /**
     * Returns a string representation of the message.
     *
     * @return a string representation of the message
     */
    @Override
    public String toString(){
        
        return "<" + this.getTime() + " - " + this.getType().name() +" #"+this.getUsername()+" > " + this.getMsg() +
                "\n";
    }
}
