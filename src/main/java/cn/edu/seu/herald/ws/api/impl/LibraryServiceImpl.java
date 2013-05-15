package cn.edu.seu.herald.ws.api.impl;

import cn.edu.seu.herald.ws.api.LibraryService;
import cn.edu.seu.herald.ws.api.ServiceException;
import cn.edu.seu.herald.ws.api.library.*;
import com.sun.jersey.api.client.ClientResponse;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Copyright (c) 2013 Ray <predator.ray@gmail.com>
 */
class LibraryServiceImpl extends AbstractXmlService implements LibraryService {

    private static final String LIBRARY_URI = "/library";
    private static final String LOG_IN_TEMPLATE =
            LIBRARY_URI + "/user?username={1}&password={2}";
    private static final String SEARCH_TEMPLATE =
            LIBRARY_URI + "/search?keyword={1}";
    private String baseResourceUri;

    public LibraryServiceImpl(String baseResourceUri) {
        this.baseResourceUri = baseResourceUri;
    }

    @Override
    public User logIn(String username, String password)
            throws ServiceException {
        URI uri = UriBuilder.fromUri(baseResourceUri + LOG_IN_TEMPLATE)
                .build(username, password);
        return getJaxbObjectByResource(uri, User.class);
    }

    @Override
    public Booklist search(String keyword) throws ServiceException {
        URI uri = UriBuilder.fromUri(baseResourceUri + SEARCH_TEMPLATE)
                .build(keyword);
        return getJaxbObjectByResource(uri, Booklist.class);
    }

    @Override
    public Booklist getBooksBorrowedByUser(User user) throws ServiceException {
        String href = user.getBorrowing().getHref();
        return getBooklistByHref(href);
    }

    @Override
    public Booklist getBooksReservedByUser(User user) throws ServiceException {
        String href = user.getReserving().getHref();
        return getBooklistByHref(href);
    }

    @Override
    public Booklist getBorrowHistoryOfUser(User user) throws ServiceException {
        String href = user.getBorrowed().getHref();
        return getBooklistByHref(href);
    }

    private Booklist getBooklistByHref(String href) throws ServiceException {
        return getJaxbObjectByResource(URI.create(href), Booklist.class);
    }

    @Override
    public Book getBookDetails(Book book) throws ServiceException {
        String href = book.getHref();
        return getJaxbObjectByResource(URI.create(href), Book.class);
    }

    @Override
    public void renew(Book book) throws ServiceException {
        String href = book.getRenewal().getHref();
        ClientResponse response = getWebResource(URI.create(href))
                .post(ClientResponse.class);
        int status = response.getStatus();
        if (status == 204) {
            return;
        }
        throw new UnexpectedStatusException(status);
    }

    @Override
    public void makeReservation(ReservationType reservation)
            throws ServiceException {
        String href = reservation.getHref();
        ClientResponse response = getWebResource(URI.create(href))
                .post(ClientResponse.class);
        int status = response.getStatus();
        if (status == 204) {
            return;
        }
        throw new UnexpectedStatusException(status);
    }

    @Override
    public void cancelReservation(ReservationType reservation)
            throws ServiceException {
        String href = reservation.getHref();
        ClientResponse response = getWebResource(URI.create(href))
                .delete(ClientResponse.class);
        int status = response.getStatus();
        if (status == 204) {
            return;
        }
        throw new UnexpectedStatusException(status);
    }
}
