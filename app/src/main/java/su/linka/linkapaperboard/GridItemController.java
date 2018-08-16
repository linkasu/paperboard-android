package su.linka.linkapaperboard;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static su.linka.linkapaperboard.MainActivity.context;

public class GridItemController extends ArrayAdapter {

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
        RecyclerView.ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
        }
        float size = GridController.getInstance().getGridHeight() /(GridController.getInstance().getSize()+2);
        ((TextView) row).setTextSize( size / context.getResources().getDisplayMetrics().scaledDensity);

        ((TextView) row).setText(data[position]);
        return row;
    }
}
