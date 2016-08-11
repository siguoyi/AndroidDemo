// IBookManager.aidl
package com.example.chenlei1_iri.androiddemo.aidl;

import com.example.chenlei1_iri.androiddemo.aidl.Book;

interface IBookManager {
     List<Book> getBookList();
     void addBook(in Book book);
}
