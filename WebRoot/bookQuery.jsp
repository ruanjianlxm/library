<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.dao.BookDAO" %>
<%@ page import="com.actionForm.BookForm" %>
<%@ page import="java.util.*"%>
<html>
<%
Collection coll=(Collection)request.getAttribute("ifbook");
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
    <td height="510" valign="top" style="padding:5px;"><table width="98%" height="487"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="22" valign="top" class="word_orange">当前位置：系统查询 &gt; 图书信息查询 &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td align="center" valign="top">
	<form action="" method="post" name="form1">  
 <table width="98%" height="38"  border="0" cellpadding="0" cellspacing="0" bgcolor="#E3F4F7" class="tableBorder_gray">
  <tr>
    <td align="center" bgcolor="#2af802">

&nbsp;<img src="Images/search.gif" width="45" height="28"></td>
    <td bgcolor="#2af802">请选择查询依据：
      <select name="f" class="wenbenkuang" id="f">
        <option value="barcode">图书编码</option>
        <option value="typename">类别</option>
        <option value="bookname" selected>书名</option>
        <option value="author">作者</option>
        <option value="publishing">出版社</option>
       
                  </select>
      <input name="key" type="text" id="key" size="50">
      <input name="Submit" type="submit" class="btn_grey" value="查询"></td>
  </tr>
</table>
<%
if(coll==null || coll.isEmpty()){
%>
          <table width="100%" height="30"  border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="36" align="center">暂无图书信息！</td>
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
  <table width="98%"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#F6B83B" bordercolorlight="#FFFFFF">
  <tr align="center" bgcolor="#e3F4F7">
    <td width="17%" bgcolor="#2af802">条形码</td>  
    <td width="31%" bgcolor="#2af802">图书名称</td>
    <td width="18%" bgcolor="#2af802">图书类型</td>
    <td width="19%" bgcolor="#2af802">出版社</td>
    </tr>
<%
  while(it.hasNext()){
    BookForm bookForm=(BookForm)it.next();
	ID=bookForm.getId().intValue();
	bookname=bookForm.getBookName();
	barcode=bookForm.getBarcode();
	if(barcode==null) barcode="";
	typename=bookForm.getTypeName();
	publishing=bookForm.getPublishing();
	%> 
  <tr>
    <td style="padding:5px;">&nbsp;<%=barcode%></td>  
    <td style="padding:5px;"><a href="book?action=bookDetail&form.Id=<%=ID%>"><%=bookname%></a></td>
    <td style="padding:5px;">&nbsp;<%=typename%></td>  
    <td style="padding:5px;">&nbsp;<%=publishing%></td>  
    </tr>
<%
  }
}
%>  
</table>
	</form>
</td>
      </tr>
    </table>
</td>
  </tr>
</table><%@ include file="copyright.jsp"%></td>
  </tr>
</table>
</body>
</html>
