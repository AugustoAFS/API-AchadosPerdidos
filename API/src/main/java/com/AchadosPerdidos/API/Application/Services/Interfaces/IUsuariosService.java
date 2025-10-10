package com.AchadosPerdidos.API.Application.Services.Interfaces;

import com.AchadosPerdidos.API.Application.DTOs.UsuariosDTO;
import com.AchadosPerdidos.API.Application.DTOs.UsuariosListDTO;

public interface IUsuariosService {
    UsuariosListDTO getAllUsuarios();
    UsuariosDTO getUsuarioById(int id);
    UsuariosDTO getUsuarioByEmail(String email);
    UsuariosDTO createUsuario(UsuariosDTO usuariosDTO);
    UsuariosDTO updateUsuario(int id, UsuariosDTO usuariosDTO);
    boolean deleteUsuario(int id);
    UsuariosListDTO getActiveUsuarios();
    UsuariosListDTO getUsuariosByRole(int tipoRoleId);
    UsuariosListDTO getUsuariosByInstitution(int instituicaoId);
    UsuariosListDTO getUsuariosByCampus(int campusId);
    UsuariosDTO authenticateUsuario(String email, String senha);
}