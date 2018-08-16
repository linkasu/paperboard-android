package su.linka.linkapaperboard;

import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeoutException;

public class TextViewController {

    private static TextViewController instance;
    private final TextView textView;
    private static MainActivity context;


    public static TextViewController getInstance() {
        if(instance==null||context!=MainActivity.context){
            instance = new TextViewController();
        }
        return instance;
    }

    private TextViewController(){
        context = MainActivity.context;
        textView = (TextView) context.findViewById(R.id.textView);
    }

    public void write(String text) {
        textView.append(text);
    }

    public void backspace() {
        String text = getText();
        textView.setText( text.substring(0, text.length()-1));
    }
    public void clean(){
        textView.setText("");
    }
    public String getText(){
        return textView.getText().toString();
    }
}
