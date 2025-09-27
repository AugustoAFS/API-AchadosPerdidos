package com.AchadosPerdidos.API.Domain.Repository;

import com.AchadosPerdidos.API.Domain.Entity.Chat.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    /**
     * Busca mensagens por chatId ordenadas por timestamp
     */
    List<ChatMessage> findByChatIdOrderByTimestampAsc(String chatId);

    /**
     * Busca mensagens por chatId e tipo ordenadas por timestamp
     */
    List<ChatMessage> findByChatIdAndTypeOrderByTimestampAsc(String chatId, ChatMessage.MessageType type);

    /**
     * Busca mensagens por senderId ordenadas por timestamp
     */
    List<ChatMessage> findBySenderIdOrderByTimestampDesc(String senderId);

    /**
     * Busca mensagens por receiverId ordenadas por timestamp
     */
    List<ChatMessage> findByReceiverIdOrderByTimestampDesc(String receiverId);

    /**
     * Busca mensagens entre dois usuários ordenadas por timestamp
     */
    @Query("{'$or': [{'senderId': ?0, 'receiverId': ?1}, {'senderId': ?1, 'receiverId': ?0}]}")
    List<ChatMessage> findMessagesBetweenUsers(String userId1, String userId2);

    /**
     * Busca mensagens por chatId em um período específico
     */
    List<ChatMessage> findByChatIdAndTimestampBetweenOrderByTimestampAsc(
            String chatId, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * Conta mensagens por chatId
     */
    long countByChatId(String chatId);

    /**
     * Busca mensagens não lidas por receiverId
     */
    List<ChatMessage> findByReceiverIdAndStatusOrderByTimestampAsc(String receiverId, ChatMessage.MessageStatus status);

    /**
     * Busca as últimas N mensagens de um chat
     */
    @Query(value = "{'chatId': ?0}", sort = "{'timestamp': -1}")
    List<ChatMessage> findTopNByChatIdOrderByTimestampDesc(String chatId, int limit);
}
