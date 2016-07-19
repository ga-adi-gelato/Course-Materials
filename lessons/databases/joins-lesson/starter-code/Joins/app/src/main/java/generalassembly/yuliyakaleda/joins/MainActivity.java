package generalassembly.yuliyakaleda.joins;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  private TextView text;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    DatabaseHelper helper = DatabaseHelper.getInstance(MainActivity.this);

    Employee employee = new Employee("John", 32, "NY", 30);
    Employee employee1 = new Employee("Harry", 31, "VA", 60);
    Employee employee2 = new Employee("Mike", 30, "NY", 100);
    Employee employee3 = new Employee("Stan", 40, "NY", 70);
    Employee employee4 = new Employee("Sally", 31, "VA", 60);
    Employee employee5 = new Employee("Tony", 25, "NY", 90);
    Employee employee6 = new Employee("Sarah", 45, "NY", 100);

    Department department = new Department("IT Building", 7);
    Department department1 = new Department("Engineering", 2);

    helper.insertRow(employee);
    helper.insertRow(employee1);
    helper.insertRow(employee2);
    helper.insertRow(employee3);
    helper.insertRow(employee4);
    helper.insertRow(employee5);
    helper.insertRow(employee6);

    helper.insertRowDepartment(department);
    helper.insertRowDepartment(department1);

    String name = helper.getNameJoins();

    //TODO: uncomment the line below to test your solution for independent practice, change the
    // value of the return line to "return fullInfo"

    //String fullInfo = helper.getFullInformation();

    text = (TextView) findViewById(R.id.text);
    text.setText("Result: "+name);
  }
}
