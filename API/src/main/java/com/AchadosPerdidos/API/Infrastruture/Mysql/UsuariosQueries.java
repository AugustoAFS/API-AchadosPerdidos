package com.AchadosPerdidos.API.Infrastruture.Mysql;

import com.AchadosPerdidos.API.Domain.Entity.Usuarios;
import com.AchadosPerdidos.API.Infrastruture.Mysql.Interfaces.IUsuariosQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UsuariosQueries implements IUsuariosQueries {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Usuarios> rowMapper = new RowMapper<Usuarios>() {
        @Override
        public Usuarios mapRow(ResultSet rs, int rowNum) throws SQLException {
            Usuarios usuarios = new Usuarios();
            usuarios.setId_Usuario(rs.getInt("Id_Usuario"));
            usuarios.setNome_Usuario(rs.getString("Nome_Usuario"));
            usuarios.setCPF_Usuario(rs.getString("CPF_Usuario"));
            usuarios.setEmail_Usuario(rs.getString("Email_Usuario"));
            usuarios.setSenha_Usuario(rs.getString("Senha_Usuario"));
            usuarios.setMatricula_Usuario(rs.getString("Matricula_Usuario"));
            usuarios.setTelefone_Usuario(rs.getString("Telefone_Usuario"));
            usuarios.setData_Cadastro(rs.getDate("Data_Cadastro"));
            usuarios.setTipo_Role_Id(rs.getInt("Tipo_Role_Id"));
            usuarios.setFoto_item_id(rs.getInt("foto_item_id"));
            usuarios.setFoto_perfil_usuario(rs.getInt("foto_perfil_usuario"));
            usuarios.setFlg_Inativo(rs.getBoolean("Flg_Inativo"));
            usuarios.setId_Instituicao(rs.getInt("Id_Instituicao"));
            usuarios.setId_Empresa(rs.getInt("Id_Empresa"));
            usuarios.setId_Campus(rs.getInt("Id_Campus"));
            return usuarios;
        }
    };

    @Override
    public List<Usuarios> findAll() {
        String sql = "SELECT * FROM Usuarios ORDER BY Nome_Usuario";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Usuarios findById(int id) {
        String sql = "SELECT * FROM Usuarios WHERE Id_Usuario = ?";
        List<Usuarios> usuarios = jdbcTemplate.query(sql, rowMapper, id);
        return usuarios.isEmpty() ? null : usuarios.get(0);
    }

    @Override
    public Usuarios findByEmail(String email) {
        String sql = "SELECT * FROM Usuarios WHERE Email_Usuario = ?";
        List<Usuarios> usuarios = jdbcTemplate.query(sql, rowMapper, email);
        return usuarios.isEmpty() ? null : usuarios.get(0);
    }

    @Override
    public Usuarios findByEmailAndPassword(String email, String senha) {
        String sql = "SELECT * FROM Usuarios WHERE Email_Usuario = ? AND Senha_Usuario = ?";
        List<Usuarios> usuarios = jdbcTemplate.query(sql, rowMapper, email, senha);
        return usuarios.isEmpty() ? null : usuarios.get(0);
    }

    @Override
    public Usuarios insert(Usuarios usuarios) {
        String sql = "INSERT INTO Usuarios (Nome_Usuario, CPF_Usuario, Email_Usuario, Senha_Usuario, Matricula_Usuario, Telefone_Usuario, Data_Cadastro, Tipo_Role_Id, foto_item_id, foto_perfil_usuario, Flg_Inativo, Id_Instituicao, Id_Empresa, Id_Campus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, 
            usuarios.getNome_Usuario(),
            usuarios.getCPF_Usuario(),
            usuarios.getEmail_Usuario(),
            usuarios.getSenha_Usuario(),
            usuarios.getMatricula_Usuario(),
            usuarios.getTelefone_Usuario(),
            usuarios.getData_Cadastro(),
            usuarios.getTipo_Role_Id(),
            usuarios.getFoto_item_id(),
            usuarios.getFoto_perfil_usuario(),
            usuarios.getFlg_Inativo(),
            usuarios.getId_Instituicao(),
            usuarios.getId_Empresa(),
            usuarios.getId_Campus());
        
        // Buscar o registro inserido para retornar com o ID
        String selectSql = "SELECT * FROM Usuarios WHERE Email_Usuario = ? AND Data_Cadastro = ? ORDER BY Id_Usuario DESC LIMIT 1";
        List<Usuarios> inserted = jdbcTemplate.query(selectSql, rowMapper, 
            usuarios.getEmail_Usuario(), 
            usuarios.getData_Cadastro());
        
        return inserted.isEmpty() ? null : inserted.get(0);
    }

    @Override
    public Usuarios update(Usuarios usuarios) {
        String sql = "UPDATE Usuarios SET Nome_Usuario = ?, CPF_Usuario = ?, Email_Usuario = ?, Senha_Usuario = ?, Matricula_Usuario = ?, Telefone_Usuario = ?, Tipo_Role_Id = ?, foto_item_id = ?, foto_perfil_usuario = ?, Flg_Inativo = ?, Id_Instituicao = ?, Id_Empresa = ?, Id_Campus = ? WHERE Id_Usuario = ?";
        jdbcTemplate.update(sql, 
            usuarios.getNome_Usuario(),
            usuarios.getCPF_Usuario(),
            usuarios.getEmail_Usuario(),
            usuarios.getSenha_Usuario(),
            usuarios.getMatricula_Usuario(),
            usuarios.getTelefone_Usuario(),
            usuarios.getTipo_Role_Id(),
            usuarios.getFoto_item_id(),
            usuarios.getFoto_perfil_usuario(),
            usuarios.getFlg_Inativo(),
            usuarios.getId_Instituicao(),
            usuarios.getId_Empresa(),
            usuarios.getId_Campus(),
            usuarios.getId_Usuario());
        
        return findById(usuarios.getId_Usuario());
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM Usuarios WHERE Id_Usuario = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }

    @Override
    public List<Usuarios> findActive() {
        String sql = "SELECT * FROM Usuarios WHERE Flg_Inativo = false ORDER BY Nome_Usuario";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<Usuarios> findByRole(int tipoRoleId) {
        String sql = "SELECT * FROM Usuarios WHERE Tipo_Role_Id = ? ORDER BY Nome_Usuario";
        return jdbcTemplate.query(sql, rowMapper, tipoRoleId);
    }

    @Override
    public List<Usuarios> findByInstitution(int instituicaoId) {
        String sql = "SELECT * FROM Usuarios WHERE Id_Instituicao = ? ORDER BY Nome_Usuario";
        return jdbcTemplate.query(sql, rowMapper, instituicaoId);
    }

    @Override
    public List<Usuarios> findByCampus(int campusId) {
        String sql = "SELECT * FROM Usuarios WHERE Id_Campus = ? ORDER BY Nome_Usuario";
        return jdbcTemplate.query(sql, rowMapper, campusId);
    }
}