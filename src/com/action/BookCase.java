
package com.action;

import com.actionForm.BookCaseForm;
import com.dao.BookCaseDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

public class BookCase extends ActionSupport
    implements ServletRequestAware
{

    public BookCase()
    {
        bookCaseDAO = null;
        bookCaseDAO = new BookCaseDAO();
    }

    public String execute()
    {
        String action = request.getParameter("action");
        System.out.println((new StringBuilder("\nbookCase*********************action=")).append(action).toString());
        if(action == null || "".equals(action))
            return "error";
        if("bookCaseAdd".equals(action))
            return bookCaseAdd();
        if("bookCaseQuery".equals(action))
            return bookCaseQuery();
        if("bookCaseModifyQuery".equals(action))
            return bookCaseModifyQuery();
        if("bookCaseModify".equals(action))
            return bookCaseModify();
        if("bookCaseDel".equals(action))
        {
            return bookCaseDel();
        } else
        {
            request.setAttribute("error", "\u64CD\u4F5C\u5931\u8D25\uFF01");
            return "error";
        }
    }

    public String bookCaseAdd()
    {
        BookCaseForm bookCaseForm = form;
        System.out.println((new StringBuilder("servlet:")).append(request.getParameter("name")).toString());
        bookCaseForm.setName(bookCaseForm.getName());
        int a = bookCaseDAO.insert(bookCaseForm);
        if(a == 0)
        {
            request.setAttribute("error", "\u4E66\u67B6\u4FE1\u606F\u6DFB\u52A0\u5931\u8D25\uFF01");
            return "error";
        }
        if(a == 2)
        {
            request.setAttribute("error", "\u8BE5\u4E66\u67B6\u4FE1\u606F\u5DF2\u7ECF\u6DFB\u52A0\uFF01");
            return "error";
        } else
        {
            return "bookcaseAdd";
        }
    }

    public String bookCaseQuery()
    {
        String str = null;
        request.setAttribute("bookcase", bookCaseDAO.query(str));
        return "bookcaseQuery";
    }

    public String bookCaseModifyQuery()
    {
        request.setAttribute("bookCaseQueryif", bookCaseDAO.queryM(form));
        return "bookCaseQueryModify";
    }

    public String bookCaseModify()
    {
        int ret = bookCaseDAO.update(form);
        if(ret == 0)
        {
            request.setAttribute("error", "\u4FEE\u6539\u4E66\u67B6\u4FE1\u606F\u5931\u8D25\uFF01");
            return "error";
        } else
        {
            return "bookCaseModify";
        }
    }

    public String bookCaseDel()
    {
        int ret = bookCaseDAO.delete(form);
        if(ret == 0)
        {
            request.setAttribute("error", "\u5220\u9664\u4E66\u67B6\u4FE1\u606F\u5931\u8D25\uFF01");
            return "error";
        } else
        {
            return "bookCaseDel";
        }
    }

    public BookCaseForm getForm()
    {
        return form;
    }

    public void setForm(BookCaseForm form)
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

    private BookCaseDAO bookCaseDAO;
    private BookCaseForm form;
    private HttpSession session;
    private HttpServletRequest request;
}