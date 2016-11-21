package br.com.christian.lojadecds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtPesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtPesquisa = (EditText)findViewById(R.id.edtPesquisa);
    }

    public void GeneroClick(View view) {
        Intent it = new Intent(this, GenerosActivity.class);
        startActivity(it);
    }

    public void CdsClick(View view) {
        Intent it = new Intent(this, CdsActivity.class);
        startActivity(it);
    }

    public void lstCdsClick(View view) {
        Intent it = new Intent(this, ListActivity.class);
        startActivity(it);
    }

    public void PesquisarClick(View view) {
        Intent it = new Intent(this, ResultadoActivity.class);
        String disco = edtPesquisa.getText().toString();
        if (disco.trim().isEmpty()){
            Toast.makeText(this, "Insira o Título do Disco", Toast.LENGTH_SHORT).show();
            edtPesquisa.requestFocus();
            return;
        }
        LojacdsDB loja = new LojacdsDB(this);
        Cds pesq = loja.buscar(disco);
        if (pesq._id == 0){
            Toast.makeText(this, "Não existe", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(this, "Ok - "+pesq.titulo, Toast.LENGTH_SHORT).show();
            it.putExtra("titulo", pesq.titulo);
            it.putExtra("preco", pesq.preco);
            it.putExtra("ano", pesq.anolcto);
            it.putExtra("genero", pesq.genero);
            startActivity(it);

        }

    }
}
