package com.AchadosPerdidos.API.Presentation.Controller;

import com.AchadosPerdidos.API.Application.Services.ChatService;
import com.AchadosPerdidos.API.Domain.Entity.Chat.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    /**
     * Endpoint para receber mensagens do chat
     * @param chatMessage A mensagem recebida
     * @return A mensagem processada para ser enviada a todos os clientes
     */
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        // Define um chatId padrão se não fornecido (para chat público)
        if (chatMessage.getChatId() == null) {
            chatMessage.setChatId("public");
        }
        
        // Salva a mensagem no MongoDB
        ChatMessage savedMessage = chatService.saveMessage(chatMessage);
        return savedMessage;
    }

    /**
     * Endpoint para quando um usuário se conecta ao chat
     * @param chatMessage Mensagem de JOIN
     * @param headerAccessor Acesso aos headers da mensagem
     * @return Mensagem de boas-vindas
     */
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, 
                               SimpMessageHeaderAccessor headerAccessor) {
        // Adiciona o ID do usuário na sessão WebSocket
        headerAccessor.getSessionAttributes().put("userId", chatMessage.getSenderId());
        
        // Define chatId padrão para chat público
        if (chatMessage.getChatId() == null) {
            chatMessage.setChatId("public");
        }
        
        // Salva a mensagem de JOIN no MongoDB
        ChatMessage savedMessage = chatService.saveMessage(chatMessage);
        
        return savedMessage;
    }

    /**
     * Endpoint para quando um usuário sai do chat
     * @param chatMessage Mensagem de LEAVE
     * @return Mensagem de despedida
     */
    @MessageMapping("/chat.leaveUser")
    @SendTo("/topic/public")
    public ChatMessage leaveUser(@Payload ChatMessage chatMessage) {
        // Define chatId padrão para chat público
        if (chatMessage.getChatId() == null) {
            chatMessage.setChatId("public");
        }
        
        // Salva a mensagem de LEAVE no MongoDB
        ChatMessage savedMessage = chatService.saveMessage(chatMessage);
        
        return savedMessage;
    }
}
