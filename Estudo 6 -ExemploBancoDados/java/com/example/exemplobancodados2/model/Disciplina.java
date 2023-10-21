package com.example.exemplobancodados2.model;

public class Disciplina extends Professor{

    private int idDisciplina;

    private String descricao;

    private int periodo;

    private Double cargaHorario;

    private int idProfessor;

    public Disciplina() {}

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public Double getCargaHorario() {
        return cargaHorario;
    }

    public void setCargaHorario(Double cargaHorario) {
        this.cargaHorario = cargaHorario;
    }

    @Override
    public int getIdProfessor() {
        return idProfessor;
    }

    @Override
    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }
}
