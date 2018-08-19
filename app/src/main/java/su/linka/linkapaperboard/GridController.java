package su.linka.linkapaperboard;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.Arrays;

public class
GridController implements AdapterView.OnItemClickListener {

     static GridController instance ;
    private static MainActivity context;
    private final GridView grid;
    private int page = 0;
    private String text = " абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private int gridSize = 3;


    public static GridController getInstance() {
        if(instance==null||(context!=MainActivity.context)){
            instance = new GridController();
        }
        return instance;
    }

    GridController (){

        context = MainActivity.context;
        grid = (GridView) context.findViewById(R.id.main_grid);
        grid.setOnItemClickListener(this);


        gridSize = Cookie.getInstance().getGridSize();

        draw();

    }

    public void draw() {

        int size = gridSize;
        grid.setNumColumns(size);
        size*=size;


        String localtext = text.substring(page * size, Math.min(text.length(), size*page+size));
        String[] arr = Arrays.copyOfRange(localtext.split(""), 1, localtext.length()+1);


        grid.setAdapter(new GridItemController(context, R.layout.grid_button, arr));
    }

    public void  previosPage(){
        if(page==0) {
            page = text.length()/(gridSize*gridSize);
        } else {
            page--;
        }
        draw();
    }
    public void nextPage(){
        int size = gridSize*gridSize;
        if(text.length()<size*page+size) {
            page=0;
        }
        else {
        page++; }
        draw();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextViewController.getInstance().write(text.charAt(gridSize*gridSize*page+i)+"");
    }

    public int getSize() {
        return gridSize;
    }

    public float getGridHeight() {
        return grid.getMeasuredHeight();
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
        Cookie.getInstance().setGridSize(gridSize);
        draw();
    }

    public void setText(String text) {
        this.text = text;
        draw();
    }
}
