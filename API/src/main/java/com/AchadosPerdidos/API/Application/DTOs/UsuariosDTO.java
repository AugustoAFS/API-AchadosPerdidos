package com.AchadosPerdidos.API.Application.DTOs;

import java.util.Date;

public class UsuariosDTO {
    private int idUsuario;
    private String nomeUsuario;
    private String cpfUsuario;
    private String emailUsuario;
    private String senhaUsuario;
    private String matriculaUsuario;
    private String telefoneUsuario;
    private Date dataCadastro;
    private int tipoRoleId;
    private int fotoId;
    private Boolean flgInativo;
    private int instituicaoPublicaId;
    private int instituicaoPrivadaId;
    
    // Construtores
    public UsuariosDTO() {}
    
    public UsuariosDTO(int idUsuario, String nomeUsuario, String cpfUsuario, String emailUsuario,
                      String senhaUsuario, String matriculaUsuario, String telefoneUsuario,
                      Date dataCadastro, int tipoRoleId, int fotoId, Boolean flgInativo,
                      int instituicaoPublicaId, int instituicaoPrivadaId) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.cpfUsuario = cpfUsuario;
        this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;
        this.matriculaUsuario = matriculaUsuario;
        this.telefoneUsuario = telefoneUsuario;
        this.dataCadastro = dataCadastro;
        this.tipoRoleId = tipoRoleId;
        this.fotoId = fotoId;
        this.flgInativo = flgInativo;
        this.instituicaoPublicaId = instituicaoPublicaId;
        this.instituicaoPrivadaId = instituicaoPrivadaId;
    }
    
    // Getters e Setters
    public int getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getNomeUsuario() {
        return nomeUsuario;
    }
    
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
    public String getCpfUsuario() {
        return cpfUsuario;
    }
    
    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }
    
    public String getEmailUsuario() {
        return emailUsuario;
    }
    
    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }
    
    public String getSenhaUsuario() {
        return senhaUsuario;
    }
    
    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }
    
    public String getMatriculaUsuario() {
        return matriculaUsuario;
    }
    
    public void setMatriculaUsuario(String matriculaUsuario) {
        this.matriculaUsuario = matriculaUsuario;
    }
    
    public String getTelefoneUsuario() {
        return telefoneUsuario;
    }
    
    public void setTelefoneUsuario(String telefoneUsuario) {
        this.telefoneUsuario = telefoneUsuario;
    }
    
    public Date getDataCadastro() {
        return dataCadastro;
    }
    
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    public int getTipoRoleId() {
        return tipoRoleId;
    }
    
    public void setTipoRoleId(int tipoRoleId) {
        this.tipoRoleId = tipoRoleId;
    }
    
    public int getFotoId() {
        return fotoId;
    }
    
    public void setFotoId(int fotoId) {
        this.fotoId = fotoId;
    }
    
    public Boolean getFlgInativo() {
        return flgInativo;
    }
    
    public void setFlgInativo(Boolean flgInativo) {
        this.flgInativo = flgInativo;
    }
    
    public int getInstituicaoPublicaId() {
        return instituicaoPublicaId;
    }
    
    public void setInstituicaoPublicaId(int instituicaoPublicaId) {
        this.instituicaoPublicaId = instituicaoPublicaId;
    }
    
    public int getInstituicaoPrivadaId() {
        return instituicaoPrivadaId;
    }
    
    public void setInstituicaoPrivadaId(int instituicaoPrivadaId) {
        this.instituicaoPrivadaId = instituicaoPrivadaId;
    }
}
