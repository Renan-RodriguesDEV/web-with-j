package controllers;

import java.util.List;

import jakarta.ws.rs.*;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.Carro;
import repositories.CarroRepository;


@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class Application {
	
	CarroRepository carroRepository = new CarroRepository();
	
	@GET
	@Path("")
	public List<Carro> carros() {
		return carroRepository.findAll();
	}
	@GET
	@Path("/{id}")
	public Carro carro(@PathParam("id") Long id) {
		return carroRepository.findByID(id);
	}
	
	@POST
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCarro(Carro carro) {
		carroRepository.add(carro);
		return Response.status(201).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public int updateCarro(@PathParam("id") Long id,Carro carro) {
		return carroRepository.update(id,carro.getNome());
		
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean deleteCarro(@PathParam("id") Long id) {
		return carroRepository.delete(id);
	}
}
