package mbeans;

import java.util.List;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.faces.view.ViewScoped; // Alterado de RequestScoped
import jakarta.inject.Named;
import modelo.dao.FuncionarioDAO;
import modelo.entities.Funcionario;

@ViewScoped // Alterado de RequestScoped para manter os dados entre requisições
@Named
public class FuncionarioMBean implements Serializable { // Implementação necessária para ViewScoped
	private static final long serialVersionUID = 1L;

	private Part uploadedFile;
	private Funcionario funcionario = new Funcionario();
	private List<Funcionario> funcionarios;
	private FuncionarioDAO dao = new FuncionarioDAO();

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String salvar() {
		System.out.println("ID do funcionario:" + funcionario.getId());
		if (uploadedFile != null) {
			try {
				InputStream is = uploadedFile.getInputStream();
				byte[] bytes = is.readAllBytes();
				funcionario.setFoto(bytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Verifica se é uma inserção ou atualização
		if (funcionario.getId() == 0) {
			dao.inserir(funcionario);
		} else {
			dao.salvar(funcionario);
		}
		System.out.println("ID do funcionario atualizado ou salvo:" + funcionario.getId());
		// Limpa o funcionário após salvar
		this.funcionario = new Funcionario();
		// Força o recarregamento da lista
		this.funcionarios = null;
		return "ListaFuncionarios";
	}

	// Método para preparar novo cadastro
	public String prepararNovo() {
		this.funcionario = new Funcionario();
		return "FormFuncionario";
	}

	// Resto do código permanece igual...
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

	// Método para edição
	public String editar(Funcionario f) {
		System.out.println("Editando funcionario: " + f.getId());
		this.funcionario = dao.getFuncionario(f.getId());
		return "FormFuncionario";
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
}