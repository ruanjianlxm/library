<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.dao.ManagerDAO"%>
<%@ page import="com.actionForm.ManagerForm"%>
<%@ page import="com.core.ChStr" %>
<%
	ManagerDAO managerDAO=new ManagerDAO();
	String strManager=(String)session.getAttribute("manager");
	ManagerForm form1=(ManagerForm)managerDAO.query_p(strManager);
	int sysset1 = 0;
	int readerset1 = 0;
	int bookset1 = 0;
	int borrowback1 = 0;
	int sysquery1 = 0;
	if (form1 != null) {
		sysset1 = form1.getSysset();
		readerset1 = form1.getReaderset();
		bookset1 = form1.getBookset();
		borrowback1 = form1.getBorrowback();
		sysquery1 = form1.getSysquery();
	}
%>
<%--<tr><td id=bookcase onMouseOver=overbg(bookcase) onMouseOut=outbg(bookcase)><a href=bookCase?action=bookCaseQuery>出版社设置</a></td></tr>\
			 --%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="JS/onclock.JS"></script>
<script src="JS/menu.JS"></script>
<div class=menuskin id=popmenu
      onmouseover="clearhidemenu();highlightmenu(event,'on')"
      onmouseout="highlightmenu(event,'off');dynamichide(event)" style="Z-index:100;position:absolute;"></div>
<table width="778"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
      <tr bgcolor="#red">
        <td width="3%" height="27">&nbsp;</td>
        <td width="29%"><div id="bgclock" class="word_white"></div></td>
		<script language="javascript">
			function quit(){
				if(confirm("真的要退出系统吗?")){
					window.location.href="logout.jsp";
				}
			}

			var arr_week=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
			
			var sysmenu='<table width=80><tr><td id=library onMouseOver=overbg(library) onMouseOut=outbg(library)><a href=library?action=libraryQuery>图书馆信息设置</a></td></tr>\
				<tr><td id=manager onMouseOver=overbg(manager) onMouseOut=outbg(manager)><a href=manager?action=managerQuery>管理员设置</a></td></tr>\
				<tr><td id=bookcase onMouseOver=overbg(bookcase) onMouseOut=outbg(bookcase)><a href=#>新功能等待开放</a></td></tr>\
				<tr><td id=para onMouseOver=overbg(para) onMouseOut=outbg(para)><a href=#>新功能等待开放</a></td></tr>\
				</table>'

				var readermenu='<table width=90><tr><td id=readerType onMouseOver=overbg(readerType) onMouseOut=outbg(readerType)><a href=readerType?action=readerTypeQuery>读者类型管理</a></td></tr>\
				<tr><td id=reader onMouseOver=overbg(reader) onMouseOut=outbg(reader)><a href=reader?action=readerQuery>读者管理</a></td></tr>\
				</table>'

				var bookmenu='<table width=90><tr><td id=bookType onMouseOver=overbg(bookType) onMouseOut=outbg(bookType)><a href=bookType?action=bookTypeQuery>图书类型设置</a></td></tr>\
				<tr><td id=book onMouseOver=overbg(book) onMouseOut=outbg(book)><a href=book?action=bookQuery>图书管理</a></td></tr>\
				</table>'

				var borrowmenu='<table width=60><tr><td id=Borrow onMouseOver=overbg(Borrow) onMouseOut=outbg(Borrow)><a href=bookBorrow.jsp>图书借阅</a></td></tr>\
				<tr><td id=renew onMouseOver=overbg(renew) onMouseOut=outbg(renew)><a href=#>未开发功能</a></td></tr>\
				<tr><td id=giveback onMouseOver=overbg(giveback) onMouseOut=outbg(giveback)><a href=bookBack.jsp>还书</a></td></tr>\
				</table>'

				var querymenu='<table width=90><tr><td id=bookQuery onMouseOver=overbg(bookQuery) onMouseOut=outbg(bookQuery)><a href=book?action=bookifQuery>借书记录查询</a></td></tr>\
				<tr><td id=borrowQuery onMouseOver=overbg(borrowQuery) onMouseOut=outbg(borrowQuery)><a href=borrow?action=borrowQuery>借书记录查询</a></td></tr>\
				<tr><td id=givebackQuery onMouseOver=overbg(givebackQuery) onMouseOut=outbg(givebackQuery)><a href=#>未开发功能</a></td></tr>\
				</table>'
		</script>
        <td width="66%" align="right" bgcolor="blue" class="word_white"><a href="main.jsp" class="word_white">首页</a>
			|
			<%
				if (sysset1 == 1) {
			%><a  onmouseover=showmenu(event,sysmenu) onmouseout=delayhidemenu() class="word_white" style="CURSOR:hand" >图书馆设置</a> | <%
				}
			%><%
				if (readerset1 == 1) {
			%><a  onmouseover=showmenu(event,readermenu) onmouseout=delayhidemenu() style="CURSOR:hand"  class="word_white">管理成员</a> | <%
				}
			%><%
				if (bookset1 == 1) {
			%><a  onmouseover=showmenu(event,bookmenu) onmouseout=delayhidemenu() class="word_white" style="CURSOR:hand" >图书管理</a> | <%
				}
			%><%
				if (borrowback1 == 1) {
			%><a  onmouseover=showmenu(event,borrowmenu) onmouseout=delayhidemenu() class="word_white" style="CURSOR:hand">借还书处理</a> | <%
				}
			%><%
				if (sysquery1 == 1) {
			%><a  onmouseover=showmenu(event,querymenu) onmouseout=delayhidemenu()  class="word_white" style="CURSOR:hand" >记录查询</a> | <%
				}
			%><a  href="manager?action=querypwd" class="word_white">更改密码</a> | <a href="#" onClick="quit()" class="word_white">我要退出</a></td>
        <td width="2%" bgcolor="#B0690B">&nbsp;</td>
  </tr>
      <tr bgcolor="#DFA40C">
        <td height="9" colspan="4" background="Images/navigation_bg_bottom.gif"></td>
      </tr>
</table>
