package su.linka.linkapaperboard;

import android.content.Context;
import android.view.View;
import android.widget.Button;

public class SlideButtonsController {
    static  SlideButtonsController instance;
    private static View context;
    private final Button leftBtn;
    private final Button rightBtn;


    public SlideButtonsController(View cxt){
        context = cxt;
        leftBtn = (Button) context.findViewById(R.id.left_btn);
        rightBtn = (Button) context.findViewById(R.id.right_btn);

    }

    public void setTextForLeftBtn(String text){
        leftBtn.setText(text.substring(0, 3)+"..."+text.substring(text.length()-3)+"\n⬅️");
    }
    public void setTextForRightBtn(String text){
        rightBtn.setText(text.substring(0, 3)+"..."+text.substring(text.length()-3)+"\n➡️");
    }

}
