/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.20
 * Generated at: 2019-06-17 04:41:38 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Dto.MovieDTO;
import Dao.ReserveDAO;
import java.util.ArrayList;

public final class movieseat_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("Dao.ReserveDAO");
    _jspx_imports_classes.add("Dto.MovieDTO");
    _jspx_imports_classes.add("java.util.ArrayList");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP?????? ?????? GET, POST ?????? HEAD ??????????????? ???????????????. Jasper??? OPTIONS ????????? ?????? ???????????????.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=EUC-KR");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"EUC-KR\">\r\n");
      out.write("<title>?????? ??????</title>\r\n");

	String id = (String) session.getAttribute("id");
	String pw = (String) session.getAttribute("pw");
	ReserveDAO movieDAO = new ReserveDAO();

	request.setCharacterEncoding("UTF-8");
	String userID = id;
	String theater = request.getParameter("theater");
	String m_No = request.getParameter("m_No");
	String r_Date = request.getParameter("r_Date");
	String r_Time = request.getParameter("r_Time");
	String r_No;

      out.write("\r\n");
      out.write("<style>\r\n");
      out.write("a {\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("input {\r\n");
      out.write("\tfont-weight: bold;\r\n");
      out.write("\tfont-size: 20px;\r\n");
      out.write("\tpadding: 10px 20px;\r\n");
      out.write("\tRadius: 20px;\r\n");
      out.write("\tcolor: #353F48;\r\n");
      out.write("\tbackground-color: #FFE699;\r\n");
      out.write("\tborder: solid 2px #C2722E;\r\n");
      out.write("\tborder-radius: 10px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("font {\r\n");
      out.write("\tcolor: #353F48;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<table border=1 align=\"center\">\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td rowspan=4><a href=\"Main.jsp\"><img src=\"Image\\Logo.png\"\r\n");
      out.write("\t\t\t\t\twidth=\"150px\" height=\"150px\" align=\"center\"></a></img></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t<tr align=\"center\">\r\n");
      out.write("\t\t\t<td colspan=\"2\" height=\"50px\" width = \"600px\"><font size=\"150\"\r\n");
      out.write("\t\t\t\tface=\"Arial Black\">MOVIE</font></td>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t");

				if (id == null && pw == null) {
			
      out.write("\r\n");
      out.write("\t\t\t<td height=\"80px\"  width = \"600px\" colspan=2><b> <a href=\"Login.html\">?????????\r\n");
      out.write("\t\t\t\t</a> | <a href=\"Join.html\">????????????</a></b></td>\r\n");
      out.write("\t\t\t");

				} else {
			
      out.write("\r\n");
      out.write("\t\t\t<td height=\"80px\"  width = \"600px\" colspan=2><b> <a href=\"Logout.jsp\">????????????\r\n");
      out.write("\t\t\t\t</a> | <a href=\"ModifyForm.do?id=");
      out.print(id);
      out.write("\">???????????? ??????</a></b></td>\r\n");
      out.write("\t\t\t");

				}
			
      out.write("\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t<tr align=\"center\">\r\n");
      out.write("\t\t\t<td bgcolor=\"#FFD966\" width=\"100px\"><a href=\"Movies.do\">??????</a></td>\r\n");
      out.write("\t\t\t<td bgcolor=\"#FFD966\" width=\"100px\"><a href=\"moviechoice.jsp\">??????</a></td>\r\n");
      out.write("\t\t\t<td bgcolor=\"#FFD966\" width=\"100px\"><a href=\"MyPageView.do\">???????????????</td>\r\n");
      out.write("\t\t\t<td bgcolor=\"#FFD966\" width=\"100px\"><a href=\"list.do?pgNum=1\">???????????????</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t<br>\r\n");
      out.write("\t<br>\r\n");
      out.write("\t<form action=\"reserve.do\" method=\"post\">\r\n");
      out.write("\t\t<fieldset>\r\n");
      out.write("\t\t\t<legend>????????????</legend>\r\n");
      out.write("\t\t\t<center>\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"userID\" value=");
      out.print(userID);
      out.write("> <input\r\n");
      out.write("\t\t\t\t\ttype=\"hidden\" name=\"theater\" value=");
      out.print(theater);
      out.write("> <input\r\n");
      out.write("\t\t\t\t\ttype=\"hidden\" name=\"m_No\" value=");
      out.print(m_No);
      out.write("> <input\r\n");
      out.write("\t\t\t\t\ttype=\"hidden\" name=\"r_Date\" value=");
      out.print(r_Date);
      out.write("> <input\r\n");
      out.write("\t\t\t\t\ttype=\"hidden\" name=\"r_Time\" value=");
      out.print(r_Time);
      out.write("> <input\r\n");
      out.write("\t\t\t\t\ttype=\"hidden\" name=\"userID\" value=");
      out.print(id );
      out.write(">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<table border=\"0\" align=\"center\">\r\n");
      out.write("\t\t\t\t\t<th width=\"650\" height=\"30\" bgcolor=\"orange\">SCREEN</th>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t<br />\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<table border=\"0\">\r\n");
      out.write("\t\t\t\t\t<thead>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"50\" height=\"50\"></td>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"50\" height=\"50\" align=\"center\">1</td>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"50\" height=\"50\" align=\"center\">2</td>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"50\" height=\"50\" align=\"center\">3</td>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"50\" height=\"50\" align=\"center\">4</td>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"50\" height=\"50\" align=\"center\"></td>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"50\" height=\"50\" align=\"center\"></td>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"50\" height=\"50\" align=\"center\">5</td>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"50\" height=\"50\" align=\"center\">6</td>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"50\" height=\"50\" align=\"center\">7</td>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"50\" height=\"50\" align=\"center\">8</td>\r\n");
      out.write("\t\t\t\t\t</thead>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t");

							char col = 'A'; // ???
							char row = 'a'; // ???

							for (int i = 0; i < 5; i++) {
						
      out.write("\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"50\" height=\"50\">");
      out.print(col);
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t");

							int count = 1;
								for (int j = 1; j < 11; j++) {
									if (j == 5 || j == 6) {
						
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<td width=\"50\" height=\"50\" align=\"center / center\"></td>\r\n");
      out.write("\t\t\t\t\t\t");

							} else {
										if (1 == movieDAO.isTicket(theater + m_No + r_Date + r_Time + row + count)) {
						
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<td width=\"50\" height=\"50\" align=\"center / center\"><input\r\n");
      out.write("\t\t\t\t\t\t\tid=\"");
      out.print(row);
      out.print(count);
      out.write("\" type=\"checkbox\" disabled=\"disabled\"\r\n");
      out.write("\t\t\t\t\t\t\tname=\"seat\"\r\n");
      out.write("\t\t\t\t\t\t\tvalue=\"");
      out.print(theater);
      out.print(m_No);
      out.print(r_Date);
      out.print(r_Time);
      out.print(row);
      out.print(count);
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\t\tstyle=\"width: 90%; height: 90%;\"></td>\r\n");
      out.write("\t\t\t\t\t\t");

							} else if (0 == movieDAO.isTicket(theater + m_No + r_Date + r_Time + row + count)) {
						
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<td width=\"50\" height=\"50\" align=\"center / center\"><input\r\n");
      out.write("\t\t\t\t\t\t\tid=\"");
      out.print(row);
      out.print(count);
      out.write("\" type=\"checkbox\" name=\"seat\"\r\n");
      out.write("\t\t\t\t\t\t\tvalue=\"");
      out.print(theater);
      out.print(m_No);
      out.print(r_Date);
      out.print(r_Time);
      out.print(row);
      out.print(count);
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\t\tstyle=\"width: 90%; height: 90%;\"></td>\r\n");
      out.write("\t\t\t\t\t\t");

							}
						
      out.write("\r\n");
      out.write("\t\t\t\t\t\t");

							count++;
									}
						
      out.write("\r\n");
      out.write("\t\t\t\t\t\t");

							}
								col++;
								row++;
						
      out.write("\r\n");
      out.write("\t\t\t\t\t\t");

							}
						
      out.write("\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t</fieldset>\r\n");
      out.write("\t\t</center>\r\n");
      out.write("\t\t<br> <br>\r\n");
      out.write("\t\t<center>\r\n");
      out.write("\t\t\t");
if(userID!=null){
      out.write("<input type=\"submit\" value=\"????????????\">");
}
      out.write("\r\n");
      out.write("\t\t</center>\r\n");
      out.write("\t\t<br> <br>\r\n");
      out.write("\t</form>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
