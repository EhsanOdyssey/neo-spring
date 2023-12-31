<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
<body>

<jsp:include page="../views/fragments/header.jsp"></jsp:include>

<div class="container">
    <div class="row">

        <form action="<spring:url value="/project/add"/>" method="post" class="col-md-8 col-md-offset-2">

            <div class="form-group">
                <label for="project-name">Name</label>
                <input type="text" id="project-name"
                       class="form-control" name="name"/>
            </div>

            <div class="form-group">
                <label for="project_type">Type</label>
                <select name="type" class="selectpicker">
                    <option></option>
                    <option value="single">Single Year</option>
                    <option value="multi">Multi-Year</option>
                </select>
            </div>

            <div class="form-group">
                <label for="sponsor">Sponsor</label>
                <input id="sponsor" type="text"
                       class="form-control" name="sponsor"/>
            </div>

            <div class="form-group">
                <label for="funds">Authorized Funds</label>
                <input id="funds" type="text"
                       class="form-control" name="authorized_funds"/>
            </div>

            <div class="form-group">
                <label for="hours">Authorized Hours</label>
                <input id="hours" type="text"
                       class="form-control" name="authorized_hours"/>
            </div>

            <div class="form-group">
                <label for="project-name">Description</label>
                <textarea class="form-control" rows="3"></textarea>
            </div>

            <div class="form-group">
                <label for="special">Special</label>
                <input id="special" name="special" type="checkbox"/>
            </div>

            <button type="submit" class="btn btn-default">Submit</button>

        </form>

    </div>
</div>
</body>
</html>
