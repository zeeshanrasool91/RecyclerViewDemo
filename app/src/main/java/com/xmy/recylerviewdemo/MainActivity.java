package com.xmy.recylerviewdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xmy.recylerviewdemo.adapter.MyAdapter;
import com.xmy.recylerviewdemo.bean.MyItemBean;
import com.xmy.recylerviewdemo.decoration.MyDecoration;
import com.xmy.recylerviewdemo.layoutmanager.MyLayoutManager;
import com.xmy.recylerviewdemo.listener.MyItemClickListener;
import com.xmy.recylerviewdemo.listener.MyItemLongClickListener;

public class MainActivity extends Activity implements MyItemClickListener, MyItemLongClickListener {

    private RecyclerView mRecyclerView, mRecyclerView_two;

    private List<MyItemBean> mData;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    //HI this is just a change for testing
    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView_two = (RecyclerView) findViewById(R.id.recyclerView_two);
        MyLayoutManager manager = new MyLayoutManager(this);
        MyLayoutManager manager_two = new MyLayoutManager(this);
        manager.setOrientation(LinearLayout.HORIZONTAL);//Ĭ����LinearLayout.VERTICAL
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        manager_two.setOrientation(LinearLayout.HORIZONTAL);
        mRecyclerView_two.setLayoutManager(manager_two);
        mRecyclerView_two.setItemAnimator(new DefaultItemAnimator());
    }

    private void initData() {
        this.mData = new ArrayList<MyItemBean>();
        for (int i = 0; i < 20; i++) {
            MyItemBean bean = new MyItemBean();
            bean.tv = "Xmy" + i;
            mData.add(bean);
        }
        this.mAdapter = new MyAdapter(mData);
        this.mRecyclerView.setAdapter(mAdapter);
        this.mRecyclerView_two.setAdapter(mAdapter);
        RecyclerView.ItemDecoration decoration = new MyDecoration(this);
        this.mRecyclerView.addItemDecoration(decoration);
        this.mRecyclerView_two.addItemDecoration(decoration);
        this.mAdapter.setOnItemClickListener(this);
        this.mAdapter.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int size = mData.size();
        switch (item.getItemId()) {
            case R.id.action_add:
                MyItemBean bean = new MyItemBean();
                bean.tv = "Xmy" + (size);
                mData.add(1, bean);
                mAdapter.notifyItemInserted(1);
                break;
            case R.id.action_remove:
                mData.remove(size - 1);
                mAdapter.notifyItemRemoved(1);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Item click
     */
    @Override
    public void onItemClick(View view, int postion) {
        MyItemBean bean = mData.get(postion);
        if (bean != null) {
            Toast.makeText(this, bean.tv, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemLongClick(View view, int postion) {
        MyItemBean bean = mData.get(postion);
        if (bean != null) {
            Toast.makeText(this, "LongClick " + bean.tv, Toast.LENGTH_SHORT).show();
        }
    }
}
