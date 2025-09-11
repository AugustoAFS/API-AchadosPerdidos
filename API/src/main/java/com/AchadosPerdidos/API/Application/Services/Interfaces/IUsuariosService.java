package com.AchadosPerdidos.API.Application.Services.Interfaces;

import com.AchadosPerdidos.API.Application.DTOs.UsuariosDTO;
import java.util.List;

/**
 * Interface do Service de Usuários
 * Define os contratos para operações de negócio relacionadas a usuários
 */
public interface IUsuariosService {
    
    /**
     * Criar um novo usuário
     * @param dto - DTO com os dados do usuário
     * @return DTO do usuário criado
     */
    UsuariosDTO criarUsuario(UsuariosDTO dto);
    
    /**
     * Buscar usuário por ID
     * @param id - ID do usuário
     * @return DTO do usuário encontrado ou null se não existir
     */
    UsuariosDTO buscarPorId(int id);
    
    /**
     * Buscar usuário por email
     * @param email - Email do usuário
     * @return DTO do usuário encontrado ou null se não existir
     */
    UsuariosDTO buscarPorEmail(String email);
    
    /**
     * Buscar usuário por CPF
     * @param cpf - CPF do usuário
     * @return DTO do usuário encontrado ou null se não existir
     */
    UsuariosDTO buscarPorCPF(String cpf);
    
    /**
     * Buscar usuário por matrícula
     * @param matricula - Matrícula do usuário
     * @return DTO do usuário encontrado ou null se não existir
     */
    UsuariosDTO buscarPorMatricula(String matricula);
    
    /**
     * Listar todos os usuários
     * @return Lista de DTOs dos usuários
     */
    List<UsuariosDTO> listarTodos();
    
    /**
     * Listar usuários ativos
     * @return Lista de DTOs dos usuários ativos
     */
    List<UsuariosDTO> listarAtivos();
    
    /**
     * Listar usuários por tipo de role
     * @param tipoRoleId - ID do tipo de role
     * @return Lista de DTOs dos usuários com o tipo de role especificado
     */
    List<UsuariosDTO> listarPorTipoRole(int tipoRoleId);
    
    /**
     * Atualizar usuário
     * @param id - ID do usuário
     * @param dto - DTO com os novos dados do usuário
     * @return DTO do usuário atualizado
     */
    UsuariosDTO atualizarUsuario(int id, UsuariosDTO dto);
    
    /**
     * Desativar usuário (soft delete)
     * @param id - ID do usuário
     * @return true se desativado com sucesso, false caso contrário
     */
    boolean desativarUsuario(int id);
    
    /**
     * Ativar usuário
     * @param id - ID do usuário
     * @return true se ativado com sucesso, false caso contrário
     */
    boolean ativarUsuario(int id);
    
    /**
     * Deletar usuário permanentemente
     * @param id - ID do usuário
     * @return true se deletado com sucesso, false caso contrário
     */
    boolean deletarUsuario(int id);
    
    /**
     * Validar se email já existe
     * @param email - Email a ser validado
     * @param idUsuario - ID do usuário (opcional, para excluir da validação)
     * @return true se email já existe, false caso contrário
     */
    boolean emailJaExiste(String email, Integer idUsuario);
    
    /**
     * Validar se CPF já existe
     * @param cpf - CPF a ser validado
     * @param idUsuario - ID do usuário (opcional, para excluir da validação)
     * @return true se CPF já existe, false caso contrário
     */
    boolean cpfJaExiste(String cpf, Integer idUsuario);
    
    /**
     * Validar se matrícula já existe
     * @param matricula - Matrícula a ser validada
     * @param idUsuario - ID do usuário (opcional, para excluir da validação)
     * @return true se matrícula já existe, false caso contrário
     */
    boolean matriculaJaExiste(String matricula, Integer idUsuario);
}
