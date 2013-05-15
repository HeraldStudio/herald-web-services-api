package cn.edu.seu.herald.ws.api;

import cn.edu.seu.herald.ws.api.library.*;

/**
 * Copyright (c) 2013 Ray <predator.ray@gmail.com>
 */
public interface LibraryService {

    User logIn(String username, String password) throws ServiceException;

    Booklist search(String keyword) throws ServiceException;

    Booklist getBooksBorrowedByUser(User user) throws ServiceException;

    Booklist getBooksReservedByUser(User user) throws ServiceException;

    Booklist getBorrowHistoryOfUser(User user) throws ServiceException;

    Book getBookDetails(Book book) throws ServiceException;

    void renew(Book book) throws ServiceException;

    void reserve(Book book) throws ServiceException;

    void cancelReservation(Book book) throws ServiceException;
}
