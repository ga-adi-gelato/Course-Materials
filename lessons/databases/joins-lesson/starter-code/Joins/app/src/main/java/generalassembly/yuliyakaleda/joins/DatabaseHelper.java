package generalassembly.yuliyakaleda.joins;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
  public static final String EMPLOYEE_TABLE_NAME = "employee";
  public static final String COL_ID = "_id";
  public static final String COL_NAME = "name";
  public static final String COL_AGE = "age";
  public static final String COL_ADDRESS = "address";
  public static final String COL_SALARY = "salary";

  public static final String DEPARTMENT_TABLE_NAME = "department";
  public static final String COL_DEPARTMENT = "department";
  public static final String COL_EMP_ID = "emp_id";



  private DatabaseHelper(Context context) {
    super(context, "db", null, 1);
  }

  private static DatabaseHelper INSTANCE;

  public static synchronized DatabaseHelper getInstance(Context context) {
    if (INSTANCE == null)
      INSTANCE = new DatabaseHelper(context.getApplicationContext());
    return INSTANCE;
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(SQL_CREATE_ENTRIES_EMPLOYEE);
    db.execSQL(SQL_CREATE_ENTRIES_DEPARTMENT);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL(SQL_DELETE_ENTRIES_EMPLOYEE);
    db.execSQL(SQL_DELETE_ENTRIES_DEPARTMENT);
    onCreate(db);
  }

  private static final String SQL_CREATE_ENTRIES_EMPLOYEE = "CREATE TABLE " +
      EMPLOYEE_TABLE_NAME + " (" +
      COL_ID + " INTEGER PRIMARY KEY," +
      COL_NAME + " TEXT," +
      COL_AGE + " INTEGER," +
      COL_ADDRESS + " TEXT," +
      COL_SALARY + " INTEGER" + ")";

  private static final String SQL_CREATE_ENTRIES_DEPARTMENT = "CREATE TABLE " +
      DEPARTMENT_TABLE_NAME + " (" +
      COL_ID + " INTEGER PRIMARY KEY," +
      COL_DEPARTMENT + " TEXT," +
      COL_EMP_ID + " INTEGER" + "," +
          "FOREIGN KEY("+ COL_EMP_ID +") REFERENCES "+ EMPLOYEE_TABLE_NAME+"("+ COL_ID +") )";

  private static final String SQL_DELETE_ENTRIES_EMPLOYEE = "DROP TABLE IF EXISTS " +
      EMPLOYEE_TABLE_NAME;
  private static final String SQL_DELETE_ENTRIES_DEPARTMENT = "DROP TABLE IF EXISTS " +
      DEPARTMENT_TABLE_NAME;

  public void insertRow(Employee employee) {
    SQLiteDatabase db = getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(COL_NAME, employee.getName());
    values.put(COL_AGE, employee.getAge());
    values.put(COL_ADDRESS, employee.getAddress());
    values.put(COL_SALARY, employee.getSalary());
    db.insertOrThrow(EMPLOYEE_TABLE_NAME, null, values);
  }

  public void insertRowDepartment(Department department) {
    SQLiteDatabase db = getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(COL_DEPARTMENT, department.getDept());
    values.put(COL_EMP_ID, department.getEmpId());
    db.insertOrThrow(DEPARTMENT_TABLE_NAME, null, values);
  }

  public String getNameJoins() {
    //TODO: add the code from the lesson.
  }

  //The method is the solution for the independent part of the lesso
  //public String getFullInformation() {
    //TODO: add the code from the lesson.
  //}
}

