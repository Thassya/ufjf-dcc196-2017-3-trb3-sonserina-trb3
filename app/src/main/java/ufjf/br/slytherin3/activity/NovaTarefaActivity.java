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

public class NovaTarefaActivity extends AppCompatActivity {
    private EditText txtTitulo;
    private EditText txtDescricao;
    private Spinner spnEstado;
    private Spinner spnDificuldade;
    private Button btnSalvar;
    private Button btnCancelar;

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

        spnEstado.setAdapter(new ArrayAdapter<Utils.Estados>(this, android.R.layout.simple_spinner_item, Utils.Estados.values()));
        spnDificuldade.setAdapter(new ArrayAdapter<Utils.Dificuldade>(this, android.R.layout.simple_spinner_item, Utils.Dificuldade.values()));


        adapter = new TarefaAdapter(getBaseContext(), null);

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

                adapter.inserirTarefa(t);
                adapter.atualizar();
                Toast.makeText(NovaTarefaActivity.this, R.string.tarefaCadastrada, Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
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
