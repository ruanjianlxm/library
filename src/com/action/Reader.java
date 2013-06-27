// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2012/12/25 15:30:30
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Reader.java

package com.action;

import com.actionForm.ReaderForm;
import com.dao.ReaderDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintStream;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

public class Reader extends ActionSupport
    implements ServletRequestAware
{
	private ReaderDAO readerDAO;
    private ReaderForm form;
    private HttpSession session;
    private HttpServletRequest request;

    public Reader()
    {
        readerDAO = null;
        readerDAO = new ReaderDAO();
    }

    public String execute()
    {
        String action = request.getParameter("action");
         if(action == null || "".equals(action))
        {
            request.setAttribute("error", "读者出错了");
            return "error";
        }
        if("readerAdd".equals(action))
            return readerAdd();
        if("readerQuery".equals(action))
            return readerQuery();
        if("readerModifyQuery".equals(action))
            return readerModifyQuery();
        if("readerModify".equals(action))
            return readerModify();
        if("readerDel".equals(action))
            return readerDel();
        if("readerDetail".equals(action))
        {
            return readerDetail();
        } else
        {
            request.setAttribute("error", "请查看你的信息");
            return "error";
        }
    }

    private String readerAdd()
    {
        ReaderForm readerForm = form;
        readerForm.setName(readerForm.getName());
        readerForm.setSex(readerForm.getSex());
        readerForm.setBarcode(readerForm.getBarcode());
        readerForm.setVocation(readerForm.getVocation());
        readerForm.setBirthday(readerForm.getBirthday());
        readerForm.setPaperType(readerForm.getPaperType());
        readerForm.setPaperNO(readerForm.getPaperNO());
        readerForm.setTel(readerForm.getTel());
        readerForm.setEmail(readerForm.getEmail());
        Date date1 = new Date();
        java.sql.Date date = new java.sql.Date(date1.getTime());
        readerForm.setCreateDate(date.toString());
        readerForm.setOperator(readerForm.getOperator());
        readerForm.setRemark(readerForm.getRemark());
        readerForm.setTypeid(readerForm.getTypeid());
        int a = readerDAO.insert(readerForm);
        if(a == 2)
        {
            request.setAttribute("error", "该读者已存在");
            return "error";
        }
        if(a == 0)
        {
            request.setAttribute("error", "没有这个读者");
            return "error";
        } else
        {
            return "readerAdd";
        }
    }

    private String readerQuery()
    {
        String str = null;
        request.setAttribute("reader", readerDAO.query(str));
        return "readerQuery";
    }

    private String readerModifyQuery()
    {
        ReaderForm readerForm = new ReaderForm();
         readerForm.setId(Integer.valueOf(request.getParameter("ID")));
        request.setAttribute("readerQueryif", readerDAO.queryM(readerForm));
        return "readerQueryModify";
    }

    private String readerDetail()
    {
        ReaderForm readerForm = new ReaderForm();
        readerForm.setId(Integer.valueOf(request.getParameter("ID")));
        request.setAttribute("readerDetail", readerDAO.queryM(readerForm));
        return "readerDeatil";
    }

    private String readerModify()
    {
        ReaderForm readerForm = form;
        readerForm.setName(readerForm.getName());
        readerForm.setSex(readerForm.getSex());
        readerForm.setBarcode(readerForm.getBarcode());
        readerForm.setVocation(readerForm.getVocation());
        readerForm.setBirthday(readerForm.getBirthday());
        readerForm.setPaperType(readerForm.getPaperType());
        readerForm.setPaperNO(readerForm.getPaperNO());
        readerForm.setTel(readerForm.getTel());
        readerForm.setEmail(readerForm.getEmail());
        readerForm.setOperator(readerForm.getOperator());
        readerForm.setRemark(readerForm.getRemark());
        readerForm.setTypeid(readerForm.getTypeid());
        int ret = readerDAO.update(readerForm);
        if(ret == 0)
        {
            request.setAttribute("error", "日期格式出错了1992-12-13");
            return "error";
        } else
        {
            return "readerModify";
        }
    }

    private String readerDel()
    {
        ReaderForm readerForm = new ReaderForm();
        readerForm.setId(Integer.valueOf(request.getParameter("ID")));
        int ret = readerDAO.delete(readerForm);
        if(ret == 0)
        {
            request.setAttribute("error", "没有这个读者");
            return "error";
        } else
        {
            return "readerDel";
        }
    }

    public ReaderForm getForm()
    {
        return form;
    }

    public void setForm(ReaderForm form)
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