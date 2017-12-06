package ufjf.br.slytherin3.dao;

import android.provider.BaseColumns;

/**
 * Created by thassya on 05/12/17.
 */

public class TarefaEtiquetaContract {
    public TarefaEtiquetaContract() {
    }

    public static final String TEXT_TYPE = " TEXT ";
    public static final String INT_TYPE = " INTEGER ";
    public static final String SEP = ", ";

    public static final class TarefaEtiqueta implements BaseColumns {
        public static final String TABLE_NAME = "tarefa_etiqueta";
        public static final String COLUMN_NAME_TAREFA = "idTarefa";
        public static final String COLUMN_NAME_ETIQUETA = "idEtiqueta";

        public static final String SQL_DROP_TAREFA_ETIQUETA = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( " + _ID + INT_TYPE + " PRIMARY KEY AUTOINCREMENT" + SEP +
                COLUMN_NAME_TAREFA + INT_TYPE + SEP + COLUMN_NAME_ETIQUETA + INT_TYPE + SEP +
                " FOREIGN KEY ( " + COLUMN_NAME_TAREFA + ") REFERENCES " + TarefaContract.Tarefa.TABLE_NAME + " (" + TarefaContract.Tarefa._ID + ") " + SEP +
                " FOREIGN KEY ( " + COLUMN_NAME_ETIQUETA + ") REFERENCES " + EtiquetaContract.Etiqueta.TABLE_NAME + " ( " + EtiquetaContract.Etiqueta._ID + ")"
                + " );";

        public static final String SQL_ETIQUETAS_TAREFA = "SELECT * FROM " + TABLE_NAME + " te " +
                "INNER JOIN " + EtiquetaContract.Etiqueta.TABLE_NAME + " e ON te._id = e._id WHERE te." + COLUMN_NAME_TAREFA + "=?";

        public static final String SQL_REMOVE_ETIQUETA = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_TAREFA + "=? AND " + COLUMN_NAME_ETIQUETA + "=?";



        public static final String SQL_GET_TAREFAS_COM_ETIQUETA = "SELECT *, t." + TarefaContract.Tarefa.COLUMN_NAME_TITULO + " FROM " +
                TABLE_NAME + " te INNER JOIN " + TarefaContract.Tarefa.TABLE_NAME + " t ON t."+ TarefaContract.Tarefa._ID +"=te." + COLUMN_NAME_TAREFA +
                " WHERE " + COLUMN_NAME_ETIQUETA + "=?";
    }
}
