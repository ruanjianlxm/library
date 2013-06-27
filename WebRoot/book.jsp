<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.dao.BookDAO" %>
<%@ page import="com.actionForm.BookForm" %>
<%@ page import="java.util.*"%>
<html>
<%
Collection coll=(Collection)request.getAttribute("book");
%>
<head>
<title>图书馆管理系统</title>
<link href="CSS/style.css" rel="stylesheet">
</head>
<body onLoad="clockon(bgclock)">
<%@include file="banner.jsp"%>
<%@include file="navigation.jsp"%>
<table width="778"  border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="99%" height="510"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="tableBorder_gray">
  <tr>
    <td height="510" valign="top" style="padding:5px;"><table width="98%"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="22" valign="top" class="word_orange">当前位置：图书管理 &gt; 图书信息管理 &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td align="center" valign="top"><%
if(coll==null || coll.isEmpty()){
%>
          <table width="100%" height="30"  border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="36" align="center">没有图书信息！</td>
            </tr>
          </table>
          <table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
      <a href="book_add.jsp">添加图书信息</a> </td>
  </tr>
</table>
 <%
}else{
  //通过迭代方式显示数据
  Iterator it=coll.iterator();
  int ID=0;
  String bookname="";
  String barcode="";
  String typename="";
  String publishing="";
  String bookcase="";
  int storage=0;
  %>
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="87%">&nbsp;      </td>
<td width="13%">
      <a href="book_add.jsp">添加图书信息</a></td>	  
  </tr>
</table>  
  <table width="98%"  border="1" cellpadding="0" cellspacing="0" bordercolor="#0e9efa" bordercolordark="#F6B83B" bordercolorlight="#FFFFFF">
  <tr align="center" bgcolor="#e3F4F7">
    <td width="13%" bgcolor="#0e9efa">图书码</td>  
    <td width="26%" bgcolor="#0e9efa">图书名称</td>
    <td width="15%" bgcolor="#0e9efa">图书类型</td>
    <td width="14%" bgcolor="#0e9efa">出版社</td>
   
    <td width="9%" bgcolor="#0e9efa">修改</td>
    <td width="5%" bgcolor="#0e9efa">删除</td>
  </tr>
<%
  while(it.hasNext()){
    BookForm bookForm=(BookForm)it.next();
	ID=bookForm.getId().intValue();
	bookname=bookForm.getBookName();
	barcode=chStr.nullToString(bookForm.getBarcode(),"&nbsp;");
	typename=bookForm.getTypeName();
	publishing=bookForm.getPublishing();
	%> 
  <tr>
    <td style="padding:5px;">&nbsp;<%=barcode%></td>  
    <td style="padding:5px;"><a href="book?action=bookDetail&form.Id=<%=ID%>"><%=bookname%></a></td>
    <td style="padding:5px;">&nbsp;<%=typename%></td>  
    <td style="padding:5px;">&nbsp;<%=publishing%></td>  
	
    <td align="center"><a href="book?action=bookModifyQuery&form.Id=<%=ID%>">修改</a></td>
    <td align="center"><a href="book?action=bookDel&form.Id=<%=ID%>">删除</a></td>
  </tr>
<%
  }
}
%>  
</table></td>
      </tr>
    </table>
</td>
  </tr>
</table><%@ include file="copyright.jsp"%></td>
  </tr>
</table>
</body>
</html>
