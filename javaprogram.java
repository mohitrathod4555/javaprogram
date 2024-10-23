Index.html
-----------
<!DOCTYPE html>
<html>
<head>
<title>Servlet with JDBC</title>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-
scale=1.0">

</head>
<body>
<div>
<form name="form1" target="output">
<table>
<tr>
<td>Enter Rlno:</td><td><input type="text"
name="txtRlno"></td>
</tr>
<tr>
<td>Enter Name:</td><td><input type="text"
name="txtName"></td>
</tr>
<tr>
<td>Enter City: </td><td><input type="text"
name="txtCity"></td>
</tr>
</table>
<button type="submit"
formaction="/ServletDemo9/InsertData">Insert</button>
<button type="submit"
formaction="/ServletDemo9/UpdateData">Update</button>
<button type="submit"
formaction="/ServletDemo9/DeleteData">Delete</button>
<button type="submit"
formaction="/ServletDemo9/ViewData">View</button>
</form>

</div>
<iframe name="output"></iframe>
</body>
</html>
-------------------------------------------------------------
InsertData.java
-------------------------
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;
public class InsertData extends HttpServlet {
protected void processRequest(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
response.setContentType("text/html;charset=UTF-8");
try (PrintWriter out = response.getWriter()) {
out.println("<!DOCTYPE html>");
out.println("<html>");
out.println("<head>");
out.println("<title>Servlet InsertData</title>");
out.println("</head>");
out.println("<body>");
try{
Class.forName("com.mysql.jdbc.Driver");
Connection
con=DriverManager.getConnection("jdbc:mysql://localhost/College",
"root", "");
Statement st=con.createStatement();
String r,s,c;
r=request.getParameter("txtRlno");
s=request.getParameter("txtName");
c=request.getParameter("txtCity");
String qry="Insert into student
values("+r+",'"+s+"','"+c+"')";
//out.print(qry);
int rows = st.executeUpdate(qry);
out.println(rows+ "<b> Record(s) Inserted</b><br>");
st.close();
con.close();
}catch(Exception e){
e.printStackTrace();
}
RequestDispatcher rd =
request.getRequestDispatcher("ViewData");
rd.forward(request,response);
out.println("</body>");
out.println("</html>");
}
}
@Override

protected void doGet(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
processRequest(request, response);
}
@Override
protected void doPost(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
processRequest(request, response);
}
@Override
public String getServletInfo() {
return "Short description";
}// </editor-fold>
}
------------------------------------------------------
ViewData.java
-------------
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
public class ViewData extends HttpServlet {
protected void processRequest(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
response.setContentType("text/html;charset=UTF-8");
try (PrintWriter out = response.getWriter()) {
out.println("<!DOCTYPE html>");
out.println("<html>");
out.println("<head>");
out.println("<title>Servlet ViewData</title>");
out.println("</head>");
out.println("<body>");
try{
Class.forName("com.mysql.jdbc.Driver");
Connection
con=DriverManager.getConnection("jdbc:mysql://localhost/College",
"root", "");
Statement st=con.createStatement();
String qry="select * from student";
ResultSet rs = st.executeQuery(qry);
out.println("<table border=1>");
out.println("<thead><b>Student's List</b></thead>");
out.println("<tr>");
out.println("<th>Rlno<th>Name<th>City</tr>");
while(rs.next()){
int r;
String s,c;
r=rs.getInt("rlno");

s=rs.getString("sname");
c=rs.getString("city");
out.println("<tr>");
out.println("<td>"+r+"<td>"+s+"<td>"+c+"</tr>");
}
out.println("</table>");
rs.close();
st.close();
con.close();
}catch(Exception e){
e.printStackTrace();
}
out.println("</body>");
out.println("</html>");
}
}
@Override
protected void doGet(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
processRequest(request, response);
}
@Override
protected void doPost(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
processRequest(request, response);
}
@Override
public String getServletInfo() {
return "Short description";
}// </editor-fold>
}
------------------------------------------------------
DeleteData.java
-------------
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
public class DeleteData extends HttpServlet {
protected void processRequest(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
response.setContentType("text/html;charset=UTF-8");
try (PrintWriter out = response.getWriter()) {

    out.println("<!DOCTYPE html>");
out.println("<html>");
out.println("<head>");
out.println("<title>Servlet DeleteData</title>");
out.println("</head>");
out.println("<body>");
try{
Class.forName("com.mysql.jdbc.Driver");
Connection
con=DriverManager.getConnection("jdbc:mysql://localhost/College",
"root", "");
Statement st=con.createStatement();
String r;
r=request.getParameter("txtRlno");
String qry="Delete from student where rlno="+r;
int rows = st.executeUpdate(qry);
out.println(rows+ "<b> Record(s) Deletede</b>");
st.close();
con.close();
}catch(Exception e){
e.printStackTrace();
}
RequestDispatcher rd =
request.getRequestDispatcher("ViewData");
rd.forward(request,response);
out.println("</body>");
out.println("</html>");
}
}
@Override
protected void doGet(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
processRequest(request, response);
}
@Override
protected void doPost(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
processRequest(request, response);
}
@Override
public String getServletInfo() {
return "Short description";
}// </editor-fold>
}
------------------------------------------------------
UpdateData.java
-------------
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;
public class UpdateData extends HttpServlet {
protected void processRequest(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
response.setContentType("text/html;charset=UTF-8");
try (PrintWriter out = response.getWriter()) {
out.println("<!DOCTYPE html>");
out.println("<html>");
out.println("<head>");
out.println("<title>Servlet UpdateData</title>");
out.println("</head>");
out.println("<body>");
try{
Class.forName("com.mysql.jdbc.Driver");
Connection
con=DriverManager.getConnection("jdbc:mysql://localhost/College",
"root", "");
Statement st=con.createStatement();
String r,s,c;
r=request.getParameter("txtRlno");
s=request.getParameter("txtName");
c=request.getParameter("txtCity");
String qry="Update student set sname='"+s+"',
city='"+c+"' where rlno="+r;
//out.print(qry);
int rows = st.executeUpdate(qry);
out.println(rows+ "<b> Record(s) Updated</b><br>");
st.close();
con.close();
}catch(Exception e){
e.printStackTrace();
}
RequestDispatcher rd =
request.getRequestDispatcher("ViewData");
rd.forward(request,response);
out.println("</body>");
out.println("</html>");
}
}
@Override
protected void doGet(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
processRequest(request, response);
}
@Override
protected void doPost(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
processRequest(request, response);

}
@Override
public String getServletInfo() {
return "Short description";
}// </editor-fold>
}