package com.example.jeesan_2.testingpro.roomdatabase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by KAMAL on 11/17/2017.
 */

public class RepoViewModel extends AndroidViewModel {

    private RepoDatabase database;

    private LiveData<Repo> data;
    private LiveData<List<Repo>> arrData;

    public RepoViewModel(@NonNull Application application) {
        super(application);

        database = RepoDatabase.getInstance(application.getApplicationContext());

        data = database.getRepoDao().getRepo(1);
        arrData = database.getRepoDao().getAllRepos();
    }

    public LiveData<Repo> getLiveDataRepo() {
        return data;
    }

    public LiveData<List<Repo>> getLiveDataAllRepo() {
        return arrData;
    }
}
