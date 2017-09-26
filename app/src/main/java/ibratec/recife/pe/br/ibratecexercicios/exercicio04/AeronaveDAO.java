package ibratec.recife.pe.br.ibratecexercicios.exercicio04;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frederico on 26/09/2017.
 */

public class AeronaveDAO {

    private AeronavesSQLHelper bdHelper;

    public AeronaveDAO(Context context) {
        bdHelper = new AeronavesSQLHelper(context);
    }

    private long inserir(Aeronave aeronave) {

        SQLiteDatabase db = bdHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(bdHelper.TABELA_AERONAVE_COLUNA_MODELO, aeronave.getModelo());
        cv.put(bdHelper.TABELA_AERONAVE_COLUNA_FABRICANTE, aeronave.getFabricante());
        cv.put(bdHelper.TABELA_AERONAVE_COLUNA_ASA_FIXA, valorBooleano(aeronave.isAsaFixa()));
        cv.put(bdHelper.TABELA_AERONAVE_COLUNA_TREM_RETRATIL, valorBooleano(aeronave.isTremRetratil()));
        cv.put(bdHelper.TABELA_AERONAVE_COLUNA_MULTIMOTOR, valorBooleano(aeronave.isMultimotor()));
        cv.put(bdHelper.TABELA_AERONAVE_COLUNA_VELOCIDADE_CRUZEIRO, aeronave.getVelocidadeCruzeiro());
        cv.put(bdHelper.TABELA_AERONAVE_COLUNA_HANGAR, aeronave.getHangar());
        cv.put(bdHelper.TABELA_AERONAVE_COLUNA_APTO, valorBooleano(aeronave.isApto()));

        long id = db.insert(bdHelper.TABELA_AERONAVE, null, cv);

        if (id != -1) {
            aeronave.setId(id);
        }

        db.close();

        return id;
    }

    private int atualizar(Aeronave aeronave) {

        SQLiteDatabase db = bdHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(bdHelper.TABELA_AERONAVE_COLUNA_MODELO, aeronave.getModelo());
        cv.put(bdHelper.TABELA_AERONAVE_COLUNA_FABRICANTE, aeronave.getFabricante());
        cv.put(bdHelper.TABELA_AERONAVE_COLUNA_ASA_FIXA, valorBooleano(aeronave.isAsaFixa()));
        cv.put(bdHelper.TABELA_AERONAVE_COLUNA_TREM_RETRATIL, valorBooleano(aeronave.isTremRetratil()));
        cv.put(bdHelper.TABELA_AERONAVE_COLUNA_MULTIMOTOR, valorBooleano(aeronave.isMultimotor()));
        cv.put(bdHelper.TABELA_AERONAVE_COLUNA_VELOCIDADE_CRUZEIRO, aeronave.getVelocidadeCruzeiro());
        cv.put(bdHelper.TABELA_AERONAVE_COLUNA_HANGAR, aeronave.getHangar());
        cv.put(bdHelper.TABELA_AERONAVE_COLUNA_APTO, valorBooleano(aeronave.isApto()));

        int linhasAlteradas = db.update(bdHelper.TABELA_AERONAVE, cv,
                bdHelper.TABELA_AERONAVE_COLUNA_ID + " = ?",
                new String[]{String.valueOf(aeronave.getId())});
        db.close();

        return linhasAlteradas;
    }

    public void salvar(Aeronave aeronave) {
        if (aeronave.getId() == 0) {
            this.inserir(aeronave);
        } else {
            this.atualizar(aeronave);
        }
    }

    public int excluir(Aeronave aeronave) {
        SQLiteDatabase db = bdHelper.getWritableDatabase();

        int linhasExcluidas = db.delete(bdHelper.TABELA_AERONAVE,
                bdHelper.TABELA_AERONAVE_COLUNA_ID + " = ?",
                new String[]{String.valueOf(aeronave.getId())});
        db.close();

        return linhasExcluidas;
    }

    public List<Aeronave> buscarAeronavesPorModelo(String modelo) {

        List<Aeronave> res = new ArrayList<Aeronave>();

        SQLiteDatabase db = bdHelper.getReadableDatabase();

        String sql = "SELECT * FROM " + bdHelper.TABELA_AERONAVE;
        String[] args = null;

        if (modelo != null) {
            sql = sql + " WHERE " + bdHelper.TABELA_AERONAVE_COLUNA_MODELO + " LIKE ?";
            args = new String[]{modelo};
        }

        sql = sql + " ORDER BY " + bdHelper.TABELA_AERONAVE_COLUNA_MODELO;

        Cursor cursor = db.rawQuery(sql, args);
        while (cursor.moveToNext()) {
            long idCol = cursor.getLong(cursor.getColumnIndex(bdHelper.TABELA_AERONAVE_COLUNA_ID));
            String modeloCol = cursor.getString(cursor.getColumnIndex(bdHelper.TABELA_AERONAVE_COLUNA_MODELO));

            //
            //.........
            //..........
            //

            Aeronave aeronave = new Aeronave();
            aeronave.setId(idCol);
            aeronave.setModelo(modeloCol);
            res.add(aeronave);
        }

        return res;
    }

    private int valorBooleano(Boolean valor) {
        int res = 0;
        if (valor != null) {
            if (valor) {
                res = 1;
            }
        }
        return res;
    }
}
