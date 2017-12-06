package ufjf.br.slytherin3.activity;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import ufjf.br.slytherin3.R;
import ufjf.br.slytherin3.adapter.EtiquetaAdapter;
import ufjf.br.slytherin3.adapter.TarefaAdapter;
import ufjf.br.slytherin3.adapter.TarefaEtiquetaAdapter;
import ufjf.br.slytherin3.dao.TarefaEtiquetaContract;
import ufjf.br.slytherin3.modelos.Etiqueta;
import ufjf.br.slytherin3.modelos.Tarefa;
import ufjf.br.slytherin3.modelos.Utils;

/**
 * Created by thassya on 05/12/17.
 */

public class NovaTarefaActivity extends AppCompatActivity {
    private EditText txtTitulo;
    private EditText txtDescricao;
    private Spinner spnEstado;
    private Spinner spnDificuldade;
    private Button btnSalvar;
    private Button btnCancelar;

    private Button btnAddEtiqueta;
    private Spinner spnEtiquetas;
    private ListView lstEtiquetas;

    private TarefaAdapter tarefaAdapter;
    private EtiquetaAdapter etiquetaAdapter;
    private TarefaEtiquetaAdapter tarefaEtiquetaAdapter;

    ArrayList<Etiqueta> etiquetasadicionadas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_tarefa);

        etiquetasadicionadas = new ArrayList<>();

        txtTitulo = (EditText) findViewById(R.id.txtTitulo);
        txtDescricao = (EditText) findViewById(R.id.txtDescricao);
        spnEstado = (Spinner) findViewById(R.id.spnEstado);
        spnDificuldade = (Spinner) findViewById(R.id.spnDificuldade);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnAddEtiqueta = (Button) findViewById(R.id.btnAddEtiqueta);
        spnEtiquetas = (Spinner) findViewById(R.id.spnEtiquetas);
        lstEtiquetas = (ListView) findViewById(R.id.lstEtiquetas);

        spnEstado.setAdapter(new ArrayAdapter<Utils.Estados>(this, android.R.layout.simple_spinner_item, Utils.Estados.values()));
        spnDificuldade.setAdapter(new ArrayAdapter<Utils.Dificuldade>(this, android.R.layout.simple_spinner_item, Utils.Dificuldade.values()));


        tarefaAdapter = new TarefaAdapter(getBaseContext(), null);
        etiquetaAdapter = new EtiquetaAdapter(getBaseContext(), null);
        tarefaEtiquetaAdapter = new TarefaEtiquetaAdapter(getBaseContext(), null);

        tarefaAdapter.atualizar();
        etiquetaAdapter.atualizar();
        spnEtiquetas.setAdapter(etiquetaAdapter);

        final ArrayAdapter<Etiqueta> arrayAdapter = new ArrayAdapter<Etiqueta>(this, android.R.layout.simple_list_item_1, etiquetasadicionadas);
        lstEtiquetas.setAdapter(arrayAdapter);

        btnAddEtiqueta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = etiquetaAdapter.getId(spnEtiquetas.getSelectedItemPosition());
                Etiqueta et = etiquetaAdapter.getEtiqueta(id);

                if (!etiquetasadicionadas.contains((Etiqueta)et))
                    etiquetasadicionadas.add(et);
                arrayAdapter.notifyDataSetChanged();

            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = txtTitulo.getText().toString();
                String descricao = txtDescricao.getText().toString();

                Tarefa t = new Tarefa();
                t.setTitulo(titulo);
                t.setDescricao(descricao);

                Utils.Estados testeS = (Utils.Estados) spnEstado.getSelectedItem();
                t.setEstados(testeS);

                Utils.Dificuldade dificuldade = (Utils.Dificuldade) spnDificuldade.getSelectedItem();
                t.setDificuldade(dificuldade);

                long idTarefa = tarefaAdapter.inserirTarefa(t);

                tarefaEtiquetaAdapter.inserirTarefaEtiqueta(String.valueOf(idTarefa), etiquetasadicionadas);

                Toast.makeText(NovaTarefaActivity.this, R.string.tarefaCadastrada, Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);

                tarefaAdapter.atualizar();
                finish();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
