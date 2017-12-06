package ufjf.br.slytherin3.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by thassya on 05/12/17.
 */

public class PlannerDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "sonserinaPlanner.db";

    public PlannerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TarefaContract.Tarefa.SQL_CREATE_TABLE);
        db.execSQL(EtiquetaContract.Etiqueta.SQL_CREATE_TABLE);
        db.execSQL(TarefaEtiquetaContract.TarefaEtiqueta.SQL_CREATE_TABLE);
        Log.i("PLANNER sql", TarefaEtiquetaContract.TarefaEtiqueta.SQL_CREATE_TABLE);
        Log.i("PLANNER", "Tabelas CRIADAS");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TarefaContract.Tarefa.SQL_DROP_TAREFA);
        db.execSQL(EtiquetaContract.Etiqueta.SQL_DROP_ETIQUETA);
        db.execSQL(TarefaEtiquetaContract.TarefaEtiqueta.SQL_DROP_TAREFA_ETIQUETA);

        Log.i("PLANNER", "Tabelas atualizadas de v" + oldVersion + " para v" + newVersion);
        onCreate(db);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("PLANNER", "Tabelas revertidas de v" + oldVersion + " para v" + newVersion);
        onUpgrade(db, oldVersion, newVersion);
    }
}
