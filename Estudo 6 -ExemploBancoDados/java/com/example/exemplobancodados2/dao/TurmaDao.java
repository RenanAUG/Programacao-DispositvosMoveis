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
import com.example.exemplobancodados2.model.Turma;

import java.util.ArrayList;

public class TurmaDao implements GenericDao<Turma> {

    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase sqLiteDatabase;

    private String nomeTabela = "TURMA";

    private String[]colunas = {"IDTURMA", "CURSO", "ANOINICIO", "ANOFIM"};

    private Context context;

    public static TurmaDao instancia;

    public static TurmaDao getInstancia(Context context) {
        if (instancia == null) {
            return instancia = new TurmaDao(context);
        } else {
            return instancia;
        }
    }

    private TurmaDao(Context context) {
        this.context = context;

        openHelper = new SQLiteDataHelper(this.context, "UNIPAR",
                null, 1);

        sqLiteDatabase = openHelper.getWritableDatabase();
    }


    @Override
    public long insert(Turma obj) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("IDTURMA", obj.getIdTurma());
            contentValues.put("CURSO", obj.getCurso());
            contentValues.put("ANOINICIO", obj.getAnoInicio());
            contentValues.put("ANOFIM", obj.getAnoFim());

            return sqLiteDatabase.insert(nomeTabela, null, contentValues);
        } catch (SQLException sqlException) {
            Log.e("ERRO", "TurmaDao.insert(): "+sqlException.getMessage());
        }

        return 0;
    }

    @Override
    public long update(Turma obj) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(colunas[1], obj.getCurso());
            contentValues.put(colunas[2], obj.getAnoInicio());
            contentValues.put(colunas[3], obj.getAnoFim());

            String[]identificador = {String.valueOf(obj.getIdTurma())};
            return sqLiteDatabase.update(nomeTabela, contentValues, "RA = ?", identificador);

        } catch (SQLException ex) {
            Log.e("ERRO", "TurmaDao.update(): "+ex.getMessage());

        }
        return 0;
    }

    @Override
    public long delete(Turma obj) {
        try {
            String[]identificador = {String.valueOf(obj.getIdTurma())};
            return sqLiteDatabase.delete(nomeTabela, colunas[0]+" = ?", identificador);
        } catch (SQLException ex) {
            Log.e("ERRO", "TurmaDao.delete(): "+ex.getMessage());
        }

        return 0;
    }

    @Override
    public ArrayList<Turma> getAll() {
        ArrayList<Turma> lista = new ArrayList<>();
        try {
            Cursor cursor = sqLiteDatabase.query(nomeTabela, colunas, null, null, null, null, colunas[0]);

            if (cursor.moveToFirst()) {
                do {
                    Turma turma = new Turma();
                    turma.setIdTurma(cursor.getInt(0));
                    turma.setCurso(cursor.getString(1));
                    turma.setAnoInicio(cursor.getInt(2));
                    turma.setAnoFim(cursor.getInt(3));

                    lista.add(turma);
                } while (cursor.moveToNext());
            }
        } catch (SQLException ex) {
            Log.e("ERRO", "TurmaDao.getAll(): "+ex.getMessage());
        }
        return null;
    }

    @Override
    public Turma getById(int id) {
        try {
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = sqLiteDatabase.query(nomeTabela, colunas, colunas[0]+" = ?", identificador, null, null, null);

            if (cursor.moveToFirst()) {
                Turma turma = new Turma();
                turma.setIdTurma(cursor.getInt(0));
                turma.setCurso(cursor.getString(1));
                turma.setAnoInicio(cursor.getInt(2));
                turma.setAnoFim(cursor.getInt(3));

                return turma;
            }
        } catch (SQLException ex) {
            Log.e("ERRO", "TurmaDao.getById(): "+ex.getMessage());
        }

        return null;
    }
}
