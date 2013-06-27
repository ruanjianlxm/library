<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.core.ChStr" %>
<%
ChStr chStr=new ChStr();
String manager=(String)session.getAttribute("manager");
//验证用户是否登录
if (manager==null || "".equals(manager)){
	response.sendRedirect("login.jsp");
}
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<table width="778" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="118" valign="top" background="Images/top_bg.gif" bgcolor="#EEEEEE"><table width="100%" height="33"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="81%" height="10"></td>
        <td colspan="2"></td>
      </tr>
      <tr>
        <td height="20">&nbsp;</td>
        </tr>
    </table>
      <table width="93%" height="79"  border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="69" align="right" valign="bottom">当前登录用户：<%=chStr.toChinese(manager)%></td>
        </tr>
    </table></td>
  </tr>
</table>
