package nisd.uz.dailydiary.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import nisd.uz.dailydiary.Model.Daily;


public class DbHelper extends SQLiteOpenHelper {
    public static String DB_PATH = "/data/data/nisd.uz.dailydiary/databases/";
    public static final String DATABASE_NAME = "database.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "kasallik";

    Context mcontext;
    SQLiteDatabase db;

    public DbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mcontext = context;
//       SQLiteDatabase db =this.getWritableDatabase();

        boolean check = checkdatabase();
        if (check) {
            openDB();
        } else {
            System.out.println("Database doesn't exist");
            createDB();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SANA + " TEXT," + KBAZA_ID + " TEXT," + PHOTO + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
//        onCreate(db);
        if (newVersion < oldVersion) {
            try {
                this.copyDB();
            } catch (IOException x) {
                x.printStackTrace();
            }
        }
    }

    public List<Daily> korinish(int kid){
        SQLiteDatabase db =this.getReadableDatabase();
        List<Daily> dailyList =new ArrayList<>();
        String s="select * from kasallik where kid=" + kid;
        Cursor cursor=db.rawQuery(s,null);
        if (cursor.moveToFirst())
        {
            do {
                Daily daily;
                daily=new Daily();
//                        daily.setKid(cursor.getInt(cursor.getColumnIndex("kid")));
                daily.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                daily.setDescription(cursor.getString(cursor.getColumnIndex("description")));

                dailyList.add(daily);
            }while (cursor.moveToNext());
        }
        db.close();
        return dailyList;
    }
    public List<Daily> barchaishlar(){
                SQLiteDatabase db =this.getReadableDatabase();
                List<Daily> dailyList =new ArrayList<>();
                String s=null;
                s="select * from kasallik";
                Cursor cursor=db.rawQuery(s,null);
                if (cursor.moveToFirst())
                {
                    do {
                        Daily daily;
                        daily=new Daily();
//                        daily.setKid(cursor.getInt(cursor.getColumnIndex("kid")));
                        daily.setSana(cursor.getString(cursor.getColumnIndex("sana")));
                        daily.setTitle(cursor.getString(cursor.getColumnIndex("title")));

                        dailyList.add(daily);
                    }while (cursor.moveToNext());
                }
                db.close();
                return dailyList;
            }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean insertData(String formattedDate, String description,String title) {
//        boolean createSuccessfull =false;
//        Calendar calendar = Calendar.getInstance();
//        currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("sana", String.valueOf(formattedDate));
        cv.put("description",description);
        cv.put("title",title);
     db.insert(TABLE_NAME,null,cv);
       db.close();
        return true;
    }

    public void createDB() {
        boolean dbExist = checkDB();
        this.getReadableDatabase();
        this.close();
        if (!dbExist) {
            try {
                copyDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }}
        private void copyDB () throws IOException {
            InputStream dbInput = mcontext.getAssets().open(DATABASE_NAME);
            String outFile = DB_PATH + DATABASE_NAME;
            OutputStream dbOutput = new FileOutputStream(outFile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = dbInput.read(buffer)) > 0) {
                dbOutput.write(buffer, 0, length);
            }
            dbOutput.flush();
            dbOutput.close();
            dbInput.close();
        }
        private boolean checkdatabase () {

            boolean checkdb = false;
            try {
                String myPath = DB_PATH + DATABASE_NAME;
                File dbfile = new File(myPath);
                checkdb = dbfile.exists();
            } catch (SQLiteException e) {
                System.out.println("Database doesn't exist");
            }
            return checkdb;
        }
        private boolean checkDB () {
            SQLiteDatabase check = null;
            try {
                String dbPath = DB_PATH + DATABASE_NAME;
                check = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (check != null) {
                check.close();
            }
            return check != null ? true : false;
        }

        public void openDB () {
            String dbPath = DB_PATH + DATABASE_NAME;
            db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
        }
        public synchronized void close () {
            if (db != null)
                db.close();
            super.close();
        }
        public Cursor QueryData (String query){
            return db.rawQuery(query, null);
        }


}
