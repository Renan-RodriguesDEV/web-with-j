package servicos;

import java.util.List;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import modelo.dao.VeiculoDAO;
import modelo.entities.Veiculo;

@Path("/veiculo")
public class VeiculoService {
	private VeiculoDAO dao = new VeiculoDAO();

	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Veiculo> todos() {
		List<Veiculo> veiculos = dao.getList();
		return veiculos;
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Veiculo busca(@PathParam("id") int id) {
		Veiculo v = dao.getVeiculo(id);
		return v;
	}

	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cria(Veiculo v) {
		try {
			dao.salvar(v);
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.status(Response.Status.CREATED).build();
	}

	@Path("/")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response altera(Veiculo v) {
		Veiculo atual = dao.getVeiculo(v.getId());
		if (atual != null) {
			dao.salvar(v);
			return Response.status(Response.Status.OK).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@Path("/")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response exclui(Veiculo v) {
		Veiculo atual = dao.getVeiculo(v.getId());
		if (atual != null) {
			dao.excluir(v.getId());
			return Response.status(Response.Status.OK).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
