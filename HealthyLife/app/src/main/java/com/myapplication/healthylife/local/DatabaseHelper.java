package com.myapplication.healthylife.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.myapplication.healthylife.model.Diet;
import com.myapplication.healthylife.model.Dish;
import com.myapplication.healthylife.model.Exercise;
import com.myapplication.healthylife.model.Stat;

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

    public static final String STAT = "STAT";
    public static final String HEIGHT = "HEIGHT";
    public static final String WEIGHT = "WEIGHT";
    public static final String BMI = "BMI";
    public static final String DATE = "DATE";

    public static final String DIET = "DIET";
    public static final String DISH = "DISH";
    public static final String NOTE = "NOTE";
    public static final String INGREDIENTS = "INGREDIENTS";
    public static final String CALORIES ="CALORIES";
    public static final String ISBREAKFAST = "ISBREAKFAST";
    public static final String ISLUNCH = "ISLUNCH";
    public static final String ISDINNER = "ISDINNER";
    public static final String ISASSIGNED = " ISASSIGNED";
    public static final String DAY = "DAY"; // in-week
    public static final String ISCARBALLOWED ="ISCARBALLOWED";
    public static final String ISFATALLOWED = "ISFATALLOWED";
    public static final String ISCARB ="ISCARB";
    public static final String ISFAT = "ISFAT";
    public static final String ISVEGAN = "ISVEGAN";
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

        );

        db.execSQL("CREATE TABLE " + STAT + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HEIGHT + " FLOAT, "
                + WEIGHT + " FLOAT, "
                + BMI + " FLOAT, "
                + DATE + " CHAR(20)) "
        );

        db.execSQL("CREATE TABLE " + DIET + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " VARCHAR(50), "
                + DESCRIPTION + " NVARCHAR(1000), "
                + NOTE + " NVARCHAR(1000), "
                + CALORIES +" INTEGER, "
                + TYPES + " VARCHAR(20), "
                + ISASSIGNED + " INTEGER, "
                + ISCARBALLOWED + " INTEGER ,"
                + ISFATALLOWED + " INTEGER ,"
                + ISVEGAN + " INTEGER, "
                + ISRECOMMENDED + " INTEGER, "
                + IMAGE + " INTEGER) "
        );

        db.execSQL("CREATE TABLE " + DISH + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " VARCHAR(50), "
                + TYPES + " VARCHAR(20), "
                + DESCRIPTION + " NVARCHAR(1000), "
                + TUTORIAL + " NVARCHAR(1000), "
                + NOTE + " NVARCHAR(1000), "
                + INGREDIENTS+ " NVARCHAR(1000), "
                + IMAGE + " INTEGER, "
                + VIDEO + " INTEGER, "
                + ISCARB + " INTEGER, "
                + ISFAT + " INTEGER, "
                + ISVEGAN + " INTEGER, "
                + ISBREAKFAST + " INTEGER, "
                + ISLUNCH + " INTEGER, "
                + ISDINNER + " INTEGER) "
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ EXERCISES);
        db.execSQL("DROP TABLE IF EXISTS "+ STAT);
        db.execSQL("DROP TABLE IF EXISTS "+ DIET);
        db.execSQL("DROP TABLE IF EXISTS "+ DISH);
        onCreate(db);
    }

    public boolean addExercise(Exercise exercise)    {
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

    public ArrayList<Exercise> getExerciseList()    {
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

    public ArrayList<Exercise> getRecommendedExerciseList()    {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Exercise> returnList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + EXERCISES + " LIMIT 5", null);
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

    public boolean deleteAllExercises()  {
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

    public boolean addStat(Stat stat)    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(HEIGHT, stat.getHeight());
        cv.put(WEIGHT, stat.getWeight());
        cv.put(BMI, stat.getBmi());
        cv.put(DATE, stat.getDate());

        long insert = db.insert(STAT, null, cv);
        if (insert != -1)   {
            return true;
        }else {
            return false;
        }
    }

    public ArrayList<Stat> getStatList()    {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Stat> returnList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + STAT, null);
        if (cursor.moveToFirst())   {
            do {
                int id = cursor.getInt(0);
                float height = cursor.getFloat(1);
                float weight = cursor.getFloat(2);
                double bmi = cursor.getDouble(3);
                String date = cursor.getString(4);

                returnList.add(new Stat(id, weight, height, bmi, date));
            }while (cursor.moveToNext());
        }
        return returnList;
    }

    public boolean deleteStat(int id) {
        SQLiteDatabase db = getWritableDatabase();
        int res = db.delete(STAT, ID+" = ?", new String[]{String.valueOf(id)});
        if (res == 1)   {
            return true;
        }else   {
            return false;
        }
    }

    public boolean deleteAllStat()  {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("delete from " + STAT);
            return true;
        }catch (Exception e)    {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addDiet(Diet diet){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        String types = new String();
        boolean start = false;
        for (int i: diet.getTypes())    {
            if (!start) {
                types += String.valueOf(i);
                start = true;
            }else {
                types += "," + String.valueOf(i);
            }
        }
        cv.put(ID,diet.getID());
        cv.put(NAME, diet.getName());
        cv.put(DESCRIPTION, diet.getDescription());
        cv.put(NOTE, diet.getNote());
        cv.put(CALORIES, diet.getCalories());
        cv.put(TYPES, types);
        cv.put(ISASSIGNED, diet.isAssigned());
        cv.put(ISCARBALLOWED, diet.isCarbAllowed());
        cv.put(ISFATALLOWED, diet.isFatAllowed());
        cv.put(ISVEGAN, diet.isVegan());
        cv.put(IMAGE, diet.getImage());

        long insert = db.insert(DIET, null, cv);
        if (insert != -1)   {
            return true;
        }else {
            return false;
        }
    }

    public ArrayList<Diet> getDietList(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Diet> returnList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DIET, null);
        if (cursor.moveToFirst())   {
            do {
                int id = cursor.getInt(0);
                String Name = cursor.getString(1);
                String Description = cursor.getString(2);
                int Calories = cursor.getInt( 3);
                String Note = cursor.getString(4);;
                String temp = cursor.getString(5);
                String[] arr = temp.split(",");
                int[] types = new int[arr.length];
                int count = 0;
                for (String s:arr)  {
                    types[count++] = Integer.parseInt(s);
                }
                boolean isAssigned = cursor.getInt(6) == 0 ? false : true;
                boolean isCarbAllowed =cursor.getInt(7)==0 ? false :true;
                boolean isFatAllowed =cursor.getInt(8)==0 ? false :true;
                boolean isVegan =cursor.getInt(9)==0 ? false :true;
                boolean isRecommended=cursor.getInt(10)==0 ? false:true;
                int image = cursor.getInt(11);
                returnList.add(new Diet(id, Name, Description, Note, Calories, types,
                        isFatAllowed, isCarbAllowed, isVegan, isAssigned,isRecommended, image));
            }while (cursor.moveToNext());
        }
        return returnList;
    }
    public ArrayList<Diet> getRecommendedDietList(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Diet> returnList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DIET + "WHERE ISRECOMMENDED = 1", null);
        if (cursor.moveToFirst())   {
            do {
                int id = cursor.getInt(0);
                String Name = cursor.getString(1);
                String Description = cursor.getString(2);
                int Calories = cursor.getInt( 3);
                String Note = cursor.getString(4);;
                String temp = cursor.getString(5);
                String[] arr = temp.split(",");
                int[] types = new int[arr.length];
                int count = 0;
                for (String s:arr)  {
                    types[count++] = Integer.parseInt(s);
                }
                boolean isAssigned = cursor.getInt(6) == 0 ? false : true;
                boolean isCarbAllowed =cursor.getInt(7)==0 ? false :true;
                boolean isFatAllowed =cursor.getInt(8)==0 ? false :true;
                boolean isVegan =cursor.getInt(9)==0 ? false :true;
                boolean isRecommended=cursor.getInt(10)==0 ? false:true;
                int image = cursor.getInt(11);
                returnList.add(new Diet(id, Name, Description, Note, Calories, types,
                        isFatAllowed, isCarbAllowed, isVegan, isAssigned,isRecommended, image));
            }while (cursor.moveToNext());
        }
        return returnList;
    }

    public boolean deleteDiet(int id) {
        SQLiteDatabase db = getWritableDatabase();
        int res = db.delete(DIET, ID+" = ?", new String[]{String.valueOf(id)});
        if (res == 1)   {
            return true;
        }else   {
            return false;
        }
    }

    public boolean addDish(Dish dish){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        String types = new String();
        boolean start = false;
        cv.put(ID,dish.getID());
        cv.put(NAME, dish.getName());
        cv.put(TYPES, types);
        cv.put(DESCRIPTION, dish.getDescription());
        cv.put(TUTORIAL, dish.getTutorial());
        cv.put(NOTE, dish.getNote());
        cv.put(INGREDIENTS, dish.getIngredients());
        cv.put(IMAGE, dish.getImage());
        cv.put(VIDEO, dish.getVideo());
        cv.put(ISFAT, dish.isFat());
        cv.put(ISCARB, dish.isCarb());
        cv.put(ISVEGAN, dish.isVegan());
        cv.put(ISBREAKFAST, dish.isBreakfast());
        cv.put(ISLUNCH, dish.isLunch());
        cv.put(ISDINNER, dish.isDinner());

        long insert = db.insert(DIET, null, cv);
        if (insert != -1)   {
            return true;
        }else {
            return false;
        }
    }
    public ArrayList<Dish> getDishList(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Dish> returnList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DISH, null);
        if (cursor.moveToFirst())   {
            do {
                int id = cursor.getInt(0);
                String Name = cursor.getString(1);


                String Description = cursor.getString(2);
                String Tutorial = cursor.getString( 3);
                String Note = cursor.getString(4);
                String Ingredients = cursor.getString(5);
                int image = cursor.getInt(6);
                int video = cursor.getInt(7);
                boolean isFat = cursor.getInt(8) == 0 ? false : true;
                boolean isCarb = cursor.getInt(9) == 0 ? false : true;
                boolean isVegan = cursor.getInt(10) == 0 ? false : true;
                boolean isBreakfast = cursor.getInt(11) == 0 ? false : true;
                boolean isLunch = cursor.getInt(12) == 0 ? false : true;
                boolean isDinner = cursor.getInt(13) == 0 ? false : true;

                returnList.add(new Dish(id, Name, Description, Tutorial,
                        Note, Ingredients, image, video,isFat, isCarb, isVegan, isBreakfast, isLunch, isDinner));
            }while (cursor.moveToNext());
        }
        return returnList;
    }
    public boolean deleteDish(int id) {
        SQLiteDatabase db = getWritableDatabase();
        int res = db.delete(DISH, ID+" = ?", new String[]{String.valueOf(id)});
        if (res == 1)   {
            return true;
        }else   {
            return false;
        }
    }
}


