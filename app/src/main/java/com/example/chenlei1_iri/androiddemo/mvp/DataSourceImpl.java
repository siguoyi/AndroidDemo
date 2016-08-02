package com.example.chenlei1_iri.androiddemo.mvp;

/**
 * Created by chenlei1-iri on 2016/8/2.
 */
public class DataSourceImpl implements DataSource {
    @Override
    public String getStringFromRemote() {
        return "Hello";
    }

    @Override
    public String getStringFromCache() {
        return " world !";
    }
}
