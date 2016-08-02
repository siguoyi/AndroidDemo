package com.example.chenlei1_iri.androiddemo.mvp;

/**
 * Created by chenlei1-iri on 2016/8/2.
 */
public interface DataSource {
    String getStringFromRemote();
    String getStringFromCache();
}
