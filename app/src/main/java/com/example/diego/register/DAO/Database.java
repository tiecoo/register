package com.example.diego.register.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by hpandelo on 12/11/15.
 */
public class Database extends SQLiteOpenHelper {
    String DBPATH;

    private Context mAppContext = null;

    private static final String NOME_BANCO = "registro";
    private static final int VERSAO = 1;



    public Database(Context context) {
        super(context, NOME_BANCO, null, VERSAO);

        onCreate(getWritableDatabase());
        mAppContext = context;

        DBPATH = context.getDatabasePath(NOME_BANCO).getAbsolutePath();

        Log.d("SQL", "[SQLite] Database iniciado em: " + DBPATH);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("SQL", "Atualizando database..");

        /* GET DATA */
        db.execSQL("DROP TABLE IF EXISTS USUARIO");
        onCreate(db);
    }


    @Override
    public void onCreate(SQLiteDatabase db) throws SQLiteException {
        if(db.isOpen())
            Log.d("SQL", "DB Open..");
        if(db.isReadOnly())
            Log.d("SQL", "DB Read Only..");

        Log.d("SQL", "Criando database..");
        String sqlQuery = "";

        try {
            sqlQuery = "CREATE TABLE IF NOT EXISTS `USUARIO` (" +
                    "ID           integer       primary key     autoincrement" +
                    ",NOME        varchar(60)" +
                    ",EMAIL      varchar(60)" +
                    ",SENHA      varchar(60)" +
                    ");";
            db.execSQL(sqlQuery);


            Log.d("SQL", "Database Criado.. ");
        } catch(Exception e) {
            Log.d("SQL", "Erro ao criar database! " + e.toString());
            e.printStackTrace();
        }
    }

}