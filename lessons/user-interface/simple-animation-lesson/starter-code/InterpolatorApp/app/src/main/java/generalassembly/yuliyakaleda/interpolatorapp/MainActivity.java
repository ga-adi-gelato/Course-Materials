package generalassembly.yuliyakaleda.interpolatorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
  private TextView text;
  private Button animate;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    text = (TextView) findViewById(R.id.animated_view);
    animate = (Button) findViewById(R.id.animate_button);
    animate.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
//    Intent intent = new Intent(this, SecondActivity.class);
//    startActivity(intent);
//    overridePendingTransition(R.anim.pull_left, R.anim.push_out_right);
    Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
    text.startAnimation(hyperspaceJumpAnimation);
  }
}
