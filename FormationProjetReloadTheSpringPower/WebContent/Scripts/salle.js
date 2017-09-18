$(document).ready(function() {
	let nbElems=0;
	function removeEvents() {
		let j=0;
		while(j<nbElems){
			console.log(j);
		$(document).off('click',`#removeSalleid${j}`);
			j++;
		}
	}
	$('#salleLink').on('click', function(e) {
		e.preventDefault();
		$("#list").find("*").off();
		$('#headerTitle').html("Salle");
		
		removeEvents();
		$('#addForm').empty();


		console.log("test");

		$.ajax({
			url : 'http://localhost:8080/FormationProjetReloadTheSpringPower/formation/salles',
			data : [],
			type : 'GET',
			cache : false,
			dataType : 'json',
			success : function(data) {
				let html = "";
				let i=0;
				html+=`<table class="table table-bordered">  <thead>
				 <th> Nom </th> 
					

			 </thead><tbody id="tbody">
										`
				for (let salle of data) {
					nbElems=data.length;

					html += `
						 <tr>
						 <td>${salle.nom}</td> 

			    	
			    		 <td class="removeSalle">
 			    		   <form>
					 <button type="button" id="removeSalleid${i}" name="remove">Supprimer</button>
						 </form>
		 
 			    		  </td>
			    		  
			    		
			    		  
			    		 </tr>
			    		
			    		 
						
			    		 
			    		 ` 
						
						$(document).on('click',`#removeSalleid${i}`,
							function(e) {
								e.preventDefault();
								console.log("test");

								$.ajax({
									url : `http://localhost:8080/FormationProjetReloadTheSpringPower/formation/salles/${salle.id}/delete`,
									data : [],
									type : 'DELETE',
									cache : false,
									dataType : 'text',
									success : function(data) {
										 $('#salleLink').trigger('click');
									
							
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
				$('#btnAddDiv').html(`<button type="button" id=btnAddSalle> Ajouter Salle </button>`);

			


			}
		});

	}
	);
	
	

	class Salle {
		  constructor( nom , 
) {
			  this.nom =  nom; 
	  
			  
		  }
		} 
	
	$(document).on('click','#btnAddReelSalle', function(e) {
		e.preventDefault();
		let salle = new Salle($('#inputSallenom').val(),
);
		
		$.ajax({
			  headers: { 
			        'Accept': 'application/json',
			        'Content-Type': 'application/json' 
			    },
			url : 'http://localhost:8080/FormationProjetReloadTheSpringPower/formation/salles/add',
			data : JSON.stringify(salle),
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {			
				 $('#salleLink').trigger('click');

			
			}
		});
		


	});
		

	
	
	$(document).on('click','#btnAddSalle', function(e) {
		
		$('#addForm').load("jsp/salleFormular.html")



	});


}

);