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
    public User logIn(String username, String password)
            throws ServiceException {
        return null;
    }

    @Override
    public Booklist search(String keyword) throws ServiceException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Booklist getBooksBorrowed(String borrowingHref) throws ServiceException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Booklist getBooksReserved(String reservingHref) throws ServiceException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Booklist getBorrowHistory(String borrowedHref) throws ServiceException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Book getBookByHref(String bookHref) throws ServiceException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void renew(String renewalHref) throws ServiceException {
        URI renewalURI = URI.create(renewalHref);
        ClientResponse response = getWebResource(renewalURI)
                .post(ClientResponse.class);
        int status = response.getStatus();
        if (status == 204) {
            return;
        }

        throw new UnexpectedStatusException(status);
    }

    @Override
    public void reserve(String reservationHref) throws ServiceException {
        URI reservationURI = URI.create(reservationHref);
        ClientResponse response = getWebResource(reservationURI)
                .post(ClientResponse.class);
        int status = response.getStatus();
        if (status == 204) {
            return;
        }

        throw new UnexpectedStatusException(status);
    }

    @Override
    public void cancelReservation(String reservationHref)
            throws ServiceException {
        URI reservationURI = URI.create(reservationHref);
        ClientResponse response = getWebResource(reservationURI)
                .delete(ClientResponse.class);
        int status = response.getStatus();
        if (status == 204) {
            return;
        }

        throw new UnexpectedStatusException(status);
    }
}
