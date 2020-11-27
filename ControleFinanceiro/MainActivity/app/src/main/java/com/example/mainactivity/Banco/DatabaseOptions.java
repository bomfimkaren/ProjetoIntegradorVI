package com.example.mainactivity.Banco;
public class DatabaseOptions {

    public static final String DB_NAME = "local.db";
    public static final int DB_VERSION = 1;

    public static final String USERS_TABLE = "users";

    public static final String ID = "id";
    public static final String LOGIN = "login";
    public static final String SENHA = "senha";

    public static final String CREATE_USERS_TABLE_ =
            "CREATE TABLE  " + USERS_TABLE + "(" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    LOGIN + " TEXT NOT NULL," +
                    SENHA + " TEXT );";
}