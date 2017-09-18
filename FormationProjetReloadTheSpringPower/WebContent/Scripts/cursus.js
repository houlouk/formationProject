$(document).ready(function() {
	let nbElems=0;
	function removeEvents() {
		let j=0;
		while(j<nbElems){
			console.log(j);
		$(document).off('click',`#removeCursusid${j}`);
			j++;
		}
	}
	$('#cursusLink').on('click', function(e) {
		e.preventDefault();
		$("#list").find("*").off();
		$('#headerTitle').html("Cursus");
		
		removeEvents();
		$('#addForm').empty();


		console.log("test");

		$.ajax({
			url : 'http://localhost:8080/FormationProjetReloadTheSpringPower/formation/cursuss',
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
				for (let cursus of data) {
					nbElems=data.length;

					html += `
						 <tr>
						 <td>${cursus.nom}</td> 

			    	
			    		 <td class="removeCursus">
 			    		   <form>
					 <button type="button" id="removeCursusid${i}" name="remove">Supprimer</button>
						 </form>
		 
 			    		  </td>
			    		  
			    		
			    		  
			    		 </tr>
			    		
			    		 
						
			    		 
			    		 ` 
						
						$(document).on('click',`#removeCursusid${i}`,
							function(e) {
								e.preventDefault();
								console.log("test");

								$.ajax({
									url : `http://localhost:8080/FormationProjetReloadTheSpringPower/formation/cursuss/${cursus.id}/delete`,
									data : [],
									type : 'DELETE',
									cache : false,
									dataType : 'text',
									success : function(data) {
										 $('#cursusLink').trigger('click');
									
							
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
				$('#btnAddDiv').html(`<button type="button" id=btnAddCursus> Ajouter Cursus </button>`);

			


			}
		});

	}
	);
	
	

	class Cursus {
		  constructor( nom , 
) {
			  this.nom =  nom; 
	  
			  
		  }
		} 
	
	$(document).on('click','#btnAddReelCursus', function(e) {
		e.preventDefault();
		let cursus = new Cursus($('#inputCursusnom').val(),
);
		
		$.ajax({
			  headers: { 
			        'Accept': 'application/json',
			        'Content-Type': 'application/json' 
			    },
			url : 'http://localhost:8080/FormationProjetReloadTheSpringPower/formation/cursuss/add',
			data : JSON.stringify(cursus),
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data) {			
				 $('#cursusLink').trigger('click');

			
			}
		});
		


	});
		

	
	
	$(document).on('click','#btnAddCursus', function(e) {
		
		$('#addForm').load("jsp/cursusFormular.html")
	


	});


}

);