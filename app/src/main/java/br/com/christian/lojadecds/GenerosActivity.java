package br.com.christian.lojadecds;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by christian on 10/11/16.
 */
public class GenerosActivity extends AppCompatActivity {

    private EditText edtGenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generos);

        edtGenero = (EditText) findViewById(R.id.edtGenero);
    }

    public void VoltarMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void SalvarClick(View view) {
        String genero = edtGenero.getText().toString();

        if (genero.trim().isEmpty()) {
            Toast.makeText(this, "Informe o Gênero musical", Toast.LENGTH_SHORT).show();
            edtGenero.requestFocus();
            return;
        }

        LojacdsDB novoGenero = new LojacdsDB(this);
        long id = novoGenero.gravar(new Generos(0, genero));
        Toast.makeText(this, "Ok! Gênero cadastrado - Cód.: " + id, Toast.LENGTH_SHORT).show();

        edtGenero.setText("");
        edtGenero.requestFocus();
    }
}
