package generalassembly.yuliyakaleda.interpolatorapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.second_activity);
    overridePendingTransition(R.anim.pull_left, R.anim.push_out_right);
  }
}
