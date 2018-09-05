package su.linka.linkapaperboard;

import android.widget.TextView;

public class TextViewController {

    private static TextViewController instance;
    private final TextView textView;
    private static MainActivity context;


    public static TextViewController getInstance() {
        if (instance == null || context != MainActivity.context) {
            instance = new TextViewController();
        }
        return instance;
    }

    private TextViewController() {
        context = MainActivity.context;
        textView = context.findViewById(R.id.textView);
    }

    public void write(String text) {

        textView.append(text.equals("_") ? " " : text);
    }

    public void backspace() {
        String text = getText();
        if (text.length() == 0) return;
        textView.setText(text.substring(0, text.length() - 1));
    }

    public void clean() {
        textView.setText("");
    }

    public String getText() {
        return textView.getText().toString();
    }
}
