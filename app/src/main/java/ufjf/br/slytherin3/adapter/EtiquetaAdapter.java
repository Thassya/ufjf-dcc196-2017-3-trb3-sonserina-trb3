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

import ufjf.br.slytherin3.dao.EtiquetaContract;
import ufjf.br.slytherin3.dao.PlannerDbHelper;
import ufjf.br.slytherin3.modelos.Etiqueta;

import ufjf.br.slytherin3.R;

/**
 * Created by thassya on 05/12/17.
 */

public class EtiquetaAdapter extends CursorAdapter {
    private PlannerDbHelper dbHelper;

    public EtiquetaAdapter(Context context, Cursor c) {
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
        textDesc.setText(desc);
    }

    public void atualizar() {
        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String[] visao = {
                    EtiquetaContract.Etiqueta._ID,
                    EtiquetaContract.Etiqueta.COLUMN_NAME_DESCRICAO,
            };
            Cursor c = db.query(EtiquetaContract.Etiqueta.TABLE_NAME, visao, null, null, null, null, null);
            this.changeCursor(c);
        } catch (Exception e) {
            Log.e("ETIQUETA", e.getLocalizedMessage());
            Log.e("ETIQUETA", e.getStackTrace().toString());
        }
    }

    public void inserir(Etiqueta model) {
        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(EtiquetaContract.Etiqueta.COLUMN_NAME_DESCRICAO, model.getDescricao());
            long id = db.insert(EtiquetaContract.Etiqueta.TABLE_NAME, null, values);
            atualizar();
        } catch (Exception e) {
            Log.e("INSERIR ETIQUETA", e.getLocalizedMessage());
            Log.e("INSERIR ETIQUETA", e.getStackTrace().toString());
        }
    }
}
