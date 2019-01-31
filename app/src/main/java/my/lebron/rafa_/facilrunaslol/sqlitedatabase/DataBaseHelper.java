package my.lebron.rafa_.facilrunaslol.sqlitedatabase;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rafa_ on 20/02/2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "MiBD";
    public static final int version = 21;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(GestorBD.CREATE_TABLE_CAMPEON);
            db.execSQL(GestorBD.CREATE_TABLE_RUNAS);
            db.execSQL(GestorBD.CREATE_TABLE_RUNAS_PRINCIPALES);
            db.execSQL(GestorBD.CREATE_TABLE_RUNAS_SECUNDARIAS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS campeon");
        db.execSQL("DROP TABLE IF EXISTS runas");
        db.execSQL("DROP TABLE IF EXISTS runasp");
        db.execSQL("DROP TABLE IF EXISTS runass");
        onCreate(db);
    }
}
