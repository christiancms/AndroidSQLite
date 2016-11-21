package br.com.christian.lojadecds;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by christian on 15/11/16.
 */
public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lstCds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        LojacdsDB loja = new LojacdsDB(this);
        Cursor c = loja.listarCds();


        String[] nomeCampos = new String[]{"_id", "titulo", "preco", "anolcto", "genero"};
        int[] idViews = new int[]{R.id.txtId, R.id.txtTitulo, R.id.txtPreco, R.id.txtAno, R.id.txtGenero};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item_list_cds, c, nomeCampos, idViews, 0);

        lstCds = (ListView) findViewById(R.id.lstCds);
        lstCds.setAdapter(adapter);

        lstCds.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
        Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
        Toast.makeText(this, "Disco: " + cursor.getString(1), Toast.LENGTH_SHORT).show();

    }
}
