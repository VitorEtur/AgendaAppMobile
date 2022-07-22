package com.example.crudsimples.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//**Classe que faz conexão com o banco de dados
public class Conexao extends SQLiteOpenHelper {

    private static final String name = "banco.db"; //*********** nome do banco de dados banco.db
    private static final int version = 2; //*********** versão 2

    public Conexao(Context context) {
        super(context, name, null, version);

    }
    //*********** Classe que auxilia a conexão com o banco de dados
    //*********** Varchar - delimita a quantidade de caracteres
    //*********** Create table - cria a tabela
    //*********** Primary Key Define a chave primária do banco de dados
    //*********** AutoIncrement Auto incrementa a id. Ele vai somando de 1 em 1 cada adição no banco de dados
    @Override
    public void onCreate (SQLiteDatabase db) {
    db.execSQL("create table contato(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome varchar(50), telefone varchar(50), cidade varchar(50))");

    db.execSQL("create table contatoimportante(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome varchar(50), telefone varchar(50), cidade varchar(50))");

    db.execSQL("create table contatoblock(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome varchar(50), telefone varchar(50), cidade varchar(50))");

    }

    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1l) {

    }


}
