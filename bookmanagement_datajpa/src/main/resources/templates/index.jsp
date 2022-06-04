<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>List Book</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<style type="text/css">
	table {
		border-collapse: collpase;
		width: 100%;
	}
	th,th {
		text-align: left;
		padding: 8px;
	}
	tr:nth-child(even){background-color: #f2f2f2}
	th {
		background-color: #4CAF50;
		color: white;
	}
</style>
</head>
<body>
		<div class="container mt-3 jumbotron">
			<h2 class="text align-items-center">List All Books</h2>
			
			<table class="table table-striped table-hover table-bordered">
				<tr>
					<th>Code</th>
					<th>Title</th>
					<th>Author</th>
					<th>Price</th>	
					<th>Action</th>			
				</tr>
				<tr th:each="book: ${list}">
					<td th:text="${book.code }"></td>
					<td th:text="${book.title }"></td>
					<td th:text="${book.author }"></td>
					<td th:text="${book.price }"></td>
					<td>
					 <a href="#" th:href="@{/setupUpdateBook(code=${book.code })}">Update</a>
					 <a href="#" th:href="@{/deleteBook(code=${book.code })}">Delete</a>
					</td>
				</tr>
			</table>
			<a href="#" th:href="@{/setupAddbook}" class="btn btn-success">New Book</a>
			<a href="#" th:href="@{/generatePdf}" class="btn btn-success">Download PDF</a>
			<a href="#" th:href="@{/generateExcel}" class="btn btn-success">Download Excel</a>
		</div>
</body>
</html>