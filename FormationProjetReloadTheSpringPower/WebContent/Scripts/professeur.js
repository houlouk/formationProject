$(document).ready(function() {
	let nbElems=0;
	function removeEvents() {
		let j=0;
		while(j<nbElems){
			console.log(j);
		$(document).off('click',`#removeProfesseurid${j}`);
			j++;
		}
	}
	$('#professeurLink').on('click', function(e) {
		e.preventDefault();
		$("#list").find("*").off();
		$('#headerTitle').html("Professeur");
		
		removeEvents();
		$('#addForm').empty();


		console.log("test");

		$.ajax({
			url : 'http://localhost:8080/FormationProjetReloadTheSpringPower/formation/professeurs',
			data : [],
			type : 'GET',
			cache : false,
			dataType : 'json',
			success : function(data) {
				let html = "";
				let i=0;
				html+=`<table class="table table-bordered">  <thead>
				 <th> Nom </th> 
 <th> Prenom </th> 
					

			 </thead><tbody id="tbody">
										`
				for (let professeur of data) {
					nbElems=data.length;

					html += `
						 <tr>
						 <td>${professeur.nom}</td> 
 <td>${professeur.prenom}</td> 

			    	
			    		 <td class="removeProfesseur">
 			    		   <form>
					 <button type="button" id="removeProfesseurid${i}" name="remove">Supprimer</button>
						 </form>
		 
 			    		  </td>
			    		  
			    		
			    		  
			    		 </tr>
			    		
			    		 
						
			    		 
			    		 ` 
						
						$(document).on('click',`#removeProfesseurid${i}`,
							function(e) {
								e.preventDefault();
								console.log("test");

								$.ajax({
									url : `http://localhost:8080/FormationProjetReloadTheSpringPower/formation/professeurs/${professeur.id}/delete`,
									data : [],
									type : 'DELETE',
									cache : false,
									dataType : 'text',
									success : function(data) {
										 $('#professeurLink').trigger('click');
									
							
						}
								}
								
								);
								}
						
				);
						i++;
				}
				
				html+=` </tbody>
			    		 </table>
			    		`
				$('#list').html(html);
				$('#btnAddDiv').html(`<button type="button" id=btnAddProfesseur> Ajouter Professeur </button>`);

			


			}
		});

	}
	);
	
	

	class Professeur {
		  constructor( nom , 
 prenom , 
) {
			  this.nom =  nom; 
 this.prenom =  prenom; 
	  
			  
		  }
		} 
	
	$(document).on('click','#btnAddReelProfesseur', function(e) {
		e.preventDefault();
		let professeur = new Professeur($('#inputProfesseurnom').val(),
$('#inputProfesseurprenom').val(),
);
		
		$.ajax({
			  headers: { 
			        'Accept': 'application/json',
			        'Content-Type': 'application/json' 
			    },
			url : 'http://localhost:8080/FormationProjetReloadTheSpringPower/formation/professeurs/add',
			data : JSON.stringify(professeur),
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {			
				 $('#professeurLink').trigger('click');

			
			}
		});
		


	});
		

	
	
	$(document).on('click','#btnAddProfesseur', function(e) {
		
		$('#addForm').load("jsp/professeurFormular.html")


	});


}

);