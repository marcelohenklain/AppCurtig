package com.example.appcurtig.model;

public class Usuario {

    private String nome;
    private String idade; //Transformar depois para integer
    private String curso;
    private String universidade;
    //private String estado; //implementar no futuro
    private String email;
    private String senha;

    //GET AND SET PARA NOME
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //GET AND SET PARA IDADE
    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    //GET AND SET PARA CURSO
    public String getCurso() {
        return curso;
    }

    public void setCurso(String celular) {
        this.curso = curso;
    }

    //GET AND SET PARA UNIVERSIDADE
    public String getUniversidade() {
        return universidade;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    //GET AND SET PARA ESTADO
/*    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }*/

    //GET AND SET PARA EMAIL
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //GET AND SET PARA SENHA
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
