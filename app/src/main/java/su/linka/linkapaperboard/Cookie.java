package su.linka.linkapaperboard;

import android.content.Context;
import android.content.SharedPreferences;

class Cookie {
    private static final String STORAGENAME = "storage";
    private static Cookie instance;
    private static Context context;
    private final SharedPreferences sharedPref;
    private final SharedPreferences.Editor editor;
    private String gridSizeID = "gridsize";

    public static Cookie getInstance() {
        return getInstance(MainActivity.context);
    }

    private Cookie(Context cxt){
        context = cxt;
        sharedPref = context.getSharedPreferences(STORAGENAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();

    }

    public int getGridSize() {

        return sharedPref.getInt(gridSizeID, 3);
    }

    public void setGridSize(int gridSize) {
        editor.putInt(gridSizeID, gridSize);
        editor.commit();

    }

    public static Cookie getInstance(Context creationContext) {
        if (instance==null||context!=creationContext) instance = new Cookie(creationContext);
        return instance;

    }
}
