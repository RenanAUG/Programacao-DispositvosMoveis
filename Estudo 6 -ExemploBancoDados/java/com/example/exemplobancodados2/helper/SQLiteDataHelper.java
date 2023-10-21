package com.example.exemplobancodados2.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/***
* Classe responsável em criar/manter a base dados
*/
public class SQLiteDataHelper extends SQLiteOpenHelper {

    public SQLiteDataHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /***
     * Método responsável pela criação das tabelas
     * Ele executa os scripts no momento de instalação do aplicativo
     * @param sqLiteDatabase
     */

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE ALUNO(RA INTEGER, NOME VARCHAR(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int OLDVERSION, int NEWVERSION) {

    }
}
