<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andy Chen
  Date: 5/21/2019
  Time: 10:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
  </head>
  <body>
      List of all students:
      <br>
      <c:forEach items="${studentsList}" var="student">
          <tr>
              <td>---------------------------</td>
              <td>${stduent.id}</td>
              <td>${student.name}</td>
              <td>${student.major}</td>
              <td>${student.course}</td>
              <td>---------------------------</td>
              <br>
          </tr>
      </c:forEach>

        ------------------------------
        <form  action="/student/update" method="Post">
            <label>Student Id:<input name="studentId" type="text"></label>
            <br>
            <label>New Major:<input name="major" type="text"/></label>
            <br>
            <button type="submit">update</button>
        </form>
  </body>
</html>
