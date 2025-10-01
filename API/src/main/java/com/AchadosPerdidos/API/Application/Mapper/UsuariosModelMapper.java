package com.AchadosPerdidos.API.Application.Mapper;
import com.AchadosPerdidos.API.Application.DTOs.UsuariosDTO;
import com.AchadosPerdidos.API.Application.DTOs.UsuariosListDTO;
import com.AchadosPerdidos.API.Domain.Entity.Usuarios;
import org.springframework.stereotype.Component;

@Component
public class UsuariosModelMapper {
    
    public UsuariosDTO toDTO(Usuarios entity) { 
        if (entity == null) {
            return null;
        }
        
        return new UsuariosDTO(
            entity.getId_Usuario(),
            entity.getNome_Usuario(),
            entity.getCPF_Usuario(),
            entity.getEmail_Usuario(),
            entity.getSenha_Usuario(),
            entity.getMatricula_Usuario(),
            entity.getTelefone_Usuario(),
            entity.getTipo_Role_Id(),
            entity.getFoto_item_id(),
            entity.getFoto_perfil_usuario(),
            entity.getFlg_Inativo(),
            entity.getId_Instituicao(),
            entity.getId_Empresa(),
            entity.getId_Campus(),
            entity.getData_Cadastro()
        );
    }
    
    public Usuarios toEntity(UsuariosDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Usuarios entity = new Usuarios();
        
        entity.setId_Usuario(dto.Id_Usuario());
        entity.setNome_Usuario(dto.Nome_Usuario());
        entity.setCPF_Usuario(dto.CPF_Usuario());
        entity.setEmail_Usuario(dto.Email_Usuario());
        entity.setSenha_Usuario(dto.Senha_Usuario());
        entity.setMatricula_Usuario(dto.Matricula_Usuario());
        entity.setTelefone_Usuario(dto.Telefone_Usuario());
        entity.setTipo_Role_Id(dto.Tipo_Role_Id());
        entity.setFoto_item_id(dto.foto_item_id());
        entity.setFoto_perfil_usuario(dto.foto_perfil_usuario());
        entity.setFlg_Inativo(dto.Flg_Inativo());
        entity.setId_Instituicao(dto.Id_Instituicao());
        entity.setId_Empresa(dto.Id_Empresa());
        entity.setId_Campus(dto.Id_Campus());
        entity.setData_Cadastro(dto.Data_Cadastro());
        
        return entity;
    }

    public UsuariosListDTO toListDTO(Usuarios entity) {
        if (entity == null) {
            return null;
        }

        return new UsuariosListDTO(
            entity.getId_Usuario(),
            entity.getNome_Usuario(),
            entity.getCPF_Usuario(),
            entity.getEmail_Usuario(),
            entity.getMatricula_Usuario(),
            entity.getTelefone_Usuario(),
            entity.getTipo_Role_Id(),
            entity.getFoto_item_id(),
            entity.getFoto_perfil_usuario(),
            entity.getFlg_Inativo(),
            entity.getId_Instituicao(),
            entity.getId_Empresa(),
            entity.getId_Campus(),
            entity.getData_Cadastro()
        );
    }
}
