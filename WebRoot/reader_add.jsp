<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.dao.ReaderDAO" %>
<%@ page import="com.dao.ReaderTypeDAO" %>
<%@ page import="com.actionForm.ReaderForm" %>
<%@ page import="com.actionForm.ReaderTypeForm"%>
<%@ page import="java.util.*"%>
<html>
<%ReaderForm form = new ReaderForm();
String str=null;
ReaderTypeDAO readerTypeDAO=new ReaderTypeDAO();
Collection coll=(Collection)readerTypeDAO.query(str);
if(coll==null || coll.isEmpty()){
	out.println("<script>alert('请先录入读者类型信息!');history.back(-1);</script>");
}else{
	  Iterator it=coll.iterator();
	  int ID=0;
	  String name="";
%>
<script language="jscript">
function check(form){
	if(form.name.value==""){
		alert("请输入读者姓名!");form.name.focus();return false;
	}
	if(form.barcode.value==""){
		alert("请输入条形码!");form.barcode.focus();return false;
	}
	if(form.paperNO.value==""){
		alert("请输入证件号码!");form.paperNO.focus();return false;
	}
}
</script>
<head>
<title>图书馆管理系统</title>
<link href="CSS/style.css" rel="stylesheet">
</head>
<body onLoad="clockon(bgclock)">
<%--@include file="banner.jsp"--%>
<%@include file="banner.jsp"%>
<%@include file="navigation.jsp"%>
<table width="778"  border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="99%" height="510"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="tableBorder_gray">
  <tr>
    <td height="510" valign="top" style="padding:5px;"><table width="98%" height="487"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="22" valign="top" class="word_orange">当前位置：读者管理 &gt; 读者信息管理 &gt; 添加读者信息 &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td align="center" valign="top"><table width="100%" height="493"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" valign="top">
	<form name="form1" method="post" action="reader?action=readerAdd">
	<table width="600" height="432"  border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
      <tr>
        <td width="173" align="center">姓名：</td>
        <td width="427" height="39">
          <input name="form.Name" type="text"> 
          *         </td>
      </tr>
      <tr>
        <td width="173" align="center">性别：</td>
        <td height="35"><input name="form.Sex" type="radio" class="noborder" id="radiobutton" value="男" checked>
          <label for="radiobutton">男 </label>
          <label>
          <input name="form.Sex" type="radio" class="noborder" value="女">
          女</label></td>
      </tr>
      <tr>
        <td align="center">条形码：</td>
        <td><input name="form.Barcode" type="text" id="barcode">
* </td>
      </tr>
      <tr>
        <td align="center">读者身份：</td>
        <td>
		<select name="form.Typeid" class="wenbenkuang" id="typeid">
<%
  while(it.hasNext()){
    ReaderTypeForm readerTypeForm=(ReaderTypeForm)it.next();
	ID=readerTypeForm.getId().intValue();
	name=readerTypeForm.getName();
	%> 		
				
          <option value="<%=ID%>"><%=name%></option>
<%}%> 
        </select>        </td>
      </tr>
      <tr>
        <td align="center">职业：</td>
        <td><input name="form.Vocation" type="text" id="vocation"></td>
      </tr>
      <tr>
        <td align="center">出生日期：</td>
        <td><input name="form.Birthday" type="text" id="birthday">*</td>
      </tr>
      <tr>
        <td align="center">有效证件：</td>
        <td><select name="form.PaperType" class="wenbenkuang" id="paperType">
          <option value="身份证" selected>身份证</option>
          <option value="学生证">学生证</option>
          
                        </select></td>
      </tr>
      <tr>
        <td align="center">证件号码：</td>
        <td><input name="form.PaperNO" type="text" id="paperNO"> 
          * </td>
      </tr>
      <tr>
        <td align="center">电话：</td>
        <td><input name="form.Tel" type="text" id="tel"></td>
      </tr>
      <tr>
        <td align="center">Email：</td>
        <td><input name="form.Email" type="text" id="email" size="50">
          <input name="form.Operator" type="hidden" id="operator" value="<%=manager%>"></td>
      </tr>
      <tr>
        <td align="center">备注：</td>
        <td><textarea name="form.Remark" cols="50" rows="5" class="wenbenkuang" id="remark"></textarea></td>
      </tr>
      <tr>
        <td align="center">&nbsp;</td>
        <td><input name="Submit" type="submit" class="btn_grey" value="保存" onClick="return check(form1)">
&nbsp;
<input name="Submit2" type="button" class="btn_grey" value="返回" onClick="history.back()"></td>
      </tr>
    </table>
	</form>
	</td>
  </tr>
</table></td>
      </tr>
    </table>
</td>
  </tr>
</table><%@ include file="copyright.jsp"%></td>
  </tr>
</table>
<%}%>
</body>
</html>
