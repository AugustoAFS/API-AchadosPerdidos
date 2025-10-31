package com.AchadosPerdidos.API.Application.Services;

import com.AchadosPerdidos.API.Application.DTOs.Estado.EstadoDTO;
import com.AchadosPerdidos.API.Application.DTOs.Estado.EstadoCreateDTO;
import com.AchadosPerdidos.API.Application.DTOs.Estado.EstadoUpdateDTO;
import com.AchadosPerdidos.API.Application.Mapper.EstadoModelMapper;
import com.AchadosPerdidos.API.Application.Services.Interfaces.IEstadoService;
import com.AchadosPerdidos.API.Domain.Entity.Estado;
import com.AchadosPerdidos.API.Domain.Repository.CidadeRepository;
import com.AchadosPerdidos.API.Domain.Repository.EstadoRepository;
import com.AchadosPerdidos.API.Exeptions.BusinessException;
import com.AchadosPerdidos.API.Exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoService implements IEstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoModelMapper estadoModelMapper;

    @Override
    @Cacheable(value = "estados", key = "'all'")
    public List<EstadoDTO> getAllEstados() {
        List<Estado> estados = estadoRepository.findAll();
        return estados.stream()
                .map(estadoModelMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "estados", key = "#id")
    public EstadoDTO getEstadoById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do estado deve ser válido");
        }
        
        Estado estado = estadoRepository.findById(id);
        if (estado == null) {
            throw new ResourceNotFoundException("Estado", "ID", id);
        }
        return estadoModelMapper.toDTO(estado);
    }

    @Override
    @Cacheable(value = "estados", key = "'uf_' + #uf")
    public EstadoDTO getEstadoByUf(String uf) {
        if (!StringUtils.hasText(uf)) {
            throw new IllegalArgumentException("UF do estado não pode ser vazio");
        }
        
        if (uf.length() != 2) {
            throw new IllegalArgumentException("UF deve ter exatamente 2 caracteres");
        }
        
        Estado estado = estadoRepository.findByUf(uf.toUpperCase());
        if (estado == null) {
            throw new ResourceNotFoundException("Estado", "UF", uf);
        }
        return estadoModelMapper.toDTO(estado);
    }

    @Override
    @CacheEvict(value = "estados", allEntries = true)
    public EstadoDTO createEstado(EstadoCreateDTO createDTO) {
        if (createDTO == null) {
            throw new IllegalArgumentException("Dados do estado não podem ser nulos");
        }
        
        if (!StringUtils.hasText(createDTO.getNome())) {
            throw new BusinessException("Estado", "criar", "Nome é obrigatório");
        }
        
        if (!StringUtils.hasText(createDTO.getUf())) {
            throw new BusinessException("Estado", "criar", "UF é obrigatória");
        }
        
        if (createDTO.getUf().length() != 2) {
            throw new BusinessException("Estado", "criar", "UF deve ter exatamente 2 caracteres");
        }
        
        // Regra de negócio: Verificar se já existe um estado com a mesma UF
        Estado estadoExistente = estadoRepository.findByUf(createDTO.getUf().toUpperCase());
        if (estadoExistente != null) {
            throw new BusinessException("Estado", "criar", "Já existe um estado com a UF: " + createDTO.getUf());
        }
        
        Estado estado = new Estado();
        estado.setNome(createDTO.getNome());
        estado.setUf(createDTO.getUf().toUpperCase());
        estado.setDtaCriacao(new Date());
        estado.setFlgInativo(false);
        
        Estado savedEstado = estadoRepository.save(estado);
        return estadoModelMapper.toDTO(savedEstado);
    }

    @Override
    @CacheEvict(value = "estados", allEntries = true)
    public EstadoDTO updateEstado(Integer id, EstadoUpdateDTO updateDTO) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do estado deve ser válido");
        }
        
        if (updateDTO == null) {
            throw new IllegalArgumentException("Dados de atualização não podem ser nulos");
        }
        
        Estado existingEstado = estadoRepository.findById(id);
        if (existingEstado == null) {
            throw new ResourceNotFoundException("Estado", "ID", id);
        }
        
        if (updateDTO.getNome() != null) {
            if (!StringUtils.hasText(updateDTO.getNome())) {
                throw new BusinessException("Estado", "atualizar", "Nome não pode ser vazio");
            }
            existingEstado.setNome(updateDTO.getNome());
        }
        if (updateDTO.getUf() != null) {
            if (!StringUtils.hasText(updateDTO.getUf())) {
                throw new BusinessException("Estado", "atualizar", "UF não pode ser vazia");
            }
            if (updateDTO.getUf().length() != 2) {
                throw new BusinessException("Estado", "atualizar", "UF deve ter exatamente 2 caracteres");
            }
            
            // Regra de negócio: Se estiver alterando a UF, verificar se não existe outro com a mesma UF
            String novaUf = updateDTO.getUf().toUpperCase();
            if (!novaUf.equals(existingEstado.getUf())) {
                Estado estadoComUf = estadoRepository.findByUf(novaUf);
                if (estadoComUf != null && !estadoComUf.getId().equals(id)) {
                    throw new BusinessException("Estado", "atualizar", "Já existe outro estado com a UF: " + novaUf);
                }
            }
            existingEstado.setUf(novaUf);
        }
        if (updateDTO.getFlgInativo() != null) {
            existingEstado.setFlgInativo(updateDTO.getFlgInativo());
        }
        
        Estado updatedEstado = estadoRepository.save(existingEstado);
        return estadoModelMapper.toDTO(updatedEstado);
    }

    @Override
    @CacheEvict(value = "estados", allEntries = true)
    public EstadoDTO deleteEstado(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do estado deve ser válido");
        }
        
        Estado estado = estadoRepository.findById(id);
        if (estado == null) {
            throw new ResourceNotFoundException("Estado", "ID", id);
        }
        
        // Soft delete: Marcar como inativo ao invés de deletar fisicamente
        if (Boolean.TRUE.equals(estado.getFlgInativo())) {
            throw new BusinessException("Estado", "deletar", "O estado já está inativo");
        }
        
        estado.setFlgInativo(true);
        estado.setDtaRemocao(new Date());
        
        Estado updatedEstado = estadoRepository.save(estado);
        return estadoModelMapper.toDTO(updatedEstado);
    }

    @Override
    @Cacheable(value = "estados", key = "'active'")
    public List<EstadoDTO> getActiveEstados() {
        List<Estado> activeEstados = estadoRepository.findActive();
        return activeEstados.stream()
                .map(estadoModelMapper::toDTO)
                .collect(Collectors.toList());
    }
}
