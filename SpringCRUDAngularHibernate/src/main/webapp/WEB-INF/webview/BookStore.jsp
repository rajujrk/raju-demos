<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Spring 4 MVC + Hibernate 4 + AngularJS 1.x </title>
<style>
.bookname.ng-valid {
	background-color: lightgreen;
}

.bookname.ng-dirty.ng-invalid-required {
	background-color: red;
}

.bookname.ng-dirty.ng-invalid-minlength {
	background-color: yellow;
}

.email.ng-valid {
	background-color: lightgreen;
}

.email.ng-dirty.ng-invalid-required {
	background-color: red;
}

.email.ng-dirty.ng-invalid-email {
	background-color: yellow;
}
</style>
<link href="<c:url value='/resources/css/bootstrap.css'/>" rel="stylesheet"/>
<link href="<c:url value='/resources/css/app.css'/>" rel="stylesheet"/>
</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="generic-container" ng-controller="BookController as ctrl">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="lead">Book Registration Form </span>
			</div>
			<div class="formcontainer">
				<form ng-submit="ctrl.submit()" name="myForm"
					class="form-horizontal">
					<input type="hidden" ng-model="ctrl.book.bookid" />
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="bookname">Book Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.book.bookname" id="bookname"
									class="bookname form-control input-sm"
									placeholder="Enter Book name" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.bookname.$error.required">This is a
										required field</span> <span ng-show="myForm.bookname.$error.minlength">Minimum
										length required is 3</span> <span ng-show="myForm.bookname.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="author">Author</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.book.author" id="author"
									class="author form-control input-sm"
									placeholder="Enter Author name" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.author.$error.required">This is a
										required field</span> <span ng-show="myForm.author.$error.minlength">Minimum
										length required is 3</span> <span ng-show="myForm.author.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>
	
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="category">Category</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.book.category" id="category"
									class="category form-control input-sm"
									placeholder="Enter Category name" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.category.$error.required">This is a
										required field</span> <span ng-show="myForm.category.$error.minlength">Minimum
										length required is 3</span> <span ng-show="myForm.category.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>
					
					
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="price">Price</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.book.price" id="price"
									class="price form-control input-sm"
									placeholder="Enter Price" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.price.$error.required">This is a
										required field</span> <span ng-show="myForm.price.$error.minlength">Minimum
										length required is 3</span> <span ng-show="myForm.price.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="publisher">Publisher</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.book.publisher" id="publisher"
									class="publisher form-control input-sm"
									placeholder="Enter Publisher name" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.publisher.$error.required">This is a
										required field</span> <span ng-show="myForm.publisher.$error.minlength">Minimum
										length required is 3</span> <span ng-show="myForm.publisher.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="publishdate">Published Date</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.book.publishdate" id="publishdate"
									class="publishdate form-control input-sm"
									placeholder="Enter Published Date" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.publishdate.$error.required">This is a
										required field</span> <span ng-show="myForm.publishdate.$error.minlength">Minimum
										length required is 3</span> <span ng-show="myForm.publishdate.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>

					

					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="{{!ctrl.book.bookid ? 'Add' : 'Update'}}"
								class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
							<button type="button" ng-click="ctrl.reset()"
								class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset
								Form</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">List of Books </span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>ID.</th>
							<th>Name</th>
							<th>Author</th>
							<th>Category</th>
							<th>Price</th>
							<th>Publisher</th>
							<th>Published Date</th>
							<th width="20%"></th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="u in ctrl.books">
							<td><span ng-bind="u.bookid"></span></td>
							<td><span ng-bind="u.bookname"></span></td>
							<td><span ng-bind="u.author"></span></td>
							<td><span ng-bind="u.category"></span></td>
							<td><span ng-bind="u.price"></span></td>
							<td><span ng-bind="u.publisher"></span></td>
							<td><span ng-bind="u.publishdate"></span></td>
							<td>
								<button type="button" ng-click="ctrl.edit(u.bookid)"
									class="btn btn-success custom-width">Edit</button>
								<button type="button" ng-click="ctrl.remove(u.bookid)"
									class="btn btn-danger custom-width">Remove</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
	<script src="<c:url value='/resources/js/modules/app.js' />"></script>
	<script src="<c:url value='/resources/js/services/bookServices.js' />"></script>
	<script
		src="<c:url value='/resources/js/controller/bookController.js' />"></script>
</body>
</html>