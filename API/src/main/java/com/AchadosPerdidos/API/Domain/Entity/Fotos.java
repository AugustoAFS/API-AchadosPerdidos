package com.AchadosPerdidos.API.Domain.Entity;

import com.AchadosPerdidos.API.Domain.Enum.Provedor_Armazenamento;

import java.util.Date;

public class Fotos {
    public int Id_Foto;
    public Integer Usuario_Id;
    public Integer Item_Id;
    public Provedor_Armazenamento Provedor_Armazenamento;
    public String Nome_Bucket;
    public String Chave_Objeto;
    public String Chave_Armazenamento;
    public String Url_Arquivo;
    public String Nome_Original;
    public Long Tamanho_Bytes;
    public Integer Largura;
    public Integer Altura;
    public Boolean Perfil_Usuario;
    public Boolean Foto_Item;
    public Boolean Flg_Inativo;
    public Date Data_Envio;
    public Date Data_Exclusao;
    public Date Data_Atualizacao;
}