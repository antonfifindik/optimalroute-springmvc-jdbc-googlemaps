<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Редагування кур'єра</title>

<style>

.aligntop {
		margin-top: 20px;
	}

</style>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="//netdna.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	
</head>
<body>

<nav class="navbar navbar-inverse">
<div class="container-fluid">
	<div class="navbar-header">
		<a href="./home" class="navbar-brand">OPTIMAL ROUTE</a>
		</div>	
	<div>
		<ul class="nav navbar-nav">
			<li><a href="./home">Замовлення</a></li>
			<li><a href="addresses">Адреси</a></li>
			<li><a href="clients">Клієнти</a></li>
			<li><a href="couriers">Кур'єри</a></li>
			<li><a href="map">Побудова маршруту</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
            <li><a href="#">${account.login}</a></li>
            <li><a href="login">Вихід</a></li>
          </ul>
	</div>
</div>
</nav>

		<div class="container" align="center">
	 <div class="page-header">
  <h1>Редагувати кур'єра</h1>
</div>

<form name="courier" action="./updateCourier" method="post">
			<div class="input-group">
  <input type="text" name="surname" class="form-control" placeholder="Прізвище" value="${updateCourier.surname}" required>
</div>
		
		
			<form name="courier" action="./updateCourier" method="post">
			<div class="input-group aligntop">
  <input type="text" name="name" class="form-control" placeholder="Ім'я" value="${updateCourier.name}" required>
</div>

			<form name="courier" action="./updateCourier" method="post">
			<div class="input-group aligntop">
  <input type="text" name="patronymic" class="form-control" placeholder="По-батькові" value="${updateCourier.patronymic}" required>
</div>
		
				<form name="courier" action="./updateCourier" method="post">
			<div style="width:195px;'" class="input-group aligntop">
		<span class="input-group-addon">+380</span>
  <input type="text" name="phoneNumber" class="form-control" placeholder="Номер телефону" value="${updateCourier.phoneNumber.substring(4)}" required>
</div>
		<input type="submit" value="Прийняти" class="btn btn-success aligntop"> 
	</form>	
	
</div>

</body>
</html>