package com.AchadosPerdidos.API.Presentation.Controller;

import com.AchadosPerdidos.API.Application.DTOs.Usuario.UsuariosDTO;
import com.AchadosPerdidos.API.Application.DTOs.Usuario.UsuariosCreateDTO;
import com.AchadosPerdidos.API.Application.DTOs.Usuario.UsuariosListDTO;
import com.AchadosPerdidos.API.Application.DTOs.Usuario.UsuariosUpdateDTO;
import com.AchadosPerdidos.API.Application.Services.Interfaces.IUsuariosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
@Tag(name = "Usuários", description = "API para gerenciamento de usuários")
public class UsuariosController {

    @Autowired
    private IUsuariosService usuariosService;

    @GetMapping
    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista de todos os usuários cadastrados")
    public ResponseEntity<UsuariosListDTO> getAllUsuarios() {
        UsuariosListDTO usuarios = usuariosService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuariosDTO> getUsuarioById(@PathVariable int id) {
        UsuariosDTO usuario = usuariosService.getUsuarioById(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsuariosDTO> getUsuarioByEmail(@PathVariable String email) {
        UsuariosDTO usuario = usuariosService.getUsuarioByEmail(email);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Criar novo usuário", description = "Cria um novo usuário no sistema. A instituição será preenchida automaticamente baseada no campus selecionado.")
    public ResponseEntity<UsuariosDTO> createUsuario(@RequestBody UsuariosCreateDTO usuariosCreateDTO) {
        UsuariosDTO createdUsuario = usuariosService.createUsuarioFromDTO(usuariosCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuario);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente")
    public ResponseEntity<UsuariosDTO> updateUsuario(
            @Parameter(description = "ID do usuário a ser atualizado") @PathVariable int id, 
            @RequestBody UsuariosUpdateDTO usuariosUpdateDTO) {
        UsuariosDTO updatedUsuario = usuariosService.updateUsuarioFromDTO(id, usuariosUpdateDTO);
        if (updatedUsuario != null) {
            return ResponseEntity.ok(updatedUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable int id) {
        boolean deleted = usuariosService.deleteUsuario(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/active")
    public ResponseEntity<UsuariosListDTO> getActiveUsuarios() {
        UsuariosListDTO activeUsuarios = usuariosService.getActiveUsuarios();
        return ResponseEntity.ok(activeUsuarios);
    }

    @GetMapping("/role/{tipoRoleId}")
    public ResponseEntity<UsuariosListDTO> getUsuariosByRole(@PathVariable int tipoRoleId) {
        UsuariosListDTO usuarios = usuariosService.getUsuariosByRole(tipoRoleId);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/institution/{instituicaoId}")
    public ResponseEntity<UsuariosListDTO> getUsuariosByInstitution(@PathVariable int instituicaoId) {
        UsuariosListDTO usuarios = usuariosService.getUsuariosByInstitution(instituicaoId);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/campus/{campusId}")
    public ResponseEntity<UsuariosListDTO> getUsuariosByCampus(@PathVariable int campusId) {
        UsuariosListDTO usuarios = usuariosService.getUsuariosByCampus(campusId);
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UsuariosDTO> authenticateUsuario(@RequestParam String email, @RequestParam String senha) {
        UsuariosDTO usuario = usuariosService.authenticateUsuario(email, senha);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}