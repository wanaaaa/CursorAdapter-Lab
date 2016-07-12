package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wanmac on 7/12/16.
 */
public class AaSQLiteOpenHelper extends SQLiteOpenHelper{
    private static final String TAG = AaSQLiteOpenHelper.class.getCanonicalName();

    private static AaSQLiteOpenHelper instance;

    private static final int DATABASE_VERSION = 8;
    public static final String DATABASE_NAME = "SHOPPING_DB";
    public static final String SHOPPING_LIST_TABLE_NAME = "SHOPPING_LIST";

    public static final String COL_ID = "_id";
    public static final String COL_ITEM_NAME = "ITEM_NAME";
    public static final String COL_ITEM_DESCRIPTION = "DESCRIPTION";

    public static final String[] SHOPPING_COLUMNS = {COL_ID,COL_ITEM_NAME,COL_ITEM_DESCRIPTION};

    private static final String CREATE_SHOPPING_LIST_TABLE =
            "CREATE TABLE " + SHOPPING_LIST_TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY, " +
                    COL_ITEM_NAME + " TEXT, " +
                    COL_ITEM_DESCRIPTION + " TEXT )";

    public static AaSQLiteOpenHelper getInstance(Context context){
        if (instance == null) {
            instance = new AaSQLiteOpenHelper(context);
        }
        return instance;
    }

    private AaSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SHOPPING_LIST_TABLE);
//        this.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SHOPPING_LIST_TABLE_NAME);
        this.onCreate(db);
    }

    public Cursor getShoppingList(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(SHOPPING_LIST_TABLE_NAME, // a. table
                SHOPPING_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        return cursor;
    }
}
