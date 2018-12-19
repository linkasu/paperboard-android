package su.linka.linkapaperboard;

import android.content.Context;
import android.content.SharedPreferences;

class Cookie {
    private static final String STORAGE_NAME = "storage";
    private static final int DEF_VALUE = 3;
    private static final String GRID_SIZE_ID = "gridsize";
    private static final String GRID_UPPERCASE_ID = "griduppercase";

    private static Cookie instance;
    private static Context context;

    private final SharedPreferences mSharedPref;

    public static Cookie getInstance() {
        return getInstance(MainActivity.context);
    }

    private Cookie(Context cxt) {
        context = cxt;
        mSharedPref = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);

    }

    public int getGridSize() {
        return mSharedPref.getInt(GRID_SIZE_ID, DEF_VALUE);
    }

    public void setGridSize(int gridSize) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putInt(GRID_SIZE_ID, gridSize);
        editor.apply();

    }

    public static Cookie getInstance(Context creationContext) {
        if (instance == null || context != creationContext) {
            instance = new Cookie(creationContext);
        }
        return instance;
    }

    public void setUppercaseChecked(boolean checked) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(GRID_UPPERCASE_ID, checked);
        editor.apply();

    }

    public boolean getUppercaseChecked() {
        return mSharedPref.getBoolean(GRID_UPPERCASE_ID, true);
    }
}
