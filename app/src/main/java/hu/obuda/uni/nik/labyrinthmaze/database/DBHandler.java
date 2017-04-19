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
    private int dBCount=0;
    private final int maxDbCount=10;

    public DBHandler(Context context) {
        dbHelpher = new DBHelper(context);
    }


    public long insertRank(String name, double time) {
        SQLiteDatabase db = dbHelpher.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RankContract.DatabaseColumns.COLUMN_NAME_NAME, name);
        values.put(RankContract.DatabaseColumns.COLUMN_NAME_TIME, time);

        if(dBCount<maxDbCount)
        {
            long newRowID = db.insert(RankContract.DatabaseColumns.TABLE_NAME, null, values);
            dBCount++;
            db.close();
            return newRowID;
        }
        else
        {
            long newRowID = db.insert(RankContract.DatabaseColumns.TABLE_NAME, null, values);
            db.close();
            return newRowID;
        }
    }

    private int GetDbCount(){
        String countQuery = "SELECT * FROM "+ RankContract.DatabaseColumns.TABLE_NAME;
        SQLiteDatabase db = dbHelpher.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        int cnt = cursor.getCount();
        return cnt;
    }

    public Cursor loadUsers() {
        SQLiteDatabase db = dbHelpher.getReadableDatabase();
        String[] projection = {
                RankContract.DatabaseColumns.COLUMN_NAME_NAME,
                RankContract.DatabaseColumns.COLUMN_NAME_TIME
        };
        //String sortOder = RankContract.DatabaseColumns.COLUMN_NAME_TIME + " DESC";
        Cursor result = db.query(RankContract.DatabaseColumns.TABLE_NAME,null,null,null,null,null,null);
        result.moveToFirst();
        db.close();
        return result;
    }
}
