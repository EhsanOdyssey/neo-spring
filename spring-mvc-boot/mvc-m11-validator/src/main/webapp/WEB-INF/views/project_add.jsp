<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Project Manager</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="<spring:url value="/css/bootstrap-select.min.css"/>" type="text/css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script src="<spring:url value="/js/bootstrap-select.min.js"/>"></script>

</head>
<style>
    .error-msg {
        color: #ff0000;
    }
</style>
<body>

<jsp:include page="../views/fragments/header.jsp"></jsp:include>

<div class="container">
    <div class="row">

<%--        <form action="<spring:url value="/project/add"/>" method="post"--%>
<%--              class="col-md-8 col-md-offset-2" model>--%>
        <spring:url value="/project/add" var="formUrl"/>
        <form:form modelAttribute="project" action="${formUrl}" method="post" cssClass="col-md-8 col-md-offset-2">

            <div class="form-group">
                <label for="project-name">Name</label>
                <form:input id="project-name" cssClass="form-control" path="name"/>
                <form:errors path="name" cssClass="error-msg"/>
            </div>

            <div class="form-group">
                <label for="project-type">Type</label>
                <form:select id="project-type" path="type" cssClass="selectpicker" items="${types}"/>
            </div>

            <!-- Composite DataBinding -->
            <div class="form-group">
                <label for="sponsor-name">Sponsor Name</label>
                <form:input id="sponsor-name" cssClass="form-control" path="sponsor.name"/>
            </div>

            <div class="form-group">
                <label for="sponsor-phone">Sponsor Phone</label>
                <form:input id="sponsor-phone" cssClass="form-control" path="sponsor.phone"/>
            </div>

            <div class="form-group">
                <label for="sponsor-email">Sponsor Email</label>
                <form:input id="sponsor-email" cssClass="form-control" path="sponsor.email"/>
            </div>
            <!-- Composite DataBinding -->

            <div class="form-group">
                <label for="funds">Authorized Funds</label>
                <form:input id="funds" cssClass="form-control" path="authorizedFunds"/>
            </div>

            <div class="form-group">
                <label for="hours">Authorized Hours</label>
                <form:input id="hours" cssClass="form-control" path="authorizedHours"/>
            </div>

            <div class="form-group">
                <label for="project-description">Description</label>
                <form:textarea id="project-description" cssClass="form-control" path="description" rows="3"/>
            </div>

            <div class="form-group">
                <label for="special">Special</label>
                <form:checkbox id="special" path="special"/>
            </div>

            <!-- List DataBinding -->
            <div class="form-group">
                <label for="poc1">Point Of Contact 1</label>
                <form:input id="poc1" cssClass="form-control" path="pointsOfContact[0]"/>
            </div>

            <div class="form-group">
                <label for="poc2">Point Of Contact 2</label>
                <form:input id="poc2" cssClass="form-control" path="pointsOfContact[1]"/>
            </div>

            <div class="form-group">
                <label for="poc3">Point Of Contact 3</label>
                <form:input id="poc3" cssClass="form-control" path="pointsOfContact[2]"/>
            </div>
            <!-- List DataBinding -->

            <button type="submit" class="btn btn-default">Submit</button>

        </form:form>

    </div>
</div>
</body>
</html>
