package com.example.crudsimples;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
//************ Classe de acesso aos dados.
public class ContatoDAO {
    //************ Atributos
    private Conexao conexao;
    private SQLiteDatabase banco;
    //************ Método consultor contato
    public ContatoDAO (Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }
    //************ Método inserir contato
    public long inserir(Contato contato){
        ContentValues values = new ContentValues();
        values.put("nome", contato.getNome());
        values.put("telefone", contato.getTelefone());
        values.put("cidade", contato.getCidade());

        return banco.insert("contato", null, values);
    }

    public List<Contato> obterTodos(){
//*************** Associando o formulario com as colunas do banco de dados
        List<Contato> contatos = new ArrayList<>();
        Cursor cursor = banco.query("contato", new String[]{"id", "nome", "telefone", "cidade"},
                null, null, null, null, null);

        while (cursor.moveToNext()){
            Contato a = new Contato();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setTelefone(cursor.getString(2));
            a.setCidade(cursor.getString(3));
            contatos.add(a);
        }
        return contatos;
    }

    public void excluir(Contato contato){
        banco.delete("contato", "id = ?", new String[] {contato.getId().toString()} );
    }

    public void atualizar(Contato contato){
        ContentValues values = new ContentValues();
        values.put("nome", contato.getNome());
        values.put("telefone", contato.getTelefone());
        values.put("cidade", contato.getCidade());
        banco.update("contato", values, "id = ?", new String[]{contato.getId().toString()});
    }

}
