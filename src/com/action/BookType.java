
package com.action;

import com.actionForm.BookTypeForm;
import com.dao.BookTypeDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

public class BookType extends ActionSupport
    implements ServletRequestAware
{
	 private BookTypeDAO bookTypeDAO;
	    private BookTypeForm form;
	    private HttpSession session;
	    private HttpServletRequest request;
    public BookType()
    {
        bookTypeDAO = null;
        bookTypeDAO = new BookTypeDAO();
    }

    public String execute()
    {
        String action = request.getParameter("action");
             if(action == null || "".equals(action))
        {
            request.setAttribute("error", "\u60A8\u7684\u64CD\u4F5C\u6709\u8BEF\uFF01");
            return "error";
        }
        if("bookTypeAdd".equals(action))
            return bookTypeAdd();
        if("bookTypeQuery".equals(action))
            return bookTypeQuery();
        if("bookTypeModifyQuery".equals(action))
            return bookTypeModifyQuery();
        if("bookTypeModify".equals(action))
            return bookTypeModify();
        if("bookTypeDel".equals(action))
        {
            return bookTypeDel();
        } else
        {
            request.setAttribute("error", "\u64CD\u4F5C\u5931\u8D25\uFF01");
            return "error";
        }
    }

    public String bookTypeAdd()
    {
        BookTypeForm bookTypeForm = form;
          bookTypeForm.setTypeName(bookTypeForm.getTypeName());
        int a = bookTypeDAO.insert(bookTypeForm);
        if(a == 0)
        {
            request.setAttribute("error", "\u56FE\u4E66\u7C7B\u578B\u4FE1\u606F\u6DFB\u52A0\u5931\u8D25\uFF01");
            return "error";
        }
        if(a == 2)
        {
            request.setAttribute("error", "\u8BE5\u56FE\u4E66\u7C7B\u578B\u4FE1\u606F\u5DF2\u7ECF\u6DFB\u52A0\uFF01");
            return "error";
        } else
        {
            return "bookTypeAdd";
        }
    }

    public String bookTypeQuery()
    {
        String str = null;
        request.setAttribute("bookType", bookTypeDAO.query(str));
        return "bookTypeQuery";
    }

    public String bookTypeModifyQuery()
    {
        request.setAttribute("bookTypeQueryif", bookTypeDAO.queryM(form));
        return "bookTypeQueryModify";
    }

    public String bookTypeModify()
    {
        int ret = bookTypeDAO.update(form);
        if(ret == 0)
        {
            request.setAttribute("error", "\u4FEE\u6539\u56FE\u4E66\u7C7B\u578B\u4FE1\u606F\u5931\u8D25\uFF01");
            return "error";
        } else
        {
            return "bookTypeModify";
        }
    }

    public String bookTypeDel()
    {
        int ret = bookTypeDAO.delete(form);
        if(ret == 0)
        {
            request.setAttribute("error", "\u5220\u9664\u56FE\u4E66\u7C7B\u578B\u4FE1\u606F\u5931\u8D25\uFF01");
            return "error";
        } else
        {
            return "bookTypeDel";
        }
    }

    public BookTypeForm getForm()
    {
        return form;
    }

    public void setForm(BookTypeForm form)
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