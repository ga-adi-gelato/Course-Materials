package generalassembly.yuliyakaleda.contentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
  private static final String TAG = "MainActivity";
  public static final Uri CONTENT_URI = ProductsContract.Products.CONTENT_URI;

  private EditText mInputName;
  private EditText mInputQuantity;
  private TextView mResultTextView;

  private ContentResolver mContentResolver;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button add = (Button) findViewById(R.id.add_button);
    Button find = (Button) findViewById(R.id.find_button);
    Button delete = (Button) findViewById(R.id.delete_button);
    Button update = (Button) findViewById(R.id.update_button);

    add.setOnClickListener(this);
    find.setOnClickListener(this);
    delete.setOnClickListener(this);
    update.setOnClickListener(this);

    mInputName = (EditText) findViewById(R.id.input_name);
    mInputQuantity = (EditText) findViewById(R.id.input_quantity);
    mResultTextView = (TextView) findViewById(R.id.result);

    mContentResolver = getContentResolver();
  }

  public void addProduct () {
    ContentValues values = new ContentValues();
    values.put(ProductsContract.Products.COLUMN_PRODUCTNAME,mInputName.getText().toString());
    values.put(ProductsContract.Products.COLUMN_QUANTITY,mInputQuantity.getText().toString());
    Uri uri = mContentResolver.insert(CONTENT_URI,values);
    mResultTextView.setText(mInputName.getText().toString()+ " has been added!");
  }

  public void lookupProduct () {
    String name = mInputName.getText().toString();
    int quantity = getProductCount(name);
    if(quantity == -1){
      mResultTextView.setText("Product was not found!");
    }else {
      mResultTextView.setText(name + " count: " + quantity);
    }
  }

  public int getProductCount(String name){
    Cursor cursor = mContentResolver.query(CONTENT_URI,
            null,
            ProductsContract.Products.COLUMN_PRODUCTNAME+" = ?",
            new String[]{name},null);
    int quantity = -1;
    if(cursor.moveToFirst()){
      quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
    }
    return quantity;
  }

  public void removeProduct () {
    String name = mInputName.getText().toString();
    int rowsDeleted = mContentResolver.delete(CONTENT_URI,
            ProductsContract.Products.COLUMN_PRODUCTNAME+" = ?",
            new String[]{name});
    if (rowsDeleted > 0) {
      mResultTextView.setText("Product deleted");
    } else {
      mResultTextView.setText("Error deleting. That product cannot be found");
    }
  }

  public void updateProduct(){
    ContentValues values = new ContentValues();
    values.put("quantity",mInputQuantity.getText().toString());
    int rowsUpdated = mContentResolver.update(CONTENT_URI,values,
            ProductsContract.Products.COLUMN_PRODUCTNAME+" = ?",
            new String[]{mInputName.getText().toString()});

    if(rowsUpdated > 0)
      mResultTextView.setText(mInputName.getText().toString()+ " has been updated!");
    else
      mResultTextView.setText("Error updating. That product cannot be found");
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.add_button:
        addProduct();
        break;
      case R.id.find_button:
        lookupProduct();
        break;
      case R.id.delete_button:
        removeProduct();
        break;
      case R.id.update_button:
        updateProduct();
        break;
      default:
        break;
    }
  }
}
