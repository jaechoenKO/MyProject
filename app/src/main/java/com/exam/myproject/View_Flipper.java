package com.exam.myproject;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;


public class View_Flipper extends LinearLayout implements View.OnTouchListener{

    // 3개의 화면, 뷰플리퍼를 구현할 화면의 갯수
    public static int cntIndex = 3;

    // 현재 보이는 화면, 인덱스를 표현하기 위한 레이아웃
    LinearLayout indexLayout;

    // 현재 화면의 인덱스를 나타내는 이미지
    ImageView[] indexImgs;

    // 뷰플리퍼에 사용할 뷰. 액티비티와는 다른 개념.
    View[] views;

    // 뷰플리퍼. 안드로이드에서 기본으로 제공
    ViewFlipper viewFlipper;

    // 처음 터치 시 x좌표 값을 저장하기 위한 변수
    float startX;
    // 터치가 끝났을 때 x좌표 값을 저장하기 위한 변수
    float endX;

    // 처음 화면 위치 값.
    int currentIndex = 0;

    /**
     * 생성자를 만들기 위해서는
     * LinearLayout을 상속 받고 있기 때문에 context 객체가 필요하다.
     **/
    public View_Flipper(Context context) {
        super(context);

        // 초기화를 위해서 init()을 만들어 사용.
        init(context);

    }

    public View_Flipper(Context context, AttributeSet attrs){
        super(context, attrs);

        init(context);

    }

    private void init(Context context) {

        // color 지정
        setBackgroundColor(0xffbbbbff);

        // 레이아웃 인플레이터 과정이 필요하다.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // this : View_Flipper가 root가 된다. true : flipper가 View_Flipper에 해당하는 객체이기 때문에.
        inflater.inflate(R.layout.flipper, this, true);

        indexLayout = findViewById(R.id.screenIdx);
        viewFlipper = findViewById(R.id.flipper);

        // 리스너 등록, 위에 인터페이스로 선언 되었기 때문에 this를 사용한다.
        viewFlipper.setOnTouchListener(this);

        // 레이아웃의 params가 필요하다.
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.leftMargin = 50;

        // 별 이미지, 전체 배열의 갯수
        indexImgs = new ImageView[cntIndex];
        views = new TextView[cntIndex];

        // 각각 객체 정의, 뷰에 인덱스 이미지와 뷰플리퍼 화면을 만드는 과정.
        for(int i = 0; i < cntIndex; i++){
            // 이미지 뷰를 넣어준다.
            indexImgs[i] = new ImageView(context);

            // 현재 화면과 같다면
            if(i == currentIndex){
                // 이미지 세팅
                indexImgs[i].setImageResource(android.R.drawable.btn_star_big_on);
            }else{
                indexImgs[i].setImageResource(android.R.drawable.btn_star_big_off);
            }

//            indexImgs[i].setPadding();

            // params에 세팅을 하여 자동적으로 추가 할 때 leftMargin 값이 들어간다.
            indexLayout.addView(indexImgs[i], params);

            // 현재 화면에 보이는 버튼
            TextView currentView = new TextView(context);

            // 화면 구분을 나타내기 위한 코드
            currentView.setText(i+1+"화면"); // 첫 번째 화면
            currentView.setTextSize(30);
            // 만든 버튼을 뷰에 추가해서 담는다.
            views[i] = currentView;

            // 플리퍼에 추가해 준다.
            viewFlipper.addView(views[i]);

        }

    }

    // 이미지 수정 메소드
    public void modifyIndex(){
        // 인덱스 이미지 수정
        for(int i = 0; i < cntIndex; i++){
            // 현재 인덱스와 같을 때
            if(i == currentIndex){
                indexImgs[i].setImageResource(android.R.drawable.btn_star_big_on);
            }else{
                indexImgs[i].setImageResource(android.R.drawable.btn_star_big_off);
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        // 애니메이션을 이용.
        // 뷰플리퍼가 아닐 시
        if(v != viewFlipper){
            return false;
        }

        // 화면이 눌렸을때
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            startX = event.getX();
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            endX = event.getX();

            // 왼쪽에서 오른쪽으로 터치
            if(startX < endX){
                // 애니메이션 로딩
                Animation leftIn = AnimationUtils.loadAnimation(getContext(), R.anim.left_in);
                // 뷰플리퍼에 애니메이션 세팅
                viewFlipper.setInAnimation(leftIn);
                Animation rightOut = AnimationUtils.loadAnimation(getContext(), R.anim.right_out);
                viewFlipper.setInAnimation(rightOut);

                // 위에 세팅한 애니메이션을 보려면 다음 화면을 보여주어야 한다.
                if(currentIndex > 0){
                    // 이전 화면 보여주기
                    viewFlipper.showPrevious();
                    currentIndex--;

                    modifyIndex();

                }

            }else if(startX > endX){ // 오른쪽에서 왼쪽으로 터치

                Animation rightIn = AnimationUtils.loadAnimation(getContext(), R.anim.right_in);
                viewFlipper.setInAnimation(rightIn);

                Animation leftOut = AnimationUtils.loadAnimation(getContext(), R.anim.left_out);
                viewFlipper.setInAnimation(leftOut);

                // index가 0부터 시작이니 cntIndex는 -1을 해주어야 한다.
                if(currentIndex < cntIndex-1){

                     viewFlipper.showNext();
                     currentIndex++;
                     modifyIndex();

                }

            }

        }

        return true;
    }
}
