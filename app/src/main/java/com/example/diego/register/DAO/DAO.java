package com.example.diego.register.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DAO {
    private static String DBPATH;

    private static Context mAppContext = null;

    private static final String NOME_BANCO = "registro";
    private static final int VERSAO = 1;

    private static Database database;

    public DAO(Context context) {
        mAppContext = context;


              /* BANCO DE DADOS */
        try {
            Log.d("SQL", "Inicializando database! ");
            database = new Database(context);

            /* Inicializa e/ou constrói o database */
            Log.d("SQL", "Database a ser utilizado: " + mAppContext.getDatabasePath("registro").getName());

        } catch (Exception e) {
            Log.d("SQL", "Erro ao iniciar database! " + e.toString());
            e.printStackTrace();
        }

            if (mAppContext.getDatabasePath("registro").exists()) {
                Log.d("SQL", "DB Existe! ");

            }

        }

    /**
     * Executa uma Query SEM RETORNO - Utilizado quando não necessita de resposta (Não utilizado para UPDATE/INSERT/DELETE)
     * @author Helcio Macedo
     * @param sqlQuery
     */
    public static void executarQuery(String sqlQuery){
        SQLiteDatabase db = database.getWritableDatabase();

        db.execSQL(sqlQuery);
    }

    /**
     * Executa uma Query de INSERT para inserir uma tupla no banco de dados
     * Retorna o numero de tuplas afetadas
     * @author Helcio Macedo
     * @param nomeDaTabela
     * @param values
     * @return resultado (Long)
     */
    public static long inserir(String nomeDaTabela, ContentValues values){
        SQLiteDatabase db = database.getWritableDatabase();
        long resultado = -1;
        try {
            resultado = db.insert(nomeDaTabela, null, values);
        } catch (Exception e) {
            Log.d("DEBUG", "Erro ao inserir dados! "+e.toString());
        }
        db.close(); // Closing database connection

        return resultado;
    }


    /**
     * Executa uma Query de SELECT para retornar uma tupla no banco de dados
     * @author Helcio Macedo
     * @param tabela
     * @param campos
     * @param where
     * @param whereArgs
     * @param groupBy
     * @param having
     * @param orderBy
     * @return Cursor
     */
    public static Cursor select(String tabela, String campos[], String where, String[] whereArgs, String groupBy, String having, String orderBy) {
        Cursor resultado = null;
        SQLiteDatabase db = database.getReadableDatabase();

        try {
            resultado = db.query(tabela, campos, where, whereArgs, groupBy, having, orderBy);
        } finally {
            db.close();
        }

        return resultado;
    }

    /**
     * Executa uma Query de SELECT para retornar uma tupla no banco de dados (Menos Complexo)
     * @author Helcio Macedo
     * @param oQue
     * @param nomeDaTabela
     * @return Cursor
     */
    public static Cursor select(String oQue, String nomeDaTabela){
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + oQue + " FROM " + nomeDaTabela + ";", null);
        return cursor;
    }

    /**
     * Executa uma Query de SELECT com clausula WHERE para retornar uma tupla no banco de dados (Menos Complexo)
     * @author Helcio Macedo
     * @param oQue
     * @param nomeDaTabela
     * @param where
     * @return Cursor
     */
    public static Cursor select(String oQue, String nomeDaTabela, String where){
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + oQue + " FROM " + nomeDaTabela + " WHERE " + where + ";", null);
        return cursor;
    }

    public static ArrayList<HashMap<String, String>> selectAll(String nomeDaTabela) {
        String selectQuery = "SELECT  * FROM "+nomeDaTabela;

        SQLiteDatabase db = database.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> wordList = new ArrayList<HashMap<String, String>>();

        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {

                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=0;i<cursor.getColumnCount();i++) {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }

                wordList.add(map);

                //Log.d("DEBUG", "SelectAll | Cursor Atual: "+cursor.getString(cursor.getColumnCount()-1));

                cursor.moveToNext();
            }
        }
        else if(cursor.getCount()>0){
            int name = cursor.getCount();
            Log.d("DEBUG", "Unic Result: " +name);
        }
        else {
            Log.d("DEBUG", "[1] No results in " + nomeDaTabela);
        }

        // return contact list
        return wordList;
    }


    public static ArrayList<HashMap<String, String>> selectAll(String nomeDaTabela, String where, String valor) {
        String selectQuery = "SELECT  * FROM "+nomeDaTabela+" WHERE '"+where+"' = "+valor;

        SQLiteDatabase db = database.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> wordList = new ArrayList<HashMap<String, String>>();

        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {

                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=0;i<cursor.getColumnCount();i++) {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }

                wordList.add(map);

                //Log.d("DEBUG", "SelectAll | Cursor Atual: "+cursor.getString(cursor.getColumnCount()-1));

                cursor.moveToNext();
            }
        }
        else if(cursor.getCount()>0){
            int name = cursor.getCount();
            Log.d("DEBUG", "Unic Result: " +name);
        }
        else {
            Log.d("DEBUG", "[2] No results in " + nomeDaTabela);
        }

        // return contact list
        return wordList;
    }



    public static ArrayList<HashMap<String, String>> selectAllLike(String nomeDaTabela, String where, String valor) {
        String selectQuery = "SELECT  * FROM "+nomeDaTabela+" WHERE '"+where+"' LIKE '%"+valor+"%'";

        SQLiteDatabase db = database.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> wordList = new ArrayList<HashMap<String, String>>();

        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {

                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=0;i<cursor.getColumnCount();i++) {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }

                wordList.add(map);

                //Log.d("DEBUG", "SelectAll | Cursor Atual: "+cursor.getString(cursor.getColumnCount()-1));

                cursor.moveToNext();
            }
        }
        else if(cursor.getCount()>0){
            int name = cursor.getCount();
            Log.d("DEBUG", "Unic Result: " +name);
        }
        else {
            Log.d("DEBUG", "[0] No results in " + nomeDaTabela);
        }

        // return contact list
        return wordList;
    }



    public static boolean existe(String coluna, String valor, String nomeDaTabela) {
        String selectQuery = "SELECT COUNT(*) FROM "+nomeDaTabela+" WHERE "+coluna+" = "+valor;

        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            if(cursor.getInt(0)>0) {
                Log.d("DEBUG/SQL","Exists: "+cursor.getString(0));
                return true;
            }

            else return false;
        }
        else return false;
    }



    public static int atualizar(String nomeDaTabela, ContentValues valores) {
        SQLiteDatabase db = database.getWritableDatabase();
        int resultado = -1;

        try {
            resultado = db.update(nomeDaTabela,valores,"ID="+valores.getAsString("ID"), null);
        } finally {
            db.close();
        }
        return resultado;
    }


    public static int atualizarWhere(String nomeDaTabela, ContentValues valores, String where) {
        SQLiteDatabase db = database.getWritableDatabase();
        int resultado = -1;

        try {
            resultado = db.update(nomeDaTabela,valores,where, null);
        } finally {
            db.close();
        }
        return resultado;
    }



    public static int remover(String nomeDaTabela, String where, String[] whereArgs) {
        SQLiteDatabase db = database.getWritableDatabase();
        int resultado = -1;

        try {
            resultado = db.delete(nomeDaTabela, where, whereArgs);
        } finally {
            db.close();
        }
        return resultado;
    }


    public static Cursor showAllTables(){
        SQLiteDatabase db = database.getReadableDatabase();
        return db.rawQuery("SELECT name FROM "+NOME_BANCO+" WHERE type='table' AND name LIKE 'PR_%'", null);
    }



    public static List<String> showAllTablesArray(){
        Cursor c = null;
        List<String> tables = new ArrayList<String>();
        SQLiteDatabase db = database.getReadableDatabase();

        try {
            c = db.rawQuery("SELECT name FROM "+database.getDatabaseName()+" WHERE type='table'", null);
            Log.d("DEBUG", ""+c.toString());

            if (c.moveToFirst())
            {
                do{
                    tables.add(c.getString(0));

                }while (c.moveToNext());
            }
        } catch( SQLiteException e) {
            Log.d("DEBUG","Erro ao selecionar tabelas: "+e.toString());
        }

        return tables;
    }

    public static boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DBPATH;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            Log.d("DEBUG", "Database não existente.." +e.toString());
        }

        if (checkDB != null) {
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }



    public static boolean tableExists(String tableName) {
        SQLiteDatabase db = database.getReadableDatabase();
        Log.d("DEBUG","Checking: "+db.getPath());

        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);
//        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from "+Configuracoes._DATABASE_NAME+" where tbl_name = '"+tableName+"'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    public static boolean isTableEmpty(String tableName) {
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT count(*) FROM "+tableName, null);

        if(cursor!=null)
            cursor.moveToFirst();

        if (cursor.getInt(0) > 0)
            return false;
        else
            return true;
    }


    public static int limparTabela(String nomeDaTabela) {
        SQLiteDatabase db = database.getWritableDatabase();
        return db.delete(nomeDaTabela, "1", null);
    }


    public static boolean resetActualDatatase(){
        try {
            Log.d("SQL", "_DATABASE_RESET habilitado!");
            mAppContext.deleteDatabase("registro"); // RESET DATABASE
            Log.d("SQL", "Database resetado..");
            return true;

        } catch (Exception e) {
            Log.d("SQL", "Falha ao remover database: "+e);
            e.printStackTrace();
            return false;
        }
    }


    public static boolean resetAllDatatases(){
        try {
            Log.d("SQL", "_FULL_DATABASE_RESET habilitado!");
            for (String database : mAppContext.databaseList()) {
                mAppContext.deleteDatabase(database); // RESET DATABASE
                Log.d("SQL", "Removido database: "+database);
            }
            return true;
        } catch (Exception e) {
            Log.d("SQL", "Falha ao remover database: "+e);
            e.printStackTrace();
            return false;
        }
    }
}