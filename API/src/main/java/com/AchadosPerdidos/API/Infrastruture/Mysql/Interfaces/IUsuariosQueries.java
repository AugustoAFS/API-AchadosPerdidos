package com.AchadosPerdidos.API.Infrastruture.Mysql.Interfaces;

/**
 * Interface para consultas SQL da tabela Usuarios
 * Define os contratos para todas as queries relacionadas a usuários
 */
public interface IUsuariosQueries {
    
    // ========== QUERIES DE INSERÇÃO ==========
    
    /**
     * Query para inserir novo usuário
     * @return SQL para inserção de usuário
     */
    String getInsertUsuario();
    
    // ========== QUERIES DE BUSCA ==========
    
    /**
     * Query para buscar usuário por ID
     * @return SQL para busca por ID
     */
    String getSelectById();
    
    /**
     * Query para buscar usuário por email
     * @return SQL para busca por email
     */
    String getSelectByEmail();
    
    /**
     * Query para buscar usuário por CPF
     * @return SQL para busca por CPF
     */
    String getSelectByCpf();
    
    /**
     * Query para buscar usuário por matrícula
     * @return SQL para busca por matrícula
     */
    String getSelectByMatricula();
    
    /**
     * Query para listar todos os usuários
     * @return SQL para listar todos
     */
    String getSelectAll();
    
    /**
     * Query para listar usuários ativos
     * @return SQL para listar ativos
     */
    String getSelectAtivos();
    
    /**
     * Query para listar usuários por tipo de role
     * @return SQL para listar por tipo de role
     */
    String getSelectByTipoRole();
    
    /**
     * Query para listar usuários por instituição pública
     * @return SQL para listar por instituição pública
     */
    String getSelectByInstituicaoPublica();
    
    /**
     * Query para listar usuários por instituição privada
     * @return SQL para listar por instituição privada
     */
    String getSelectByInstituicaoPrivada();
    
    // ========== QUERIES DE ATUALIZAÇÃO ==========
    
    /**
     * Query para atualizar usuário
     * @return SQL para atualização
     */
    String getUpdateUsuario();
    
    /**
     * Query para desativar usuário
     * @return SQL para desativação
     */
    String getUpdateDesativar();
    
    /**
     * Query para ativar usuário
     * @return SQL para ativação
     */
    String getUpdateAtivar();
    
    // ========== QUERIES DE EXCLUSÃO ==========
    
    /**
     * Query para deletar usuário permanentemente
     * @return SQL para exclusão
     */
    String getDeleteUsuario();
    
    // ========== QUERIES DE VALIDAÇÃO ==========
    
    /**
     * Query para verificar se email já existe (sem excluir usuário específico)
     * @return SQL para verificar email
     */
    String getCountEmailExists();
    
    /**
     * Query para verificar se email já existe (excluindo usuário específico)
     * @return SQL para verificar email excluindo usuário
     */
    String getCountEmailExistsExcludeUser();
    
    /**
     * Query para verificar se CPF já existe (sem excluir usuário específico)
     * @return SQL para verificar CPF
     */
    String getCountCpfExists();
    
    /**
     * Query para verificar se CPF já existe (excluindo usuário específico)
     * @return SQL para verificar CPF excluindo usuário
     */
    String getCountCpfExistsExcludeUser();
    
    /**
     * Query para verificar se matrícula já existe (sem excluir usuário específico)
     * @return SQL para verificar matrícula
     */
    String getCountMatriculaExists();
    
    /**
     * Query para verificar se matrícula já existe (excluindo usuário específico)
     * @return SQL para verificar matrícula excluindo usuário
     */
    String getCountMatriculaExistsExcludeUser();
    
    // ========== QUERIES DE CONTAGEM ==========
    
    /**
     * Query para contar total de usuários
     * @return SQL para contar total
     */
    String getCountTotal();
    
    /**
     * Query para contar usuários ativos
     * @return SQL para contar ativos
     */
    String getCountAtivos();
    
    // ========== QUERIES DE RELATÓRIOS ==========
    
    /**
     * Query para buscar usuários com informações de role
     * @return SQL para buscar com informações de role
     */
    String getSelectWithRoleInfo();
    
    /**
     * Query para buscar usuários com informações de instituição
     * @return SQL para buscar com informações de instituição
     */
    String getSelectWithInstituicaoInfo();
    
    // ========== QUERIES DE BUSCA AVANÇADA ==========
    
    /**
     * Query para buscar usuários por nome (LIKE)
     * @return SQL para busca por nome
     */
    String getSelectByNomeLike();
    
    /**
     * Query para buscar usuários por telefone
     * @return SQL para busca por telefone
     */
    String getSelectByTelefone();
    
    /**
     * Query para buscar usuários criados em um período
     * @return SQL para busca por período
     */
    String getSelectByPeriodoCadastro();
}
