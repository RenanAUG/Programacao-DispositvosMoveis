package com.example.exemplobancodados2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.exemplobancodados2.helper.SQLiteDataHelper;
import com.example.exemplobancodados2.model.Disciplina;

import java.util.ArrayList;

public class DisciplinaDao implements GenericDao<Disciplina>{

    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase sqLiteDatabase;

    private String nomeTabela = "DISCIPLINA";

    private String[]colunas = {"IDDISCIPLINA", "DESCRICAO", "PERIODO", "CARGAHORARIA", "IDPROFESSOR"};

    private Context context;

    public static DisciplinaDao instancia;

    public static DisciplinaDao getInstancia(Context context) {
        if (instancia == null) {
            return instancia = new DisciplinaDao(context);
        } else {
            return instancia;
        }
    }

    private DisciplinaDao(Context context) {
        this.context = context;

        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null, 1);

        sqLiteDatabase = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Disciplina obj) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("IDDISCIPLINA", obj.getIdDisciplina());
            contentValues.put("DESCRICAO", obj.getDescricao());
            contentValues.put("PERIODO", obj.getPeriodo());
            contentValues.put("CARGAHORARIA", obj.getCargaHorario());
            contentValues.put("IDPROFESSOR", obj.getIdProfessor());

            return sqLiteDatabase.insert(nomeTabela, null, contentValues);
        } catch (SQLException ex) {
            Log.e("ERRO", "DisciplinaDao.insert(): "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Disciplina obj) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(colunas[1],obj.getDescricao());
            contentValues.put(colunas[2],obj.getPeriodo());
            contentValues.put(colunas[3],obj.getCargaHorario());
            contentValues.put(colunas[4],obj.getIdProfessor());

            String[]identificador = {String.valueOf(obj.getIdDisciplina())};

            return sqLiteDatabase.update(nomeTabela, contentValues, colunas[0]+" = ?", identificador);
        } catch (SQLException ex) {
            Log.e("ERRO", "DisciplinaDao.update(): "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Disciplina obj) {
        try {
            String[]identificador = {String.valueOf(obj.getIdDisciplina())};

            return sqLiteDatabase.delete(nomeTabela, colunas[0]+" = ?", identificador);
        } catch (SQLException ex) {
            Log.e("ERRO", "DisciplinaDao.delete(): "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Disciplina> getAll() {
        ArrayList<Disciplina> lista = new ArrayList<>();
        try {
            Cursor cursor = sqLiteDatabase.query(nomeTabela, colunas, null, null, null, null, colunas[0]);

            if (cursor.moveToFirst()) {
                do {
                    Disciplina disciplina = new Disciplina();
                    disciplina.setIdDisciplina(cursor.getInt(0));
                    disciplina.setDescricao(cursor.getString(1));
                    disciplina.setPeriodo(cursor.getInt(2));
                    disciplina.setCargaHorario(cursor.getDouble(3));
                    disciplina.setIdProfessor(cursor.getInt(4));

                    lista.add(disciplina);
                } while (cursor.moveToNext());
            }

        } catch (SQLException ex) {
            Log.e("ERRO", "DisciplinaDao.getAll(): "+ex.getMessage());
        }
        return null;
    }

    @Override
    public Disciplina getById(int id) {
        try {
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = sqLiteDatabase.query(nomeTabela, colunas, colunas[0]+" = ?", identificador, null, null, null);

            if (cursor.moveToFirst()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setIdDisciplina(cursor.getInt(0));
                disciplina.setDescricao(cursor.getString(1));
                disciplina.setPeriodo(cursor.getInt(2));
                disciplina.setCargaHorario(cursor.getDouble(3));
                disciplina.setIdProfessor(cursor.getInt(4));

                return disciplina;
            }
        } catch (SQLException ex) {
            Log.e("ERRO", "DisciplinaDao.getById(): "+ex.getMessage());
        }
        return null;
    }
}
