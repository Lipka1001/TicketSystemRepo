package com.kubistalipowska.ticketsystem;

/**
 * http://www.javahelps.com/2015/04/import-and-use-external-database-in.html
 */
import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "baza_nasza_sqlite2.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

}