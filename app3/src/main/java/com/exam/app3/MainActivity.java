package com.exam.app3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button notiBtn;
    NotificationManager notificationManager;

    // notification을 알 수 있는 id 변수
    final static int NOTIFICATION_ID = 11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notiBtn = findViewById(R.id.btn_noti);

        notiBtn.setOnClickListener(listener);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    // 메세지를 알려주는 메소드
    private void notiMsg(){

        // notification bar
        Intent intent = new Intent(this, NotiActivity.class);
        intent.putExtra("msg", "메세지");
        // 나중에 해야할 일은 PendingIntent에서 한다.
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // notivication 객체화, 11버전 이하는 NotificationCompat.Builder를 이용하는 것이 좋다.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setSmallIcon(android.R.drawable.ic_dialog_email); // 상태바에서 나타내는 icon
        builder.setTicker("noti message"); // 알림 발생시 잠깐 보이는 메세지
        builder.setContentTitle("알림 제목"); // 알림창의 제목
        builder.setContentText("알림 내용"); // 알림창의 내용
        builder.setWhen(System.currentTimeMillis()); // 언제 알림을 하는지 정하는 메소드, currentTimeMillis는 시스템의 시간
        builder.setContentIntent(pendingIntent);


        // Notification.Builder를 사용했을 경우
        Notification notification = builder.build();

        // notificationManager에 등록
        notificationManager.notify(NOTIFICATION_ID, notification);

    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(v.getId() == R.id.btn_noti ){

                notiMsg();

            }

        }
    };

}
