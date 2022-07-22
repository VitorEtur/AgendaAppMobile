package com.example.crudsimples.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoImportante extends SQLiteOpenHelper {

    private static final String name = "bancoimportante.db"; //*********** nome do banco de dados banco.db
    private static final int version = 1; //*********** versão 1

    public ConexaoImportante(Context context) {
        super(context, name, null, version);

    }
    //*********** Classe que auxilia a conexão com o banco de dados
    //*********** Varchar - delimita a quantidade de caracteres
    //*********** Create table - cria a tabela
    //*********** Primary Key Define a chave primária do banco de dados
    //*********** AutoIncrement Auto incrementa a id. Ele vai somando de 1 em 1 cada adição no banco de dados
    @Override
    public void onCreate (SQLiteDatabase db) {
        db.execSQL("create table contatoimportante1(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome varchar(50), telefone varchar(50), cidade varchar(50))");
    }

    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1l) {

    }


}

