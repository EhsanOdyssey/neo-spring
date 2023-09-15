<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Project Manager</title>

    <script>var ctx = "${pageContext.request.contextPath}"</script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="<spring:url value="/css/bootstrap-select.min.css"/>" type="text/css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script src="<spring:url value="/js/bootstrap-select.min.js"/>"></script>
    <script src="<spring:url value="/js/resource.js"/>"></script>

</head>
<body>

<jsp:include page="../views/fragments/header.jsp"></jsp:include>

<div class="container">

    <div class="row">
        <h1>Resource</h1>
    </div>


    <spring:url value="/resource/review" var="formUrl"/>
    <form:form action="${formUrl}" method="POST" modelAttribute="resource">

        <div class="row">

            <div class="form-group">
                <label for="resource-name">Name</label>

                <form:input path="name" cssClass="form-control" id="resource-name"/>

            </div>

            <div class="form-group">
                <label for="type">Type</label>

                <form:select path="type" items="${typeOptions}" cssClass="selectpicker"/>

            </div>

            <div class="form-group">
                <label for="cost">Cost</label> <input id="cost" type="text"
                                                      class="form-control" name="cost"/>
            </div>

            <div class="form-group">
                <label for="unit">Unit of Measure</label>
                <ul style="list-style: none">
                    <form:radiobuttons path="unitOfMeasure" items="${radioOptions}" element="li"/>
                </ul>
            </div>

            <div class="form-group">
                <label for="indicators">Indicators</label>
                <ul style="list-style: none">
                    <form:checkboxes items="${checkOptions}" path="indicators" element="li"/>
                </ul>
            </div>

            <div class="form-group">
                <label for="notes">Notes</label>
                <form:textarea path="notes" cssClass="form-control" rows="3"/>
            </div>

            <button type="submit" class="btn btn-default">Submit</button>

        </div>

    </form:form>

</div>
</body>
</html>