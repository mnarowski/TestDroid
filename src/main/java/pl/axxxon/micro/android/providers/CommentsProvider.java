package pl.axxxon.micro.android.providers;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import pl.axxxon.micro.android.database.Comment;
import pl.axxxon.micro.android.database.columns.CommentsTableDefinition;
import pl.axxxon.micro.android.database.helpers.CommentDbHelper;

/**
 * Created by mnarowski on 03.09.14.
 */
public class CommentsProvider extends ContentProvider {
    private CommentDbHelper mCommentDbHelper;

    public static final String AUTHORITY = "pl.axxxon.micro.android.comments";
    // used for the UriMacher
    private static final int TODOS = 10;
    private static final int TODO_ID = 20;
    private static final String BASE_PATH = "comments";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + BASE_PATH);

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/"+BASE_PATH;
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/comment";

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, TODOS);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", TODO_ID);
    }

    @Override
    public boolean onCreate() {
        mCommentDbHelper = new CommentDbHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri pUri, String[] pStrings, String s, String[] pStrings2, String s2) {

        // Uisng SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // check if the caller has requested a column which does not exists
//        checkColumns(projection);

        // Set the table
        queryBuilder.setTables(CommentsTableDefinition.TABLE_NAME);

        int uriType = sURIMatcher.match(pUri);
        switch (uriType) {
            case TODOS:
                break;
            case TODO_ID:
                // adding the ID to the original query
                queryBuilder.appendWhere(CommentsTableDefinition._ID + "="
                        + pUri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + pUri);
        }

        SQLiteDatabase db = mCommentDbHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, pStrings, s,
                pStrings2, null, null, s2);
        // make sure that potential listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), pUri);
        return cursor;
    }

    @Override
    public String getType(Uri pUri) {
        return null;
    }

    @Override
    public Uri insert(Uri pUri, ContentValues pContentValues) {
        int uriType = sURIMatcher.match(pUri);
        SQLiteDatabase sqlDB = mCommentDbHelper.getWritableDatabase();
        int rowsDeleted = 0;
        long id = 0;
        switch (uriType) {
            case TODOS:
                id = sqlDB.insert(CommentsTableDefinition.TABLE_NAME, null, pContentValues);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + pUri);
        }
        getContext().getContentResolver().notifyChange(pUri, null);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri pUri, String s, String[] pStrings) {
        int uriType = sURIMatcher.match(pUri);
        SQLiteDatabase sqlDB = mCommentDbHelper.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case TODOS:
                rowsDeleted = sqlDB.delete(CommentsTableDefinition.TABLE_NAME, s,
                        pStrings);
                break;
            case TODO_ID:
                String id = pUri.getLastPathSegment();
                if (TextUtils.isEmpty(s)) {
                    rowsDeleted = sqlDB.delete(CommentsTableDefinition.TABLE_NAME,
                            CommentsTableDefinition._ID + "=" + id,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(CommentsTableDefinition.TABLE_NAME,
                            CommentsTableDefinition._ID + "=" + id
                                    + " and " + s,
                            pStrings);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + pUri);
        }
        getContext().getContentResolver().notifyChange(pUri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri pUri, ContentValues pContentValues, String s, String[] pStrings) {

        int uriType = sURIMatcher.match(pUri);
        SQLiteDatabase sqlDB = mCommentDbHelper.getWritableDatabase();
        int rowsUpdated = 0;
        switch (uriType) {
            case TODOS:
                rowsUpdated = sqlDB.update(CommentsTableDefinition.TABLE_NAME,
                        pContentValues,
                        s,
                        pStrings);
                break;
            case TODO_ID:
                String id = pUri.getLastPathSegment();
                if (TextUtils.isEmpty(s)) {
                    rowsUpdated = sqlDB.update(CommentsTableDefinition.TABLE_NAME,
                            pContentValues,
                            CommentsTableDefinition._ID + "=" + id,
                            null);
                } else {
                    rowsUpdated = sqlDB.update(CommentsTableDefinition.TABLE_NAME,
                            pContentValues,
                            CommentsTableDefinition._ID + "=" + id
                                    + " and "
                                    + s,
                            pStrings);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + pUri);
        }
        getContext().getContentResolver().notifyChange(pUri, null);
        return rowsUpdated;
    }
}
