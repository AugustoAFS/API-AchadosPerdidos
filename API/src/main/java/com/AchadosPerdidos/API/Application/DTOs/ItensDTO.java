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
public class ItensDTO {
    private int Id_Item;
    private String Nome_Item;
    private String Descricao_Item;
    private Date Data_Hora_Item;
    private Date Data_Cadastro;
    private Boolean Flg_Inativo;
    private Integer Status_Item_Id;
    private Integer Usuario_Id;
    private Integer Local_Id;
    private Integer Campus_Id;
    private Integer Id_Empresa;
}
