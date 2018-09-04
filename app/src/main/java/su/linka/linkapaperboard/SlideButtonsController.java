package su.linka.linkapaperboard;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

public class SlideButtonsController {
    private final Button leftBtn;
    private final Button rightBtn;


    public SlideButtonsController(View view) {
        leftBtn = view.findViewById(R.id.left_btn);
        rightBtn = view.findViewById(R.id.right_btn);
    }

    public void setTextForLeftBtn(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        String leftText = text.substring(0, 3) + "..." + text.substring(text.length() - 3) + "\n⬅️";
        leftBtn.setText(leftText);
    }

    public void setTextForRightBtn(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        String rightText = text.substring(0, 3) + "..." + text.substring(text.length() - 3) + "\n➡️";
        rightBtn.setText(rightText);
    }

}
