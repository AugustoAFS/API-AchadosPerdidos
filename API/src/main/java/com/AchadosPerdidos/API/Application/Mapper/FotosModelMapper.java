package com.AchadosPerdidos.API.Application.Mapper;

import com.AchadosPerdidos.API.Application.DTOs.FotosDTO;
import com.AchadosPerdidos.API.Application.DTOs.FotosListDTO;
import com.AchadosPerdidos.API.Domain.Entity.Fotos;
import com.AchadosPerdidos.API.Domain.Enum.Provedor_Armazenamento;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FotosModelMapper {

    public FotosDTO toDTO(Fotos fotos) {
        if (fotos == null) {
            return null;
        }
        
        FotosDTO dto = new FotosDTO();
        dto.setId_Foto(fotos.getId_Foto());
        dto.setUsuario_Id(fotos.getUsuario_Id());
        dto.setItem_Id(fotos.getItem_Id());
        dto.setProvedor_Armazenamento(fotos.getProvedor_Armazenamento() != null ? fotos.getProvedor_Armazenamento().name() : null);
        dto.setNome_Bucket(fotos.getNome_Bucket());
        dto.setChave_Objeto(fotos.getChave_Objeto());
        dto.setUrl_Arquivo(fotos.getUrl_Arquivo());
        dto.setNome_Original(fotos.getNome_Original());
        dto.setLargura(fotos.getLargura());
        dto.setAltura(fotos.getAltura());
        dto.setPerfil_Usuario(fotos.getPerfil_Usuario());
        dto.setFoto_Item(fotos.getFoto_Item());
        dto.setFlg_Inativo(fotos.getFlg_Inativo());
        dto.setData_Envio(fotos.getData_Envio());
        dto.setData_Exclusao(fotos.getData_Exclusao());
        dto.setData_Atualizacao(fotos.getData_Atualizacao());
        
        return dto;
    }

    public Fotos toEntity(FotosDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Fotos fotos = new Fotos();
        fotos.setId_Foto(dto.getId_Foto());
        fotos.setUsuario_Id(dto.getUsuario_Id());
        fotos.setItem_Id(dto.getItem_Id());
        fotos.setProvedor_Armazenamento(dto.getProvedor_Armazenamento() != null ? Provedor_Armazenamento.valueOf(dto.getProvedor_Armazenamento()) : null);
        fotos.setNome_Bucket(dto.getNome_Bucket());
        fotos.setChave_Objeto(dto.getChave_Objeto());
        fotos.setUrl_Arquivo(dto.getUrl_Arquivo());
        fotos.setNome_Original(dto.getNome_Original());
        fotos.setLargura(dto.getLargura());
        fotos.setAltura(dto.getAltura());
        fotos.setPerfil_Usuario(dto.getPerfil_Usuario());
        fotos.setFoto_Item(dto.getFoto_Item());
        fotos.setFlg_Inativo(dto.getFlg_Inativo());
        fotos.setData_Envio(dto.getData_Envio());
        fotos.setData_Exclusao(dto.getData_Exclusao());
        fotos.setData_Atualizacao(dto.getData_Atualizacao());
        
        return fotos;
    }

    public FotosListDTO toListDTO(List<Fotos> fotos) {
        if (fotos == null) {
            return null;
        }
        
        List<FotosDTO> dtoList = fotos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        
        FotosListDTO listDTO = new FotosListDTO();
        listDTO.setFotos(dtoList);
        listDTO.setTotalCount(dtoList.size());
        
        return listDTO;
    }
}
