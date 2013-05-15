package cn.edu.seu.herald.ws.api.impl;

import cn.edu.seu.herald.ws.api.LibraryService;
import cn.edu.seu.herald.ws.api.ServiceException;
import cn.edu.seu.herald.ws.api.library.*;
import com.sun.jersey.api.client.ClientResponse;

import java.net.URI;

/**
 * Copyright (c) 2013 Ray <predator.ray@gmail.com>
 */
class LibraryServiceImpl extends AbstractXmlService implements LibraryService {

    private String baseResourceUri;

    public LibraryServiceImpl(String baseResourceUri) {
        this.baseResourceUri = baseResourceUri;
    }

    @Override
    public User logIn(String username, String password) throws ServiceException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Booklist search(String keyword) throws ServiceException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Booklist getBooksBorrowedByUser(User user) throws ServiceException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Booklist getBooksReservedByUser(User user) throws ServiceException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Booklist getBorrowHistoryOfUser(User user) throws ServiceException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Book getBookDetails(Book book) throws ServiceException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void renew(Book book) throws ServiceException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void reserve(Book book) throws ServiceException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void cancelReservation(Book book) throws ServiceException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
