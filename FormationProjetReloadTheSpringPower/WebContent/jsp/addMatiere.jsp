<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<link rel="stylesheet" type="text/css"
	href="/PremierProjetWeb/Styles/formation.css" media="screen" />
<link rel="stylesheet" type="text/css"
	href="/PremierProjetWeb/Styles/accueil.css" media="screen" />
<script src="/PremierProjetWeb/Scripts/jquery-3.2.1.js" charset="utf-8"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

</head>
<body>

	<jsp:include page="banniere.xhtml"></jsp:include>
	<jsp:include page="menu.html"></jsp:include>

	<h1>Ajout d'une matière</h1>

	<form method="post" action="/PremierProjetWeb/ServletMatiere">
		<input type="hidden" name="cursusId" value="${cursusId}" />
	
		<table>
			<tr>
				<td>Nom</td>
				<td><input type="text" name="matiereNom" />
			</tr>
		</table>
		<input type="submit" name="add" value="Ajouter" />
	</form>
</body>
</html>