package ufjf.br.slytherin3.dao;

import android.provider.BaseColumns;

/**
 * Created by thassya on 05/12/17.
 */

public class EtiquetaContract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String INT_TYPE = " INTEGER";
    public static final String SEP = ",";

    public static final class Etiqueta implements BaseColumns {
        public static final String TABLE_NAME = "etiquetas";
        public static final String COLUMN_NAME_DESCRICAO = "descricao";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + Etiqueta.TABLE_NAME + " (" +
                Etiqueta._ID + INT_TYPE + " PRIMARY KEY AUTOINCREMENT" + SEP +
                Etiqueta.COLUMN_NAME_DESCRICAO + TEXT_TYPE + ")";

        public static final String SQL_DROP_ETIQUETA = "DROP TABLE IF EXISTS " + Etiqueta.TABLE_NAME;

    }
    public EtiquetaContract(){}
}
