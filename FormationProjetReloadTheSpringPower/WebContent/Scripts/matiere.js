$(document).ready(function() {

	$('#matiereLink').on('click', function(e) {
		e.preventDefault();
		console.log("test");

		$.ajax({
			url : 'http://localhost:8080/FormationProjetReloadTheSpringPower/rest/matieres',
			data : [],
			type : 'GET',
			cache : false,
			dataType : 'json',
			success : function(data) {
				let html = "";
				let i=0;
				html+=`<table class="table table-bordered">  <thead>
						 <th> Nom </th> 
						 </thead><tbody>
`
				for (let matiere of data) {
					html += `
						 <tr>
			    		 <td>${matiere.nom}</td> 

			    	
			    		 <td class="removeMatiere">
 			    		   <form>
					 <button type="button" id="removeid${i}" name="remove">Supprimer</button>
						 </form>
		 
 			    		  </td>
			    		  
			    		
			    		  
			    		 </tr>
			    		
			    		 
						
			    		 
			    		 ` 
						
						$(document).on('click',`#removeid${i}`,
							function(e) {
								e.preventDefault();
								console.log("test");

								$.ajax({
									url : `http://localhost:8080/FormationProjetReloadTheSpringPower/rest/matieres/${matiere.id}/delete`,
									data : [],
									type : 'GET',
									cache : false,
									dataType : 'json',
									success : function(data) {
									
							
						}
								}
								
								);
								}
						
				);
						i++;
				}
				
				html+=` </tbody>
			    		 </table>
			    		 <button type="button" id=btnAddMatiere> Ajouter Matiere </button>`
				$('#list').html(html);

			


			}
		});

	}
	);
	
	

	class Matiere {
		  constructor(nom ) {
		   this.nom =  nom; 
		  }
		} 
	
	$(document).on('click','#btnAddReelMatiere', function() {
		let matiere = new Matiere($('#inputMatierenom').val() );
		
		$.ajax({
			  headers: { 
			        'Accept': 'application/json',
			        'Content-Type': 'application/json' 
			    },
			url : 'http://localhost:8080/FormationProjetReloadTheSpringPower/rest/matieres/add',
			data : JSON.stringify(matiere),
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {			


			}
		});
		


	});
	
	
	$(document).on('click','#btnAddMatiere', function() {
		$('#addForm').load('/FormationProjetReloadTheSpringPower/jsp/matiere.html');
		


	});


}

);