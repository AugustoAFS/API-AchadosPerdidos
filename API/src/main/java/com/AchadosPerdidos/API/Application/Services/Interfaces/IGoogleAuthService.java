package com.AchadosPerdidos.API.Application.Services.Interfaces;

import com.AchadosPerdidos.API.Application.DTOs.GoogleUserDTO;

/**
 * Interface para serviço de autenticação Google OAuth2
 */
public interface IGoogleAuthService {
    
    /**
     * Constrói a URL de autorização do Google
     * @return URL para redirecionamento do usuário
     */
    String buildGoogleAuthorizationUrl();
    
    /**
     * Obtém informações do usuário usando o código de autorização
     * @param code Código de autorização retornado pelo Google
     * @return Informações do usuário do Google
     */
    GoogleUserDTO getUserInfoFromAuthorizationCode(String code);
    
    /**
     * Obtém a URI de redirecionamento configurada
     * @return URI de redirecionamento
     */
    String getRedirectUri();
}
