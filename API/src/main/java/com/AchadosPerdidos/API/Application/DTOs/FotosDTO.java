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
public class FotosDTO {
    private int Id_Foto;
    private Integer Usuario_Id;
    private Integer Item_Id;
    private String Provedor_Armazenamento;
    private String Nome_Bucket;
    private String Chave_Objeto;
    private String Url_Arquivo;
    private String Nome_Original;
    private Integer Largura;
    private Integer Altura;
    private Boolean Perfil_Usuario;
    private Boolean Foto_Item;
    private Boolean Flg_Inativo;
    private Date Data_Envio;
    private Date Data_Exclusao;
    private Date Data_Atualizacao;
}
