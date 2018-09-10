package com.exam.app2;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PicturePage extends LinearLayout{

    Context mContext;

    TextView title_text;
    Button msgButton;
    ImageView imgView;


    public PicturePage(Context context) {
        super(context);

        init(context);

    }

    public PicturePage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    // 인프레이션 작업
    private void init(final Context context) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.viewpager, this, true);

        title_text = findViewById(R.id.screentTitle);
        imgView = findViewById(R.id.img);
        msgButton = findViewById(R.id.btn01);

        msgButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                // 메세지 보여줌. getTag() 반대 되는 개념. setTag(), 메세지 불러오기
                String showMsg = (String) msgButton.getTag();

                Toast.makeText(context, "메세지 버튼 클릭 : " + showMsg, Toast.LENGTH_LONG).show();

            }
        });

    }

    // 버튼에 메세지 세팅
    public void setMsg(String msg){

        msgButton.setTag(msg);

    }

    // 이미지 세팅
    public void setImage(int imgId){
        imgView.setImageResource(imgId);
    }

    // 타이틀 세팅
    public void setTitle(String titleStr){
        title_text.setTag(titleStr);
    }

    public String getTitle(){
        return title_text.getText().toString();
    }


}
