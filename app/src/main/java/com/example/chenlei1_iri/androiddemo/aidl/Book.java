package com.example.chenlei1_iri.androiddemo.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chenlei1-iri on 2016/8/8.
 */
public class Book implements Parcelable{

    public int bookId;
    public String bookName;

    private Book(Parcel source){
        bookId = source.readInt();
        bookName = source.readString();
    }

    public Book(int bookId, String bookName){
        this.bookId = bookId;
        this.bookName = bookName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>(){

        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
