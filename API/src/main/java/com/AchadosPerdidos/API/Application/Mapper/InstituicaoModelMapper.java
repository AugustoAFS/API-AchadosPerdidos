package com.AchadosPerdidos.API.Application.Mapper;

import com.AchadosPerdidos.API.Application.DTOs.InstituicaoDTO;
import com.AchadosPerdidos.API.Application.DTOs.InstituicaoListDTO;
import com.AchadosPerdidos.API.Domain.Entity.Instituicao;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InstituicaoModelMapper {

    public InstituicaoDTO toDTO(Instituicao instituicao) {
        if (instituicao == null) {
            return null;
        }
        
        InstituicaoDTO dto = new InstituicaoDTO();
        dto.setId_Instituicao(instituicao.getId_Instituicao());
        dto.setTipo_Instituicao(instituicao.getTipo_Instituicao());
        dto.setNome_Instituicao(instituicao.getNome_Instituicao());
        dto.setCNPJ_Filial(instituicao.getCNPJ_Filial());
        dto.setFlg_Inativo(instituicao.getFlg_Inativo());
        dto.setData_Cadastro(instituicao.getData_Cadastro());
        
        return dto;
    }

    public Instituicao toEntity(InstituicaoDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Instituicao instituicao = new Instituicao();
        instituicao.setId_Instituicao(dto.getId_Instituicao());
        instituicao.setTipo_Instituicao(dto.getTipo_Instituicao());
        instituicao.setNome_Instituicao(dto.getNome_Instituicao());
        instituicao.setCNPJ_Filial(dto.getCNPJ_Filial());
        instituicao.setFlg_Inativo(dto.getFlg_Inativo());
        instituicao.setData_Cadastro(dto.getData_Cadastro());
        
        return instituicao;
    }

    public InstituicaoListDTO toListDTO(List<Instituicao> instituicoes) {
        if (instituicoes == null) {
            return null;
        }
        
        List<InstituicaoDTO> dtoList = instituicoes.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        
        InstituicaoListDTO listDTO = new InstituicaoListDTO();
        listDTO.setInstituicoes(dtoList);
        listDTO.setTotalCount(dtoList.size());
        
        return listDTO;
    }
}
