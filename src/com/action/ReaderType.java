// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2012/12/25 15:31:00
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ReaderType.java

package com.action;

import com.actionForm.ReaderTypeForm;
import com.dao.ReaderTypeDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

public class ReaderType extends ActionSupport
    implements ServletRequestAware
{private ReaderTypeDAO readerTypeDAO;
private ReaderTypeForm form;
private ReaderTypeForm readerTypeForm1;
private HttpSession session;
private HttpServletRequest request;

    public ReaderType()
    {
        readerTypeDAO = null;
        readerTypeDAO = new ReaderTypeDAO();
        readerTypeForm1 = new ReaderTypeForm();
    }

    public String execute()
    {
        String action = request.getParameter("action");
         if(action == null || "".equals(action))
        {
            request.setAttribute("error", "\u60A8\u7684\u64CD\u4F5C\u6709\u8BEF\uFF01");
            return "error";
        }
        if("readerTypeAdd".equals(action))
            return readerTypeAdd();
        if("readerTypeQuery".equals(action))
            return readerTypeQuery();
        if("readerTypeModifyQuery".equals(action))
            return readerTypeModifyQuery();
        if("readerTypeModify".equals(action))
            return readerTypeModify();
        if("readerTypeDel".equals(action))
        {
            return readerTypeDel();
        } else
        {
            request.setAttribute("error", "读者类型出错了哦");
            return "error";
        }
    }

    private String readerTypeAdd()
    {
        ReaderTypeForm readerTypeForm = form;
           readerTypeForm.setName(readerTypeForm.getName());
        int a = readerTypeDAO.insert(readerTypeForm);
        if(a == 0)
        {
            request.setAttribute("error", "添加失败");
            return "error";
        }
        if(a == 2)
        {
            request.setAttribute("error", "出错了，请检查");
            return "error";
        } else
        {
            return "readerTypeAdd";
        }
    }

    private String readerTypeQuery()
    {
        String str = null;
        request.setAttribute("readerType", readerTypeDAO.query(str));
        return "readerTypeQuery";
    }

    private String readerTypeModifyQuery()
    {
        readerTypeForm1.setId(Integer.valueOf(request.getParameter("ID")));
        request.setAttribute("readerTypeQueryif", readerTypeDAO.queryM(readerTypeForm1));
        return "readerTypeQueryModify";
    }

    private String readerTypeModify()
    {
        ReaderTypeForm readerTypeForm = form;
        readerTypeForm.setName(readerTypeForm.getName());
        readerTypeForm.setNumber(readerTypeForm.getNumber());
        int ret = readerTypeDAO.update(readerTypeForm);
        if(ret == 0)
        {
            request.setAttribute("error", "出错了，诶");
            return "error";
        } else
        {
            return "readerTypeModify";
        }
    }

    private String readerTypeDel()
    {
        readerTypeForm1.setId(Integer.valueOf(request.getParameter("ID")));
        int ret = readerTypeDAO.delete(readerTypeForm1);
        if(ret == 0)
        {
            request.setAttribute("error", "还存在该类型的读者档案，请先删除");
            return "error";
        } else
        {
            return "readerTypeDel";
        }
    }

    public ReaderTypeForm getForm()
    {
        return form;
    }

    public void setForm(ReaderTypeForm form)
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