package com.myapplication.healthylife.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.myapplication.healthylife.model.Exercise;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String EXERCISES = "EXERCISESS";
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
    public static final String VIDEO = "VIDEO";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String TUTORIAL = "TUTORIAL";
    public static final String EQUIPMENT = "EQUIPMENT";
    public static final String DURATIONSET = "DURATIONSET";
    public static final String BREAKSET = "BREAKSET";
    public static final String NUMSET = "NUMSET";
    public static final String BREAKEX = "BREAKEX";
    public static final String CALOSET = "CALOSET";




//    public static final String EXERCISES = "EXERCISESS";
//    public static final String ISDIETFFIRST="ISDIETFIRST";


    public DatabaseHelper(Context context) {
        super(context, "healthylife.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + EXERCISES + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " VARCHAR(50), "
                + LEVEL + " CHAR(8), "
                + DURATION + " INTEGER, "
                + PROGRESS + " INTEGER, "
                + IMAGE + " INTEGER, "
                + ISFINISHED + " INTEGER, "
                + ISRECOMMENDED + " INTEGER, "
                + ISOTHERS + " INTEGER, "
                + ISFIRST + " INTEGER, "
                + VIDEO + " INTEGER, "
                + DESCRIPTION + " NVARCHAR(1000), "
                + TUTORIAL + " NVARCHAR(1000), "
                + EQUIPMENT + " NVARCHAR(150),"
                + DURATIONSET + " INTEGER, "
                + BREAKSET + " INTEGER, "
                + NUMSET + " INTEGER, "
                + BREAKEX + " INTEGER, "
                + CALOSET +" INTEGER,"
                + TYPES + " VARCHAR(20)) "

                // + TYPES + " VARCHAR(150), "
                // +ISDIETFFIRST +"INTEGER )"
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
        int isOthers = exercise.isOthers()?1:0;
        int isFirst = exercise.isFirst()?1:0;

        String types = new String();
        boolean start = false;
        for (int i: exercise.getTypes())    {
            if (!start) {
                types += String.valueOf(i);
                start = true;
            }else {
                types += "," + String.valueOf(i);
            }
        }

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, exercise.getName());
        cv.put(LEVEL, exercise.getLevel());
        cv.put(DURATION, exercise.getDuration());
        cv.put(PROGRESS, exercise.getProgress());
        cv.put(IMAGE, exercise.getImage());
        cv.put(ISFINISHED, isFinished);
        cv.put(ISRECOMMENDED, isRecommended);
        cv.put(ISOTHERS , isOthers);
        cv.put(ISFIRST, isFirst);
        cv.put(VIDEO, exercise.getVideo());
        cv.put(DESCRIPTION, exercise.getDescription());
        cv.put(TUTORIAL, exercise.getTutorial());
        cv.put(EQUIPMENT, exercise.getEquipment());
        cv.put(DURATIONSET, exercise.getDurationSet());
        cv.put(BREAKSET, exercise.getbreakSet());
        cv.put(NUMSET, exercise.getnumSet());
        cv.put(BREAKEX, exercise.getbreakEx());
        cv.put(CALOSET, exercise.getcaloSet());
        cv.put(TYPES, types);

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
        Cursor cursor = db.rawQuery("SELECT * FROM " + EXERCISES, null);
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
                int video = cursor.getInt(10);
                String description = cursor.getString(11);
                String tutorial = cursor.getString(12);
                String equipment = cursor.getString(13);
                // boolean isDietFirst = cursor.getInt(10) == 0 ? false:true;

                int durationSet = cursor.getInt(14);
                int breakSet = cursor.getInt(15);
                int numSet = cursor.getInt(16);
                int breakEx = cursor.getInt(17);
                int caloSet = cursor.getInt(18);

                String temp = cursor.getString(19);;
                String[] arr = temp.split(",");
                int[] types = new int[arr.length];
                int count = 0;
                for (String s:arr)  {
                    types[count++] = Integer.parseInt(s);
                }


                returnList.add(new Exercise(id, name, level, duration, progress, image, isFinished, isRecommended, isOthers, isFirst, types, video, description, tutorial, equipment, durationSet, breakSet, numSet, breakEx, caloSet));
            }while (cursor.moveToNext());
        }
        return returnList;
    }

    public boolean deleteAll()  {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("delete from " + EXERCISES);
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

    public boolean edit(Exercise exercise) {
        int isFinished = exercise.isFinished()?1:0;
        int isRecommended = exercise.isRecommended()?1:0;
        int isOthers = exercise.isOthers()?1:0;
        int isFirst = exercise.isFirst()?1:0;

        String types = new String();
        boolean start = false;
        for (int i: exercise.getTypes())    {
            if (!start) {
                types += String.valueOf(i);
                start = true;
            }else {
                types += "," + String.valueOf(i);
            }
        }
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, exercise.getName());
        cv.put(LEVEL, exercise.getLevel());
        cv.put(DURATION, exercise.getDuration());
        cv.put(PROGRESS, exercise.getProgress());
        cv.put(IMAGE, exercise.getImage());
        cv.put(ISFINISHED, isFinished);
        cv.put(ISRECOMMENDED, isRecommended);
        cv.put(ISOTHERS , isOthers);
        cv.put(ISFIRST, isFirst);
        cv.put(VIDEO, exercise.getVideo());
        cv.put(DESCRIPTION, exercise.getDescription());
        cv.put(TUTORIAL, exercise.getTutorial());
        cv.put(EQUIPMENT, exercise.getEquipment());
        cv.put(TYPES, types);

        int update = db.update(EXERCISES, cv, ID + " = ?", new String[]{String.valueOf(exercise.getId())});
        if (update == 1)    {
            return true;
        }else   {
            return false;
        }
    }
}


