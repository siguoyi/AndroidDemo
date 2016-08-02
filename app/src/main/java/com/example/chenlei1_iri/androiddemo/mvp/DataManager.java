package com.example.chenlei1_iri.androiddemo.mvp;

/**
 * Created by chenlei1-iri on 2016/8/2.
 */
public class DataManager {
    DataSource taskDataSource;

    public DataManager(DataSource taskDataSource){
        this.taskDataSource = taskDataSource;
    }

    public String getShowContent(){
        return taskDataSource.getStringFromRemote() + taskDataSource.getStringFromCache();
    }
}
