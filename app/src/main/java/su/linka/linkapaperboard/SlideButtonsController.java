package su.linka.linkapaperboard;

import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;

public class SlideButtonsController {

    private final AppCompatButton leftBtn;
    private final AppCompatButton rightBtn;


    public SlideButtonsController(View view) {
        leftBtn = view.findViewById(R.id.left_btn);
        rightBtn = view.findViewById(R.id.right_btn);
    }

    public void setTextForLeftBtn(String text) {
        setTextForButton(leftBtn, text);
    }

    public void setTextForRightBtn(String text) {
        setTextForButton(rightBtn, text);
    }

    private void setTextForButton(AppCompatButton button, String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        int length = text.length();
        String rightText;
        if (length == 1) {
            rightText = text + "\n➡️";
        } else {
            rightText = text.substring(0, 3) + "..." + text.substring(length - 3) + "\n➡️";
        }
        button.setText(rightText);
    }

}
