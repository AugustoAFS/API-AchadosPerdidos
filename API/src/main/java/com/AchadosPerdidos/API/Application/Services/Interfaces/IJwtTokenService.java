package com.AchadosPerdidos.API.Application.Services.Interfaces;

/**
 * Interface para serviços de JWT
 */
public interface IJwtTokenService {
    
    /**
     * Gera um token JWT para o usuário
     * @param email Email do usuário
     * @param name Nome do usuário
     * @param role Role do usuário
     * @param userId ID do usuário
     * @return Token JWT
     */
    String generateToken(String email, String name, String role, String userId);
    
    /**
     * Valida um token JWT
     * @param token Token a ser validado
     * @return true se válido, false caso contrário
     */
    boolean validateToken(String token);
    
    /**
     * Extrai o email do usuário do token
     * @param token Token JWT
     * @return Email do usuário
     */
    String getEmailFromToken(String token);
    
    /**
     * Extrai o ID do usuário do token
     * @param token Token JWT
     * @return ID do usuário
     */
    String getUserIdFromToken(String token);
    
    /**
     * Verifica se o token está expirado
     * @param token Token JWT
     * @return true se expirado, false caso contrário
     */
    boolean isTokenExpired(String token);
}