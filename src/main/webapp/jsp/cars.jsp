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

	<div class="container col-md-5">
        <div class="card">
    	    <div class="card-body">
                <caption>
                    <h2>
                         Information about your cars
                    </h2>
                </caption>

                <c:if test="${cars != null}">
                    <table class="table">
                        <thead>
                          <tr>
                            <th scope="col">Information</th>
                            <th scope="col"></th>
                          </tr>
                        </thead>
                        <tbody>

                            <c:forEach var="car" items="${cars}">
                                <tr>
                                    <td>
                                        <p>ID: ${car.id}</p>
                                        <p>Brand: ${car.brand}</p>
                                        <p>Model: ${car.model}</p>
                                        <p>Plate Number: ${car.plateNum}</p>
                                    </td>
                                    <td>
                                        <a href="<%=request.getContextPath()%>/car/edit?id=${car.id}" class="btn btn-success" role="button">Edit</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                      </table>
                </c:if>
            </div>
        </div>
	</div>
</body>
</html>