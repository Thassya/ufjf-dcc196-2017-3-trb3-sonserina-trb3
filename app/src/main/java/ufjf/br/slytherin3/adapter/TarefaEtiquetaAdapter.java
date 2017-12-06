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

import java.util.ArrayList;
import java.util.List;

import ufjf.br.slytherin3.R;
import ufjf.br.slytherin3.dao.EtiquetaContract;
import ufjf.br.slytherin3.dao.PlannerDbHelper;
import ufjf.br.slytherin3.dao.TarefaContract;
import ufjf.br.slytherin3.dao.TarefaEtiquetaContract;
import ufjf.br.slytherin3.modelos.Etiqueta;
import ufjf.br.slytherin3.modelos.Tarefa;

/**
 * Created by thassya on 05/12/17.
 */

public class TarefaEtiquetaAdapter extends CursorAdapter {
    private PlannerDbHelper dbHelper;

    public TarefaEtiquetaAdapter(Context context, Cursor c) {
        super(context, c, 0);
        dbHelper = new PlannerDbHelper(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.layout_etiqueta, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textDesc = view.findViewById(R.id.txtEtiqueta);
        String desc = cursor.getString(cursor.getColumnIndexOrThrow(EtiquetaContract.Etiqueta.COLUMN_NAME_DESCRICAO));
        String id = cursor.getString(cursor.getColumnIndexOrThrow(EtiquetaContract.Etiqueta._ID));
        textDesc.setText(desc);

    }

    public void atualizar() {
        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String[] visao = {
                    TarefaEtiquetaContract.TarefaEtiqueta.COLUMN_NAME_ETIQUETA,
                    TarefaEtiquetaContract.TarefaEtiqueta.COLUMN_NAME_TAREFA
            };
            Cursor c = db.query(TarefaEtiquetaContract.TarefaEtiqueta.TABLE_NAME, visao, null, null, null, null, null);
            this.changeCursor(c);
        } catch (Exception e) {
            Log.e("TAREFA ETIQ", e.getLocalizedMessage());
            Log.e("TAREFA ETIQ", e.getStackTrace().toString());
        }
    }

    public void atualizar(String idTarefa) {
        String rawQuery = TarefaEtiquetaContract.TarefaEtiqueta.SQL_ETIQUETAS_TAREFA;
        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor c = db.rawQuery(rawQuery, new String[]{String.valueOf(idTarefa)});
            this.changeCursor(c);
        } catch (Exception e) {
            Log.e("RESERVA", e.getLocalizedMessage());
            Log.e("RESERVA", e.getStackTrace().toString());
        }
    }

    public String getId(int i) {
        Cursor c = getCursor();
        c.moveToPosition(i);
        return c.getString(c.getColumnIndex("_id"));
    }

    public void removerEtiqueta(String idTarefa, String idEtiqueta) {
        String rawQuery = TarefaEtiquetaContract.TarefaEtiqueta.SQL_REMOVE_ETIQUETA;
        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //db.rawQuery(rawQuery, new String[]{String.valueOf(idTarefa), String.valueOf(idTag)});

            db.execSQL(rawQuery, new String[]{String.valueOf(idTarefa), String.valueOf(idEtiqueta)});
            atualizar(idTarefa);
        } catch (Exception e) {
            Log.e("ERRO_EXCLUSAO_ETIQUETA", e.getLocalizedMessage());
            Log.e("ERRO_EXCLUSAO_ETIQUETA", e.getStackTrace().toString());
        }
    }

    public void inserirEtiqueta(String idTarefa, String idEtiqueta) {

        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(TarefaEtiquetaContract.TarefaEtiqueta.COLUMN_NAME_TAREFA, idTarefa);
            values.put(TarefaEtiquetaContract.TarefaEtiqueta.COLUMN_NAME_ETIQUETA, idEtiqueta);
            long id = db.insert(TarefaEtiquetaContract.TarefaEtiqueta.TABLE_NAME, null, values);
            atualizar(idTarefa);

        } catch (Exception e) {
            Log.e("ETIQETACONSULTA", e.getLocalizedMessage());
            Log.e("ETIQETACONSULTA", e.getStackTrace().toString());
        }

    }

    public void inserirTarefaEtiqueta(String idTarefa, ArrayList<Etiqueta> etiquetas) {
        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            for (Etiqueta e : etiquetas) {
                values.put(TarefaEtiquetaContract.TarefaEtiqueta.COLUMN_NAME_TAREFA, idTarefa);
                values.put(TarefaEtiquetaContract.TarefaEtiqueta.COLUMN_NAME_ETIQUETA, e.getId());
                db.insert(TarefaEtiquetaContract.TarefaEtiqueta.TABLE_NAME, null, values);
            }
            atualizar(idTarefa);
        } catch (Exception e) {
            Log.e("INSERIR TAREFA ETIQ", e.getLocalizedMessage());
            Log.e("INSERIR TAREFA ETIQ", e.getStackTrace().toString());
        }
    }

    public ArrayList<Tarefa> listaTarefas(String idEtiqueta) {
        ArrayList<Tarefa> resp = new ArrayList<>();
        // String rawQuery = "SELECT t.titulo FROM tarefa_etiqueta te INNER JOIN tarefas t ON t._id=te.idTarefa WHERE te.idEtiqueta = " + idEtiqueta + ";";
        String rawQuery = TarefaEtiquetaContract.TarefaEtiqueta.SQL_GET_TAREFAS_COM_ETIQUETA;

        try {

            SQLiteDatabase db = dbHelper.getReadableDatabase();
            //rawQuery("SELECT id, name FROM people WHERE name = ? AND id = ?", new String[] {"David", "2"});
            Cursor c = db.rawQuery(rawQuery, new String[]{idEtiqueta});
            c.moveToFirst();

            while (!c.isAfterLast()) {
                Tarefa ta = new Tarefa();
                ta.setTitulo(c.getString(c.getColumnIndex(TarefaContract.Tarefa.COLUMN_NAME_TITULO)));
                ta.setDescricao(c.getString(c.getColumnIndex(TarefaContract.Tarefa.COLUMN_NAME_DESCRICAO)));
                resp.add(ta);
                c.moveToNext();
            }

        } catch (Exception e) {
            Log.e("INSERIR TAREFA ETIQ", e.getLocalizedMessage());
            Log.e("INSERIR TAREFA ETIQ", e.getStackTrace().toString());
        }

        return resp;
    }
}
