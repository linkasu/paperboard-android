package su.linka.linkapaperboard;

import android.view.View;
import android.widget.Button;

public class ControlButtonsController {

    public static void init() {
        new ControlButtonsController();
    }

    private ControlButtonsController() {
        MainActivity context = MainActivity.context;
        Button backspace = context.findViewById(R.id.button_backspace);
        Button clean = context.findViewById(R.id.button_clean);
        Button say = context.findViewById(R.id.button_say);

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextViewController.getInstance().backspace();
            }
        });
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextViewController.getInstance().clean();
            }
        });
        say.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TTS.getInstance().speak(TextViewController.getInstance().getText());
            }
        });

    }


}
