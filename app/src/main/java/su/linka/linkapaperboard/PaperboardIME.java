package su.linka.linkapaperboard;

import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.view.inputmethod.InputConnection;

public class PaperboardIME extends InputMethodService {

    @Override
    public View onCreateInputView() {
        View view = getLayoutInflater().inflate(R.layout.keyboard_main, null);
        GridController gc = new GridController(view);
        gc.setOnKeyListener(new GridController.OnKeyListener() {
            @Override
            public void press(String k) {
                InputConnection ic = getCurrentInputConnection();
                ic.commitText(k, 1);
            }
        });
        view.findViewById(R.id.keyboard_backspace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputConnection ic = getCurrentInputConnection();
                ic.deleteSurroundingText(1, 0);
            }
        });
        return view;
    }
}
