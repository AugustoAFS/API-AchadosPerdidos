package com.AchadosPerdidos.API.Application.Mapper;

import com.AchadosPerdidos.API.Application.DTOs.AuxLocalItemDTO;
import com.AchadosPerdidos.API.Application.DTOs.AuxLocalItemListDTO;
import com.AchadosPerdidos.API.Domain.Entity.Aux_Local_Item;
import org.springframework.stereotype.Component;

@Component
public class AuxLocalItemModelMapper {
    
    public AuxLocalItemDTO toDTO(Aux_Local_Item entity) { 
        if (entity == null) {
            return null;
        }
        
        return new AuxLocalItemDTO(
            entity.getId_Aux_Local_Item(),
            entity.getNome_Local_Item(),
            entity.getDescricao_Local_Item(),
            entity.getData_Cadastro_Local_Item(),
            entity.getFlg_Inativo_Local_Item()
        );
    }
    
    public Aux_Local_Item toEntity(AuxLocalItemDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Aux_Local_Item entity = new Aux_Local_Item();
        
        entity.setId_Aux_Local_Item(dto.Id_Aux_Local_Item());
        entity.setNome_Local_Item(dto.Nome_Local_Item());
        entity.setDescricao_Local_Item(dto.Descricao_Local_Item());
        entity.setData_Cadastro_Local_Item(dto.Data_Cadastro_Local_Item());
        entity.setFlg_Inativo_Local_Item(dto.Flg_Inativo_Local_Item());
        
        return entity;
    }

    public AuxLocalItemListDTO toListDTO(Aux_Local_Item entity) {
        if (entity == null) {
            return null;
        }

        return new AuxLocalItemListDTO(
            entity.getId_Aux_Local_Item(),
            entity.getNome_Local_Item(),
            entity.getDescricao_Local_Item(),
            entity.getData_Cadastro_Local_Item(),
            entity.getFlg_Inativo_Local_Item()
        );
    }
}
