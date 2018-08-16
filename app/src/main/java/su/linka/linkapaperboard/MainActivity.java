package su.linka.linkapaperboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;

import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

public class MainActivity extends AppCompatActivity {

    public static MainActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Инициализация AppMetrica SDK
        YandexMetricaConfig.Builder configBuilder = YandexMetricaConfig.newConfigBuilder("ddd48356-81f7-4567-bf41-14a42b1a8de8");
        YandexMetrica.activate(getApplicationContext(), configBuilder.build());
        // Отслеживание активности пользователей
        YandexMetrica.enableActivityAutoTracking(this.getApplication());

        Button leftButton = (Button) findViewById(R.id.left_btn);
        Button rightButton = (Button) findViewById(R.id.right_btn);

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GridController.getInstance().previosPage();
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GridController.getInstance().nextPage();
            }
        });

        context = this;

        GridController.getInstance();
        ControllButtonsController.getInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        context = this;
        GridController.getInstance().draw();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_change_grid_size) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.change_grid_size);
            final SeekBar input = new SeekBar(this);

            input.setMax(3);

            input.setProgress(GridController.getInstance().getSize()-1);
            builder.setView(input);


            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int i ){
                    int size = input.getProgress()+1;
                    GridController.getInstance().setGridSize(size);

                }
            });
            builder.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
