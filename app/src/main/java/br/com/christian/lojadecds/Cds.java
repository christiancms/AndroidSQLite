package br.com.christian.lojadecds;

import android.graphics.Bitmap;

import java.sql.Date;

/**
 * Created by christian on 15/11/16.
 */
public class Cds {
    public long _id;
    public String titulo;
    public double preco;
    public int anolcto;
    public long genero_id;
    public String genero;

    public Cds(long _id, String titulo, double preco, int anolcto, long genero_id) {
        this._id = _id;
        this.titulo = titulo;
        this.preco = preco;
        this.anolcto = anolcto;
        this.genero_id = genero_id;
    }

    public Cds(long _id, String titulo, double preco, int anolcto, String genero) {
        this._id = _id;
        this.titulo = titulo;
        this.preco = preco;
        this.anolcto = anolcto;
        this.genero = genero;
    }
}
