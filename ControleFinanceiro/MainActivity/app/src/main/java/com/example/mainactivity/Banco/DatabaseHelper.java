package com.example.mainactivity.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mainactivity.Objetos.Usuario;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, DatabaseOptions.DB_NAME, null, DatabaseOptions.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table
        db.execSQL(DatabaseOptions.CREATE_USERS_TABLE_);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseOptions.USERS_TABLE);
        // Create tables again
        onCreate(db);
    }

    public Usuario queryUser(String login, String senha) {

        SQLiteDatabase db = this.getReadableDatabase();
        Usuario user = null;

        Cursor cursor = db.query(DatabaseOptions.USERS_TABLE, new String[]{DatabaseOptions.ID,
                        DatabaseOptions.LOGIN, DatabaseOptions.SENHA}, DatabaseOptions.LOGIN + "=? and " + DatabaseOptions.SENHA + "=?",
                new String[]{login, senha}, null, null, null, "1");
        if (cursor != null)
            cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            user = new Usuario(cursor.getString(1), cursor.getString(2));
        }
        // return user
        return user;
    }

    public void addUser(Usuario user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseOptions.LOGIN, user.getLogin());
        values.put(DatabaseOptions.SENHA, user.getSenha());

        // Inserting Row
        db.insert(DatabaseOptions.USERS_TABLE, null, values);
        db.close(); // Closing database connection

    }
}
