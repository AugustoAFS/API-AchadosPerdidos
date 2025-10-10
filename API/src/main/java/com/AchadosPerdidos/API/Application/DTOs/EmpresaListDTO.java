package com.AchadosPerdidos.API.Application.DTOs;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaListDTO {
    private List<EmpresaDTO> empresas;
    private int totalCount;
}
