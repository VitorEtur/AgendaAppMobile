package com.example.crudsimples.contatoimportante;

import java.io.Serializable;
/*Classe Contato Importante - ela faz parte da tabela contatoimportante*/
/*criando a classe e Implementando Serializable para colocar nos inputs extras*/
public class ContatoImportante implements Serializable {
    /*Declarando as variáveis que vamos utilizar*/

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
    /*Nessa metodo de sobrescrever esta retornando os itens do listview da tabela como nome e telefone do contato bloqueado*/

    @Override
    public String toString(){
        return "Nome: " + nomeimportante + "                                                              " + "Telefone: " + telefoneimportante;

    }

}
