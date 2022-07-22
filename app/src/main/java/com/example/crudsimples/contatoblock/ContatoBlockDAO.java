package com.example.crudsimples.contatoblock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.crudsimples.Database.Conexao;
import com.example.crudsimples.Database.ConexaoBlock;
import com.example.crudsimples.Database.ConexaoImportante;
import com.example.crudsimples.contatoimportante.ContatoImportante;

import java.util.ArrayList;
import java.util.List;
/*Classe de acesso aos dados.*/
public class ContatoBlockDAO {
/*Atributos*/
    private Conexao conexao;
    private SQLiteDatabase banco;
/*    //************ Método consultor contato
 */
    public ContatoBlockDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }
/*    //************ Método inserir contato
 */
    public long inserir(ContatoBlock contatoBlock) {
        ContentValues values = new ContentValues();
        values.put("nome", contatoBlock.getNomeblock());
        values.put("telefone", contatoBlock.getTelefoneblock());
        values.put("cidade", contatoBlock.getCidadeblock());
        return banco.insert("contatoblock", null, values);
    }

    public List<ContatoBlock> obterTodosBlocks(){
        /*//*************** Associando o formulario com as colunas do banco de dados
         */
        List<ContatoBlock> contatoBlocks = new ArrayList<>();
        Cursor cursor = banco.query("contatoblock", new String[]{"id", "nome", "telefone", "cidade"},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            ContatoBlock a = new ContatoBlock();
            a.setId(cursor.getInt(0));
            a.setNomeblock(cursor.getString(1));
            a.setTelefoneblock(cursor.getString(2));
            a.setCidadeblock(cursor.getString(3));
            contatoBlocks.add(a);
        }
        return contatoBlocks;
    }
/*método para excluir contato bloqueado*/
    public void excluirblock(ContatoBlock contatoBlock){
        banco.delete("contatoblock", "id = ?", new String[] {contatoBlock.getId().toString()} );
    }
/*método para atualizar contato bloqueado*/
    public void atualizarblock(ContatoBlock contatoBlock){
        ContentValues values = new ContentValues();
        values.put("nome", contatoBlock.getNomeblock());
        values.put("telefone", contatoBlock.getTelefoneblock());
        values.put("cidade", contatoBlock.getCidadeblock());
        banco.update("contatoblock", values, "id = ?", new String[]{contatoBlock.getId().toString()});
    }
}
