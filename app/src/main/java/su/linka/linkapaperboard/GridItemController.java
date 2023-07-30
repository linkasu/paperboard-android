package su.linka.linkapaperboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import static su.linka.linkapaperboard.MainActivity.context;

public class GridItemController extends ArrayAdapter<String> {

    private int layoutResourceId;
    private String[] data;

    public GridItemController(@NonNull Context context, int resource, String[] arr) {
        super(context, resource, arr);
        layoutResourceId = resource;
        data = arr;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
        }
        float size = GridController.getInstance().getGridHeight() / (GridController.getInstance().getSize() + 2);
        ((TextView) row).setTextSize(size / context.getResources().getDisplayMetrics().scaledDensity);

        String symbol = data[position];
        if(symbol!=null) {
            if (Cookie.getInstance().getUppercaseChecked()) symbol = symbol.toUpperCase();
            ((TextView) row).setText(symbol);
        }
        return row;
    }
}
