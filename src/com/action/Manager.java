// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2012/12/25 15:28:47
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Manager.java

package com.action;

import com.actionForm.ManagerForm;
import com.dao.ManagerDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

public class Manager extends ActionSupport
    implements ServletRequestAware
{
	  private ManagerDAO managerDAO;
	    private ManagerForm form;
	    private HttpSession session;
	    private HttpServletRequest request;

    public Manager()
    {
        managerDAO = null;
        managerDAO = new ManagerDAO();
    }

    public String execute()
    {
        String action = request.getParameter("action");
         if(action == null || "".equals(action))
            return "error";
        if("login".equals(action))
            return managerLogin();
        if("managerAdd".equals(action))
            return managerAdd();
        if("managerQuery".equals(action))
            return managerQuery();
        if("managerModifyQuery".equals(action))
            return managerModifyQuery();
        if("managerModify".equals(action))
            return managerModify();
        if("managerDel".equals(action))
            return managerDel();
        if("querypwd".equals(action))
            return pwdQuery();
        if("modifypwd".equals(action))
        {
            return modifypwd();
        } else
        {
            request.setAttribute("error", "密码出错");
            return "error";
        }
    }

    public String managerLogin()
    {
        ManagerForm managerForm = form;
        managerForm.setName(managerForm.getName());
        managerForm.setPwd(managerForm.getPwd());
          int ret = managerDAO.checkManager(managerForm);
         if(ret == 1)
        {
            HttpSession session = request.getSession();
            session.setAttribute("manager", managerForm.getName());
            ManagerForm form1 = managerDAO.query_p(managerForm.getName());
            session.setAttribute("managerParam", form1);
            return "managerLoginok";
        } else
        {
            request.setAttribute("error", "管理员出错，登陆失败");
            return "error";
        }
    }

    public String managerQuery()
    {
        String str = null;
        request.setAttribute("managerQuery", managerDAO.query(str));
        return "managerQuery";
    }

    public String managerAdd()
    {
        ManagerForm managerForm = form;
        managerForm.setName(managerForm.getName());
        managerForm.setPwd(managerForm.getPwd());
        int ret = managerDAO.insert(managerForm);
        if(ret == 1)
            return "managerAdd";
        if(ret == 2)
        {
            request.setAttribute("error", "管理员已存在");
            return "error";
        } else
        {
            request.setAttribute("error", "添加失败了");
            return "error";
        }
    }

    public String managerModifyQuery()
    {
        ManagerForm managerForm = new ManagerForm();
        managerForm.setId(Integer.valueOf(request.getParameter("id")));
           request.setAttribute("managerQueryif", managerDAO.query_update(managerForm));
        return "managerQueryModify";
    }

    public String pwdQuery()
    {
        ManagerForm managerForm = new ManagerForm();
        HttpSession session = request.getSession();
        String manager = (String)session.getAttribute("manager");
        managerForm.setName(manager);
           request.setAttribute("pwdQueryif", managerDAO.query_pwd(managerForm));
        return "pwdQueryModify";
    }

    public String managerModify()
    {
        ManagerForm managerForm = form;
        managerForm.setId(managerForm.getId());
        managerForm.setName(managerForm.getName());
        managerForm.setPwd(managerForm.getPwd());
        managerForm.setSysset(managerForm.getSysset());
        managerForm.setReaderset(managerForm.getReaderset());
        managerForm.setBookset(managerForm.getBookset());
        managerForm.setBorrowback(managerForm.getBorrowback());
        managerForm.setSysquery(managerForm.getSysquery());
        int ret = managerDAO.update(managerForm);
        if(ret == 0)
        {
            request.setAttribute("error", "管理员权限更改失败");
            return "error";
        } else
        {
            return "managerModify";
        }
    }

    public String managerDel()
    {
        ManagerForm managerForm = new ManagerForm();
        managerForm.setId(Integer.valueOf(request.getParameter("id")));
        int ret = managerDAO.delete(managerForm);
        if(ret == 0)
        {
            request.setAttribute("error", "管理员不存在");
            return "error";
        } else
        {
            return "managerDel";
        }
    }

    public String modifypwd()
    {
        ManagerForm managerForm = form;
        managerForm.setName(managerForm.getName());
        managerForm.setPwd(managerForm.getPwd());
        int ret = managerDAO.updatePwd(managerForm);
        if(ret == 0)
        {
            request.setAttribute("error", "失败了");
            return "error";
        } else
        {
            return "pwdModify";
        }
    }

    public ManagerForm getForm()
    {
        return form;
    }

    public void setForm(ManagerForm form)
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