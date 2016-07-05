package br.com.frameworksystem.marvelapp.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import br.com.frameworksystem.marvelapp.util.Constante;

/**
 * Created by User on 04/07/2016.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "marvelapp.db";
    private static final int DATABASE_VERSION = 2;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Constante.DDL_CHARACTER);
        sqLiteDatabase.execSQL(Constante.DDL_EVENT);

        Log.i("BD", "CRIADO");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constante.CHARATER_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constante.EVENT_TABLE);

        onCreate(sqLiteDatabase);

    }

    public static SQLiteDatabase getDataBase(Context context){
        SQLiteHelper sqLiteHelper = new SQLiteHelper(context);
        return sqLiteHelper.getWritableDatabase();
    }
}
