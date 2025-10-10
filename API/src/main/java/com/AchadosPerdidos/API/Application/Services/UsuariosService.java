package com.AchadosPerdidos.API.Application.Services;

import com.AchadosPerdidos.API.Application.DTOs.UsuariosDTO;
import com.AchadosPerdidos.API.Application.DTOs.UsuariosListDTO;
import com.AchadosPerdidos.API.Application.Mapper.UsuariosModelMapper;
import com.AchadosPerdidos.API.Application.Services.Interfaces.IUsuariosService;
import com.AchadosPerdidos.API.Domain.Entity.Usuarios;
import com.AchadosPerdidos.API.Domain.Repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UsuariosService implements IUsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private UsuariosModelMapper usuariosModelMapper;

    @Override
    public UsuariosListDTO getAllUsuarios() {
        List<Usuarios> usuarios = usuariosRepository.findAll();
        return usuariosModelMapper.toListDTO(usuarios);
    }

    @Override
    public UsuariosDTO getUsuarioById(int id) {
        Usuarios usuarios = usuariosRepository.findById(id);
        return usuariosModelMapper.toDTO(usuarios);
    }

    @Override
    public UsuariosDTO getUsuarioByEmail(String email) {
        Usuarios usuarios = usuariosRepository.findByEmail(email);
        return usuariosModelMapper.toDTO(usuarios);
    }

    @Override
    public UsuariosDTO createUsuario(UsuariosDTO usuariosDTO) {
        Usuarios usuarios = usuariosModelMapper.toEntity(usuariosDTO);
        usuarios.setData_Cadastro(new Date());
        usuarios.setFlg_Inativo(false);
        
        Usuarios savedUsuarios = usuariosRepository.save(usuarios);
        return usuariosModelMapper.toDTO(savedUsuarios);
    }

    @Override
    public UsuariosDTO updateUsuario(int id, UsuariosDTO usuariosDTO) {
        Usuarios existingUsuarios = usuariosRepository.findById(id);
        if (existingUsuarios == null) {
            return null;
        }
        
        existingUsuarios.setNome_Usuario(usuariosDTO.getNome_Usuario());
        existingUsuarios.setCPF_Usuario(usuariosDTO.getCPF_Usuario());
        existingUsuarios.setEmail_Usuario(usuariosDTO.getEmail_Usuario());
        existingUsuarios.setSenha_Usuario(usuariosDTO.getSenha_Usuario());
        existingUsuarios.setMatricula_Usuario(usuariosDTO.getMatricula_Usuario());
        existingUsuarios.setTelefone_Usuario(usuariosDTO.getTelefone_Usuario());
        existingUsuarios.setTipo_Role_Id(usuariosDTO.getTipo_Role_Id());
        existingUsuarios.setFoto_item_id(usuariosDTO.getFoto_item_id());
        existingUsuarios.setFoto_perfil_usuario(usuariosDTO.getFoto_perfil_usuario());
        existingUsuarios.setFlg_Inativo(usuariosDTO.getFlg_Inativo());
        existingUsuarios.setId_Instituicao(usuariosDTO.getId_Instituicao());
        existingUsuarios.setId_Empresa(usuariosDTO.getId_Empresa());
        existingUsuarios.setId_Campus(usuariosDTO.getId_Campus());
        
        Usuarios updatedUsuarios = usuariosRepository.save(existingUsuarios);
        return usuariosModelMapper.toDTO(updatedUsuarios);
    }

    @Override
    public boolean deleteUsuario(int id) {
        Usuarios usuarios = usuariosRepository.findById(id);
        if (usuarios == null) {
            return false;
        }
        
        return usuariosRepository.deleteById(id);
    }

    @Override
    public UsuariosListDTO getActiveUsuarios() {
        List<Usuarios> activeUsuarios = usuariosRepository.findActive();
        return usuariosModelMapper.toListDTO(activeUsuarios);
    }

    @Override
    public UsuariosListDTO getUsuariosByRole(int tipoRoleId) {
        List<Usuarios> usuarios = usuariosRepository.findByRole(tipoRoleId);
        return usuariosModelMapper.toListDTO(usuarios);
    }

    @Override
    public UsuariosListDTO getUsuariosByInstitution(int instituicaoId) {
        List<Usuarios> usuarios = usuariosRepository.findByInstitution(instituicaoId);
        return usuariosModelMapper.toListDTO(usuarios);
    }

    @Override
    public UsuariosListDTO getUsuariosByCampus(int campusId) {
        List<Usuarios> usuarios = usuariosRepository.findByCampus(campusId);
        return usuariosModelMapper.toListDTO(usuarios);
    }

    @Override
    public UsuariosDTO authenticateUsuario(String email, String senha) {
        Usuarios usuarios = usuariosRepository.findByEmailAndPassword(email, senha);
        return usuariosModelMapper.toDTO(usuarios);
    }
}