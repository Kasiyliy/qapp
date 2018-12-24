package com.iitu.utils;

/**
 * @author Assylkhan
 * on 22.12.2018
 * @project qapp
 */
public class MessageUtils {

    public static String getMessageJSON(String body){
        return String.format("{ \"message\" : \"%1$s\"}" , body);
    }

}
