package com.example.stockmarket;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.example.stockmarket.adapter.LeftAdapter;
import com.example.stockmarket.adapter.RightAdapter;
import com.example.stockmarket.entity.Product;
import com.example.stockmarket.view.MyListView;
import com.example.stockmarket.view.MySyncHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity {
    MySyncHorizontalScrollView rightTitleHorscrollView = null, rightContentHorscrollView = null;
    MyListView contentListViewLeft = null, contentListViewRight = null;
    LeftAdapter leftAdapter = null;
    RightAdapter rightAdapter = null;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //initviews
        rightTitleHorscrollView = (MySyncHorizontalScrollView)findViewById(R.id.rightTitleHorscrollView);
        rightContentHorscrollView = (MySyncHorizontalScrollView)findViewById(R.id.rightContentHorscrollView);
        contentListViewLeft = (MyListView)findViewById(R.id.contentListViewLeft);
        contentListViewRight = (MyListView)findViewById(R.id.contentListViewRight);

        //相互引用
        rightTitleHorscrollView.setmSyncView(rightContentHorscrollView);
        rightContentHorscrollView.setmSyncView(rightTitleHorscrollView);

        //setadapter
        leftAdapter = new LeftAdapter(this, 0, new ArrayList<Product>());
        contentListViewLeft.setAdapter(leftAdapter);
        rightAdapter = new RightAdapter(this, 0, new ArrayList<Product>());
        contentListViewRight.setAdapter(rightAdapter);

        //get data
        new Thread(){
            @Override
            public void run() {
                super.run();

                List<Product> productList = DataUtil.getData(MyActivity.this);
                Message message = handler.obtainMessage();
                message.what = 0;
                message.obj = productList;
                message.sendToTarget();
            }
        }.start();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    for (Product product : (ArrayList<Product>)msg.obj) {
                        leftAdapter.add(product);

                        rightAdapter.add(product);
                    }

                    break;
            }

        }
    };
}
