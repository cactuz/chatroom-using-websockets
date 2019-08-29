package edu.udacity.java.nano.chat;


import lombok.*;

/**
 * WebSocket message model
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message {
    private MessageType type;
    private String content;
    private String sender;
    private int onlineCount;

    public enum MessageType {
        ENTER,
        CHAT,
        LEAVE
    }
}
