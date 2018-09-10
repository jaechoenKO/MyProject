package com.exam.app3;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class NotiActivity extends Activity{

    // notification을 사용하기 위한 매니저 선언
    NotificationManager notificationManager;

    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noti_message);

        // notificationmanager 객체화
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // notofication을 확인하면 사라진다. 즉 상태 표시창에서 사라지게 한다.
        notificationManager.cancel(MainActivity.NOTIFICATION_ID);

        textView = findViewById(R.id.tv_01);

        Intent intent = getIntent();

        String str = intent.getStringExtra("msg");
        textView.setText(str);

    }
}
