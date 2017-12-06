package ufjf.br.slytherin3.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ufjf.br.slytherin3.R;
import ufjf.br.slytherin3.adapter.EtiquetaAdapter;
import ufjf.br.slytherin3.adapter.TarefaEtiquetaAdapter;
import ufjf.br.slytherin3.modelos.Etiqueta;
import ufjf.br.slytherin3.modelos.Tarefa;

/**
 * Created by thassya on 05/12/17.
 */

public class NovaEtiquetaActivity extends AppCompatActivity {
    private EditText txtDescricao;
    private ListView lstEtiquetas;
    private Button btnSalvar;
    private Button btnVoltar;
    private EtiquetaAdapter etiquetaAdapter;
    private TarefaEtiquetaAdapter tarefaEtiquetaAdapter;
    private ListView lstTarefas;
    private ArrayList<Tarefa> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_etiqueta);
        model = new ArrayList<>();

        txtDescricao = (EditText)findViewById(R.id.txtDescricao);
        lstEtiquetas = (ListView)findViewById(R.id.lstEtiquetas);
        btnSalvar = (Button)findViewById(R.id.btnSalvar);
        btnVoltar = (Button)findViewById(R.id.btnVoltar);
        lstTarefas = (ListView) findViewById(R.id.lstTarefas);

        etiquetaAdapter = new EtiquetaAdapter(getBaseContext(),null);
        tarefaEtiquetaAdapter= new TarefaEtiquetaAdapter(getBaseContext(),null);
        etiquetaAdapter.atualizar();
        lstEtiquetas.setAdapter(etiquetaAdapter);

        final ArrayAdapter<Tarefa> arrayAdapter = new ArrayAdapter<Tarefa>(this, android.R.layout.simple_list_item_1, model);
        lstTarefas.setAdapter(arrayAdapter);

        lstEtiquetas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String idEtiqueta = String.valueOf(id);

                model.clear();

                ArrayList<Tarefa> teste =tarefaEtiquetaAdapter.listaTarefas(idEtiqueta);
                for(Tarefa t : teste){
                    model.add(t);
                }
                arrayAdapter.notifyDataSetChanged();

            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String desc = txtDescricao.getText().toString();
                if(desc.isEmpty()){
                    txtDescricao.setError(getString(R.string.erroDescricao));
                    txtDescricao.requestFocus();

                    Toast.makeText(NovaEtiquetaActivity.this, R.string.ops, Toast.LENGTH_SHORT).show();

                }else {
                    Etiqueta model = new Etiqueta(desc);
                    txtDescricao.setText("");

                    try{
                        etiquetaAdapter.inserir(model);
                        Toast.makeText(NovaEtiquetaActivity.this, R.string.cadastroRealizado, Toast.LENGTH_SHORT).show();
                        etiquetaAdapter.atualizar();
                    }
                    catch (Exception e){
                        Toast.makeText(NovaEtiquetaActivity.this, getString(R.string.ops) + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
