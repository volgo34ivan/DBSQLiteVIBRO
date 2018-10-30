package com.volgo34ivan.database.dbsqlitevibro;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH; // полный путь к базе данных
    private static String DB_NAME = "INFO.db";
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "INFO"; // название таблицы в бд
    // названия столбцов
    static final String COLUMN_ID = "_id";
    static final String COLUMN_SUBDIVISION = "subdivision";
    static final String COLUMN_STATION = "station";
    static final String COLUMN_UNIT = "unit";
    static final String COLUMN_SPEED_EM = "speedEM";
    static final String COLUMN_SPEED_WW = "speedWW";
    static  final String COLUMN_NBEARINGS = "nbearings";
    static final String COLUMN_NCOUPLINGS = "ncouplings";
    static final String COLUMN_TYPE_COUPLING = "typeCoupling";
    static final String COLUMN_BLADES = "blades";
    static final String COLUMN_TOOTH_SLOW = "toothSlow";
    static final String COLUMN_TOOTH_FAST = "toothFast";
    static final String COLUMN_GROOVE_STATOR = "grooveStator";
    static final String COLUMN_GROOVE_ROTOR = "grooveRotor";
    static final String COLUMN_CHEEK_COLLECTOR = "cheekCollector";
    static final String COLUMN_TOOTH_COUPLING = "toothCoupling";
    static final String COLUMN_HEIGH_ROTOR = "heighRotor";
    static final String COLUMN_HEIGH_REDUCER = "heighReducer";
    static final String COLUMN_HEIGH_WW = "heighWW";
    private Context myContext;

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext=context;
        DB_PATH =context.getFilesDir().getPath() + DB_NAME;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
    }
    void create_db(){
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            File file = new File(DB_PATH);
            if (!file.exists()) {
                this.getReadableDatabase();
                //получаем локальную бд как поток
                myInput = myContext.getAssets().open(DB_NAME);
                // Путь к новой бд
                String outFileName = DB_PATH;

                // Открываем пустую бд
                myOutput = new FileOutputStream(outFileName);

                // побайтово копируем данные
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        }
        catch(IOException ex){
            Log.d("DatabaseHelper", ex.getMessage());
        }
    }
    SQLiteDatabase open()throws SQLException {

        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }

}
