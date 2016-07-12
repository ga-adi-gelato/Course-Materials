package ly.generalassemb.drewmahrt.cursoradapterdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ly.generalassemb.drewmahrt.cursoradapterdemo.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

    }
}
