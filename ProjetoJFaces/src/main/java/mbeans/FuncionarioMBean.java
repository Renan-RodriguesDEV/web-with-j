package mbeans;

import java.util.List;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
// import jakarta.faces.view.ViewScoped; // Alterado de RequestScoped
import jakarta.inject.Named;
import modelo.dao.FuncionarioDAO;
import modelo.entities.Funcionario;

@SessionScoped
@Named
public class FuncionarioMBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Part uploadedFile;
	private Funcionario funcionario = new Funcionario();
	private List<Funcionario> funcionarios;
	private FuncionarioDAO dao = new FuncionarioDAO();
	private Long idFuncionarioEditando = 0L;

	@PostConstruct
	public void init() {
		String idParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if (idParam != null) {
			try {
				Long id = Long.parseLong(idParam);
				this.funcionario = dao.getFuncionario(id);
				this.idFuncionarioEditando = id;
			} catch (Exception e) {
				// ignora ou loga
			}
		}
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String salvar() {
		System.out.println("ID do funcionario sendo editado:" + idFuncionarioEditando);
		System.out.println("ID do funcionario:" + funcionario.getId());
		if (uploadedFile != null) {
			try {
				InputStream is = uploadedFile.getInputStream();
				byte[] bytes = is.readAllBytes();
				funcionario.setFoto(bytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Nenhum arquivo enviado para upload.");
		}

		if (idFuncionarioEditando == 0L || idFuncionarioEditando == null) {
			dao.inserir(funcionario);
		} else {
			funcionario.setId(idFuncionarioEditando);
			dao.salvar(funcionario);
			this.idFuncionarioEditando = 0L;

		}
		System.out.println("ID do funcionario atualizado ou salvo:" + idFuncionarioEditando);
		this.funcionario = new Funcionario();
		this.funcionarios = null;
		return "ListaFuncionarios";
	}

	public String prepararNovo() {
		this.idFuncionarioEditando = 0L;
		this.funcionario = new Funcionario();
		return "FormFuncionario";
	}

	public List<Funcionario> getFuncionarios() {
		if (this.funcionarios == null) {
			this.funcionarios = dao.getList();
		}
		System.out.println("Carregando lista de funcionários " + funcionarios.size());
		return funcionarios;
	}

	public String excluir(Funcionario f) {
		System.out.println("excluindo funcionário: " + f.getNome());
		dao.excluir(f.getId());
		this.funcionarios.remove(f);
		return "ListaFuncionarios";
	}

	public String editar(Funcionario f) {
		idFuncionarioEditando = f.getId();
		System.out.println("Editando funcionario: " + idFuncionarioEditando);
		try {
			Funcionario carregado = dao.getFuncionario(f.getId());

			this.funcionario = new Funcionario();
			this.funcionario.setId(carregado.getId());
			this.funcionario.setNome(carregado.getNome());
			this.funcionario.setCargo(carregado.getCargo());
			this.funcionario.setSalario(carregado.getSalario());
			this.funcionario.setDataAdm(carregado.getDataAdm());
			this.funcionario.setAtivo(carregado.isAtivo());
			this.funcionario.setFoto(carregado.getFoto());

			System.out.println("ID após clone: " + this.funcionario.getId());
			System.out.println("ID do funcionario editado/editando:" + idFuncionarioEditando);
		} catch (Exception e) {
			System.out.println("Houve um erro ao editar o funcionario:" + f.getNome());
			e.printStackTrace();
		}
		return "FormFuncionario?id=" + f.getId() + "&faces-redirect=true";
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
}