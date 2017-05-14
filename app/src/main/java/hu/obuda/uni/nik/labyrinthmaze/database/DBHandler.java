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
        cursor.moveToFirst();
        int count = cursor.getInt(0);
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

    public boolean checkIfEnoughToBeRanked(int score) {
        String selectQuery = String.format("SELECT * FROM %s ORDER BY %s ASC LIMIT 1", RankContract.DatabaseColumns.TABLE_NAME, RankContract.DatabaseColumns.COLUMN_NAME_SCORE);
        SQLiteDatabase db = dbHelpher.getReadableDatabase();
        Cursor dbCursor = db.rawQuery(selectQuery, null);
        dbCursor.moveToFirst();
        int lowestScore = dbCursor.getInt(dbCursor.getColumnIndex("score"));
        db.close();
        dbCursor.close();
        return score > lowestScore;
    }

    public void removeLowestRank() {
        String selectQuery = String.format("SELECT * FROM %s ORDER BY %s ASC LIMIT 1", RankContract.DatabaseColumns.TABLE_NAME, RankContract.DatabaseColumns.COLUMN_NAME_SCORE);
        SQLiteDatabase db = dbHelpher.getReadableDatabase();
        Cursor dbCursor = db.rawQuery(selectQuery, null);
        dbCursor.moveToFirst();
        long id = dbCursor.getLong(dbCursor.getColumnIndex("id"));
        db.delete(RankContract.DatabaseColumns.TABLE_NAME, "ID = " + id, null);
        db.close();
        dbCursor.close();
    }
}