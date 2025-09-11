package com.AchadosPerdidos.API.Application.Services;

import com.AchadosPerdidos.API.Application.DTOs.UsuariosDTO;
import com.AchadosPerdidos.API.Application.Mapper.UsuariosModelMapper;
import com.AchadosPerdidos.API.Application.Services.Interfaces.IUsuariosService;
import com.AchadosPerdidos.API.Domain.Entity.Usuarios;
import com.AchadosPerdidos.API.Domain.Repository.Interfaces.IUsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do Service de Usuários
 * Contém as regras de negócio para operações com usuários
 */
@Service
public class UsuariosService implements IUsuariosService {
    
    private final UsuariosModelMapper usuariosMapper;
    private final IUsuariosRepository usuariosRepository;
    
    public UsuariosService(UsuariosModelMapper usuariosMapper, IUsuariosRepository usuariosRepository) {
        this.usuariosMapper = usuariosMapper;
        this.usuariosRepository = usuariosRepository;
    }
    
    @Override
    public UsuariosDTO criarUsuario(UsuariosDTO dto) {
        // Validações de negócio
        validarDadosUsuario(dto);
        
        // Converter DTO para Entity
        Usuarios entity = usuariosMapper.toEntity(dto);
        
        // Aplicar regras de negócio
        entity.Data_Cadastro = new Date();
        entity.Flg_Inativo = false;
        
        // Salvar no banco usando repository
        int idGerado = usuariosRepository.inserir(entity);
        entity.Id_Usuario = idGerado;
        
        // Converter Entity para DTO de resposta
        return usuariosMapper.toDTO(entity);
    }
    
    @Override
    public UsuariosDTO buscarPorId(int id) {
        Usuarios entity = usuariosRepository.buscarPorId(id);
        return entity != null ? usuariosMapper.toDTO(entity) : null;
    }
    
    @Override
    public UsuariosDTO buscarPorEmail(String email) {
        Usuarios entity = usuariosRepository.buscarPorEmail(email);
        return entity != null ? usuariosMapper.toDTO(entity) : null;
    }
    
    @Override
    public UsuariosDTO buscarPorCPF(String cpf) {
        Usuarios entity = usuariosRepository.buscarPorCPF(cpf);
        return entity != null ? usuariosMapper.toDTO(entity) : null;
    }
    
    @Override
    public UsuariosDTO buscarPorMatricula(String matricula) {
        Usuarios entity = usuariosRepository.buscarPorMatricula(matricula);
        return entity != null ? usuariosMapper.toDTO(entity) : null;
    }
    
    @Override
    public List<UsuariosDTO> listarTodos() {
        return usuariosRepository.listarTodos().stream()
            .map(usuariosMapper::toDTO)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<UsuariosDTO> listarAtivos() {
        return usuariosRepository.listarAtivos().stream()
            .map(usuariosMapper::toDTO)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<UsuariosDTO> listarPorTipoRole(int tipoRoleId) {
        return usuariosRepository.listarPorTipoRole(tipoRoleId).stream()
            .map(usuariosMapper::toDTO)
            .collect(Collectors.toList());
    }
    
    @Override
    public UsuariosDTO atualizarUsuario(int id, UsuariosDTO dto) {
        // Buscar usuário existente
        Usuarios entityExistente = usuariosRepository.buscarPorId(id);
        
        if (entityExistente == null) {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
        
        // Validar dados (excluindo o próprio usuário da validação)
        validarDadosUsuario(dto, id);
        
        // Converter DTO para Entity
        Usuarios entityAtualizada = usuariosMapper.toEntity(dto);
        
        // Manter dados que não devem ser alterados
        entityAtualizada.Id_Usuario = id;
        entityAtualizada.Data_Cadastro = entityExistente.Data_Cadastro;
        
        // Atualizar no banco usando repository
        boolean sucesso = usuariosRepository.atualizar(entityAtualizada);
        
        if (!sucesso) {
            throw new RuntimeException("Erro ao atualizar usuário");
        }
        
        // Converter Entity para DTO de resposta
        return usuariosMapper.toDTO(entityAtualizada);
    }
    
    @Override
    public boolean desativarUsuario(int id) {
        return usuariosRepository.desativar(id);
    }
    
    @Override
    public boolean ativarUsuario(int id) {
        return usuariosRepository.ativar(id);
    }
    
    @Override
    public boolean deletarUsuario(int id) {
        return usuariosRepository.deletar(id);
    }
    
    @Override
    public boolean emailJaExiste(String email, Integer idUsuario) {
        return usuariosRepository.emailExiste(email, idUsuario);
    }
    
    @Override
    public boolean cpfJaExiste(String cpf, Integer idUsuario) {
        return usuariosRepository.cpfExiste(cpf, idUsuario);
    }
    
    @Override
    public boolean matriculaJaExiste(String matricula, Integer idUsuario) {
        return usuariosRepository.matriculaExiste(matricula, idUsuario);
    }
    
    /**
     * Valida os dados do usuário
     */
    private void validarDadosUsuario(UsuariosDTO dto) {
        validarDadosUsuario(dto, null);
    }
    
    /**
     * Valida os dados do usuário (com ID para exclusão na validação)
     */
    private void validarDadosUsuario(UsuariosDTO dto, Integer idUsuario) {
        if (dto.getNomeUsuario() == null || dto.getNomeUsuario().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do usuário é obrigatório");
        }
        
        if (dto.getEmailUsuario() == null || dto.getEmailUsuario().trim().isEmpty()) {
            throw new IllegalArgumentException("Email do usuário é obrigatório");
        }
        
        if (dto.getSenhaUsuario() == null || dto.getSenhaUsuario().trim().isEmpty()) {
            throw new IllegalArgumentException("Senha do usuário é obrigatória");
        }
        
        if (dto.getTelefoneUsuario() == null || dto.getTelefoneUsuario().trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone do usuário é obrigatório");
        }
        
        if (dto.getTipoRoleId() <= 0) {
            throw new IllegalArgumentException("Tipo de role é obrigatório");
        }
        
        // Validar unicidade
        if (emailJaExiste(dto.getEmailUsuario(), idUsuario)) {
            throw new IllegalArgumentException("Email já está em uso");
        }
        
        if (dto.getCpfUsuario() != null && !dto.getCpfUsuario().trim().isEmpty()) {
            if (cpfJaExiste(dto.getCpfUsuario(), idUsuario)) {
                throw new IllegalArgumentException("CPF já está em uso");
            }
        }
        
        if (dto.getMatriculaUsuario() != null && !dto.getMatriculaUsuario().trim().isEmpty()) {
            if (matriculaJaExiste(dto.getMatriculaUsuario(), idUsuario)) {
                throw new IllegalArgumentException("Matrícula já está em uso");
            }
        }
    }
}
