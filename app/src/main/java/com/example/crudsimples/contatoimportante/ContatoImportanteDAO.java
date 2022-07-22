package com.example.crudsimples.contatoimportante;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import com.example.crudsimples.Database.Conexao;
import com.example.crudsimples.Database.ConexaoImportante;
import com.example.crudsimples.contatonormal.Contato;

import java.util.ArrayList;
import java.util.List;
/*Classe de acesso aos dados.*/
public class ContatoImportanteDAO {
    /*Atributos*/
    private Conexao conexao;
    private SQLiteDatabase banco;
    /*    //************ Método consultor contato
     */
    public ContatoImportanteDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }
    /*    //************ Método inserir contato
     */
    public long inserir(ContatoImportante contatoImportante) {
        ContentValues values = new ContentValues();
        values.put("nome", contatoImportante.getNomeimportante());
        values.put("telefone", contatoImportante.getTelefoneimportante());
        values.put("cidade", contatoImportante.getCidadeimportante());
        return banco.insert("contatoimportante", null, values);
    }
    /*//*************** Associando o formulario com as colunas do banco de dados
     */
    public List<ContatoImportante> obterTodosImportantes(){
        List<ContatoImportante> contatosimportantes = new ArrayList<>();
        Cursor cursor = banco.query("contatoimportante", new String[]{"id", "nome", "telefone", "cidade"},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            ContatoImportante a = new ContatoImportante();
            a.setId(cursor.getInt(0));
            a.setNomeimportante(cursor.getString(1));
            a.setTelefoneimportante(cursor.getString(2));
            a.setCidadeimportante(cursor.getString(3));
            contatosimportantes.add(a);
        }
        return contatosimportantes;
    }
    /*método para excluir contato bloqueado*/

    public void excluirimportante(ContatoImportante contatoImportante){
        banco.delete("contatoimportante", "id = ?", new String[] {contatoImportante.getId().toString()} );
    }
    /*método para atualizar contato bloqueado*/

    public void atualizarimportante(ContatoImportante contatoImportante){
        ContentValues values = new ContentValues();
        values.put("nome", contatoImportante.getNomeimportante());
        values.put("telefone", contatoImportante.getTelefoneimportante());
        values.put("cidade", contatoImportante.getCidadeimportante());
        banco.update("contatoimportante", values, "id = ?", new String[]{contatoImportante.getId().toString()});
    }
}
