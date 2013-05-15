package cn.edu.seu.herald.ws.api;

import cn.edu.seu.herald.ws.api.library.*;

/**
 * Copyright (c) 2013 Ray <predator.ray@gmail.com>
 */
public interface LibraryService {

    User logIn(String username, String password) throws ServiceException;

    Booklist search(String keyword) throws ServiceException;

    Booklist getBooksBorrowed(String borrowingHref) throws ServiceException;

    Booklist getBooksReserved(String reservingHref) throws ServiceException;

    Booklist getBorrowHistory(String borrowedHref) throws ServiceException;

    Book getBookByHref(String bookHref) throws ServiceException;

    void renew(String renewalHref) throws ServiceException;

    void reserve(String reservationHref) throws ServiceException;

    void cancelReservation(String reservationHref) throws ServiceException;
}
