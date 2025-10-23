package com.AchadosPerdidos.API.Presentation.Controller;

import com.AchadosPerdidos.API.Application.DTOs.Auxiliares.AuxLocalItemDTO;
import com.AchadosPerdidos.API.Application.DTOs.Auxiliares.AuxLocalItemListDTO;
import com.AchadosPerdidos.API.Application.Services.Interfaces.IAuxLocalItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/aux-local-item")
@CrossOrigin(origins = "*")
public class AuxLocalItemController {
    
    private final IAuxLocalItemService auxLocalItemService;
    
    public AuxLocalItemController(IAuxLocalItemService auxLocalItemService) {
        this.auxLocalItemService = auxLocalItemService;
    }
    
    @GetMapping("/buscar-nome/{nome}")
    public ResponseEntity<AuxLocalItemListDTO> buscarPorNome(@PathVariable String nome) {
        try {
            AuxLocalItemListDTO auxLocalItem = auxLocalItemService.buscarPorNome(nome);
            if (auxLocalItem != null) {
                return ResponseEntity.ok(auxLocalItem);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
        }
    }

    @PostMapping("/criar")  
    public ResponseEntity<AuxLocalItemListDTO> criarAuxLocalItem(@Valid @RequestBody AuxLocalItemDTO dto) {
        try {
            if (dto == null)
            {
                throw new IllegalArgumentException("Corpo da requisição ausente");
            }
            if (dto.getNome_Local_Item() == null || dto.getNome_Local_Item().trim().isEmpty()) {
                throw new IllegalArgumentException("Nome_Local_Item é obrigatório");
            }
            if (dto.getDescricao_Local_Item() == null || dto.getDescricao_Local_Item().trim().isEmpty()) {
                throw new IllegalArgumentException("Descricao_Local_Item é obrigatória");
            }
            AuxLocalItemListDTO auxLocalItemCriado = auxLocalItemService.criarAuxLocalItem(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(auxLocalItemCriado);
        }
        catch (IllegalArgumentException e) {
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(String.format("Erro ao criar local de item: %s", e.getMessage()));
        }
    }
    
    @GetMapping("/listar/{id}")
    public ResponseEntity<AuxLocalItemListDTO> buscarAuxLocalItem(@PathVariable int id) {
        try
        {
            if (id <= 0) {
                throw new IllegalArgumentException("Id do Local de Item não encontrado");
            }

            AuxLocalItemListDTO auxLocalItem = auxLocalItemService.buscarPorId(id);
            if (auxLocalItem != null) {
                return ResponseEntity.ok(auxLocalItem);
            }
            return ResponseEntity.notFound().build();
        }
        catch (IllegalArgumentException e) {
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(String.format("Erro ao buscar local de item por ID: %s", e.getMessage()));
        }
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<AuxLocalItemListDTO>> listarTodos() {
        try 
        {
            List<AuxLocalItemListDTO> auxLocalItems = auxLocalItemService.listarTodos();
            return ResponseEntity.ok(auxLocalItems);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(String.format("Erro ao listar locais de item: %s", e.getMessage()));
        }
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<AuxLocalItemListDTO> atualizarAuxLocalItem(@PathVariable int id, @Valid @RequestBody AuxLocalItemDTO dto) {
        if (id <= 0) 
        {
            throw new IllegalArgumentException("Id do Local de Item não encontrado");
        }
            try 
            {
                AuxLocalItemListDTO auxLocalItemAtualizado = auxLocalItemService.atualizarAuxLocalItem(id, dto);
                return ResponseEntity.ok(auxLocalItemAtualizado);
            } 
            catch (IllegalArgumentException e)
            {
                throw new RuntimeException(String.format("Erro ao atualizar local de item: %s", e.getMessage()));
            }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarAuxLocalItem(@PathVariable int id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        
        try {
            boolean sucesso = auxLocalItemService.deletarAuxLocalItem(id);
            if (sucesso) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}