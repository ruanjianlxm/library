// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2012/12/25 15:27:44
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Borrow.java

package com.action;

import com.actionForm.BorrowForm;
import com.actionForm.ReaderForm;
import com.dao.*;
import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

public class Borrow extends ActionSupport
    implements ServletRequestAware
{

    public Borrow()
    {
        borrowDAO = null;
        readerDAO = null;
        bookDAO = null;
        readerForm = new ReaderForm();
        borrowDAO = new BorrowDAO();
        readerDAO = new ReaderDAO();
        bookDAO = new BookDAO();
    }

    public String execute()
    {
        BorrowForm borrowForm = form;
        String action = request.getParameter("action");
        if(action == null || "".equals(action))
        {
            request.setAttribute("error", "\u60A8\u7684\u64CD\u4F5C\u6709\u8BEF\uFF01");
            return "error";
        }
        if("bookBorrowSort".equals(action))
            return bookBorrowSort();
        if("bookborrow".equals(action))
            return bookborrow();
        if("bookrenew".equals(action))
            return bookrenew();
        if("bookback".equals(action))
            return bookback();
        if("Bremind".equals(action))
            return bremind();
        if("borrowQuery".equals(action))
        {
            return borrowQuery();
        } else
        {
            request.setAttribute("error", "\u64CD\u4F5C\u5931\u8D25\uFF01");
            return "error";
        }
    }

    public String bookBorrowSort()
    {
        request.setAttribute("bookBorrowSort", borrowDAO.bookBorrowSort());
        return "bookBorrowSort";
    }

    public String borrowQuery()
    {
        String str = null;
        String flag[] = request.getParameterValues("flag");
        if(flag != null)
        {
            String aa = flag[0];
            if("a".equals(aa) && request.getParameter("f") != null)
                str = (new StringBuilder(String.valueOf(request.getParameter("f")))).append(" like '%").append(request.getParameter("key")).append("%'").toString();
            if("b".equals(aa))
            {
                String sdate = request.getParameter("sdate");
                String edate = request.getParameter("edate");
                if(sdate != null && edate != null)
                    str = (new StringBuilder("borrowTime between '")).append(sdate).append("' and '").append(edate).append("'").toString();
                System.out.println((new StringBuilder("\u65E5\u671F")).append(str).toString());
            }
            if(flag.length == 2)
            {
                if(request.getParameter("f") != null)
                    str = (new StringBuilder(String.valueOf(request.getParameter("f")))).append(" like '%").append(request.getParameter("key")).append("%'").toString();
                System.out.println("\u65E5\u671F\u548C\u6761\u4EF6");
                String sdate = request.getParameter("sdate");
                String edate = request.getParameter("edate");
                String str1 = null;
                if(sdate != null && edate != null)
                    str1 = (new StringBuilder("borrowTime between '")).append(sdate).append("' and '").append(edate).append("'").toString();
                str = (new StringBuilder(String.valueOf(str))).append(" and borr.").append(str1).toString();
                System.out.println((new StringBuilder("\u6761\u4EF6\u548C\u65E5\u671F\uFF1A")).append(str).toString());
            }
        }
        request.setAttribute("borrowQuery", borrowDAO.borrowQuery(str));
        System.out.print((new StringBuilder("\u6761\u4EF6\u67E5\u8BE2\u56FE\u4E66\u501F\u9605\u4FE1\u606F\u65F6\u7684str:")).append(str).toString());
        return "borrowQuery";
    }

    public String bremind()
    {
        request.setAttribute("Bremind", borrowDAO.bremind());
        return "Bremind";
    }

    public String bookborrow()
    {
        readerForm.setBarcode(request.getParameter("barcode"));
        ReaderForm reader = readerDAO.queryM(readerForm);
        request.setAttribute("readerinfo", reader);
        request.setAttribute("borrowinfo", borrowDAO.borrowinfo(request.getParameter("barcode")));
        String f = request.getParameter("f");
        String key = request.getParameter("inputkey");
        if(key != null && !key.equals(""))
        {
            String operator = request.getParameter("operator");
            com.actionForm.BookForm bookForm = bookDAO.queryB(f, key);
            if(bookForm != null)
            {
                int ret = borrowDAO.insertBorrow(reader, bookDAO.queryB(f, key), operator);
                if(ret == 1)
                {
                    request.setAttribute("bar", request.getParameter("barcode"));
                    return "bookborrowok";
                } else
                {
                    request.setAttribute("error", "\u6DFB\u52A0\u501F\u9605\u4FE1\u606F\u5931\u8D25!");
                    return "error";
                }
            } else
            {
                request.setAttribute("error", "\u6CA1\u6709\u8BE5\u56FE\u4E66!");
                return "error";
            }
        } else
        {
            return "bookborrow";
        }
    }

    public String bookrenew()
    {
        readerForm.setBarcode(request.getParameter("barcode"));
        ReaderForm reader = readerDAO.queryM(readerForm);
        request.setAttribute("readerinfo", reader);
        request.setAttribute("borrowinfo", borrowDAO.borrowinfo(request.getParameter("barcode")));
        if(request.getParameter("id") != null)
        {
            int id = Integer.parseInt(request.getParameter("id"));
            if(id > 0)
            {
                int ret = borrowDAO.renew(id);
                if(ret == 0)
                {
                    request.setAttribute("error", "\u56FE\u4E66\u7EE7\u501F\u5931\u8D25!");
                    return "error";
                } else
                {
                    request.setAttribute("bar", request.getParameter("barcode"));
                    return "bookrenewok";
                }
            }
        }
        return "bookrenew";
    }

    public String bookback()
    {
        readerForm.setBarcode(request.getParameter("barcode"));
        ReaderForm reader = readerDAO.queryM(readerForm);
        request.setAttribute("readerinfo", reader);
        request.setAttribute("borrowinfo", borrowDAO.borrowinfo(request.getParameter("barcode")));
        if(request.getParameter("id") != null)
        {
            int id = Integer.parseInt(request.getParameter("id"));
            String operator = request.getParameter("operator");
            if(id > 0)
            {
                int ret = borrowDAO.back(id, operator);
                if(ret == 0)
                {
                    request.setAttribute("error", "\u56FE\u4E66\u5F52\u8FD8\u5931\u8D25!");
                    return "error";
                } else
                {
                    request.setAttribute("bar", request.getParameter("barcode"));
                    return "bookbackok";
                }
            }
        }
        return "bookback";
    }

    public BorrowForm getForm()
    {
        return form;
    }

    public void setForm(BorrowForm form)
    {
        this.form = form;
    }

    public void setServletRequest(HttpServletRequest request)
    {
        this.request = request;
        session = request.getSession();
    }

    public HttpServletRequest getRequest()
    {
        return request;
    }

    public HttpSession getSession()
    {
        return session;
    }

    private BorrowDAO borrowDAO;
    private ReaderDAO readerDAO;
    private BookDAO bookDAO;
    private ReaderForm readerForm;
    private BorrowForm form;
    private HttpSession session;
    private HttpServletRequest request;
}