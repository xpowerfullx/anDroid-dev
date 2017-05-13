package hu.obuda.uni.nik.labyrinthmaze.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by HP on 19-Apr-17.
 */

public class DBHandler {

    DBHelper dbHelpher;

    public DBHandler(Context context) {
        dbHelpher = new DBHelper(context);
    }

    public long insertRank(String name, double time) {
        SQLiteDatabase db = dbHelpher.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RankContract.DatabaseColumns.COLUMN_NAME_NAME, name);
        values.put(RankContract.DatabaseColumns.COLUMN_NAME_SCORE, time);

        long newRowID = db.insert(RankContract.DatabaseColumns.TABLE_NAME, null, values);
        db.close();
        return newRowID;
    }

    public int GetDbCount() {
        String countQuery = "SELECT COUNT(1) FROM " + RankContract.DatabaseColumns.TABLE_NAME;
        SQLiteDatabase db = dbHelpher.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getInt(1);
        db.close();
        cursor.close();
        return count;
    }

    public Cursor loadUsers() {
        SQLiteDatabase db = dbHelpher.getReadableDatabase();
        String sortOder = RankContract.DatabaseColumns.COLUMN_NAME_SCORE + " DESC";
        Cursor result = db.query(RankContract.DatabaseColumns.TABLE_NAME, null, null, null, null, null, sortOder);
        result.moveToFirst();
        db.close();
        return result;
    }
}