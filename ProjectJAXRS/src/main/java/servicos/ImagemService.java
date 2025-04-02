package servicos;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.*;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import modelo.dao.VeiculoDAO;
import modelo.entities.Veiculo;

@Path("/imagem")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ImagemService {

	public VeiculoDAO veiculoDAO = new VeiculoDAO();
	
	@GET
	@Path("/{id}")
	public byte [] buscar(@PathParam("id") int id) {
		Veiculo veiculo = veiculoDAO.getVeiculo(id);
		return veiculo.getImagem();
	}
	
	@DELETE
	@Path("/{id}")
	public Veiculo exclui(@PathParam("id") int id) {
		return veiculoDAO.excluiImagem(id);
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	public Response altera(@PathParam("id") int id, byte[] imagem) {
		try {

			veiculoDAO.alteraImagem(id, imagem);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao alterar imagem").build();
		}
	}

}
