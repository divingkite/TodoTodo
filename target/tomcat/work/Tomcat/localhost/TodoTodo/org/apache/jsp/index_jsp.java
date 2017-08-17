/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-08-17 10:42:06 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("    <title>New Todo App</title>\n");
      out.write("    <script type=\"application/javascript\" src=\"index.js\"></script>\n");
      out.write("    <link rel=\"stylesheet\" href=\"index.css\" />\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<!-- Large modal -->\n");
      out.write("\n");
      out.write("<div>\n");
      out.write("    <div class=\"modal-dialog modal-lg\">\n");
      out.write("        <div class=\"modal-content\">\n");
      out.write("            <div class=\"modal-header\">\n");
      out.write("                <h4 class=\"modal-title\" id=\"myModalLabel\">\n");
      out.write("                    Login/Registration </h4>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-body\">\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div class=\"col-md-8\" style=\"border-right: 1px dotted #C2C2C2;padding-right: 30px;\">\n");
      out.write("                        <!-- Nav tabs -->\n");
      out.write("                        <ul class=\"nav nav-tabs\">\n");
      out.write("                            <li class=\"active\"><a href=\"#Login\" data-toggle=\"tab\">Login</a></li>\n");
      out.write("                            <li><a href=\"#Registration\" data-toggle=\"tab\">Registration</a></li>\n");
      out.write("                        </ul>\n");
      out.write("                        <!-- Tab panes -->\n");
      out.write("                        <div class=\"tab-content\">\n");
      out.write("                            <div class=\"tab-pane active\" id=\"Login\">\n");
      out.write("                                <form role=\"form\" class=\"form-horizontal\" action=\"/TodoTodo/login\" method=\"post\">\n");
      out.write("                                    <div class=\"form-group\">\n");
      out.write("                                        <label class=\"col-sm-2 control-label\">\n");
      out.write("                                            Username</label>\n");
      out.write("                                        <div class=\"col-sm-10\">\n");
      out.write("                                            <input type=\"text\" class=\"form-control\" name=\"loginUserName\" placeholder=\"username\" required />\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"form-group\">\n");
      out.write("                                        <label for=\"loginPassword\" class=\"col-sm-2 control-label\">\n");
      out.write("                                            Password</label>\n");
      out.write("                                        <div class=\"col-sm-10\">\n");
      out.write("                                            <input type=\"password\" class=\"form-control\" name=\"loginPassword\" placeholder=\"Password\" required />\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"row\">\n");
      out.write("                                        <div class=\"col-sm-2\">\n");
      out.write("                                        </div>\n");
      out.write("                                        <div class=\"col-sm-10\">\n");
      out.write("                                            <input type=\"submit\" value=\"Submit\" class=\"btn btn-primary btn-sm\">\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                </form>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"tab-pane\" id=\"Registration\">\n");
      out.write("                                <form role=\"form\" class=\"form-horizontal\" action=\"/TodoTodo/register\" method=\"post\">\n");
      out.write("                                    <div class=\"form-group\">\n");
      out.write("                                        <label for=\"registerEmail\" class=\"col-sm-2 control-label\">\n");
      out.write("                                            Name</label>\n");
      out.write("                                        <div class=\"col-sm-10\">\n");
      out.write("                                            <div class=\"row\">\n");
      out.write("                                                <div class=\"col-md-3\">\n");
      out.write("                                                    <select class=\"form-control\">\n");
      out.write("                                                        <option>Mr.</option>\n");
      out.write("                                                        <option>Ms.</option>\n");
      out.write("                                                        <option>Mrs.</option>\n");
      out.write("                                                    </select>\n");
      out.write("                                                </div>\n");
      out.write("                                                <div class=\"col-md-9\">\n");
      out.write("                                                    <input type=\"text\" class=\"form-control\" placeholder=\"Name\" name=\"registerName\" required />\n");
      out.write("                                                </div>\n");
      out.write("                                            </div>\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"form-group\">\n");
      out.write("                                        <label for=\"registerEmail\" class=\"col-sm-2 control-label\">\n");
      out.write("                                            Email</label>\n");
      out.write("                                        <div class=\"col-sm-10\">\n");
      out.write("                                            <input type=\"email\" class=\"form-control\" name=\"registerEmail\" placeholder=\"Email\" required />\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"form-group\">\n");
      out.write("                                        <label for=\"registerMobile\" class=\"col-sm-2 control-label\">\n");
      out.write("                                            Mobile</label>\n");
      out.write("                                        <div class=\"col-sm-10\">\n");
      out.write("                                            <input type=\"phone\" class=\"form-control\" name=\"registerPhone\" placeholder=\"Mobile\" required pattern=\".{10}\" />\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"form-group\">\n");
      out.write("                                        <label for=\"registerPassword\" class=\"col-sm-2 control-label\">\n");
      out.write("                                            Password</label>\n");
      out.write("                                        <div class=\"col-sm-10\">\n");
      out.write("                                            <input type=\"password\" class=\"form-control\" name=\"registerPassword\" placeholder=\"Password\" required />\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"row\">\n");
      out.write("                                        <div class=\"col-sm-2\">\n");
      out.write("                                        </div>\n");
      out.write("                                        <div class=\"col-sm-10\">\n");
      out.write("                                            <input type=\"submit\" value=\"Submit\" class=\"btn btn-primary btn-sm\">\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                </form>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("<!-- Latest compiled and minified CSS -->\n");
      out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n");
      out.write("\n");
      out.write("<!-- jQuery library -->\n");
      out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n");
      out.write("\n");
      out.write("<!-- Latest compiled JavaScript -->\n");
      out.write("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
