package com.example.crudsimples.contatoblock;

import java.io.Serializable;
/*Classe Contato block - ela faz parte da tabela Contatoblock*/
/*criando a classe e Implementando Serializable para colocar nos inputs extras*/
public class ContatoBlock implements Serializable {
/*Declarando as variáveis que vamos utilizar*/
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
/*Nessa metodo de sobrescrever esta retornando os itens do listview da tabela como nome e telefone do contato bloqueado*/
    @Override
    public String toString(){
        return "Nome: " + nomeblock + "                                                              " + "Telefone: " + telefoneblock;
    }
}
