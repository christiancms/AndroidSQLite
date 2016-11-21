package br.com.christian.lojadecds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by christian on 10/11/16.
 */
public class LojacdsDB extends SQLiteOpenHelper {

    public LojacdsDB(Context context) {
        super(context, "lojacds.sqlite", null, 1);
        //context.deleteDatabase("lojacds.sqlite");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("drop table if exists cds");
        db.execSQL("create table if not exists cds(_id integer primary key autoincrement, titulo text," +
                "preco double, anolcto integer, genero_id integer);");
        db.execSQL("create table if not exists generos(_id integer primary key autoincrement, nome text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public long gravar(Cds novo) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("titulo", novo.titulo);
            values.put("preco", novo.preco);
            values.put("anolcto", novo.anolcto);
            values.put("genero_id", novo.genero_id);
            long id = db.insert("cds", "", values);
            return id;
        } finally {
            db.close();
        }
    }

    public long gravar(Generos novo) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("nome", novo.nome);
            long id = db.insert("generos", "", values);
            return id;
        } finally {
            db.close();
        }
    }

    public int excluir(int id) {
        SQLiteDatabase db = getReadableDatabase();

        try {
            String selection = "_id = ?";
            String[] selectionArgs = {String.valueOf(id)};
            return db.delete("generos", selection, selectionArgs);
        } finally {
            db.close();
        }
    }

    public long alterar(Generos genero) {
        SQLiteDatabase db = getReadableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("nome", genero.nome);
            long id = db.update("generos", values, "_id=?", new String[]{String.valueOf(genero._id)});
            return id;
        } finally {
            db.close();
        }
    }

    public List<String> listarGen() {
        List<String> lista = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c;
        try {
            //                   tabela,coluna,whereClause,whereArgs,grouping,having,orderBy
            c = db.query("generos", null, null, null, null, null, null);
            if (c.moveToFirst()) {
                do {
                    lista.add(c.getString(0) + "-" + c.getString(1));
                } while (c.moveToNext());
            }
            c.close();
        } finally {
            db.close();
        }
        return lista;
    }

    public Cursor listarCds() {
        final String sql = " SELECT c.*,g.nome as genero FROM cds AS c INNER JOIN generos AS g" +
                " ON c.genero_id=g._id ";
        SQLiteDatabase db = getWritableDatabase();
        Cursor c;

        String[] campos = {"_id", "titulo", "preco", "anolcto", "genero"};

        try {
            c = db.rawQuery(sql, null);
            if (c != null) {
                c.moveToFirst();
            }
            return c;
        } finally {
            db.close();
        }
    }

    public Cds buscar(String disco) {
        final String sql = " SELECT c.*,g.nome as genero FROM cds AS c INNER JOIN generos AS g" +
                " ON c.genero_id=g._id WHERE c.titulo LIKE ? OR g.nome LIKE ? ";
        SQLiteDatabase db = getReadableDatabase();
        try {
            Cursor c = db.rawQuery(sql, new String[]{"%" + disco + "%","%" + disco + "%"});
            if (c.getCount() > 0) {
                c.moveToFirst();
                long id = c.getLong(0);
                String titulo = c.getString(1);
                double preco = c.getDouble(2);
                int anolcto = c.getInt(3);
                String genero = c.getString(5);
                return new Cds(id, titulo, preco, anolcto, genero);
            } else {
                return new Cds(0, "", 0d, 0, "");
            }
        } finally {
            db.close();
        }
    }
}
// $sql = "SELECT c.*, g.nome as genero FROM cds c ";
//$sql .= "INNER JOIN generos g ON c.genero_id = g.id WHERE c.ativo = 1 ORDER BY c.id";