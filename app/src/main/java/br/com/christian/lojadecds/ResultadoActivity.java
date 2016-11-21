package br.com.christian.lojadecds;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by christian on 19/11/16.
 */
public class ResultadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list_resultado);

        TextView txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        TextView txtPreco = (TextView) findViewById(R.id.txtPreco);
        TextView txtAno = (TextView) findViewById(R.id.txtAno);
        TextView txtGenero = (TextView) findViewById(R.id.txtGenero);

        Intent it = getIntent();
        String titulo = it.getStringExtra("titulo");
        Double preco = it.getDoubleExtra("preco", 0d);
        Integer ano = it.getIntExtra("ano", -1);
        String genero = it.getStringExtra("genero");
        txtTitulo.setText("Título: " + titulo);
        txtPreco.setText("R$ " + preco.toString());
        txtAno.setText("Lançamento: "+ano.toString());
        txtGenero.setText("Gênero: "+genero);
    }
}
