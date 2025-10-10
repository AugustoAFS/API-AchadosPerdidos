package com.AchadosPerdidos.API.Application.Services;

import com.AchadosPerdidos.API.Application.DTOs.CampusDTO;
import com.AchadosPerdidos.API.Application.DTOs.CampusListDTO;
import com.AchadosPerdidos.API.Application.Mapper.CampusModelMapper;
import com.AchadosPerdidos.API.Application.Services.Interfaces.ICampusService;
import com.AchadosPerdidos.API.Domain.Entity.Campus;
import com.AchadosPerdidos.API.Domain.Repository.CampusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CampusService implements ICampusService {

    @Autowired
    private CampusRepository campusRepository;

    @Autowired
    private CampusModelMapper campusModelMapper;

    @Override
    public CampusListDTO getAllCampus() {
        List<Campus> campus = campusRepository.findAll();
        return campusModelMapper.toListDTO(campus);
    }

    @Override
    public CampusDTO getCampusById(int id) {
        Campus campus = campusRepository.findById(id);
        return campusModelMapper.toDTO(campus);
    }

    @Override
    public CampusDTO createCampus(CampusDTO campusDTO) {
        Campus campus = campusModelMapper.toEntity(campusDTO);
        campus.setData_Cadastro(new Date());
        campus.setFlg_Ativo(true);
        
        Campus savedCampus = campusRepository.save(campus);
        return campusModelMapper.toDTO(savedCampus);
    }

    @Override
    public CampusDTO updateCampus(int id, CampusDTO campusDTO) {
        Campus existingCampus = campusRepository.findById(id);
        if (existingCampus == null) {
            return null;
        }
        
        existingCampus.setId_Instituicao(campusDTO.getId_Instituicao());
        existingCampus.setNome_Campus(campusDTO.getNome_Campus());
        existingCampus.setCidade(campusDTO.getCidade());
        existingCampus.setEstado(campusDTO.getEstado());
        existingCampus.setEndereco(campusDTO.getEndereco());
        existingCampus.setCEP(campusDTO.getCEP());
        existingCampus.setLatitude(campusDTO.getLatitude());
        existingCampus.setLongitude(campusDTO.getLongitude());
        existingCampus.setFlg_Ativo(campusDTO.getFlg_Ativo());
        
        Campus updatedCampus = campusRepository.save(existingCampus);
        return campusModelMapper.toDTO(updatedCampus);
    }

    @Override
    public boolean deleteCampus(int id) {
        Campus campus = campusRepository.findById(id);
        if (campus == null) {
            return false;
        }
        
        return campusRepository.deleteById(id);
    }

    @Override
    public CampusListDTO getActiveCampus() {
        List<Campus> activeCampus = campusRepository.findActive();
        return campusModelMapper.toListDTO(activeCampus);
    }

    @Override
    public CampusListDTO getCampusByInstitution(int institutionId) {
        List<Campus> campus = campusRepository.findByInstitution(institutionId);
        return campusModelMapper.toListDTO(campus);
    }
}
