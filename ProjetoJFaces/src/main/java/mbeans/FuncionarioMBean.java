package mbeans;

import java.util.List;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import modelo.dao.FuncionarioDAO;
import modelo.entities.Funcionario;

@RequestScoped
@Named
public class FuncionarioMBean {
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
		if (uploadedFile != null) {
			try {
				InputStream is = uploadedFile.getInputStream();
				byte[] bytes = is.readAllBytes();
				funcionario.setFoto(bytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		dao.inserir(funcionario);
		//this.funcionario = null;
		return "ListaFuncionarios";
	}

	public List<Funcionario> getFuncionarios() {
		if (this.funcionarios == null) {
			this.funcionarios = dao.getList();
		}
		return funcionarios;
	}

	public String excluir(Funcionario f) {
		System.out.println("excluindo funcionário: " + f.getNome());
		dao.excluir(f.getId());
		this.funcionarios.remove(f);
		return "ListaFuncionarios";
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
}