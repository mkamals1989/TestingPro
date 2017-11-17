package com.example.jeesan_2.testingpro.Room;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.jeesan_2.testingpro.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KAMAL on 11/16/2017.
 */

public class RoomMainActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;

    @BindView(R.id.tv1)
    TextView tv1;

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

        RepoViewModel viewModel = ViewModelProviders.of(this).get(RepoViewModel.class);
//        viewModel.getLiveDataRepo().observe(this, new Observer<Repo>() {
//            @Override
//            public void onChanged(@Nullable Repo repo) {
//                if (repo != null) {
//                    tv1.setText("This is your data fetched from Room DB : " + "\n" +
//                            " ID : " + repo.id + "\n" +
//                            " NAME : " + repo.name + "\n" +
//                            " URL : " + repo.url);
//                }
//            }
//        });

        viewModel.getLiveDataAllRepo().observe(this, new Observer<List<Repo>>() {
            @Override
            public void onChanged(@Nullable List<Repo> repos) {
                if (repos != null) {
                    String text = "";
                    for (Repo repo1 : repos) {
                        if (text.length() != 0) {
                            text = text + "\n" + " ID : " + repo1.id + "\n" +
                                    " NAME : " + repo1.name + "\n" +
                                    " URL : " + repo1.url;
                        } else {
                            text = "This is your data fetched from Room DB : " + "\n" +
                                    " ID : " + repo1.id + "\n" +
                                    " NAME : " + repo1.name + "\n" +
                                    " URL : " + repo1.url;
                        }


                    }
                    tv1.setText(text);
                }
            }
        });
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
                repoDao.insert(new Repo(4, "Murli", "www.murali.com"));
            }
        }).start();

        tv.setText("Data Inserted");
    }

    private void fetchSingleRepo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                repo = repoDao.getSingleRepo(1);
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
