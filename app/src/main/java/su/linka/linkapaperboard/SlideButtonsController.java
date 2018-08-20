package su.linka.linkapaperboard;

import android.widget.Button;

public class SlideButtonsController {
    static  SlideButtonsController instance;
    private static MainActivity context;
    private final Button leftBtn;
    private final Button rightBtn;

    public static SlideButtonsController getInstance() {
        if(instance==null||MainActivity.context!=context) instance = new SlideButtonsController();
        return instance;
    }

    private  SlideButtonsController(){
        context = MainActivity.context;
        leftBtn = (Button) context.findViewById(R.id.left_btn);
        rightBtn = (Button) context.findViewById(R.id.right_btn);

    }

    public void setTextForLeftBtn(String text){
        leftBtn.setText(text+"\n⬅️");
    }
    public void setTextForRightBtn(String text){
        rightBtn.setText(text+"\n➡️");
    }

}
