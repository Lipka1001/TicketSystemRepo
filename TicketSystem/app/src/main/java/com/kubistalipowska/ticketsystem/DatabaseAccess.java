package com.kubistalipowska.ticketsystem;
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import com.kubistalipowska.ticketsystem.entities.AlbumEntity;
        import com.kubistalipowska.ticketsystem.entities.ItemEntity;
        import com.kubistalipowska.ticketsystem.entities.SongEntity;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

public class DatabaseAccess {
    /*
     *  ALL FIELDS IN DATABASE
     */
    public static final String TABLE_ACCOUNTS = "UZYTKOWNICY";
    public static final String FIELD_LOGIN = "login";
    public static final String FIELD_PASSWORD = "haslo";
    public static final String TABLE_SONGS = "UTWOR";
    public static final String FIELD_SONG_NAME = "NAZWA";
    public static final String FIELD_SONG_LENGTH = "DLUGOSC";
    public static final String FIELD_GENRE = "GATUNEK";
    public static final String TABLE_CREW = "MUZYK";
    public static final String FIELD_CREW_NAME = "IMIE";
    public static final String FIELD_CREW_SURNAME = "NAWISKO";
    public static final String TABLE_CONCERTS = "KONCERT";
    public static final String FIELD_CONCERTS_DATE = "DATA";
    public static final String FIELD_CONCERTS_PLACE_ADDRESS = "MIEJSCE_ADRES";
    public static final String TABLE_BAND = "ZESPOL";
    public static final String FIELD_BAND_NAME = "NAZWA";
    public static final String FIELD_INSTRUMENT = "INSTRUMENT";
    public static final String TABLE_ALBMUS =  "PLYTA";
    private static final String FIELD_ALBUM_NAME ="NAZWA_PLYTY" ;
    private static final String FIELD_ARTIST_NAME="ZESPOL_NAZWA" ;
    private static final String FIELD_RELEASE_DATE ="DATA_WYDANIA" ;
    public static final String FIELD_ALBUM = "PLYTA";

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;

    private static DatabaseAccess instance;

    /*
    ALL TEMPORARY DATA
     */
    private String user = "";

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public int insert(String table, ContentValues values) {
        open();
        database.insert(table, null, values);
        close();
        return 0;
    }

    public int update(String table, ContentValues values,String id_field,String old_value) {
        open();
        database.update(table, values,id_field +  "='" + old_value + "'",null);
        close();
        return 0;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    /**
     *  HERE ADD DAOS LIKE IN EXAMPLE
     */

     /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public List<String> getTest() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM test", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(cursor.getColumnIndex(FIELD_LOGIN)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

                /**
                 *   ACCOUNTS
                 *
                 *
                 *
                 */

    /**
     * 
     * @param id
     */
    public void deleteAccount(int id) {
        open();
        //  database.execSQL("delete from " + TABLE_ACCOUNTS + " where " + id + " = '" + id + "' ;");
        database.delete(TABLE_ACCOUNTS, "_id" + "=" + id, null);
        close();
    }

    /**
     *
     * @param login
     * @return
     */
    public boolean checkLogin(String login){
        boolean result = true;
        open();

        try {
            Cursor res = database.rawQuery("select * from " + TABLE_ACCOUNTS +
                    " WHERE " + FIELD_LOGIN + " = " + "'" + login + "'", null);
            if(res != null && res.getCount() > 0 )
                result = false;

        }catch(Exception e){
            Log.e("login", "user doesnet exists");
        }
        close();
        return result;
    }

    /**
     *
     * @param login
     * @param password
     * @return
     */
    public boolean checkPassword(String login,String password){
        open();

        boolean result = false;
        Cursor res =  database.rawQuery("select * from " + TABLE_ACCOUNTS +
                " WHERE " + FIELD_LOGIN + " = " + "'" +login + "'", null);

        if(res.getCount() > 0) {
            res.moveToFirst();
            if(password.equals(res.getString(res.getColumnIndex(FIELD_PASSWORD))))
                result = true;

            res.close();
        }


        res.close();

        close();
        return result;
    }


    public ArrayList<ItemEntity[]> getItems(String table) {
        ArrayList<ItemEntity[]> result = new ArrayList<>();
        ItemEntity[] items;
        open();

        Cursor cursor =  database.rawQuery("select * from " + table, null);

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                items = new ItemEntity[cursor.getColumnCount()];
                for(int i = 0; i < items.length; i++){
                    items[i] = new ItemEntity(cursor.getColumnName(i),cursor.getString(i));
                }
                result.add(items);
                cursor.moveToNext();
            }
        }


        cursor.close();

        close();
        return result;
    }

    public ArrayList<AlbumEntity> getAlbums() {
        ArrayList<AlbumEntity> result = new ArrayList<>();
        AlbumEntity album;
         open();
        Cursor cursor =  database.rawQuery("select * from " + TABLE_ALBMUS, null);

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                album = new AlbumEntity(
                        cursor.getString(cursor.getColumnIndex(FIELD_ALBUM_NAME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_ARTIST_NAME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RELEASE_DATE)
                        ));
                result.add(album);
                cursor.moveToNext();
            }
        }


        cursor.close();

        close();
        return result;
    }

    public ArrayList<SongEntity> getSongsForAlbum(String album_name) {
        ArrayList<SongEntity> result = new ArrayList<>();
        SongEntity song;
        open();
        Cursor cursor =  database.rawQuery("select * from " + TABLE_SONGS + " where " + TABLE_ALBMUS + " = '" + album_name + "'", null);

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                song = new SongEntity(
                        album_name,
                        cursor.getInt(cursor.getColumnIndex(FIELD_SONG_LENGTH)),
                        cursor.getString(cursor.getColumnIndex(FIELD_GENRE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_SONG_NAME))

                        );
                result.add(song);
                cursor.moveToNext();
            }
        }


        cursor.close();

        close();
        return result;
    }


    public HashMap<String, ArrayList<SongEntity>> getSongs() {
        HashMap<String, ArrayList<SongEntity>> result = new HashMap<> ();
        List<SongEntity> songs;
        open();
        Cursor cursor =  database.rawQuery("select "   + FIELD_ALBUM + " from " + TABLE_SONGS, null);

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                result.put(cursor.getString(0), getSongsForAlbum(cursor.getString(0)));
                cursor.moveToNext();
            }
        }


        cursor.close();

        close();

        return result;
    }

    public ArrayList<String> getSongsForAlbumTo(String album_name) {
        ArrayList<String> result = new ArrayList<>();
        open();
        Cursor cursor =  database.rawQuery("select " + DatabaseAccess.FIELD_SONG_NAME +" from " + TABLE_SONGS + " where " + TABLE_ALBMUS + " != '" + album_name + "'", null);

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                result.add( cursor.getString(cursor.getColumnIndex(FIELD_SONG_NAME)));
                cursor.moveToNext();
            }
        }


        cursor.close();

        close();
        return result;
    }
}