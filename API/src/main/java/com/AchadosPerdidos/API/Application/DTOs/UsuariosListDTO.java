package com.AchadosPerdidos.API.Application.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;

@JsonPropertyOrder({
    "Id_Usuario",
    "Nome_Usuario",
    "CPF_Usuario",
    "Email_Usuario",
    "Matricula_Usuario",
    "Telefone_Usuario",
    "Tipo_Role_Id",
    "foto_item_id",
    "foto_perfil_usuario",
    "Flg_Inativo",
    "Id_Instituicao",
    "Id_Empresa",
    "Id_Campus",
    "Data_Cadastro"
})
public record UsuariosListDTO(
    @JsonProperty("Id_Usuario") Integer idUsuario,
    @JsonProperty("Nome_Usuario") String nomeUsuario,
    @JsonProperty("CPF_Usuario") String cpfUsuario,
    @JsonProperty("Email_Usuario") String emailUsuario,
    @JsonProperty("Matricula_Usuario") String matriculaUsuario,
    @JsonProperty("Telefone_Usuario") String telefoneUsuario,
    @JsonProperty("Tipo_Role_Id") int tipoRoleId,
    @JsonProperty("foto_item_id") Integer fotoId,
    @JsonProperty("foto_perfil_usuario") Integer fotoPerfilUsuario,
    @JsonProperty("Flg_Inativo") Boolean flgInativo,
    @JsonProperty("Id_Instituicao") Integer instituicaoPublicaId,
    @JsonProperty("Id_Empresa") Integer instituicaoPrivadaId,
    @JsonProperty("Id_Campus") Integer campusId,
    @JsonProperty("Data_Cadastro") Date dataCadastro
) {}