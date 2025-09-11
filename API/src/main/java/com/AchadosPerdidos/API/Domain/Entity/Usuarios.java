package com.AchadosPerdidos.API.Domain.Entity;

import java.util.Date;

public class Usuarios {
    public int Id_Usuario;
    public String Nome_Usuario;
    public String CPF_Usuario;
    public String Email_Usuario;
    public String Senha_Usuario;
    public String Matricula_Usuario;
    public String Telefone_Usuario;
    public Date Data_Cadastro;
    public int Tipo_Role_Id;
    public int Foto_Id;
    public Boolean Flg_Inativo;
    public int Instituicao_Publica_Id;
    public int Instituicao_Privada_Id;
}