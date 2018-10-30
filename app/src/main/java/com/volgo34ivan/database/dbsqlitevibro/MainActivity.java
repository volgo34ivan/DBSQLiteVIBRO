package com.volgo34ivan.database.dbsqlitevibro;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

    final String LOG_TAG = "myLogs";

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;

    Button btnAdd, btnFind, btnClear, btnUpd, btnDel;
    EditText col_1ET,col_2ET,col_3ET,col_4ET,col_5ET,col_6ET,col_7ET,col_8ET,col_9ET,col_10ET,col_11ET,col_12ET,col_13ET,col_14ET,col_15ET,col_16ET,col_17ET,col_18ET,col_19ET;

    DBHelper dbHelper;
    final Integer TEST_WINDOWS = 0; // 1 - вкл; 0 - выкл; тест корректности окон ввода (автозаполнение)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqlHelper = new DatabaseHelper(getApplicationContext());
        // создаем базу данных
        sqlHelper.create_db();

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnFind = (Button) findViewById(R.id.btnFind);
        btnFind.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        btnUpd = (Button) findViewById(R.id.btnUpd);
        btnUpd.setOnClickListener(this);

        btnDel = (Button) findViewById(R.id.btnDel);
        btnDel.setOnClickListener(this);

        col_1ET = (EditText)findViewById(R.id.col_1ET);
        col_2ET = (EditText)findViewById(R.id.col_2ET);
        col_3ET = (EditText)findViewById(R.id.col_3ET);
        col_4ET = (EditText)findViewById(R.id.col_4ET);
        col_5ET = (EditText)findViewById(R.id.col_5ET);
        col_6ET = (EditText)findViewById(R.id.col_6ET);
        col_7ET = (EditText)findViewById(R.id.col_7ET);
        col_8ET = (EditText)findViewById(R.id.col_8ET);
        col_9ET = (EditText)findViewById(R.id.col_9ET);
        col_10ET = (EditText)findViewById(R.id.col_10ET);
        col_11ET = (EditText)findViewById(R.id.col_11ET);
        col_12ET = (EditText)findViewById(R.id.col_12ET);
        col_13ET = (EditText)findViewById(R.id.col_13ET);
        col_14ET = (EditText)findViewById(R.id.col_14ET);
        col_15ET = (EditText)findViewById(R.id.col_15ET);
        col_16ET = (EditText)findViewById(R.id.col_16ET);
        col_17ET = (EditText)findViewById(R.id.col_17ET);
        col_18ET = (EditText)findViewById(R.id.col_18ET);
        col_19ET = (EditText)findViewById(R.id.col_19ET);

        // создаем объект для создания и управления версиями БД
        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        // создаем объект для данных
        ContentValues cv = new ContentValues();

        // получаем данные из полей ввода
        String col_1 = col_1ET.getText().toString(); //Подразделение
        String col_2 = col_2ET.getText().toString(); //Участок
        String col_3 = col_3ET.getText().toString(); //Агрегат
        String col_4 = col_4ET.getText().toString(); //Частота вращения ротора электродвигателя
        String col_5 = col_5ET.getText().toString(); //Частота вращения рабочего колеса
        String col_6 = col_6ET.getText().toString(); //Количество подшипников
        String col_7 = col_7ET.getText().toString(); //Количество муфт
        String col_8 = col_8ET.getText().toString(); //Тип муфты
        String col_9 = col_9ET.getText().toString(); //Число лопастей рабочего колеса
        String col_10 = col_10ET.getText().toString(); //Число зубьев тихоходного вала
        String col_11 = col_11ET.getText().toString(); //Число зубьев быстроходного вала
        String col_12 = col_12ET.getText().toString(); //Число пазов статора
        String col_13 = col_13ET.getText().toString(); //Число пазов ротора
        String col_14 = col_14ET.getText().toString(); //Число щеток коллектора
        String col_15 = col_15ET.getText().toString(); //Число зубьев муфты
        String col_16 = col_16ET.getText().toString(); //Высота оси вращения ротора
        String col_17 = col_17ET.getText().toString(); //Высота оси вращения редуктора
        String col_18 = col_18ET.getText().toString(); //Высота оси вращения рабочего колеса
        String col_19 = col_19ET.getText().toString(); //Индекс агрегата

        if(TEST_WINDOWS == 1){
            col_1ET.setText("окно 1");
            col_2ET.setText("окно 2");
            col_3ET.setText("окно 3");
            col_4ET.setText("окно 4");
            col_5ET.setText("окно 5");
            col_6ET.setText("окно 6");
            col_7ET.setText("окно 7");
            col_8ET.setText("окно 8");
            col_9ET.setText("окно 9");
            col_10ET.setText("окно 10");
            col_11ET.setText("окно 11");
            col_12ET.setText("окно 12");
            col_13ET.setText("окно 13");
            col_14ET.setText("окно 14");
            col_15ET.setText("окно 15");
            col_16ET.setText("окно 16");
            col_17ET.setText("окно 17");
            col_18ET.setText("окно 18");
            col_19ET.setText("окно 19");
        }


        // подключаемся к БД
        //SQLiteDatabase db = dbHelper.getWritableDatabase();
           SQLiteDatabase db =  sqlHelper.open();



        switch (v.getId()) {
            case R.id.btnAdd:
                Log.d(LOG_TAG, "--- Insert in mytable: ---");
                // подготовим данные для вставки в виде пар: наименование столбца - значение
                cv.put("subdivision", col_1);
                cv.put("station", col_2);
                cv.put("unit", col_3);
                cv.put("speedEM", col_4);
                cv.put("speedWW", col_5);
                cv.put("nbearings", col_6);
                cv.put("ncouplings", col_7);
                cv.put("typeCoupling", col_8);
                cv.put("blades", col_9);
                cv.put("toothSlow", col_10);
                cv.put("toothFast", col_11);
                cv.put("grooveStator", col_12);
                cv.put("grooveRotor", col_13);
                cv.put("cheekCollector", col_14);
                cv.put("toothCoupling", col_15);
                cv.put("heighRotor", col_16);
                cv.put("heighReducer", col_17);
                cv.put("heighWW", col_18);
                // вставляем запись и получаем ее ID
                long rowID = db.insert("INFO", null , cv);
                Log.d(LOG_TAG, "row inserted, ID = " + rowID);
                break;
            case R.id.btnFind:
                Intent MainActivityTwo = new Intent(MainActivity.this, MainActivityTwo.class);
                MainActivity.this.startActivity(MainActivityTwo);
                break;
            case R.id.btnClear:
                Log.d(LOG_TAG, "--- Clear mytable: ---");
                // удаляем все записи
                int clearCount = db.delete("INFO", null, null);
                Log.d(LOG_TAG, "deleted rows count = " + clearCount);
                break;
            case R.id.btnUpd:
                if (col_19.equalsIgnoreCase("")) {
                    break;
                }
                Log.d(LOG_TAG, "--- Update mytable: ---");
                // подготовим значения для обновления
                cv.put("subdivision", col_1);
                cv.put("station", col_2);
                cv.put("unit", col_3);
                cv.put("speedEM", col_4);
                cv.put("speedWW", col_5);
                cv.put("nbearings", col_6);
                cv.put("ncouplings", col_7);
                cv.put("typeCoupling", col_8);
                cv.put("blades", col_9);
                cv.put("toothSlow", col_10);
                cv.put("toothFast", col_11);
                cv.put("grooveStator", col_12);
                cv.put("grooveRotor", col_13);
                cv.put("cheekCollector", col_14);
                cv.put("toothCoupling", col_15);
                cv.put("heighRotor", col_16);
                cv.put("heighReducer", col_17);
                cv.put("heighWW", col_18);
                // обновляем по id
                int updCount = db.update("INFO", cv, "_id = ?",
                        new String[] { col_19 });
                Log.d(LOG_TAG, "updated rows count = " + updCount);
                break;
            case R.id.btnDel:
                if (col_19.equalsIgnoreCase("")) {
                    break;
                }
                Log.d(LOG_TAG, "--- Delete from mytable: ---");
                // удаляем по id
                int delCount = db.delete("INFO", "_id = " + col_19, null);
                Log.d(LOG_TAG, "deleted rows count = " + delCount);
                break;
        }
        // закрываем подключение к БД
        dbHelper.close();
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "INFO", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            // создаем таблицу с полями
            db.execSQL("create table INFO ("
                    + "id integer primary key autoincrement,"
                    + "subdivision text,"
                    + "station text,"
                    + "unit text,"
                    + "speedEM text,"
                    + "speedWW text,"
                    + "nbearings text,"
                    + "ncouplings text,"
                    + "typeCoupling text,"
                    + "blades text,"
                    + "toothSlow text,"
                    + "toothFast text,"
                    + "grooveStator text,"
                    + "grooveRotor text,"
                    + "cheekCollector text,"
                    + "toothCoupling text,"
                    + "heighRotor text,"
                    + "heighReducer text,"
                    + "heighWW text" + ");");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
