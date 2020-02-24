package tz.go.moh.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


public class EmployeeActivity extends AppCompatActivity {


    SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        mDatabase = openOrCreateDatabase(MainActivity.DATABASE_NAME, MODE_PRIVATE, null);

        loadEmployeesFromDatabase();
    }

    private void loadEmployeesFromDatabase(){
        String sql = "SELECT * FROM employees";

        Cursor cursor = mDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                cursor.getO
            } while (cursor.moveToNext());
        }
    }
}
