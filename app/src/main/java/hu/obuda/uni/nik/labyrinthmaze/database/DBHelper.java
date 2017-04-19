package hu.obuda.uni.nik.labyrinthmaze.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by HP on 19-Apr-17.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "rank";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_USERS);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_USERS =
            ("CREATE TABLE " + RankContract.DatabaseColumns.TABLE_NAME + "(" +
                    RankContract.DatabaseColumns.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    RankContract.DatabaseColumns.COLUMN_NAME_NAME + "   VARCHAR(255)," +
                    RankContract.DatabaseColumns.COLUMN_NAME_TIME + "   VARCHAR(255)" + ")");

    private static final String SQL_DELETE_USERS =
            "DROP TABLE IF EXISTS " + RankContract.DatabaseColumns.TABLE_NAME;
}