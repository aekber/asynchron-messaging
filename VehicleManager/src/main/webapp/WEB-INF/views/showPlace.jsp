<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Users List</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
 
<body>
    <div class="generic-container">
        <div class="panel panel-default">
              <!-- Default panel contents -->
            <div class="panel-heading"><span class="lead">Vehicle Locations</span></div>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Direction</th>
                        <th>Place</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${vehicles}" var="entry">
                    <tr>
                        <td>${entry.value.vehicleName}</td>
                        <td>${entry.value.direction}</td>
                        <td>${entry.value.point}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="well">
            <a href="<c:url value='/newVehicle' />">New Vehicle</a> | 
            Show Vehicle Place | 
            <a href="<c:url value='/checkStatus' />">List Vehicles</a>
        </div>
    </div>
</body>
</html>
