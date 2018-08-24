package su.linka.linkapaperboard;

import android.app.Service;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;

public class PaperboardIME extends InputMethodService {

    private GridController gc;

    @Override
    public View onCreateInputView() {
        View view = getLayoutInflater().inflate(R.layout.keyboard_main,null);
        gc = new GridController(view);
        gc.setOnKeyListener(new GridController.OnKeyListener(){
            @Override
            public void press(String k){
                InputConnection ic = getCurrentInputConnection();
                ic.commitText(k, 1);
            }
        });
        ((Button) view.findViewById(R.id.keyboard_backspace)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputConnection ic = getCurrentInputConnection();
                ic.deleteSurroundingText(1, 0);
            }
        });
        return view;
    }
}
