package ufjf.br.slytherin3.dao;

import android.provider.BaseColumns;

/**
 * Created by thassya on 05/12/17.
 */

public class TarefaContract {

    public TarefaContract() {
    }

    public static final String TEXT_TYPE = " TEXT ";
    public static final String INT_TYPE = " INTEGER ";
    public static final String SEP = ", ";

    public static final class Tarefa implements BaseColumns {
        public static final String TABLE_NAME = "tarefas";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_DESCRICAO = "descricao";
        public static final String COLUMN_NAME_DIFICULDADE = "dificuldade";
        public static final String COLUMN_NAME_ESTADO = "estado";

        public static final String SQL_DROP_TAREFA= "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT" + SEP +
                COLUMN_NAME_TITULO+TEXT_TYPE +  SEP+
                COLUMN_NAME_DESCRICAO + TEXT_TYPE + SEP +
                COLUMN_NAME_DIFICULDADE + TEXT_TYPE + SEP +
                COLUMN_NAME_ESTADO + TEXT_TYPE +"); ";

    }


}
