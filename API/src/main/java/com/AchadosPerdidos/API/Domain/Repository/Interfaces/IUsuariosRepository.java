package com.AchadosPerdidos.API.Domain.Repository.Interfaces;

import com.AchadosPerdidos.API.Domain.Entity.Usuarios;
import java.util.List;

/**
 * Interface do Repository de Usuários
 * Define os contratos para operações de acesso a dados usando SQL queries
 */
public interface IUsuariosRepository {
    
    /**
     * Inserir novo usuário
     * @param usuario - Entidade do usuário
     * @return ID do usuário inserido
     */
    int inserir(Usuarios usuario);
    
    /**
     * Buscar usuário por ID
     * @param id - ID do usuário
     * @return Usuário encontrado ou null
     */
    Usuarios buscarPorId(int id);
    
    /**
     * Buscar usuário por email
     * @param email - Email do usuário
     * @return Usuário encontrado ou null
     */
    Usuarios buscarPorEmail(String email);
    
    /**
     * Buscar usuário por CPF
     * @param cpf - CPF do usuário
     * @return Usuário encontrado ou null
     */
    Usuarios buscarPorCPF(String cpf);
    
    /**
     * Buscar usuário por matrícula
     * @param matricula - Matrícula do usuário
     * @return Usuário encontrado ou null
     */
    Usuarios buscarPorMatricula(String matricula);
    
    /**
     * Listar todos os usuários
     * @return Lista de usuários
     */
    List<Usuarios> listarTodos();
    
    /**
     * Listar usuários ativos
     * @return Lista de usuários ativos
     */
    List<Usuarios> listarAtivos();
    
    /**
     * Listar usuários por tipo de role
     * @param tipoRoleId - ID do tipo de role
     * @return Lista de usuários com o tipo de role especificado
     */
    List<Usuarios> listarPorTipoRole(int tipoRoleId);
    
    /**
     * Listar usuários por instituição pública
     * @param instituicaoId - ID da instituição pública
     * @return Lista de usuários da instituição
     */
    List<Usuarios> listarPorInstituicaoPublica(int instituicaoId);
    
    /**
     * Listar usuários por instituição privada
     * @param instituicaoId - ID da instituição privada
     * @return Lista de usuários da instituição
     */
    List<Usuarios> listarPorInstituicaoPrivada(int instituicaoId);
    
    /**
     * Atualizar usuário
     * @param usuario - Entidade do usuário com dados atualizados
     * @return true se atualizado com sucesso
     */
    boolean atualizar(Usuarios usuario);
    
    /**
     * Desativar usuário (soft delete)
     * @param id - ID do usuário
     * @return true se desativado com sucesso
     */
    boolean desativar(int id);
    
    /**
     * Ativar usuário
     * @param id - ID do usuário
     * @return true se ativado com sucesso
     */
    boolean ativar(int id);
    
    /**
     * Deletar usuário permanentemente
     * @param id - ID do usuário
     * @return true se deletado com sucesso
     */
    boolean deletar(int id);
    
    /**
     * Verificar se email já existe
     * @param email - Email a ser verificado
     * @param idUsuario - ID do usuário (opcional, para excluir da verificação)
     * @return true se email já existe
     */
    boolean emailExiste(String email, Integer idUsuario);
    
    /**
     * Verificar se CPF já existe
     * @param cpf - CPF a ser verificado
     * @param idUsuario - ID do usuário (opcional, para excluir da verificação)
     * @return true se CPF já existe
     */
    boolean cpfExiste(String cpf, Integer idUsuario);
    
    /**
     * Verificar se matrícula já existe
     * @param matricula - Matrícula a ser verificada
     * @param idUsuario - ID do usuário (opcional, para excluir da verificação)
     * @return true se matrícula já existe
     */
    boolean matriculaExiste(String matricula, Integer idUsuario);
    
    /**
     * Contar total de usuários
     * @return Número total de usuários
     */
    int contarTotal();
    
    /**
     * Contar usuários ativos
     * @return Número de usuários ativos
     */
    int contarAtivos();
}
