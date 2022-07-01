package com.example.crudsimples;

import java.io.Serializable;
//*********** Implemento Serializable para colocar nos inputs extras
public class Contato implements Serializable {
//***********Declarando as variáveis que utilizaremos
    private Integer id;
    private String nome;
    private String telefone;
    private String cidade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return nome;
    }

}
