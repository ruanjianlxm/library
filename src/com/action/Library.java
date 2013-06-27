// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2012/12/25 15:28:18
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Library.java

package com.action;

import com.actionForm.LibraryForm;
import com.dao.LibraryDAO;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

public class Library extends ActionSupport
    implements ServletRequestAware
{
	LibraryDAO libraryDAO;
    private LibraryForm form;
    private HttpSession session;
    private HttpServletRequest request;

    public Library()
    {
        libraryDAO = null;
        libraryDAO = new LibraryDAO();
    }

    public String execute()
    {
        LibraryForm libraryForm = form;
        String str = request.getParameter("action");
        if("libraryQuery".equals(str))
            return libraryModifyQuery();
        if("libraryModify".equals(str))
        {
            return libraryModify();
        } else
        {
            request.setAttribute("error", "\u60A8\u7684\u64CD\u4F5C\u6709\u8BEF\uFF01");
            return "error";
        }
    }

    public String libraryModify()
    {     LibraryForm libraryForm = form;
    	if(!libraryForm.getEmail().matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$")){
    		 request.setAttribute("error", "对不起，您的邮箱输入错了");
             return "error";
    		
    	}
    	
    	if(!libraryForm.getUrl().matches("[a-zA-z]+://[^s]*")){
   		 request.setAttribute("error", "对不起，您的URL网址输入错了");
            return "error";
   		
   	}
   	
    	
   
        libraryForm.setId(libraryForm.getId());
        libraryForm.setLibraryname(libraryForm.getLibraryname());
        libraryForm.setCurator(libraryForm.getCurator());
        libraryForm.setTel(libraryForm.getTel());
        libraryForm.setAddress(libraryForm.getAddress());
        libraryForm.setEmail(libraryForm.getEmail());
        libraryForm.setUrl(libraryForm.getUrl());
        libraryForm.setCreateDate(libraryForm.getCreateDate());
        libraryForm.setIntroduce(libraryForm.getIntroduce());
        int ret = libraryDAO.update(libraryForm);
        if(ret == 0)
        {
            request.setAttribute("error", "\u56FE\u4E66\u9986\u4FE1\u606F\u4FEE\u6539\u5931\u8D25\uFF01");
            return "error";
        } else
        {
            return "librarymodify";
        }
        
        
    }

    public String libraryModifyQuery()
    {
        request.setAttribute("libraryModifyif", libraryDAO.query());
        return "librarymodifyQuery";
    }

    public LibraryForm getForm()
    {
        return form;
    }

    public void setForm(LibraryForm form)
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