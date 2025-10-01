package com.AchadosPerdidos.API.Application.Services.Interfaces;

import com.AchadosPerdidos.API.Application.DTOs.UsuariosDTO;
import com.AchadosPerdidos.API.Application.DTOs.UsuariosListDTO;
import java.util.List;

/**
 * Interface do Service de Usuários
 * Define os contratos para operações de negócio relacionadas a usuários
 */
public interface IUsuariosService {
    
    /**
     * Criar um novo usuário
     */
    UsuariosListDTO criarUsuario(UsuariosDTO dto);
    
    /**
     * Buscar usuário por ID
     */
    UsuariosListDTO buscarPorId(int id);
    
    /**
     * Buscar usuário por email
     */
    UsuariosListDTO buscarPorEmail(String email);
    
    /**
     * Listar todos os usuários
     */
    List<UsuariosListDTO> listarTodos();
    
    /**
     * Atualizar usuário
     */
    UsuariosListDTO atualizarUsuario(int id, UsuariosDTO dto);
    
    /**
     * Deletar usuário permanentemente
     */
    boolean deletarUsuario(int id);
}
