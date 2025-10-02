package com.AchadosPerdidos.API.Application.Services.Interfaces;

import com.AchadosPerdidos.API.Application.DTOs.UsuariosDTO;
import com.AchadosPerdidos.API.Application.DTOs.UsuariosListDTO;
import java.util.List;

public interface IUsuariosService {

    UsuariosListDTO criarUsuario(UsuariosDTO dto);

    UsuariosListDTO buscarPorId(int id);

    UsuariosListDTO buscarPorEmail(String email);

    List<UsuariosListDTO> listarTodos();

    UsuariosListDTO atualizarUsuario(int id, UsuariosDTO dto);

    boolean deletarUsuario(int id);
}