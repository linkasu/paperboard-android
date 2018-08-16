package su.linka.linkapaperboard;

import android.view.View;
import android.widget.Button;

public class ControllButtonsController {
    private static ControllButtonsController instance;
    private static MainActivity context;

    public static ControllButtonsController getInstance() {
        if(instance==null||context!=MainActivity.context){
            instance = new ControllButtonsController();
        }
        return instance;
    }

    private ControllButtonsController(){
        context = MainActivity.context;
        Button backspace = (Button) context.findViewById(R.id.button_backspace);
        Button clean = (Button) context.findViewById(R.id.button_clean);
        Button say = (Button) context.findViewById(R.id.button_say);

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
