/*
 * The MIT License
 *
 * Copyright 2013 Herald Studio, Southeast University.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
            LIBRARY_URI + "/user";
    private static final String SEARCH_TEMPLATE =
            LIBRARY_URI + "/search";
    private String baseResourceUri;

    public LibraryServiceImpl(String baseResourceUri) {
        this.baseResourceUri = baseResourceUri;
    }

    @Override
    public User logIn(String username, String password)
            throws ServiceException {
        URI uri = UriBuilder.fromUri(baseResourceUri + LOG_IN_TEMPLATE)
                .queryParam("username", username)
                .queryParam("password", password)
                .build();
        return getJaxbObjectByResource(uri, User.class);
    }

    @Override
    public Booklist search(String keyword) throws ServiceException {
        URI uri = UriBuilder.fromUri(baseResourceUri + SEARCH_TEMPLATE)
                .queryParam("keyword", keyword)
                .build();
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
