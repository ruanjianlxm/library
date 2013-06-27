// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2012/12/25 15:29:20
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Parameter.java

package com.action;

import com.actionForm.ParameterForm;
import com.dao.ParameterDAO;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

public class Parameter extends ActionSupport
    implements ServletRequestAware
{
	ParameterDAO parameterDAO;
    private ParameterForm form;
    private HttpSession session;
    private HttpServletRequest request;
    public Parameter()
    {
        parameterDAO = null;
        parameterDAO = new ParameterDAO();
    }

    public String execute()
    {
        ParameterForm parameterForm = form;
        String str = request.getParameter("action");
        if("parameterQuery".equals(str))
            return parameterModifyQuery();
        if("parameterModify".equals(str))
        {
            return parameterModify();
        } else
        {
            request.setAttribute("error", "失败了哦，检查一下吧");
            return "error";
        }
    }

    public String parameterModify()
    {
        ParameterForm parameterForm = form;
        parameterForm.setId(parameterForm.getId());
        parameterForm.setCost(parameterForm.getCost());
        parameterForm.setValidity(parameterForm.getValidity());
        int ret = parameterDAO.update(parameterForm);
        if(ret == 0)
        {
            request.setAttribute("error", "出错了");
            return "error";
        } else
        {
            return "parametermodify";
        }
    }

    public String parameterModifyQuery()
    {
        request.setAttribute("parameterModifyif", parameterDAO.query());
        return "parametermodifyQuery";
    }

    public ParameterForm getForm()
    {
        return form;
    }

    public void setForm(ParameterForm form)
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