package com.AchadosPerdidos.API.Application.Mapper;

import com.AchadosPerdidos.API.Application.DTOs.EmpresaDTO;
import com.AchadosPerdidos.API.Application.DTOs.EmpresaListDTO;
import com.AchadosPerdidos.API.Domain.Entity.Empresa;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmpresaModelMapper {

    public EmpresaDTO toDTO(Empresa empresa) {
        if (empresa == null) {
            return null;
        }
        
        EmpresaDTO dto = new EmpresaDTO();
        dto.setId_Empresa(empresa.getId_Empresa());
        dto.setNome_Empresa(empresa.getNome_Empresa());
        dto.setCNPJ_Matriz(empresa.getCNPJ_Matriz());
        dto.setPais_Sede(empresa.getPais_Sede());
        dto.setWebsite(empresa.getWebsite());
        dto.setContato_Principal(empresa.getContato_Principal());
        dto.setFlg_Ativo(empresa.getFlg_Ativo());
        dto.setData_Cadastro(empresa.getData_Cadastro());
        
        return dto;
    }

    public Empresa toEntity(EmpresaDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Empresa empresa = new Empresa();
        empresa.setId_Empresa(dto.getId_Empresa());
        empresa.setNome_Empresa(dto.getNome_Empresa());
        empresa.setCNPJ_Matriz(dto.getCNPJ_Matriz());
        empresa.setPais_Sede(dto.getPais_Sede());
        empresa.setWebsite(dto.getWebsite());
        empresa.setContato_Principal(dto.getContato_Principal());
        empresa.setFlg_Ativo(dto.getFlg_Ativo());
        empresa.setData_Cadastro(dto.getData_Cadastro());
        
        return empresa;
    }

    public EmpresaListDTO toListDTO(List<Empresa> empresas) {
        if (empresas == null) {
            return null;
        }
        
        List<EmpresaDTO> dtoList = empresas.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        
        EmpresaListDTO listDTO = new EmpresaListDTO();
        listDTO.setEmpresas(dtoList);
        listDTO.setTotalCount(dtoList.size());
        
        return listDTO;
    }
}
