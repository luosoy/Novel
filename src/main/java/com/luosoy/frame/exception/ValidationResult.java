package com.luosoy.frame.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ValidationResult implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The global message list.
     */
    private List<String> globalMessageList = new ArrayList<String>();

    /**
     * The messages map.
     */
    private Map<String, String> messagesMap = new HashMap<String, String>();

    /**
     * Checks if is validation fail.
     * 
     * @return true, if is validation fail
     */
    public boolean isValidationFail() {
        return !globalMessageList.isEmpty() || !messagesMap.isEmpty();
    }

    /**
     * Adds the global message.
     * 
     * @param message
     *            the message
     */
    public void addGlobalMessage(String message) {
        globalMessageList.add(message);
    }

    /**
     * Adds the message.
     * 
     * @param fieldPath
     *            the field path
     * @param message
     *            the message
     */
    public void addMessage(String fieldPath, String message) {
        messagesMap.put(fieldPath, message);
    }

    /**
     * Gets the global message list.
     * 
     * @return the global message list
     */
    public List<String> getGlobalMessageList() {
        return globalMessageList;
    }

    /**
     * Sets the global message list.
     * 
     * @param globalMessageList
     *            the new global message list
     */
    public void setGlobalMessageList(List<String> globalMessageList) {
        this.globalMessageList = globalMessageList;
    }

    /**
     * Gets the messages map.
     * 
     * @return the messages map
     */
    public Map<String, String> getMessagesMap() {
        return messagesMap;
    }

    /**
     * Sets the messages map.
     * 
     * @param messagesMap
     *            the messages map
     */
    public void setMessagesMap(Map<String, String> messagesMap) {
        this.messagesMap = messagesMap;
    }
}
