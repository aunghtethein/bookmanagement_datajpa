<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Add Book</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body>
	<div class="container mt-5 jumbotron">
		<form action="#" th:action="@{/updateBook}" method="post" th:object="${book}">
		
			<div class="form-group">
				<div class="col-sm-4"></div>
				<div class="col-sm-4">
					<h2 style="text-align: center">Update Book</h2>
				</div>	
			</div>
			<hr />
			<div class="form-group row">
					<label for="code" class="col-md-2">Book Code</label>
					<div class="col-md-6">
						<input type="text" id="code" th:field="*{code}"
							class="form-control" />
					</div>
					<div th:if="${#fields.hasErrors('code')}" th:errors="*{code}"
						class="text-danger"></div>
			</div>
			<div class="form-group row">
					<label for="title" class="col-md-2">Book Title</label>
					<div class="col-md-6">
						<input type="text" id="title" th:field="*{title}"
							class="form-control" />
					</div>
					<div th:if="${#fields.hasErrors('title')}" th:errors="*{title}"
						class="text-danger"></div>
			</div>
			<div class="form-group row">
					<label for="author" class="col-md-2">Book Author</label>
					<div class="col-md-6">
						<input type="text" id="author" th:field="*{author}"
							class="form-control" />
					</div>
					<div th:if="${#fields.hasErrors('author')}" th:errors="*{author}"
						class="text-danger"></div>
			</div>
			<div class="form-group row">
					<label for="price" class="col-md-2">Book Price</label>
					<div class="col-md-6">
						<input type="text" id="price" th:field="*{price}"
							class="form-control" />
					</div>
					<div th:if="${#fields.hasErrors('price')}" th:errors="*{price}"
						class="text-danger"></div>
			</div>
			
			<input type="submit" value="Submit" class="btn btn-primary">
			<a href="#" th:href="@{/}" class="btn btn-secondary">Back</a>
               
		</form>
	</div>
</body>
</html>