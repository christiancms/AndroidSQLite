package br.com.christian.lojadecds;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

/**
 * Created by christian on 10/11/16.
 */
public class CdsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    private EditText edtTitulo, edtPreco, edtAno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cds);

        edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        edtPreco = (EditText) findViewById(R.id.edtPreco);
        edtAno = (EditText) findViewById(R.id.edtAno);

        // Elemento Spinner(ComboBox)
        spinner  = (Spinner) findViewById(R.id.spnGeneros);
        spinner.setOnItemSelectedListener(this);

        carregaSpinner();
    }

    public void VoltarMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void carregaSpinner(){

        LojacdsDB loja = new LojacdsDB(this);

        // Spinner Drop down elements
        List<String> generos = (List<String>) loja.listarGen();

        ArrayAdapter<String> dadosAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, generos);

        dadosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dadosAdapter);

    }

    public void GravaClick(View view) {
        String titulo = edtTitulo.getText().toString();
        double preco = Double.parseDouble(edtPreco.getText().toString());
        int ano = Integer.parseInt(edtAno.getText().toString());
        String generospn = spinner.getSelectedItem().toString();
        String[] opcaoSpn = generospn.split("-");
        long genero_id= Long.parseLong(opcaoSpn[0]);

        if (titulo.trim().isEmpty()) {
            Toast.makeText(this, "Informe o Título do disco musical", Toast.LENGTH_SHORT).show();
            edtTitulo.requestFocus();
            return;
        }
        if (String.valueOf(preco).trim().isEmpty()) {
            Toast.makeText(this, "Informe o Valor do disco musical", Toast.LENGTH_SHORT).show();
            edtPreco.requestFocus();
            return;
        }
        if (String.valueOf(ano).trim().isEmpty()) {
            Toast.makeText(this, "Informe o Ano de Lançamento do disco musical", Toast.LENGTH_SHORT).show();
            edtAno.requestFocus();
            return;
        }

        LojacdsDB novoCd = new LojacdsDB(this);
        long id = novoCd.gravar(new Cds(0, titulo, preco, ano, genero_id));
        Toast.makeText(this, "Ok! Gênero cadastrado - Cód.: " + id, Toast.LENGTH_SHORT).show();

        edtTitulo.setText("");
        edtPreco.setText("");
        edtAno.setText("");
        edtTitulo.requestFocus();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}