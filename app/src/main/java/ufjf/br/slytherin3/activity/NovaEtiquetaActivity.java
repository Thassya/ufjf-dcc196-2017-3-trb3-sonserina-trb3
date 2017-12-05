package ufjf.br.slytherin3.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import ufjf.br.slytherin3.R;
import ufjf.br.slytherin3.adapter.EtiquetaAdapter;
import ufjf.br.slytherin3.dao.EtiquetaContract;
import ufjf.br.slytherin3.modelos.Etiqueta;

/**
 * Created by thassya on 05/12/17.
 */

public class NovaEtiquetaActivity extends AppCompatActivity {
    private EditText txtDescricao;
    private ListView lstEtiquetas;
    private Button btnSalvar;
    private Button btnVoltar;
    private EtiquetaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_etiqueta);

        txtDescricao = (EditText)findViewById(R.id.txtDescricao);
        lstEtiquetas = (ListView)findViewById(R.id.lstEtiquetas);
        btnSalvar = (Button)findViewById(R.id.btnSalvar);
        btnVoltar = (Button)findViewById(R.id.btnVoltar);

        adapter = new EtiquetaAdapter(getBaseContext(),null);
        adapter.atualizar();
        lstEtiquetas.setAdapter(adapter);


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
                        adapter.inserir(model);
                        Toast.makeText(NovaEtiquetaActivity.this, R.string.cadastroRealizado, Toast.LENGTH_SHORT).show();
                        adapter.atualizar();
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
