package com.example.exemplobancodados2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.exemplobancodados2.helper.SQLiteDataHelper;
import com.example.exemplobancodados2.model.Aluno;

import java.util.ArrayList;

public class AlunoDao implements GenericDao<Aluno>{

    //Variável para abrir a conexão com BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase sqLiteDatabase;

    //Nome da tabela
    private String nomeTabela = "ALUNO";

    //Nome das colunas da tabela
    private String[]colunas = {"RA", "NOME"};

    private Context context;

    public static AlunoDao instancia;

    public static AlunoDao getInstancia(Context context) {
        if (instancia == null) {
            return instancia = new AlunoDao(context);
        } else {
            return instancia;
        }
    }

    private AlunoDao(Context context) {
        this.context = context;

        //Abrir uma conexão da BD
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR",
                null, 1);

        //Carrega o BD e da permissão para escrever na tabela
        sqLiteDatabase = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Aluno obj) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("RA", obj.getRa());
            contentValues.put("NOME", obj.getNome());

            return sqLiteDatabase.insert(nomeTabela, null, contentValues);

        } catch (SQLException ex) {
            Log.e("ERRO", "AlunoDao.insert(): " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Aluno obj) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(colunas[1], obj.getNome());

            String[]identificador = {String.valueOf(obj.getRa())};
            return sqLiteDatabase.update(nomeTabela, contentValues, "RA = ?", identificador);

        } catch (SQLException ex) {
            Log.e("ERRO", "AlunoDao.update();" +ex.getMessage());
        }

        return 0;
    }

    @Override
    public long delete(Aluno obj) {
        try {

           String[]identificador = {String.valueOf(obj.getRa())};
           return sqLiteDatabase.delete(nomeTabela, colunas[0]+" = ?", identificador);

        } catch (SQLException ex) {
            Log.e("ERRO", "AlunoDao.delete()" + ex.getMessage());
        }

        return 0;
    }

    @Override
    public ArrayList<Aluno> getAll() {
        ArrayList<Aluno> lista = new ArrayList<>();
        try {
            Cursor cursor = sqLiteDatabase.query(nomeTabela, colunas,
                    null, null, null, null, colunas[0]);

            if (cursor.moveToFirst()) {
                do {
                    Aluno aluno = new Aluno();
                    aluno.setRa(cursor.getInt(0));
                    aluno.setNome(cursor.getString(1));

                    lista.add(aluno);
                } while (cursor.moveToNext());
            }
        } catch (SQLException ex) {
            Log.e("ERRO", "AlunoDao.getAll()" + ex.getMessage());
        }
        return null;
    }

    @Override
    public Aluno getById(int id) {
        try {
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = sqLiteDatabase.query(nomeTabela, colunas, colunas[0]+" = ?", identificador, null, null, null);

            if (cursor.moveToFirst()) {
                Aluno aluno = new Aluno();
                aluno.setRa(cursor.getInt(0));
                aluno.setNome(cursor.getString(1));

                return aluno;
            }
        } catch (SQLException ex) {
            Log.e("ERRO", "AlunoDao.getById()" + ex.getMessage());
        }
        return null;
    }
}
