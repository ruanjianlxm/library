// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2012/12/25 15:29:58
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Publishing.java

package com.action;

import com.actionForm.PublishingForm;
import com.dao.PublishingDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

public class Publishing extends ActionSupport
    implements ServletRequestAware
{
	  private PublishingDAO pubDAO;
	    private PublishingForm form;
	    private HttpSession session;
	    private HttpServletRequest request;

    public Publishing()
    {
        pubDAO = null;
        pubDAO = new PublishingDAO();
    }

    public String execute()
    {
        PublishingForm publishingForm = form;
        String action = request.getParameter("action");
           if(action == null || "".equals(action))
        {
            request.setAttribute("error", "出版社这出错啦");
            return "error";
        }
        if("pubQuery".equals(action))
        {
            return pubQuery();
        } else
        {
            request.setAttribute("error", "出版社这你懂得？");
            return "error";
        }
    }

    private String pubQuery()
    {
        String str = null;
        request.setAttribute("book", pubDAO.query(str));
        return "pubQuery";
    }

    public PublishingForm getForm()
    {
        return form;
    }

    public void setForm(PublishingForm form)
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