package com.AchadosPerdidos.API.Infrastruture.Mysql;

import com.AchadosPerdidos.API.Infrastruture.Mysql.Interfaces.IUsuariosQueries;
import org.springframework.stereotype.Component;

/**
 * Implementação das consultas SQL para a tabela Usuarios
 * Centraliza as queries para facilitar manutenção e reutilização
 */
@Component
public class UsuariosQueries implements IUsuariosQueries {
    
    // ========== QUERIES DE INSERÇÃO ==========
    
    @Override
    public String getInsertUsuario() {
        return """
            INSERT INTO Usuarios (
                Nome_Usuario, CPF_Usuario, Email_Usuario, Senha_Usuario, 
                Matricula_Usuario, Telefone_Usuario, Data_Cadastro, 
                Tipo_Role_Id, Foto_Id, Flg_Inativo, 
                Instituicao_Publica_Id, Instituicao_Privada_Id
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
    }
    
    // ========== QUERIES DE BUSCA ==========
    
    @Override
    public String getSelectById() {
        return "SELECT * FROM Usuarios WHERE Id_Usuario = ?";
    }
    
    @Override
    public String getSelectByEmail() {
        return "SELECT * FROM Usuarios WHERE Email_Usuario = ?";
    }
    
    @Override
    public String getSelectByCpf() {
        return "SELECT * FROM Usuarios WHERE CPF_Usuario = ?";
    }
    
    @Override
    public String getSelectByMatricula() {
        return "SELECT * FROM Usuarios WHERE Matricula_Usuario = ?";
    }
    
    @Override
    public String getSelectAll() {
        return "SELECT * FROM Usuarios ORDER BY Nome_Usuario";
    }
    
    @Override
    public String getSelectAtivos() {
        return "SELECT * FROM Usuarios WHERE Flg_Inativo = 0 ORDER BY Nome_Usuario";
    }
    
    @Override
    public String getSelectByTipoRole() {
        return "SELECT * FROM Usuarios WHERE Tipo_Role_Id = ? ORDER BY Nome_Usuario";
    }
    
    @Override
    public String getSelectByInstituicaoPublica() {
        return "SELECT * FROM Usuarios WHERE Instituicao_Publica_Id = ? ORDER BY Nome_Usuario";
    }
    
    @Override
    public String getSelectByInstituicaoPrivada() {
        return "SELECT * FROM Usuarios WHERE Instituicao_Privada_Id = ? ORDER BY Nome_Usuario";
    }
    
    // ========== QUERIES DE ATUALIZAÇÃO ==========
    
    @Override
    public String getUpdateUsuario() {
        return """
            UPDATE Usuarios SET 
                Nome_Usuario = ?, CPF_Usuario = ?, Email_Usuario = ?, 
                Senha_Usuario = ?, Matricula_Usuario = ?, Telefone_Usuario = ?, 
                Tipo_Role_Id = ?, Foto_Id = ?, Flg_Inativo = ?, 
                Instituicao_Publica_Id = ?, Instituicao_Privada_Id = ?
            WHERE Id_Usuario = ?
            """;
    }
    
    @Override
    public String getUpdateDesativar() {
        return "UPDATE Usuarios SET Flg_Inativo = 1 WHERE Id_Usuario = ?";
    }
    
    @Override
    public String getUpdateAtivar() {
        return "UPDATE Usuarios SET Flg_Inativo = 0 WHERE Id_Usuario = ?";
    }
    
    // ========== QUERIES DE EXCLUSÃO ==========
    
    @Override
    public String getDeleteUsuario() {
        return "DELETE FROM Usuarios WHERE Id_Usuario = ?";
    }
    
    // ========== QUERIES DE VALIDAÇÃO ==========
    
    @Override
    public String getCountEmailExists() {
        return "SELECT COUNT(*) FROM Usuarios WHERE Email_Usuario = ?";
    }
    
    @Override
    public String getCountEmailExistsExcludeUser() {
        return "SELECT COUNT(*) FROM Usuarios WHERE Email_Usuario = ? AND Id_Usuario != ?";
    }
    
    @Override
    public String getCountCpfExists() {
        return "SELECT COUNT(*) FROM Usuarios WHERE CPF_Usuario = ?";
    }
    
    @Override
    public String getCountCpfExistsExcludeUser() {
        return "SELECT COUNT(*) FROM Usuarios WHERE CPF_Usuario = ? AND Id_Usuario != ?";
    }
    
    @Override
    public String getCountMatriculaExists() {
        return "SELECT COUNT(*) FROM Usuarios WHERE Matricula_Usuario = ?";
    }
    
    @Override
    public String getCountMatriculaExistsExcludeUser() {
        return "SELECT COUNT(*) FROM Usuarios WHERE Matricula_Usuario = ? AND Id_Usuario != ?";
    }
    
    // ========== QUERIES DE CONTAGEM ==========
    
    @Override
    public String getCountTotal() {
        return "SELECT COUNT(*) FROM Usuarios";
    }
    
    @Override
    public String getCountAtivos() {
        return "SELECT COUNT(*) FROM Usuarios WHERE Flg_Inativo = 0";
    }
    
    // ========== QUERIES DE RELATÓRIOS ==========
    
    @Override
    public String getSelectWithRoleInfo() {
        return """
            SELECT u.*, r.Nome_Tipo_Role 
            FROM Usuarios u 
            LEFT JOIN Aux_Tipo_Role r ON u.Tipo_Role_Id = r.Id_Tipo_Role 
            ORDER BY u.Nome_Usuario
            """;
    }
    
    @Override
    public String getSelectWithInstituicaoInfo() {
        return """
            SELECT u.*, 
                   ip.Nome_Instituicao_Publica, 
                   ipv.Nome_Instituicao_Privada
            FROM Usuarios u 
            LEFT JOIN Instituicao_Publica ip ON u.Instituicao_Publica_Id = ip.Id_Instituicao_Publica
            LEFT JOIN Instituicao_Privada ipv ON u.Instituicao_Privada_Id = ipv.Id_Instituicao_Privada
            ORDER BY u.Nome_Usuario
            """;
    }
    
    // ========== QUERIES DE BUSCA AVANÇADA ==========
    
    @Override
    public String getSelectByNomeLike() {
        return "SELECT * FROM Usuarios WHERE Nome_Usuario LIKE ? ORDER BY Nome_Usuario";
    }
    
    @Override
    public String getSelectByTelefone() {
        return "SELECT * FROM Usuarios WHERE Telefone_Usuario = ?";
    }
    
    @Override
    public String getSelectByPeriodoCadastro() {
        return """
            SELECT * FROM Usuarios 
            WHERE Data_Cadastro BETWEEN ? AND ? 
            ORDER BY Data_Cadastro DESC
            """;
    }
}
