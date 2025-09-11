package com.AchadosPerdidos.API.Application.Mapper;

import com.AchadosPerdidos.API.Application.DTOs.UsuariosDTO;
import com.AchadosPerdidos.API.Domain.Entity.Usuarios;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Mapper usando ModelMapper - SIMPLES E EFICIENTE!
 * 
 * Vantagens:
 * - Configuração automática
 * - Mapeamento por convenção
 * - Fácil de usar
 * - Spring integrado
 */
@Component
public class UsuariosModelMapper {
    
    @Autowired
    private ModelMapper modelMapper;
    
    /**
     * Converte Entity para DTO
     */
    public UsuariosDTO toDTO(Usuarios entity) {
        if (entity == null) {
            return null;
        }
        
        UsuariosDTO dto = modelMapper.map(entity, UsuariosDTO.class);
        
        // Mapeamentos manuais para campos com nomes diferentes
        dto.setIdUsuario(entity.Id_Usuario);
        dto.setNomeUsuario(entity.Nome_Usuario);
        dto.setCpfUsuario(entity.CPF_Usuario);
        dto.setEmailUsuario(entity.Email_Usuario);
        dto.setSenhaUsuario(entity.Senha_Usuario);
        dto.setMatriculaUsuario(entity.Matricula_Usuario);
        dto.setTelefoneUsuario(entity.Telefone_Usuario);
        dto.setDataCadastro(entity.Data_Cadastro);
        dto.setTipoRoleId(entity.Tipo_Role_Id);
        dto.setFotoId(entity.Foto_Id);
        dto.setFlgInativo(entity.Flg_Inativo);
        dto.setInstituicaoPublicaId(entity.Instituicao_Publica_Id);
        dto.setInstituicaoPrivadaId(entity.Instituicao_Privada_Id);
        
        return dto;
    }
    
    /**
     * Converte DTO para Entity
     */
    public Usuarios toEntity(UsuariosDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Usuarios entity = modelMapper.map(dto, Usuarios.class);
        
        // Mapeamentos manuais para campos com nomes diferentes
        entity.Id_Usuario = dto.getIdUsuario();
        entity.Nome_Usuario = dto.getNomeUsuario();
        entity.CPF_Usuario = dto.getCpfUsuario();
        entity.Email_Usuario = dto.getEmailUsuario();
        entity.Senha_Usuario = dto.getSenhaUsuario();
        entity.Matricula_Usuario = dto.getMatriculaUsuario();
        entity.Telefone_Usuario = dto.getTelefoneUsuario();
        entity.Data_Cadastro = dto.getDataCadastro();
        entity.Tipo_Role_Id = dto.getTipoRoleId();
        entity.Foto_Id = dto.getFotoId();
        entity.Flg_Inativo = dto.getFlgInativo();
        entity.Instituicao_Publica_Id = dto.getInstituicaoPublicaId();
        entity.Instituicao_Privada_Id = dto.getInstituicaoPrivadaId();
        
        return entity;
    }
}
