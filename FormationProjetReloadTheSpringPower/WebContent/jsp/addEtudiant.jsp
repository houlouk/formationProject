<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>



</head>
<body>

	<jsp:include page="banniere.xhtml"></jsp:include>
	<jsp:include page="menu.html"></jsp:include>

	<h1>Ajout d'un étudiant</h1>

	
	<form class="form-inline"
		>
		<div class="form-group">
			<table class="table borderless">
					<tr>
						<td>Numéro d'étudiant</td>
						<td><input id="etudNum" type="text" name="etudiantNumeroetudiant" />
					</tr>
					<tr>
						<td>Nom</td>
						<td><input id="etudNom" type="text" name="etudiantNom" />
					</tr>
					<tr>
						<td>Prenom</td>
						<td><input id="etudPrenom" type="text" name="etudiantPrenom" />
					</tr>
			</table>

			<button type="button" id="btnAddReel" class="form-control" name="add" value="Ajouter" >Ajouter</button>
		</div>
	</form>

</body>
</html>