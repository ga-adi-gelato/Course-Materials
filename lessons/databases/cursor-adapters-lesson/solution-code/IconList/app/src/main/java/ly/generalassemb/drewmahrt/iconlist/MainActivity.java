package ly.generalassemb.drewmahrt.iconlist;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import ly.generalassemb.drewmahrt.iconlist.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview_icons);

        //Ignore the two lines below
        DBAssetHelper dbAssetHelper = new DBAssetHelper(MainActivity.this);
        dbAssetHelper.getReadableDatabase();

        Cursor cursor = IconSQLiteOpenHelper
                .getInstance(MainActivity.this).getIconList();

        CursorAdapter adapter = new CursorAdapter(this, cursor,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context)
                        .inflate(R.layout.icon_list_item, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                ImageView icon = (ImageView) view.findViewById(R.id.imageview_icon);
                TextView iconName = (TextView) view.findViewById(R.id.textview_icon_name);

                int colIndex = cursor.getColumnIndex(IconSQLiteOpenHelper.COL_ICON_NAME);
                String name = cursor.getString(colIndex);
                
                iconName.setText(name);

                int drawableValue = getDrawableValue(name);
                icon.setImageResource(drawableValue);
            }
        };

        listView.setAdapter(adapter);

        cursor.close();

    }

    private int getDrawableValue(String icon){
        switch(icon){
            case "search":
                return android.R.drawable.ic_menu_search;
            case "add":
                return android.R.drawable.ic_menu_add;
            case "upload":
                return android.R.drawable.ic_menu_upload;
            case "play":
                return android.R.drawable.ic_media_play;
            default:
                return 0;
        }
    }
}
