package com.exam.app2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // xml에 있는 것을 객체화
        ViewPager vPager = findViewById(R.id.viewPager);

        // 어댑터 설정
        PicuterAdapter adapter = new PicuterAdapter(this);

        // 어댑터 세팅
        vPager.setAdapter(adapter);

    }

    // 어댑터 클래스
    public class PicuterAdapter extends PagerAdapter{

        private Context mContext;

        // title Data
        private String[] titles = {"page01", "page02", "page03"};

        // image Data
        private int[] imgId = {R.drawable.img1, R.drawable.img2, R.drawable.img3};

        // message Data
        private String[] msg = {"첫번째 풍경화", "두번째 풍경화", "세번째 풍경화"};

        public PicuterAdapter(Context context){

            mContext = context;

        }

        // 페이지 갯 수 구하기.
        @Override
        public int getCount() {

            return titles.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view.equals(object);
        }

        // 뷰 페이저가 만들어 질 때 호출 되는 메소드
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            // PicturePage를 불러와야 한다. 호출 될 때 레이아웃 객체도 만들어져야 하기 때문에
            PicturePage picturePage = new PicturePage(mContext);
            // Data 세팅

            picturePage.setTitle(titles[position]);
            picturePage.setImage(imgId[position]);
            picturePage.setMsg(msg[position]);

            container.addView(picturePage, position);

            return picturePage;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
        }
    }

}
