<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>User Registration Application</title>
<link rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
</head>
<body>
    <header>
		<nav class="navbar navbar-expand-sm navbar-dark" style="background-color: #008000">
          <div class="container-fluid ">
            <a class="navbar-brand " href="<%=request.getContextPath()%>/login">User Registration Application </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
              <ul class="navbar-nav ">
                <li class="nav-item">
                  <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/user/page">Home</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link active" href="<%=request.getContextPath()%>/sign-out">Sign Out</a>
                </li>
              </ul>
            </div>
          </div>
        </nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${car != null}">
					<form action="<%=request.getContextPath()%>/car/update" method="post">
				</c:if>
				<c:if test="${car == null}">
					<form action="<%=request.getContextPath()%>/car/insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${car != null}">
            			    Edit Car
            		    </c:if>
						<c:if test="${car == null}">
            			    Insert New Car
            		    </c:if>
					</h2>
				</caption>

				<c:if test="${car != null}">
                    <input type="hidden" name="id" value="<c:out value='${car.id}' />" />
                </c:if>

                <fieldset class="form-group">
					<label>Car Brand</label> <input type="text"
						value="<c:out value='${car.brand}' />" class="form-control"
						name="brand" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Car Model</label> <input type="text"
						value="<c:out value='${car.model}' />" class="form-control"
						name="model" required="required">
				</fieldset>

				<fieldset class="form-group">
                    <label>Car Plate Number</label> <input type="text"
                        value="<c:out value='${car.plateNum}' />" class="form-control"
                        name="plate" required="required">
                </fieldset>

                <c:if test="${error != null}">
                    <font color="red"> <c:out value='${error}' /></font>
                </c:if>
                <div class="text-center">
				    <button type="submit" class="btn btn-success">Save</button>
				    <c:if test="${car != null}">
				        <a href="<%=request.getContextPath()%>/car/delete?id=${car.id}" class="btn btn-danger" role="button">Delete</a>
                    </c:if>
                </div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>