package com.example.crudsimples.contatoimportante;

import java.io.Serializable;

public class ContatoImportante implements Serializable {

    private Integer id;
    private String nomeimportante;
    private String cidadeimportante;
    private String telefoneimportante;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeimportante() {
        return nomeimportante;
    }

    public void setNomeimportante(String nomeimportante) {
        this.nomeimportante = nomeimportante;
    }

    public String getCidadeimportante() {
        return cidadeimportante;
    }

    public void setCidadeimportante(String cidadeimportante) {
        this.cidadeimportante = cidadeimportante;
    }

    public String getTelefoneimportante() {
        return telefoneimportante;
    }

    public void setTelefoneimportante(String telefoneimportante) {
        this.telefoneimportante = telefoneimportante;
    }

    @Override
    public String toString(){
        return "Nome: " + nomeimportante + "                                                              " + "Telefone: " + telefoneimportante;

    }

}
