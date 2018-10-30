package com.volgo34ivan.database.dbsqlitevibro;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;

public class MainActivityTwo extends AppCompatActivity {
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    ListView userList;
    EditText userFilter;
    String sort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ver2);
        userList = (ListView)findViewById(R.id.userList);
        userFilter = (EditText)findViewById(R.id.userFilter);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.RadioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case -1:
                        sort = DatabaseHelper.COLUMN_SUBDIVISION;
                    break;
                    case R.id.radioButton1:
                        sort = DatabaseHelper.COLUMN_SUBDIVISION;
                    break;
                    case R.id.radioButton2:
                        sort = DatabaseHelper.COLUMN_STATION;
                    break;
                    case R.id.radioButton3:
                        sort = DatabaseHelper.COLUMN_UNIT;
                    break;
                }
            }
        });

        sqlHelper = new DatabaseHelper(getApplicationContext());
        // создаем базу данных
        sqlHelper.create_db();
    }
    @Override
    public void onResume() {
        super.onResume();
        try {
            db = sqlHelper.open();
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE, null);
            String[] headers = new String[]{
                    DatabaseHelper.COLUMN_ID,
                    DatabaseHelper.COLUMN_SUBDIVISION,
                    DatabaseHelper.COLUMN_STATION,
                    DatabaseHelper.COLUMN_UNIT,
                    DatabaseHelper.COLUMN_SPEED_EM,
                    DatabaseHelper.COLUMN_SPEED_WW,
                    DatabaseHelper.COLUMN_NBEARINGS,
                    DatabaseHelper.COLUMN_NCOUPLINGS,
                    DatabaseHelper.COLUMN_TYPE_COUPLING,
                    DatabaseHelper.COLUMN_BLADES,
                    DatabaseHelper.COLUMN_TOOTH_SLOW,
                    DatabaseHelper.COLUMN_TOOTH_FAST,
                    DatabaseHelper.COLUMN_GROOVE_STATOR,
                    DatabaseHelper.COLUMN_GROOVE_ROTOR,
                    DatabaseHelper.COLUMN_CHEEK_COLLECTOR,
                    DatabaseHelper.COLUMN_TOOTH_COUPLING,
                    DatabaseHelper.COLUMN_HEIGH_ROTOR,
                    DatabaseHelper.COLUMN_HEIGH_REDUCER,
                    DatabaseHelper.COLUMN_HEIGH_WW};
            userAdapter = new SimpleCursorAdapter(this, R.layout.item,
                    userCursor, headers, new int[]{R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5
                    , R.id.text6, R.id.text7, R.id.text8, R.id.text9, R.id.text10, R.id.text11, R.id.text12
                    , R.id.text13, R.id.text14, R.id.text15, R.id.text16, R.id.text17, R.id.text18, R.id.text19
            }, 0);

            // если в текстовом поле есть текст, выполняем фильтрацию
            // данная проверка нужна при переходе от одной ориентации экрана к другой
            if(!userFilter.getText().toString().isEmpty())
                userAdapter.getFilter().filter(userFilter.getText().toString());

            // установка слушателя изменения текста
            userFilter.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) { }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                // при изменении текста выполняем фильтрацию
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    userAdapter.getFilter().filter(s.toString());
                }
            });

            // устанавливаем провайдер фильтрации
            userAdapter.setFilterQueryProvider(new FilterQueryProvider() {
                @Override
                public Cursor runQuery(CharSequence constraint) {

                    if (constraint == null || constraint.length() == 0) {

                        return db.rawQuery("select * from " + DatabaseHelper.TABLE, null);
                    }
                    else {

                        return db.rawQuery("select * from " + DatabaseHelper.TABLE + " where "
                                + sort + " like ?", new String[]{"%" + constraint.toString() + "%"});

                    }
                }
            });

            userList.setAdapter(userAdapter);
        }
        catch (SQLException ex){}
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключение и курсор
        db.close();
        userCursor.close();
    }
}
