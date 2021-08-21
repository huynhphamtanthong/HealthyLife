package com.myapplication.healthylife.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.myapplication.healthylife.model.Exercise;
import com.myapplication.healthylife.model.User;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String LEVEL = "LEVEL";
    public static final String DURATION = "DURATION";
    public static final String PROGRESS = "PROGRESS";
    public static final String IMAGE = "IMAGE";
    public static final String ISFINISHED = "ISFINISHED";
    public static final String ISRECOMMENDED = " ISRECOMMENDED";
    public static final String ISOTHERS = "ISOTHERS";
    public static final String ISFIRST = "ISFIRST";
    public static final String TYPES = "TYPES";
    public static final String EXERCISES = "EXERCISESS";


    public DatabaseHelper(Context context) {
        super(context, "healthylife.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + EXERCISES + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " VARCHAR(150), "
                + LEVEL+ " VARCHAR(150), "
                + DURATION + " INTEGER, "
                + PROGRESS + " INTEGER, "
                + IMAGE + " INTEGER, "
                + ISFINISHED + " INTEGER, "
                + ISRECOMMENDED + " INTEGER, "
                + ISOTHERS + " INTEGER, "
                + ISFIRST + " INTEGER, "
                + TYPES + " VARCHAR(150) )"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ EXERCISES);
        onCreate(db);
    }

    public boolean add(Exercise exercise)    {
        int isFinished = exercise.isFinished()?1:0;
        int isRecommended = exercise.isRecommended()?1:0;
        int isOthers = exercise.isFinished()?1:0;
        int isFirst = exercise.isFinished()?1:0;

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, exercise.getName());
        cv.put(LEVEL, exercise.getLevel());
        cv.put(DURATION, exercise.getDuration());
        cv.put(PROGRESS, exercise.getProgress());
        cv.put(IMAGE, exercise.getDuration());
        cv.put(ISFINISHED, isFinished);
        cv.put(ISRECOMMENDED, isRecommended);
        cv.put(ISOTHERS , isOthers);
        cv.put(ISFIRST, isFirst);
        cv.put(TYPES, exercise.getDuration());

        long insert = db.insert(EXERCISES, null, cv);
        if (insert != -1)   {
            return true;
        }else {
            return false;
        }
    }

    public ArrayList<Exercise> getList()    {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Exercise> returnList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+EXERCISES, null);
        if (cursor.moveToFirst())   {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String level = cursor.getString(2);
                int duration = cursor.getInt(3);
                int progress = cursor.getInt(4);
                int image = cursor.getInt(5);
                boolean isFinished = cursor.getInt(6) == 0 ? false:true;
                boolean isRecommended = cursor.getInt(7) == 0 ? false:true;
                boolean isOthers = cursor.getInt(8) == 0 ? false:true;
                boolean isFirst = cursor.getInt(9) == 0 ? false:true;

                String temp = cursor.getString(10);;
                String[] arr = temp.split(",");
                int[] types = new int[arr.length];
                int count = 0;
                for (String s:arr)  {
                    types[count++] = Integer.parseInt(s);
                }

                returnList.add(new Exercise(id, name, level, duration, progress, image, isFinished, isRecommended, isOthers, isFirst, types));
            }while (cursor.moveToNext());
        }
        return returnList;
    }

    public boolean deleteAll()  {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("delete from "+ EXERCISES);
            return true;
        }catch (Exception e)    {
            e.printStackTrace();
            return false;
        }
    }

//    public boolean delete(int id) {
//        SQLiteDatabase db = getWritableDatabase();
//        int res = db.delete(DOVAT, ID+" = ?", new String[]{String.valueOf(id)});
//        if (res == 1)   {
//            return true;
//        }else   {
//            return false;
//        }
//    }

//    public Item getItemById(int id) {
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + DOVAT + " WHERE ID = " + id, null);
//        if (cursor.moveToFirst())   {
//            return new Item(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getBlob(3));
//        }
//        return null;
//    }

//    public boolean edit(Item edited) {
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(ID, edited.getId());
//        cv.put(TEN, edited.getName());
//        cv.put(MOTA, edited.getDescription());
//        cv.put(HINHANH, edited.getImg());
//        int update = db.update(DOVAT, cv, ID + " = ?", new String[]{String.valueOf(edited.getId())});
//        if (update == 1)    {
//            return true;
//        }else   {
//            return false;
//        }
//    }
}


