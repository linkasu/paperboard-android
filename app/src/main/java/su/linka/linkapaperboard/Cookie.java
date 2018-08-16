package su.linka.linkapaperboard;

import android.content.Context;
import android.content.SharedPreferences;

class Cookie {
    private static final String STORAGENAME = "storage";
    private static Cookie instance;
    private static MainActivity context;
    private final SharedPreferences sharedPref;
    private final SharedPreferences.Editor editor;
    private String gridSizeID = "gridsize";

    public static Cookie getInstance() {
        if (instance==null||context!=MainActivity.context) instance = new Cookie();
        return instance;
    }

    private Cookie(){
        context = MainActivity.context;
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
}
