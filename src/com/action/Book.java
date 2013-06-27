
package com.action;

import com.actionForm.BookForm;
import com.dao.BookDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintStream;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

public class Book extends ActionSupport
    implements ServletRequestAware
{
	 private BookDAO bookDAO;
	    private BookForm form;
	    private HttpSession session;
	    private HttpServletRequest request;

    public Book()
    {
        bookDAO = null;
        bookDAO = new BookDAO();
    }

    public String execute()
    {
        String action = request.getParameter("action");
       
        if(action == null || "".equals(action))
        {
            request.setAttribute("error", "对不起，出错啦");
            return "error";
        }
        if("bookAdd".equals(action))
            return bookAdd();
        if("bookQuery".equals(action))
            return bookQuery();
        if("bookModifyQuery".equals(action))
            return bookModifyQuery();
        if("bookModify".equals(action))
            return bookModify();
        if("bookDel".equals(action))
            return bookDel();
        if("bookDetail".equals(action))
            return bookDetail();
        if("bookifQuery".equals(action))
        {
            return bookifQuery();
        } else
        {
            request.setAttribute("error", "出错了，你懂得？");
            return "error";
        }
    }

    public String bookAdd()
    {
        BookForm bookForm = form;
        bookForm.setBarcode(bookForm.getBarcode());
        bookForm.setBookName(bookForm.getBookName());
        bookForm.setTypeId(bookForm.getTypeId());
        bookForm.setAuthor(bookForm.getAuthor());
        bookForm.setTranslator(bookForm.getTranslator());
        bookForm.setIsbn(bookForm.getIsbn());
        bookForm.setPrice(bookForm.getPrice());
        bookForm.setPage(bookForm.getPage());
        bookForm.setBookcaseid(bookForm.getBookcaseid());
        Date date1 = new Date();
        java.sql.Date date = new java.sql.Date(date1.getTime());
        bookForm.setInTime(date.toString());
        bookForm.setOperator(bookForm.getOperator());
        int a = bookDAO.insert(bookForm);
        if(a == 1)
            return "bookAdd";
        if(a == 2)
        {
            request.setAttribute("error", "书本这里出错了");
            return "error";
        } else
        {
            request.setAttribute("error", "错了，出错了");
            return "error";
        }
    }

    public String bookQuery()
    {
        String str = null;
        request.setAttribute("book", bookDAO.query(str));
        return "bookQuery";
    }

    public String bookifQuery()
    {
        String str = null;
        if(request.getParameter("f") != null)
            str = (new StringBuilder(String.valueOf(request.getParameter("f")))).append(" like '%").append(request.getParameter("key")).append("%").toString();
           request.setAttribute("ifbook", bookDAO.query(str));
       
        return "bookifQuery";
    }

    public String bookModifyQuery()
    {
           request.setAttribute("bookQueryif", bookDAO.queryM(form));
        return "bookQueryModify";
    }

    public String bookDetail()
    {
        request.setAttribute("bookDetail", bookDAO.queryM(form));
        return "bookDeatil";
    }

    public String bookModify()
    {
        BookForm bookForm = form;
        bookForm.setBarcode(bookForm.getBarcode());
        bookForm.setBookName(bookForm.getBookName());
        bookForm.setTypeId(bookForm.getTypeId());
        bookForm.setAuthor(bookForm.getAuthor());
        bookForm.setTranslator(bookForm.getTranslator());
        bookForm.setIsbn(bookForm.getIsbn());
        bookForm.setPrice(bookForm.getPrice());
        bookForm.setPage(bookForm.getPage());
        bookForm.setBookcaseid(bookForm.getBookcaseid());
        bookForm.setInTime(bookForm.getInTime());
        bookForm.setOperator(bookForm.getOperator());
        int ret = bookDAO.update(bookForm);
        if(ret == 0)
        {
            request.setAttribute("error", "修改出错了，请检查一下书");
            return "error";
        } else
        {
            return "bookModify";
        }
    }

    public String bookDel()
    {
        int ret = bookDAO.delete(form);
        if(ret == 0)
        {
            request.setAttribute("error", "删除出错了");
            return "error";
        } else
        {
            return "bookDel";
        }
    }

    public BookForm getForm()
    {
        return form;
    }

    public void setForm(BookForm form)
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

   
}