package com.github.ptr.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PtrRecyclerView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity {

    PtrRecyclerView view;
    MyAdapter adapter;
    List<Bean> data;

    /** Called when the activity is first created. */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        data = new ArrayList<>();
        data.add(new Bean());
        data.add(new Bean());
        data.add(new Bean());
        data.add(new Bean());
        data.add(new Bean());
        data.add(new Bean());
        data.add(new Bean());
        data.add(new Bean());
        data.add(new Bean());
        data.add(new Bean());
        data.add(new Bean());
        data.add(new Bean());
        data.add(new Bean());

        view = (PtrRecyclerView) findViewById(R.id.pull_refresh_recycler_view);
        adapter = new MyAdapter(data);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager manager1 = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        view.setLayoutManager(manager1);
        view.setAdapter(adapter);
        view.setMode(PullToRefreshBase.Mode.BOTH);
        view.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override public void onPullDownToRefresh(final PullToRefreshBase<RecyclerView> refreshView) {
                view.postDelayed(new Runnable() {
                    @Override public void run() {
                        refreshView.onRefreshComplete();
                    }
                }, 500);
            }

            @Override public void onPullUpToRefresh(final PullToRefreshBase<RecyclerView> refreshView) {
                view.postDelayed(new Runnable() {
                    @Override public void run() {
                        refreshView.onRefreshComplete();
                        data.add(new Bean());
                        data.add(new Bean());
                        data.add(new Bean());
                        adapter.notifyItemInserted(data.size() - 1);
                        //adapter.notifyDataSetChanged();
                    }
                }, 500);
            }
        });
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Viewholder> {

        private List<Bean> mData;

        public MyAdapter(List<Bean> mData) {
            this.mData = mData;
        }

        @Override public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

            return new Viewholder(view);
        }

        @Override public void onBindViewHolder(Viewholder holder, int position) {
            holder.text.setText("position is " + position);
        }

        @Override public int getItemCount() {
            return mData.size();
        }

        class Viewholder extends RecyclerView.ViewHolder {

            private TextView text;

            public Viewholder(View itemView) {
                super(itemView);
                text = (TextView) itemView.findViewById(R.id.textView);
            }
        }
    }
}
