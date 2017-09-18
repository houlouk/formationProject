package fr.m2i.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class Hello {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String ditBonjourEnTexte (){
		return "Bonjour Jersey";
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public String ditBonjourEnXml() {
		return "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>" +"<hello>Bonjour Jersey" +"</hello>";

		// TODO Auto-generated method stub

	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)

	public String ditBonjourEnHtml(){
		return "<html>" +"<title>"+"Hello Jersey"+"</title>"+"<body><h1>Bonjour Jersey</h1></body>" +"</html>";
	}
	
	

}
