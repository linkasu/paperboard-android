package su.linka.linkapaperboard;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import java.util.Arrays;

public class GridController implements AdapterView.OnItemClickListener {

    private static GridController instance;
    private static View context;
    private final GridView grid;
    private int page = 0;
    private String text;
    private int gridSize;
    private OnKeyListener onKeyListener;
    private SlideButtonsController sbc;


    public static GridController getInstance() {
        if (instance == null || (context != MainActivity.context.getWindow().getDecorView())) {
            instance = new GridController(MainActivity.context.getWindow().getDecorView());
            instance.setOnKeyListener(new OnKeyListener() {
                @Override
                public void press(String k) {
                    TextViewController.getInstance().write(k);
                }
            });
        }
        return instance;
    }

    GridController(View context) {

        GridController.context = context;
        sbc = new SlideButtonsController(context);
        text = "␣" + GridController.context.getResources().getString(R.string.alphabet);
        grid = (context).findViewById(R.id.main_grid);
        grid.setOnItemClickListener(this);

        Button leftButton = context.findViewById(R.id.left_btn);
        Button rightButton = context.findViewById(R.id.right_btn);
        final GridController gc = this;
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gc.previosPage();
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gc.nextPage();
            }
        });


        gridSize = Cookie.getInstance(context.getContext()).getGridSize();

        draw();

    }

    public void draw() {

        int size = gridSize * gridSize;

        grid.setAdapter(new GridItemController(context.getContext(), R.layout.grid_button, getPageArray(page)));
        sbc.setTextForLeftBtn(TextUtils.join(", ", getPageArray(page == 0 ? (text.length() / size) : page - 1)));
        sbc.setTextForRightBtn((TextUtils.join(", ", getPageArray(page == (text.length() / size) ? 0 : page + 1))));
    }

    private String[] getPageArray(int page) {
        int size = gridSize;
        grid.setNumColumns(size);
        size *= size;


        String localtext = text.substring(page * size, Math.min(text.length(), size * page + size));
        return Arrays.copyOfRange(localtext.split(""), 1, localtext.length() + 1);
    }

    public void previosPage() {
        if (page == 0) {
            page = text.length() / (gridSize * gridSize);
        } else {
            page--;
        }
        draw();
    }

    public void nextPage() {
        int size = gridSize * gridSize;
        if (text.length() < size * page + size) {
            page = 0;
        } else {
            page++;
        }
        draw();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        onKeyListener.press((text.charAt(gridSize * gridSize * page + i) + "").replace("␣", " "));
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
        page = 0;
        draw();
    }

    public void setText(String text) {
        this.text = text;
        draw();
    }

    public void setOnKeyListener(OnKeyListener onKeyListener) {
        this.onKeyListener = onKeyListener;
    }

    public static abstract class OnKeyListener {
        public abstract void press(String k);
    }
}
