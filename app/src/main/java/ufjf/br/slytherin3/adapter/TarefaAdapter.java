package ufjf.br.slytherin3.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import ufjf.br.slytherin3.R;
import ufjf.br.slytherin3.dao.PlannerDbHelper;
import ufjf.br.slytherin3.dao.TarefaContract;
import ufjf.br.slytherin3.modelos.Tarefa;
import ufjf.br.slytherin3.modelos.Utils;

/**
 * Created by thassya on 05/12/17.
 */

public class TarefaAdapter extends CursorAdapter {
    private PlannerDbHelper dbHelper;

    public TarefaAdapter(Context context, Cursor c) {
        super(context, c, 0);
        dbHelper = new PlannerDbHelper(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.layout_tarefa, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtTitulo = view.findViewById(R.id.txtTitulo);
        TextView txtDificuldade = view.findViewById(R.id.txtDificuldade);
        TextView txtEstado = view.findViewById(R.id.txtEstado);
        txtTitulo.setText(cursor.getString(cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLUMN_NAME_TITULO)));

        Utils.Dificuldade dif = Utils.Dificuldade.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLUMN_NAME_DIFICULDADE)));
        txtDificuldade.setText(dif.getValue());

        Utils.Estados est = Utils.Estados.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLUMN_NAME_ESTADO)));
        txtEstado.setText(est.getValue());
    }

    public void atualizar() {
        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String[] visao = {
                    TarefaContract.Tarefa._ID,
                    TarefaContract.Tarefa.COLUMN_NAME_TITULO,
                    TarefaContract.Tarefa.COLUMN_NAME_DESCRICAO,
                    TarefaContract.Tarefa.COLUMN_NAME_DIFICULDADE,
                    TarefaContract.Tarefa.COLUMN_NAME_ESTADO,
            };
            Cursor c = db.query(TarefaContract.Tarefa.TABLE_NAME, visao, null, null, null, null, TarefaContract.Tarefa.COLUMN_NAME_ESTADO + " ASC");
            this.changeCursor(c);
        } catch (Exception e) {
            Log.e("ATUALIZAR TAREFA", e.getLocalizedMessage());
            Log.e("ATUALIZAR TAREFA", e.getStackTrace().toString());
        }
    }

    public void inserirTarefa(Tarefa t) {
        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(TarefaContract.Tarefa.COLUMN_NAME_TITULO, t.getTitulo());
            values.put(TarefaContract.Tarefa.COLUMN_NAME_DESCRICAO, t.getDescricao());
            values.put(TarefaContract.Tarefa.COLUMN_NAME_DIFICULDADE, String.valueOf(t.getDificuldade()));
            values.put(TarefaContract.Tarefa.COLUMN_NAME_ESTADO, String.valueOf(t.getEstados()));


            long id = db.insert(TarefaContract.Tarefa.TABLE_NAME, null, values);

        } catch (Exception e) {
            Log.e("SALVAR TAREFA", e.getLocalizedMessage());
            Log.e("SALVAR TAREFA", e.getStackTrace().toString());
        }

    }

    public Tarefa getTarefa(String id) {
        Tarefa tarefa = new Tarefa();
        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String[] visao = {
                    TarefaContract.Tarefa.COLUMN_NAME_TITULO,
                    TarefaContract.Tarefa.COLUMN_NAME_DESCRICAO,
                    TarefaContract.Tarefa.COLUMN_NAME_DIFICULDADE,
                    TarefaContract.Tarefa.COLUMN_NAME_ESTADO,
            };
            String selecao = TarefaContract.Tarefa._ID + "= " + id;
            Cursor c = db.query(TarefaContract.Tarefa.TABLE_NAME, visao, selecao, null, null, null, null);
            c.moveToFirst();

            tarefa.setTitulo(c.getString(c.getColumnIndex(TarefaContract.Tarefa.COLUMN_NAME_TITULO)));
            tarefa.setDescricao(c.getString(c.getColumnIndex(TarefaContract.Tarefa.COLUMN_NAME_DESCRICAO)));
            tarefa.setDificuldade(Utils.Dificuldade.valueOf(c.getString(c.getColumnIndex(TarefaContract.Tarefa.COLUMN_NAME_DIFICULDADE))));
            tarefa.setEstados(Utils.Estados.valueOf(c.getString(c.getColumnIndex(TarefaContract.Tarefa.COLUMN_NAME_ESTADO))));

        } catch (Exception e) {
            Log.e("GETTAREFA", e.getLocalizedMessage());
            Log.e("GETTAREFA", e.getStackTrace().toString());
        }
        return tarefa;
    }

    public void atualizar(Tarefa tarefa, String id) {
        ContentValues values = new ContentValues();
        values.put(TarefaContract.Tarefa.COLUMN_NAME_TITULO, tarefa.getTitulo());
        values.put(TarefaContract.Tarefa.COLUMN_NAME_DESCRICAO, tarefa.getDescricao());
        values.put(TarefaContract.Tarefa.COLUMN_NAME_DIFICULDADE, Utils.Dificuldade.getEnumByString(tarefa.getDificuldade().getValue()));
        values.put(TarefaContract.Tarefa.COLUMN_NAME_ESTADO, Utils.Estados.getEnumByString(tarefa.getEstados().getValue()));

        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.update(TarefaContract.Tarefa.TABLE_NAME, values, "_id=" + id, null);
        } catch (Exception e) {
            Log.e("ATUALIZAR TAREFA", e.getLocalizedMessage());
            Log.e("ATUALIZAR TAREFA", e.getStackTrace().toString());
        }
    }

    public void deletar(String id) {
        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String sql = "DELETE FROM " + TarefaContract.Tarefa.TABLE_NAME + " WHERE " + TarefaContract.Tarefa._ID + "=" + id;
            db.execSQL(sql);
        } catch (Exception e) {
            Log.e("EXCLUIR TAREFA", e.getLocalizedMessage());
            Log.e("EXCLUIR TAREFA", e.getStackTrace().toString());
        }
    }
}

