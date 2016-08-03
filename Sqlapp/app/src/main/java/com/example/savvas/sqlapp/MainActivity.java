package com.example.savvas.sqlapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




public class MainActivity extends Activity {

    //----------------Ορισμός και αρχικοποίηση μεταβλητών----------------//
    OnClickListener firstlistener = null;
    OnClickListener secondlistener = null;
    OnClickListener thirdlistener = null;
    OnClickListener fourthlistener = null;
    OnClickListener fifthlistener = null;
    OnClickListener sixthlistener = null;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    TextView txtView1;


    DatabaseHelper dbHelper;

    private static final String dbName = "MyElearningDataBase.db";
    private static final int dbVersion = 1;
    private static final String tableName = "Teachers";
    private static final String fisrtname = "fisrtname";
    private static final String lastname = "lastname";
    private static final String university = "university";
    private static final String course = "course";

    private static final String errorMsg1 = "Σφάλμα: Ο πίνακας υπάρχει ή κάτι άλλο πήγε στραβά";
    private static final String errorMsg2 = "Σφάλμα: Ο πίνακας δεν υπάρχει ή κάτι άλλο πήγε στραβά";

    //----------------Προετοιμασία δημιουργίας βάσης δεδομένων SQLite----------------//

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, dbName, null, dbVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String sqlStatement = "CREATE TABLE "  + tableName +
                    " (" +
                    fisrtname + " text not null, " +
                    lastname + " text not null, " +
                    university + " text not null, " +
                    course + " text not null"+
                    ");";
            db.execSQL(sqlStatement); //Δημιουργία του πίνακα

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//σε περίπτωση update της βάσης μας εισάγουμε σχετικό κώδικα
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareListener();
        initLayout();
        dbHelper = new DatabaseHelper(this);

    }
    //---------------Προετοιμασία των listeners----------------//
    private void initLayout() {
        setTitle("SQLite Manager App");
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(firstlistener);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(secondlistener);

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(thirdlistener);
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(fourthlistener);

        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(fifthlistener);

        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(sixthlistener);

        txtView1 = (TextView)findViewById(R.id.textView1);

    }

    private void prepareListener() {
        firstlistener = new OnClickListener() {
            public void onClick(View view) {
                createTable();
            }
        };
        secondlistener = new OnClickListener() {
            public void onClick(View view) {
                dropTable();
            }
        };
        thirdlistener = new OnClickListener() {
            public void onClick(View view) {
                insertData();
            }
        };
        fourthlistener = new OnClickListener() {
            public void onClick(View view) {
                deleteData();
            }
        };
        fifthlistener = new OnClickListener() {
            public void onClick(View view) {
                countData();
            }
        };
        sixthlistener = new OnClickListener() {
            public void onClick(View view) {
                updateData();
            }
        };

    }
    //---------------Συνάρτηση δημιουργίας Πίνακα------------//
    private void createTable() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sqlStatement = "CREATE TABLE "  + tableName +
                " (" +
                fisrtname + " text not null, " +
                lastname + " text not null, " +
                university + " text not null, " +
                course + " text not null"+
                ");";
        try {
            db.execSQL(sqlStatement);
            txtView1.setText("Ο πίνακας Teachers δημιουργήθηκε");
        }
        catch (SQLException ex) {
            txtView1.setText(errorMsg1);
        }
    }


    //---------------Συνάρτηση διαγραφής Πίνακα------------//
    private void dropTable() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sqlStatement = "drop table " + tableName;
        try {
            db.execSQL(sqlStatement);
            txtView1.setText("Ο πίνακας Teachers διαγραφηκε");
        }
        catch (SQLException ex) {
            txtView1.setText(errorMsg2);
        }
    }


    //---------------Συνάρτηση εισαγωγής νέας εγγραφής------------//
    private void insertData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sqlStatement1 = "insert into " + tableName + " (" + fisrtname + ", " + lastname +","+university+","+ course + ") " +
                "values('Savvas', 'Drakoulis','Unipi','Android Development lvl1');";
        String sqlStatement2 = "insert into " + tableName + " (" + fisrtname + ", " + lastname +","+university+","+ course + ") " +
                "values('Efthimios', 'Alepis','Unipi','Android Development lvl1');";
        try {
            db.execSQL(sqlStatement1);
            db.execSQL(sqlStatement2);
            txtView1.setText("Η εισαγωγή δεδομένων ολοκληρώθηκε");
        }
        catch (SQLException ex) {
            txtView1.setText("Σφάλμα: Δεν βρέθηκαν εγγραφές ή κάτι άλλο πήγε στραβά");
        }
    }
    //---------------Συνάρτηση διαγραφής εγγραφής χωρίς τη χρήση της execSQL------------
    private void deleteData() {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.delete(tableName, " fisrtname = 'Savvas'", null);
            txtView1.setText("Όλες οι εγγραφές με fisrtname = 'Savvas' έχουν διαγραφεί");
        }
        catch (SQLException ex) {
            txtView1.setText(errorMsg2);
        }

    }


    //---------------Συνάρτηση εύρεσης πλήθους εγγραφών------------//
    private void countData() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String fields[] = { fisrtname, lastname, university, course };
        try {
            Cursor cursor = db.query(tableName, fields, null, null, null, null, null);
            Integer num = cursor.getCount();
            txtView1.setText("Το σύνολο των εγγραφών είναι: "+num);
        }
        catch (SQLException e) {
            txtView1.setText(errorMsg2);
        }
    }

    //---------------Συνάρτηση ενημέρωσης εγγραφών execSQL------------//
    private void updateData() {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentvalueHolder = new ContentValues();
        contentvalueHolder.put("fisrtname","Mr");
        contentvalueHolder.put("lastname","Android");
        contentvalueHolder.put("university","is");
        contentvalueHolder.put("course","Cool");
        try {
            db.update(tableName, contentvalueHolder , "fisrtname = 'Savvas'", null);
            txtView1.setText("Όλες οι εγγραφές με fisrtname = 'Savvas' αντικαταστάθηκαν");
        }
        catch (SQLException e) {
            txtView1.setText(errorMsg2);
        }
    }

}

