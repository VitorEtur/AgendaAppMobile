package com.example.crudsimples.contatoblock;

import java.io.Serializable;

public class ContatoBlock implements Serializable {

    private Integer id;
    private String nomeblock;
    private String telefoneblock;
    private String cidadeblock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeblock() {
        return nomeblock;
    }

    public void setNomeblock(String nomeblock) {
        this.nomeblock = nomeblock;
    }

    public String getTelefoneblock() {
        return telefoneblock;
    }

    public void setTelefoneblock(String telefoneblock) {
        this.telefoneblock = telefoneblock;
    }

    public String getCidadeblock() {
        return cidadeblock;
    }

    public void setCidadeblock(String cidadeblock) {
        this.cidadeblock = cidadeblock;
    }

    @Override
    public String toString(){
        return "Nome: " + nomeblock + "                                                              " + "Telefone: " + telefoneblock;
    }
}
