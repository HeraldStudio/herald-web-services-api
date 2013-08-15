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
package cn.edu.seu.herald.ws.api;

import cn.edu.seu.herald.ws.api.library.*;

/**
 * 图书馆服务接口
 * @author rAy <predator.ray@gmail.com>
 */
public interface LibraryService extends ConfigurableService {

    /**
     * 登录图书馆
     * @param username 图书馆账户用户名
     * @param password 图书馆账户密码
     * @return 当验证成功时，返回登录的用户；当验证失败，则返回空
     * @throws ServiceException 服务异常
     * @see cn.edu.seu.herald.ws.api.library.User
     */
    User logIn(String username, String password) throws ServiceException;

    /**
     * 根据关键字搜索图书
     * @param keyword 关键字
     * @return 满足关键字的图书列表
     * @throws ServiceException
     * @see cn.edu.seu.herald.ws.api.library.Booklist
     */
    Booklist search(String keyword) throws ServiceException;

    /**
     * 返回用户当前正在借阅的书籍
     * @param user 图书馆用户
     * @return 正在借阅的图书列表
     * @throws ServiceException
     * @see cn.edu.seu.herald.ws.api.library.Booklist
     */
    Booklist getBooksBorrowedByUser(User user) throws ServiceException;

    /**
     * 返回用户成功预约的图书
     * @param user 图书馆用户
     * @return 成功预约的图书列表
     * @throws ServiceException
     * @see cn.edu.seu.herald.ws.api.library.Booklist
     */
    Booklist getBooksReservedByUser(User user) throws ServiceException;

    /**
     * 返回用户借阅历史
     * @param user 图书馆用户
     * @return 借阅历史的图书列表
     * @throws ServiceException
     * @see cn.edu.seu.herald.ws.api.library.Booklist
     */
    Booklist getBorrowHistoryOfUser(User user) throws ServiceException;

    /**
     * 获取书的详细信息（出版社、ISBN、馆藏、预约、续借）
     * @param book 从图书列表中取出的图书对象
     * @return 图书详细信息
     * @throws ServiceException
     * @see cn.edu.seu.herald.ws.api.library.Booklist#getBooks()
     * @see cn.edu.seu.herald.ws.api.library.Book
     */
    Book getBookDetails(Book book) throws ServiceException;

    /**
     * 续借图书，需要保障<code>Book.getRenewal() != null</code>
     * @param book 要续借的图书，
     *             通过<code>LibraryService.getBooksBorrowedByUser(User)</code>
     *             中获取图书对象
     * @throws ServiceException
     * @see cn.edu.seu.herald.ws.api.LibraryService#getBooksBorrowedByUser(
     * cn.edu.seu.herald.ws.api.library.User)
     * @see cn.edu.seu.herald.ws.api.library.Book#getRenewal()
     */
    void renew(Book book) throws ServiceException;

    /**
     * 预约图书
     * @param reservation 从<code>Book.getReservations()</code>中获取的预约对象
     * @throws ServiceException
     * @see cn.edu.seu.herald.ws.api.library.Book#getReservations()
     */
    void makeReservation(ReservationType reservation) throws ServiceException;

    /**
     * 取消预约
     * @param reservation 先从<code>LibraryService.getBooksReservedByUser(User)
     *                    </code>获取预约图书列表，
     *                    再从<code>Book.getReservations()</code>中获取的预约对象
     * @throws ServiceException
     * @see cn.edu.seu.herald.ws.api.LibraryService#getBooksReservedByUser(
     * cn.edu.seu.herald.ws.api.library.User)
     * @see cn.edu.seu.herald.ws.api.library.Book#getReservations()
     */
    void cancelReservation(ReservationType reservation) throws ServiceException;
}
