$(document).ready(function() {

	let nbElems = 0;
	function removeEvents() {
		let j = 0;
		while (j < nbElems) {
			console.log(j);
			$(document).off('click', `#removeEtudiantnumeroEtudiant${j}`);
			j++;
		}
	}
	$('#etudiantLink').on('click', function(e) {
		e.preventDefault();
		$("#list").find("*").off();
		$('#headerTitle').html("Etudiant");

		removeEvents();
		$('#addForm').empty();


		console.log("test 3");

		$.ajax({
		
			url : 'http://localhost:8080/FormationProjetReloadTheSpringPower/formation/etudiants',
			data : [],
			type : 'GET',
			cache : false,
			dataType : 'json',
			success : function(data) {
				let html = "";
				let i = 0;
				html += `<table class="table table-bordered">  <thead>
				 <th> NumeroEtudiant </th>  <th> Nom </th>  <th> Prenom </th> 					

			 </thead><tbody id="tbody">
										`
				for (let etudiant of data) {
					nbElems = data.length;

					html += `
						 <tr>
						 <td>${etudiant.numeroEtudiant}</td>  
						 <td>${etudiant.nom}</td>  
						 <td>${etudiant.prenom}</td> 
			    	
			    		 <td class="removeEtudiant">
 			    		   <form>
					 <button type="button" id="removeEtudiantnumeroEtudiant${i}" name="remove">Supprimer</button>
						 </form>
		 
 			    		  </td>
 			    		  
 			    		 <td id="cell${i}" class="addCreneauEmploiDuTemps">
			    		   <form>
					 <button type="button" id="btnAddEdt${i}" name="addCreneau">Ajouter créneau</button>
						 </form>
						 
							<div id="formEdt${i}"></div>
		 
			    		  </td>
			    		  
			    		  
			    		
			    		  
			    		 </tr>
			    		
			    		 
						
			    		 
			    		 `

					$(document).on('click', `#removeEtudiantnumeroEtudiant${i}`,
						function(e) {
							e.preventDefault();
							console.log("test");

							$.ajax({
								url : `http://localhost:8080/FormationProjetReloadTheSpringPower/formation/etudiants/${etudiant.numeroEtudiant}/delete`,
								data : [],
								type : 'DELETE',
								cache : false,
								dataType : 'text',
								success : function(data) {
									$('#etudiantLink').trigger('click');


								}
							}

							);
						}

					);
					i++; }

				html += ` </tbody>
			    		 </table>
			    		`
					$('#list').html(html);

				for (let i = 0; i < nbElems; i++) {
					$(document).on('click', `#btnAddEdt${i}`, function(e) {
						console.log("test 3");


						$(`#formEdt${i}`).html(

							`<div class="form-group">
		<table class="table borderless">
				<tr>
					<td>numeroEtudiant</td>
					<td><input id="inputEtudiantnumeroEtudiant" type="text" name="etudiantNumeroEtudiant" />
				</tr> 	<td>nom</td>
					<td><input id="inputEtudiantnom" type="text" name="etudiantNom" />
				</tr> 	<td>prenom</td>
					<td><input id="inputEtudiantprenom" type="text" name="etudiantPrenom" />
				</tr> 
		</table>

		<button id="btnAddReelEtudiant" class="form-control" name="add" >Ajouter etudiant</button>
	</div>
</form>`)

					}
					
					);

				}
				$('#btnAddDiv').html(`<button type="button" id=btnAddEtudiant> Ajouter Etudiant </button>`);




			}
		});

	}
	);





	class Etudiant {
		constructor(numeroEtudiant, nom, prenom) {
			this.numeroEtudiant = numeroEtudiant;
			this.nom = nom;
			this.prenom = prenom;

		}
	}

	$(document).on('click', '#btnAddReelEtudiant', function(e) {
		e.preventDefault();
		let etudiant = new Etudiant($('#inputEtudiantnumeroEtudiant').val(), $('#inputEtudiantnom').val(), $('#inputEtudiantprenom').val());

		$.ajax({
			headers : {
				'Accept' : 'application/json',
				'Content-Type' : 'application/json'
			},
			url : 'http://localhost:8080/FormationProjetReloadTheSpringPower/formation/etudiants/add',
			data : JSON.stringify(etudiant),
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {
				$('#etudiantLink').trigger('click');


			}
		});



	});




	$(document).on('click', '#btnAddEtudiant', function(e) {
		$('#addForm').load("jsp/etudiantFormular.html")


	});


}

);