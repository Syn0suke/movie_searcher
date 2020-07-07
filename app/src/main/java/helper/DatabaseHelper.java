package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Movie;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import model.Film;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Saved.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "saved_movie";
    private static final String COLUMN_TITLE = "movie_title";
    private static final String COLUMN_YEAR = "movie_year";
    private static final String COLUMN_ID = "movie_id";
    private static final String COLUMN_PATH = "thumbnail_path";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=
                "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_TITLE + " TEXT PRIMARY KEY NOT NULL, " +
                        COLUMN_YEAR + " TEXT NOT NULL, " +
                        COLUMN_ID+ " TEXT NOT NULL, " +
                        COLUMN_PATH+ " TEXT NOT NULL);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void AddMovie(String title, String year, String id, String path) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cval = new ContentValues();

        cval.put(COLUMN_TITLE, title);
        cval.put(COLUMN_YEAR, year);
        cval.put(COLUMN_ID, id);
        cval.put(COLUMN_PATH, path);
        long result = db.insert(TABLE_NAME, null, cval);
        if (result == -1) {
            Toast.makeText(context, "Save Failed", Toast.LENGTH_LONG).show();
            db.close();
        } else {
            Toast.makeText(context, "Save successful", Toast.LENGTH_LONG).show();
            db.close();
        }

    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public int Count() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM " + TABLE_NAME;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            count = cursor.getInt(0);
        }
        cursor.close();

        return count;
    }

    public ArrayList<Film> ViewData() {
        String query = "SELECT * FROM "+ TABLE_NAME;
        ArrayList<Film> movieList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
            while(cursor.moveToNext()){
                String MovieTitle = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                String MovieYear = cursor.getString(cursor.getColumnIndex(COLUMN_YEAR));
                String MovieId = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
                String Path = cursor.getString(cursor.getColumnIndex(COLUMN_PATH));

                movieList.add(new Film(MovieTitle,MovieYear,MovieId,Path));
            }
        }
        db.close();
        return movieList;
    }

    public void Delete(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"movie_title = ?",new String[]{title});
        db.close();
    }
}
