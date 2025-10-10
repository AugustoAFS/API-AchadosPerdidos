package com.AchadosPerdidos.API.Application.DTOs;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosDTO {
    private int Id_Usuario;
    private String Nome_Usuario;
    private String CPF_Usuario;
    private String Email_Usuario;
    private String Senha_Usuario;
    private String Matricula_Usuario;
    private String Telefone_Usuario;
    private Date Data_Cadastro;
    private int Tipo_Role_Id;
    private Integer foto_item_id;
    private Integer foto_perfil_usuario;
    private Boolean Flg_Inativo;
    private Integer Id_Instituicao;
    private Integer Id_Empresa;
    private Integer Id_Campus;
}