package tz.go.moh.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String DATABASE_NAME = "my_db";

    SQLiteDatabase mDatabase;

    EditText editTextName, editTextSalary;
    Spinner spinnerDept;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        createTable();

        editTextName = (EditText) findViewById(R.id.textName);
        editTextSalary = (EditText) findViewById(R.id.textSalary);

        spinnerDept = (Spinner) findViewById(R.id.spinnerDepartment);

        findViewById(R.id.btnAddEmp).setOnClickListener(this);
        findViewById(R.id.view_employees).setOnClickListener(this);

    }


    private void createTable() {
        String sql = "CREATE TABLE employees (\n" +
                "    id INTEGER NOT NULL CONSTRAINT employees_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    name varchar(200) NOT NULL,\n" +
                "    department varchar(200) NOT NULL,\n" +
                "    joiningdate datetime NOT NULL,\n" +
                "    salary double NOT NULL\n" +
                ");";

        mDatabase.execSQL(sql);
    }

    private void addEmployee() {
        String name = editTextName.getText().toString().trim();
        String salary = editTextSalary.getText().toString().trim();
        String dept = spinnerDept.getSelectedItem().toString();

        if (name.isEmpty()) {
            editTextName.setError("Name can't be empty");
            editTextName.requestFocus();
            return;
        }

        if (salary.isEmpty()) {
            editTextSalary.setError("Salary can't be empty");
            editTextSalary.requestFocus();
            return;
        }

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String joiningDate = simpleDateFormat.format(calendar.getTime());

        String sqlInsert = "INSERT INTO employees (name, department, joiningdate, salary) " +
                "VALUES (?, ?, ?, ?);";
        mDatabase.execSQL(sqlInsert, new String[]{name, dept, joiningDate, salary});

        Toast.makeText(this, "Employee Added Successfully", Toast.LENGTH_SHORT).show();
    }

    private void viewEmployees() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddEmp:
                addEmployee();
                break;
            case R.id.view_employees:
                viewEmployees();
                break;

        }
    }
}
