package com.AchadosPerdidos.API.Application.Services;

import com.AchadosPerdidos.API.Application.DTOs.FotosDTO;
import com.AchadosPerdidos.API.Application.DTOs.FotosListDTO;
import com.AchadosPerdidos.API.Application.Mapper.FotosModelMapper;
import com.AchadosPerdidos.API.Application.Services.Interfaces.IFotosService;
import com.AchadosPerdidos.API.Domain.Entity.Fotos;
import com.AchadosPerdidos.API.Domain.Enum.Provedor_Armazenamento;
import com.AchadosPerdidos.API.Domain.Repository.FotosRepository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FotosService implements IFotosService {

    @Autowired
    private FotosRepository fotosRepository;

    @Autowired
    private FotosModelMapper fotosModelMapper;

    @Autowired
    private S3Service s3Service;

    @Override
    public FotosListDTO getAllFotos() {
        List<Fotos> fotos = fotosRepository.findAll();
        return fotosModelMapper.toListDTO(fotos);
    }

    @Override
    public FotosDTO getFotoById(int id) {
        Fotos fotos = fotosRepository.findById(id);
        return fotosModelMapper.toDTO(fotos);
    }

    @Override
    public FotosDTO createFoto(FotosDTO fotosDTO) {
        Fotos fotos = fotosModelMapper.toEntity(fotosDTO);
        fotos.setData_Envio(new Date());
        fotos.setData_Atualizacao(new Date());
        fotos.setFlg_Inativo(false);
        
        Fotos savedFotos = fotosRepository.save(fotos);
        return fotosModelMapper.toDTO(savedFotos);
    }

    @Override
    public FotosDTO updateFoto(int id, FotosDTO fotosDTO) {
        Fotos existingFotos = fotosRepository.findById(id);
        if (existingFotos == null) {
            return null;
        }
        
        existingFotos.setUsuario_Id(fotosDTO.getUsuario_Id());
        existingFotos.setItem_Id(fotosDTO.getItem_Id());
        existingFotos.setProvedor_Armazenamento(fotosDTO.getProvedor_Armazenamento() != null ? Provedor_Armazenamento.valueOf(fotosDTO.getProvedor_Armazenamento()) : null);
        existingFotos.setNome_Bucket(fotosDTO.getNome_Bucket());
        existingFotos.setChave_Objeto(fotosDTO.getChave_Objeto());
        existingFotos.setUrl_Arquivo(fotosDTO.getUrl_Arquivo());
        existingFotos.setNome_Original(fotosDTO.getNome_Original());
        existingFotos.setLargura(fotosDTO.getLargura());
        existingFotos.setAltura(fotosDTO.getAltura());
        existingFotos.setPerfil_Usuario(fotosDTO.getPerfil_Usuario());
        existingFotos.setFoto_Item(fotosDTO.getFoto_Item());
        existingFotos.setFlg_Inativo(fotosDTO.getFlg_Inativo());
        existingFotos.setData_Exclusao(fotosDTO.getData_Exclusao());
        existingFotos.setData_Atualizacao(new Date());
        
        Fotos updatedFotos = fotosRepository.save(existingFotos);
        return fotosModelMapper.toDTO(updatedFotos);
    }

    @Override
    public boolean deleteFoto(int id) {
        Fotos fotos = fotosRepository.findById(id);
        if (fotos == null) {
            return false;
        }
        
        return fotosRepository.deleteById(id);
    }

    @Override
    public FotosListDTO getActiveFotos() {
        List<Fotos> activeFotos = fotosRepository.findActive();
        return fotosModelMapper.toListDTO(activeFotos);
    }

    @Override
    public FotosListDTO getFotosByUser(int userId) {
        List<Fotos> fotos = fotosRepository.findByUser(userId);
        return fotosModelMapper.toListDTO(fotos);
    }

    @Override
    public FotosListDTO getFotosByItem(int itemId) {
        List<Fotos> fotos = fotosRepository.findByItem(itemId);
        return fotosModelMapper.toListDTO(fotos);
    }

    @Override
    public FotosListDTO getProfilePhotos(int userId) {
        List<Fotos> fotos = fotosRepository.findProfilePhotos(userId);
        return fotosModelMapper.toListDTO(fotos);
    }

    @Override
    public FotosListDTO getItemPhotos(int itemId) {
        List<Fotos> fotos = fotosRepository.findItemPhotos(itemId);
        return fotosModelMapper.toListDTO(fotos);
    }

    @Override
    public FotosDTO getMainItemPhoto(int itemId) {
        Fotos fotos = fotosRepository.findMainItemPhoto(itemId);
        return fotosModelMapper.toDTO(fotos);
    }

    @Override
    public FotosDTO getProfilePhoto(int userId) {
        Fotos fotos = fotosRepository.findProfilePhoto(userId);
        return fotosModelMapper.toDTO(fotos);
    }

    /**
     * Faz upload de uma foto para o S3 e salva no banco de dados
     * @param file Arquivo da foto
     * @param userId ID do usuário
     * @param itemId ID do item (opcional)
     * @param isProfilePhoto Se é foto de perfil
     * @return DTO da foto salva
     */
    public FotosDTO uploadPhoto(MultipartFile file, Integer userId, Integer itemId, boolean isProfilePhoto) {
        try {
            // Validar arquivo
            if (file.isEmpty()) {
                throw new IllegalArgumentException("Arquivo não pode estar vazio");
            }

            // Validar tipo de arquivo
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                throw new IllegalArgumentException("Arquivo deve ser uma imagem");
            }

            // Fazer upload para S3
            S3Service.S3UploadResult uploadResult = s3Service.uploadPhoto(
                file.getBytes(),
                file.getOriginalFilename(),
                contentType,
                userId,
                itemId,
                isProfilePhoto
            );

            // Criar entidade Fotos
            Fotos fotos = new Fotos();
            fotos.setUsuario_Id(userId);
            fotos.setItem_Id(itemId);
            fotos.setProvedor_Armazenamento(Provedor_Armazenamento.s3);
            fotos.setNome_Bucket(""); // Será preenchido pelo S3Service se necessário
            fotos.setChave_Objeto(uploadResult.getS3Key());
            fotos.setUrl_Arquivo(uploadResult.getFileUrl());
            fotos.setNome_Original(uploadResult.getOriginalName());
            fotos.setPerfil_Usuario(isProfilePhoto);
            fotos.setFoto_Item(!isProfilePhoto);
            fotos.setFlg_Inativo(false);
            fotos.setData_Envio(new Date());
            fotos.setData_Atualizacao(new Date());

            // Salvar no banco de dados
            Fotos savedFotos = fotosRepository.save(fotos);
            return fotosModelMapper.toDTO(savedFotos);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer upload da foto: " + e.getMessage(), e);
        }
    }

    /**
     * Baixa uma foto do S3
     * @param fotoId ID da foto
     * @return Conteúdo da foto em bytes
     */
    public byte[] downloadPhoto(int fotoId) {
        Fotos fotos = fotosRepository.findById(fotoId);
        if (fotos == null) {
            throw new IllegalArgumentException("Foto não encontrada");
        }

        if (fotos.getProvedor_Armazenamento() != Provedor_Armazenamento.s3) {
            throw new IllegalArgumentException("Foto não está armazenada no S3");
        }

        return s3Service.downloadFile(fotos.getChave_Objeto());
    }

    /**
     * Deleta uma foto do S3 e do banco de dados
     * @param fotoId ID da foto
     * @return true se deletada com sucesso
     */
    public boolean deletePhoto(int fotoId) {
        Fotos fotos = fotosRepository.findById(fotoId);
        if (fotos == null) {
            return false;
        }

        try {
            // Deletar do S3 se for S3
            if (fotos.getProvedor_Armazenamento() == Provedor_Armazenamento.s3) {
                s3Service.deleteFile(fotos.getChave_Objeto());
            }

            // Deletar do banco de dados
            return fotosRepository.deleteById(fotoId);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar foto: " + e.getMessage(), e);
        }
    }

    /**
     * Faz upload de foto de perfil
     * @param file Arquivo da foto
     * @param userId ID do usuário
     * @return DTO da foto salva
     */
    public FotosDTO uploadProfilePhoto(MultipartFile file, Integer userId) {
        return uploadPhoto(file, userId, null, true);
    }

    /**
     * Faz upload de foto de item
     * @param file Arquivo da foto
     * @param userId ID do usuário
     * @param itemId ID do item
     * @return DTO da foto salva
     */
    public FotosDTO uploadItemPhoto(MultipartFile file, Integer userId, Integer itemId) {
        return uploadPhoto(file, userId, itemId, false);
    }
}
