package com.chat.common.utils;

/**
 * Enum representing different types used in the chat system.
 */
public enum Type {
    
    ALL_USERS(0), // Represents a request for all users
    MSG(1),       // Represents a message
    SIGNOUT(2),   // Represents a sign-out request
    ERROR(3),     // Represents an error message
    INFO(4);      // Represents an informational message

    private int val;

    
    /**
     * Constructs a Type enum constant with the specified value.
     *
     * @param value the integer value associated with the enum constant
     */
    Type(int value){
        
        this.val = value;
    }

    
    /**
     * Returns the integer value associated with this Type enum constant.
     *
     * @return the integer value of this Type enum constant
     */
    public int getVal() {
        
        return val;
    }
}
