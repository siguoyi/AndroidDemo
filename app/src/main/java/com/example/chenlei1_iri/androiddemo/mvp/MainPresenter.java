package com.example.chenlei1_iri.androiddemo.mvp;

/**
 * Created by chenlei1-iri on 2016/8/2.
 */
public class MainPresenter {
    MainView mainView;
    DataManager taskManager;

    public MainPresenter(){
        this.taskManager = new DataManager(new DataSourceImpl());
    }

    public MainPresenter addDataListener(MainView viewListener) {
        this.mainView = viewListener;
        return this;
    }

    public void getString() {
        String str = taskManager.getShowContent();
        mainView.onShowString(str);
    }
}
