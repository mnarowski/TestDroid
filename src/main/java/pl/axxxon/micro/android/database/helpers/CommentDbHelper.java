package pl.axxxon.micro.android.database.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import pl.axxxon.micro.android.database.Comment;
import pl.axxxon.micro.android.database.columns.CommentsTableDefinition;

/**
 * Created by mnarowski on 03.09.14.
 */
public class CommentDbHelper extends SQLiteOpenHelper {

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + CommentsTableDefinition.TABLE_NAME + "(" + CommentsTableDefinition._ID
            + " integer primary key autoincrement, " + CommentsTableDefinition.COMMENT
            + " text not null);";
    private static final String DATABASE_NAME = "comments.db";
    private static final String INSERT_FORMAT = "INSERT INTO " + CommentsTableDefinition.TABLE_NAME + " (`" + CommentsTableDefinition.COMMENT + "`) VALUES('Value %d');";

    public CommentDbHelper(Context pContext) {
        super(pContext, DATABASE_NAME, null, 1);
    }

    public CommentDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase pSQLiteDatabase) {
        pSQLiteDatabase.execSQL(DATABASE_CREATE);
        for (int i = 0; i < 100; i++) {
            pSQLiteDatabase.execSQL(String.format(INSERT_FORMAT, i));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase pSQLiteDatabase, int i, int i2) {
        pSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CommentsTableDefinition.TABLE_NAME);
        onCreate(pSQLiteDatabase);
    }
}
