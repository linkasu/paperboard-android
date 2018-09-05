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
        setTextForButton(leftBtn, text, "⬅️");
    }

    public void setTextForRightBtn(String text) {
        setTextForButton(rightBtn, text, "➡️");
    }

    private void setTextForButton(AppCompatButton button, String text, String arrowSymbol) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        int length = text.length();
        StringBuilder stringBuilder = new StringBuilder();
        if (length == 1) {
            stringBuilder.append(text);
        } else {
            stringBuilder.append(text.substring(0, 3))
                    .append("...")
                    .append(text.substring(length - 3));
        }
        stringBuilder.append("\n")
                .append(arrowSymbol);

        button.setText(stringBuilder.toString());
    }

}
