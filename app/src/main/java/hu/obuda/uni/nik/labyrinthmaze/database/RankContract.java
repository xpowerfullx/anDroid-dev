package hu.obuda.uni.nik.labyrinthmaze.database;

/**
 * Created by HP on 19-Apr-17.
 */

import android.provider.BaseColumns;

public final class RankContract {
    private RankContract (){}

    public static final class DatabaseColumns implements BaseColumns{
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_SCORE = "score";
    }
}