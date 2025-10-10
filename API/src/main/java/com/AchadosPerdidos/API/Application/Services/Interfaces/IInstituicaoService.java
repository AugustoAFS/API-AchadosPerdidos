package com.AchadosPerdidos.API.Application.Services.Interfaces;

import com.AchadosPerdidos.API.Application.DTOs.InstituicaoDTO;
import com.AchadosPerdidos.API.Application.DTOs.InstituicaoListDTO;

public interface IInstituicaoService {
    InstituicaoListDTO getAllInstituicoes();
    InstituicaoDTO getInstituicaoById(int id);
    InstituicaoDTO createInstituicao(InstituicaoDTO instituicaoDTO);
    InstituicaoDTO updateInstituicao(int id, InstituicaoDTO instituicaoDTO);
    boolean deleteInstituicao(int id);
    InstituicaoListDTO getActiveInstituicoes();
    InstituicaoListDTO getInstituicoesByType(String tipoInstituicao);
}
