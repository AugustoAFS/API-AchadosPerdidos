package com.AchadosPerdidos.API.Domain.Entity.Chat;

import com.AchadosPerdidos.API.Domain.Enum.TipoMenssagem;
import com.AchadosPerdidos.API.Domain.Enum.Status_Menssagem;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "chat_messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    // Construtor customizado para inicialização padrão
    public ChatMessage(String chatId, String senderId, String receiverId, String message, TipoMenssagem type) {
        this.chatId = chatId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.type = type;
        this.timestamp = LocalDateTime.now();
        this.status = Status_Menssagem.SENT;
    }
}
