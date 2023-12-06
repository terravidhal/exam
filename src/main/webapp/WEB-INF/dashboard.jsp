<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!-- New line below to use the JSP Standard Tag Library -->
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!-- New line below to use the JSP Standard Tag Library : Form -->
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!-- use format date -->
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
            <!-- gestion ds erreurs -->
            <%@ page isErrorPage="true" %>
                <!DOCTYPE html>
                <html>

                <head>
                    <meta charset="UTF-8">
                    <title>table Manager Dashboard</title>
                    <!-- for Bootstrap CSS -->
                    <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/css/bootstrap.min.css" />
                    <!-- YOUR own local CSS -->
                    <link rel="stylesheet" href="/css/main.css" />
                </head>

                <body>
                    <h1>Welcome back,<c:out value="${user.userName}"/></h1>
                    <p><a href="/logout">Log Out</a></p>
                    <p><a href="/tables/new">+new table</a></p>

                    <h4>Your Tables</h4>

                    <table>
                        <thead style="text-align: center;">
                            <tr>
                                <th>Guest Name</th>
                                <th>#Guests</th>
                                <th>Arrived at</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="table" items="${assignedTables}">
                                <tr>
                                    <td>
                                        <c:out value="${table.name}"/>
                                    </td>
                                    <td>
                                        <c:out value="${table.number}"></c:out>
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${table.createdAt}" pattern="MMMM dd hh:mm aa" />
                                    </td>
                                    <td>
                                        <c:if test="${table.server.id == user.id}">
                                            <a href="/tables/delete/${table.id}">Finished</a>&nbsp;&nbsp;
                                        </c:if>
                                        <c:if test="${table.server.id == user.id}">
                                            <a href="/tables/edit/${table.id}">Edit</a>&nbsp;&nbsp;
                                        </c:if>
                                        <a href="/dashboard/remove/${table.id}">Give Up Table</a>&nbsp;&nbsp;
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                   

                    <p><a href="/allTables">See other Tables</a></p>


                    <!-- link Js -->
                    <script type="text/javascript" src="/js/main.js"></script>
                    <!-- For any Bootstrap that uses jquery -->
                    <script src="/webjars/jquery/jquery.min.js"></script>
                    <!--For any Bootstrap that uses JS -->
                    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
                </body>

                </html>