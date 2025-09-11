package com.AchadosPerdidos.API.Domain.Repository;

import com.AchadosPerdidos.API.Domain.Entity.Usuarios;
import com.AchadosPerdidos.API.Domain.Repository.Interfaces.IUsuariosRepository;
import com.AchadosPerdidos.API.Infrastruture.Mysql.Interfaces.IUsuariosQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Implementação do Repository de Usuários usando JdbcTemplate
 * Similar ao Dapper do .NET - SQL queries diretas
 */
@Repository
public class UsuariosRepository implements IUsuariosRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private IUsuariosQueries usuariosQueries;
    
    /**
     * RowMapper para converter ResultSet em Usuarios
     */
    private final RowMapper<Usuarios> usuarioRowMapper = new RowMapper<Usuarios>() {
        @Override
        public Usuarios mapRow(ResultSet rs, int rowNum) throws SQLException {
            Usuarios usuario = new Usuarios();
            usuario.Id_Usuario = rs.getInt("Id_Usuario");
            usuario.Nome_Usuario = rs.getString("Nome_Usuario");
            usuario.CPF_Usuario = rs.getString("CPF_Usuario");
            usuario.Email_Usuario = rs.getString("Email_Usuario");
            usuario.Senha_Usuario = rs.getString("Senha_Usuario");
            usuario.Matricula_Usuario = rs.getString("Matricula_Usuario");
            usuario.Telefone_Usuario = rs.getString("Telefone_Usuario");
            usuario.Data_Cadastro = rs.getTimestamp("Data_Cadastro");
            usuario.Tipo_Role_Id = rs.getInt("Tipo_Role_Id");
            usuario.Foto_Id = rs.getInt("Foto_Id");
            usuario.Flg_Inativo = rs.getBoolean("Flg_Inativo");
            usuario.Instituicao_Publica_Id = rs.getInt("Instituicao_Publica_Id");
            usuario.Instituicao_Privada_Id = rs.getInt("Instituicao_Privada_Id");
            return usuario;
        }
    };
    
    @Override
    public int inserir(Usuarios usuario) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(usuariosQueries.getInsertUsuario(), Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.Nome_Usuario);
            ps.setString(2, usuario.CPF_Usuario);
            ps.setString(3, usuario.Email_Usuario);
            ps.setString(4, usuario.Senha_Usuario);
            ps.setString(5, usuario.Matricula_Usuario);
            ps.setString(6, usuario.Telefone_Usuario);
            ps.setTimestamp(7, new java.sql.Timestamp(usuario.Data_Cadastro.getTime()));
            ps.setInt(8, usuario.Tipo_Role_Id);
            ps.setInt(9, usuario.Foto_Id);
            ps.setBoolean(10, usuario.Flg_Inativo != null ? usuario.Flg_Inativo : false);
            ps.setInt(11, usuario.Instituicao_Publica_Id);
            ps.setInt(12, usuario.Instituicao_Privada_Id);
            return ps;
        }, keyHolder);
        
        return keyHolder.getKey().intValue();
    }
    
    @Override
    public Usuarios buscarPorId(int id) {
        List<Usuarios> usuarios = jdbcTemplate.query(usuariosQueries.getSelectById(), usuarioRowMapper, id);
        return usuarios.isEmpty() ? null : usuarios.get(0);
    }
    
    @Override
    public Usuarios buscarPorEmail(String email) {
        List<Usuarios> usuarios = jdbcTemplate.query(usuariosQueries.getSelectByEmail(), usuarioRowMapper, email);
        return usuarios.isEmpty() ? null : usuarios.get(0);
    }
    
    @Override
    public Usuarios buscarPorCPF(String cpf) {
        List<Usuarios> usuarios = jdbcTemplate.query(usuariosQueries.getSelectByCpf(), usuarioRowMapper, cpf);
        return usuarios.isEmpty() ? null : usuarios.get(0);
    }
    
    @Override
    public Usuarios buscarPorMatricula(String matricula) {
        List<Usuarios> usuarios = jdbcTemplate.query(usuariosQueries.getSelectByMatricula(), usuarioRowMapper, matricula);
        return usuarios.isEmpty() ? null : usuarios.get(0);
    }
    
    @Override
    public List<Usuarios> listarTodos() {
        return jdbcTemplate.query(usuariosQueries.getSelectAll(), usuarioRowMapper);
    }
    
    @Override
    public List<Usuarios> listarAtivos() {
        return jdbcTemplate.query(usuariosQueries.getSelectAtivos(), usuarioRowMapper);
    }
    
    @Override
    public List<Usuarios> listarPorTipoRole(int tipoRoleId) {
        return jdbcTemplate.query(usuariosQueries.getSelectByTipoRole(), usuarioRowMapper, tipoRoleId);
    }
    
    @Override
    public List<Usuarios> listarPorInstituicaoPublica(int instituicaoId) {
        return jdbcTemplate.query(usuariosQueries.getSelectByInstituicaoPublica(), usuarioRowMapper, instituicaoId);
    }
    
    @Override
    public List<Usuarios> listarPorInstituicaoPrivada(int instituicaoId) {
        return jdbcTemplate.query(usuariosQueries.getSelectByInstituicaoPrivada(), usuarioRowMapper, instituicaoId);
    }
    
    @Override
    public boolean atualizar(Usuarios usuario) {
        int rowsAffected = jdbcTemplate.update(usuariosQueries.getUpdateUsuario(),
            usuario.Nome_Usuario,
            usuario.CPF_Usuario,
            usuario.Email_Usuario,
            usuario.Senha_Usuario,
            usuario.Matricula_Usuario,
            usuario.Telefone_Usuario,
            usuario.Tipo_Role_Id,
            usuario.Foto_Id,
            usuario.Flg_Inativo,
            usuario.Instituicao_Publica_Id,
            usuario.Instituicao_Privada_Id,
            usuario.Id_Usuario
        );
        
        return rowsAffected > 0;
    }
    
    @Override
    public boolean desativar(int id) {
        int rowsAffected = jdbcTemplate.update(usuariosQueries.getUpdateDesativar(), id);
        return rowsAffected > 0;
    }
    
    @Override
    public boolean ativar(int id) {
        int rowsAffected = jdbcTemplate.update(usuariosQueries.getUpdateAtivar(), id);
        return rowsAffected > 0;
    }
    
    @Override
    public boolean deletar(int id) {
        int rowsAffected = jdbcTemplate.update(usuariosQueries.getDeleteUsuario(), id);
        return rowsAffected > 0;
    }
    
    @Override
    public boolean emailExiste(String email, Integer idUsuario) {
        String sql = (idUsuario != null) ? 
            usuariosQueries.getCountEmailExistsExcludeUser() : 
            usuariosQueries.getCountEmailExists();
        
        Object[] params = (idUsuario != null) ? 
            new Object[]{email, idUsuario} : 
            new Object[]{email};
        
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, params);
        return count != null && count > 0;
    }
    
    @Override
    public boolean cpfExiste(String cpf, Integer idUsuario) {
        String sql = (idUsuario != null) ? 
            usuariosQueries.getCountCpfExistsExcludeUser() : 
            usuariosQueries.getCountCpfExists();
        
        Object[] params = (idUsuario != null) ? 
            new Object[]{cpf, idUsuario} : 
            new Object[]{cpf};
        
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, params);
        return count != null && count > 0;
    }
    
    @Override
    public boolean matriculaExiste(String matricula, Integer idUsuario) {
        String sql = (idUsuario != null) ? 
            usuariosQueries.getCountMatriculaExistsExcludeUser() : 
            usuariosQueries.getCountMatriculaExists();
        
        Object[] params = (idUsuario != null) ? 
            new Object[]{matricula, idUsuario} : 
            new Object[]{matricula};
        
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, params);
        return count != null && count > 0;
    }
    
    @Override
    public int contarTotal() {
        Integer count = jdbcTemplate.queryForObject(usuariosQueries.getCountTotal(), Integer.class);
        return count != null ? count : 0;
    }
    
    @Override
    public int contarAtivos() {
        Integer count = jdbcTemplate.queryForObject(usuariosQueries.getCountAtivos(), Integer.class);
        return count != null ? count : 0;
    }
}
