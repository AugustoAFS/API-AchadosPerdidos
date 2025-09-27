package com.AchadosPerdidos.API.Domain.Entity.Chat;

import com.AchadosPerdidos.API.Domain.Enum.TipoMenssagem;
import com.AchadosPerdidos.API.Domain.Enum.Status_Menssagem;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "chat_messages")
public class ChatMessage {
    @Id
    private String id;
    private String chatId;
    private String senderId;
    private String receiverId;
    private String message;
    private LocalDateTime timestamp;
    private Status_Menssagem status;
    private TipoMenssagem type;


    // Construtores
    public ChatMessage() {
        this.timestamp = LocalDateTime.now();
        this.status = Status_Menssagem.SENT;
    }

    public ChatMessage(String chatId, String senderId, String receiverId, String message, TipoMenssagem type) {
        this.chatId = chatId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.type = type;
        this.timestamp = LocalDateTime.now();
        this.status = Status_Menssagem.SENT;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Status_Menssagem getStatus() {
        return status;
    }

    public void setStatus(Status_Menssagem status) {
        this.status = status;
    }

    public TipoMenssagem getType() {
        return type;
    }

    public void setType(TipoMenssagem type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id='" + id + '\'' +
                ", chatId='" + chatId + '\'' +
                ", senderId='" + senderId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
