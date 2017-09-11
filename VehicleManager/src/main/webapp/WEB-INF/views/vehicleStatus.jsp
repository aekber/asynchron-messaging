<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Vehicles List</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
 
<body>
    <div class="generic-container">
        <div class="panel panel-default">
              <!-- Default panel contents -->
            <div class="panel-heading"><span class="lead">List of Vehicles </span></div>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Status</th>
                        <th width="25"></th>
                        <th width="25"></th>
                        <th width="25"></th>
                        <th width="25"></th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${vehicles}" var="entry">
                    <tr>
                        <td>${entry.key}</td>
                        <td>${entry.value.vehicleName}</td>
                        <td>${entry.value.status}</td>
                        <td><a href="<c:url value='/forward-${entry.key}' />" class="btn btn-success 
 
custom-width">Forward</a></td>
                        <td><a href="<c:url value='/backward-${entry.key}' />" class="btn btn-success 
 
custom-width">Backward</a></td>
                        <td><a href="<c:url value='/left-${entry.key}' />" class="btn btn-success 
 
custom-width">Left</a></td>
                        <td><a href="<c:url value='/right-${entry.key}' />" class="btn btn-success 
 
custom-width">Right</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="well">
            <a href="<c:url value='/newVehicle' />">Add New Vehicle</a> | 
            <a href="<c:url value='/showPlace' />">Show Vehicles' Place</a> | 
            List Vehicles
        </div>
    </div>
</body>
</html>
