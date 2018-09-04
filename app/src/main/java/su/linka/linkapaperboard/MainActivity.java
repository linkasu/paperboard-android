package su.linka.linkapaperboard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.SeekBar;

import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

public class MainActivity extends AppCompatActivity {

    public static MainActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        View view = getWindow().getDecorView();
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        // Инициализация AppMetrica SDK
        YandexMetricaConfig.Builder configBuilder = YandexMetricaConfig.newConfigBuilder(getString(R.string.yandex_metric_config_id));
        YandexMetrica.activate(getApplicationContext(), configBuilder.build());
        // Отслеживание активности пользователей
        YandexMetrica.enableActivityAutoTracking(this.getApplication());


        context = this;

        GridController.getInstance();
        ControlButtonsController.init();
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

            input.setProgress(GridController.getInstance().getSize() - 1);
            builder.setView(input);


            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int i) {
                    int size = input.getProgress() + 1;
                    GridController.getInstance().setGridSize(size);

                }
            });
            builder.show();

            return true;
        }
        if (id == R.id.action_install_as_system_keyboard) {
            startActivityForResult(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS), 0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 0) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.showSoftInput(getWindow().getDecorView(), InputMethodManager.SHOW_FORCED);
            imm.showInputMethodPicker();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
