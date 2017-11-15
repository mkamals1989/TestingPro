package com.example.jeesan_2.testingpro.Room;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.jeesan_2.testingpro.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KAMAL on 11/16/2017.
 */

public class RoomMainActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;

    Context mContext;
    RepoDatabase database;
    RepoDao repoDao;
    Repo repo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_activity);
        ButterKnife.bind(this);
        mContext = RoomMainActivity.this;

        database = RepoDatabase.getInstance(mContext);
        getAllDbDAO();

    }

    public void addDataMethod(View v) {
        insertSingleRepo();
    }

    public void getDataMethod(View v) {
        fetchSingleRepo();
    }

    private void insertSingleRepo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                repoDao.insert(new Repo(2, "Kanaga", "www.kanaga.com"));
            }
        }).start();

        tv.setText("Data Inserted");
    }

    private void fetchSingleRepo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                repo = repoDao.getRepo(1);
            }
        }).start();

        if (repo != null) {
            tv.setText("This is your data fetched from Room DB : " + "\n" +
                    " ID : " + repo.id + "\n" +
                    " NAME : " + repo.name + "\n" +
                    " URL : " + repo.url);
        }
    }

    private void getAllDbDAO() {
        repoDao = database.getRepoDao();
    }
}
