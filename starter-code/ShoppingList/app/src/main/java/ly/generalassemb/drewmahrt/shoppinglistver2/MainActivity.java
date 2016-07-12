package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import ly.generalassemb.drewmahrt.shoppinglistver2.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.shopping_list_view);

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        Cursor cursor = AaSQLiteOpenHelper.getInstance(this).
                getShoppingList();

        CursorAdapter adapter = new CursorAdapter(this, cursor,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {

            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.shopping_list, parent,false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView shoppingItem = (TextView) view.findViewById(R.id.textview_shopping_list);
                shoppingItem.setText(cursor.getString(cursor.getColumnIndex(AaSQLiteOpenHelper.COL_ITEM_NAME)));
            }
        };

//        ArrayList<String> test = new ArrayList<>();
//        test.add("abc");
//        test.add("aggbc");
//        test.add("afhfbc");
//        test.add("abhfhfc");
//
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this,
//                android.R.layout.simple_list_item_1, test);

        listView.setAdapter(adapter);

        //cursor.close();
    }
}
