package com.AchadosPerdidos.API.Presentation.Controller;

import com.AchadosPerdidos.API.Application.DTOs.UsuariosDTO;
import com.AchadosPerdidos.API.Application.Services.Interfaces.IUsuariosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Controller de Usuários - API REST
 * Endpoints para gerenciamento de usuários do sistema Achados e Perdidos
 */
@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*") // Permitir CORS para desenvolvimento
public class UsuariosController {
    
    private final IUsuariosService usuariosService;
    
    public UsuariosController(IUsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }
    
    /**
     * Criar novo usuário
     * @param dto Dados do usuário a ser criado
     * @return Usuário criado com ID gerado
     */
    @PostMapping
    public ResponseEntity<UsuariosDTO> criarUsuario(@Valid @RequestBody UsuariosDTO dto) {
        try {
            UsuariosDTO usuarioCriado = usuariosService.criarUsuario(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Buscar usuário por ID
     * @param id ID do usuário
     * @return Dados do usuário encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuariosDTO> buscarUsuario(@PathVariable int id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        
        UsuariosDTO usuario = usuariosService.buscarPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * Buscar usuário por email
     * @param email Email do usuário
     * @return Dados do usuário encontrado
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UsuariosDTO> buscarPorEmail(@PathVariable String email) {
        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        UsuariosDTO usuario = usuariosService.buscarPorEmail(email);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * Buscar usuário por CPF
     * @param cpf CPF do usuário
     * @return Dados do usuário encontrado
     */
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UsuariosDTO> buscarPorCPF(@PathVariable String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        UsuariosDTO usuario = usuariosService.buscarPorCPF(cpf);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * Listar todos os usuários
     * @return Lista de todos os usuários cadastrados
     */
    @GetMapping
    public ResponseEntity<List<UsuariosDTO>> listarTodos() {
        try {
            List<UsuariosDTO> usuarios = usuariosService.listarTodos();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Listar usuários ativos
     * @return Lista de usuários ativos (não inativos)
     */
    @GetMapping("/ativos")
    public ResponseEntity<List<UsuariosDTO>> listarAtivos() {
        try {
            List<UsuariosDTO> usuarios = usuariosService.listarAtivos();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Listar usuários por tipo de role
     * @param tipoRoleId ID do tipo de role
     * @return Lista de usuários com o tipo de role especificado
     */
    @GetMapping("/tipo-role/{tipoRoleId}")
    public ResponseEntity<List<UsuariosDTO>> listarPorTipoRole(@PathVariable int tipoRoleId) {
        if (tipoRoleId <= 0) {
            return ResponseEntity.badRequest().build();
        }
        
        try {
            List<UsuariosDTO> usuarios = usuariosService.listarPorTipoRole(tipoRoleId);
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Atualizar usuário existente
     * @param id ID do usuário a ser atualizado
     * @param dto Novos dados do usuário
     * @return Dados do usuário atualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<UsuariosDTO> atualizarUsuario(@PathVariable int id, @Valid @RequestBody UsuariosDTO dto) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        
        try {
            UsuariosDTO usuarioAtualizado = usuariosService.atualizarUsuario(id, dto);
            return ResponseEntity.ok(usuarioAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Desativar usuário (soft delete)
     * @param id ID do usuário a ser desativado
     * @return Status da operação
     */
    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Void> desativarUsuario(@PathVariable int id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        
        try {
            boolean sucesso = usuariosService.desativarUsuario(id);
            if (sucesso) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Ativar usuário
     * @param id ID do usuário a ser ativado
     * @return Status da operação
     */
    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativarUsuario(@PathVariable int id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        
        try {
            boolean sucesso = usuariosService.ativarUsuario(id);
            if (sucesso) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Deletar usuário permanentemente
     * @param id ID do usuário a ser deletado
     * @return Status da operação
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable int id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        
        try {
            boolean sucesso = usuariosService.deletarUsuario(id);
            if (sucesso) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
