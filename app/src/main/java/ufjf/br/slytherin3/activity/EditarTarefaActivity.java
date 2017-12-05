package ufjf.br.slytherin3.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import ufjf.br.slytherin3.R;
import ufjf.br.slytherin3.adapter.TarefaAdapter;
import ufjf.br.slytherin3.modelos.Tarefa;
import ufjf.br.slytherin3.modelos.Utils;

/**
 * Created by thassya on 05/12/17.
 */

public class EditarTarefaActivity extends AppCompatActivity {
    private EditText txtTitulo;
    private EditText txtDescricao;
    private Spinner spnEstado;
    private Spinner spnDificuldade;
    private Button btnSalvar;
    private Button btnCancelar;
    private String idTarefa;
    private Tarefa tarefa;

    private TarefaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_tarefa);

        txtTitulo = (EditText) findViewById(R.id.txtTitulo);
        txtDescricao = (EditText) findViewById(R.id.txtDescricao);
        spnEstado = (Spinner) findViewById(R.id.spnEstado);
        spnDificuldade = (Spinner) findViewById(R.id.spnDificuldade);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        idTarefa = getIntent().getStringExtra("idTarefa");

        adapter = new TarefaAdapter(getBaseContext(), null);

        spnEstado.setAdapter(new ArrayAdapter<Utils.Estados>(this, android.R.layout.simple_spinner_item, Utils.Estados.values()));
        spnDificuldade.setAdapter(new ArrayAdapter<Utils.Dificuldade>(this, android.R.layout.simple_spinner_item, Utils.Dificuldade.values()));

        adapter.atualizar();
        tarefa = adapter.getTarefa(idTarefa);
        txtTitulo.setText(tarefa.getTitulo());
        txtDescricao.setText(tarefa.getDescricao());
        switch (tarefa.getDificuldade()) {
            case FACIL: {
                spnDificuldade.setSelection(0);
                break;
            }
            case QUASEFACIL: {
                spnDificuldade.setSelection(1);
                break;
            }
            case MEDIO: {
                spnDificuldade.setSelection(2);
                break;
            }
            case DIFICIL: {
                spnDificuldade.setSelection(3);
                break;
            }
            case MUITO_DIFICIL: {
                spnDificuldade.setSelection(4);
                break;
            }
            default: {
                break;
            }
        }
        switch (tarefa.getEstados()) {
            case AFAZER: {
                spnEstado.setSelection(0);
                break;
            }
            case EMEXECUCAO: {
                spnEstado.setSelection(1);
                break;
            }
            case BLOQUEADA: {
                spnEstado.setSelection(2);
                break;
            }
            case CONLCUIDA: {
                spnEstado.setSelection(3);
                break;
            }
            default: {
                break;
            }
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarefa.setTitulo(txtTitulo.getText().toString());
                tarefa.setDescricao(txtDescricao.getText().toString());

                Utils.Estados testeS = (Utils.Estados) spnEstado.getSelectedItem();
                tarefa.setEstados(testeS);
                Utils.Dificuldade dificuldade = (Utils.Dificuldade) spnDificuldade.getSelectedItem();
                tarefa.setDificuldade(dificuldade);

                adapter.atualizar(tarefa, idTarefa);
                adapter.atualizar();

                Toast.makeText(EditarTarefaActivity.this, R.string.tarefaAtualizada, Toast.LENGTH_SHORT).show();

                setResult(RESULT_OK);
                finish();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
