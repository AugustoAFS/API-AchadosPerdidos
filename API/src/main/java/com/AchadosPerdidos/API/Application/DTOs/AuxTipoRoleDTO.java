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
public class AuxTipoRoleDTO {
    private int Id_Tipo_Role;
    private String Nome_Tipo_Role;
    private Date Data_Cadastro;
    private Boolean Flg_Inativo;
}
