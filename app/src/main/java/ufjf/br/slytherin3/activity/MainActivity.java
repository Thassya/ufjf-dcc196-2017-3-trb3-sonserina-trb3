package ufjf.br.slytherin3.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import ufjf.br.slytherin3.R;
import ufjf.br.slytherin3.adapter.TarefaAdapter;

public class MainActivity extends AppCompatActivity {

    private Button btnAddTarefa;
    private Button btnAddEtiqueta;
    private ListView lstTarefas;
    private TarefaAdapter tarefaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstTarefas = (ListView) findViewById(R.id.lstTarefas);
        btnAddEtiqueta = (Button)findViewById(R.id.btnAddEtiqueta);
        btnAddTarefa = (Button) findViewById(R.id.btnAddTarefa);

        tarefaAdapter = new TarefaAdapter(getBaseContext(), null);
        tarefaAdapter.atualizar();
        lstTarefas.setAdapter(tarefaAdapter);

        lstTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent editarTarefa = new Intent(MainActivity.this, EditarTarefaActivity.class);
                editarTarefa.putExtra("idTarefa", String.valueOf(id));
                startActivity(editarTarefa);
            }
        });
        lstTarefas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //criar DIALOG confirm https://developer.android.com/guide/topics/ui/dialogs.html?hl=pt-br
                tarefaAdapter.deletar(String.valueOf(id));
                tarefaAdapter.atualizar();
                Toast.makeText(MainActivity.this, R.string.tarefaExcluida, Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        btnAddTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent novaTarefa = new Intent(MainActivity.this, NovaTarefaActivity.class);
                startActivity(novaTarefa);
            }

            @Override
            protected void finalize() throws Throwable {
                tarefaAdapter.atualizar();
                super.finalize();
            }
        });

        btnAddEtiqueta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent novaEtiqueta = new Intent(MainActivity.this, NovaEtiquetaActivity.class);
                startActivity(novaEtiqueta);
            }
        });

    }
}
