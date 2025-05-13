package mbeans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;
import modelo.dao.FuncionarioDAO;
import modelo.entities.Funcionario;

@SessionScoped
@Named
public class FuncionarioPrimeMBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Funcionario funcionario = new Funcionario();
	private Funcionario funcionarioSelecionado = new Funcionario();
	private List<Funcionario> funcionarios;
	private FuncionarioDAO dao = new FuncionarioDAO();
	private UploadedFile arquivoFoto;
	private String cmd;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public UploadedFile getArquivoFoto() {
		return arquivoFoto;
	}

	public void setArquivoFoto(UploadedFile arquivoFoto) {
		this.arquivoFoto = arquivoFoto;
	}

	public void uploadFoto(FileUploadEvent evt) {
		arquivoFoto = evt.getFile();
		if (arquivoFoto != null) {
			funcionario.setFoto(arquivoFoto.getContent());
		}
	}

	public List<Funcionario> getFuncionarios() {
		if (this.funcionarios == null) {
			this.funcionarios = dao.getList();
		}
		return funcionarios;
	}

	public void limpaFuncionario() {
		cmd = "adicionar";
		funcionario = new Funcionario();
	}

	public void copiaFuncionario() {
		cmd = "editar";
		funcionario = funcionarioSelecionado;
	}

	public void salvar() {
		if (arquivoFoto != null) {
			funcionario.setFoto(arquivoFoto.getContent());
			arquivoFoto = null;
		}
		if (cmd.equals("adicionar")) {
			dao.inserir(funcionario);
			funcionarios.add(funcionario);
		} else {
			dao.salvar(funcionario);
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso!",
				"Funcionário atualizado."));
	}

	public void excluir() {
		if (funcionarioSelecionado != null) {
			dao.excluir(funcionarioSelecionado.getId());
			this.funcionarios.remove(funcionarioSelecionado);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso!",
					"Funcionário excluído."));

		}
		return;
	}

	// TODO: adicionado após slides 14
	private LazyDataModel<Funcionario> funcionariosLazy;

	public FuncionarioPrimeMBean() {
		funcionariosLazy = new ModeloFuncionarioLazy<Funcionario>();

	}

	public LazyDataModel<Funcionario> getFuncionariosLazy() {
		return funcionariosLazy;
	}

	public void setFuncionariosLazy(

			LazyDataModel<Funcionario> funcionariosLazy) {
		this.funcionariosLazy = funcionariosLazy;
	}

	class ModeloFuncionarioLazy<T>

			extends LazyDataModel<Funcionario> {
		private static final long serialVersionUID = 1L;
		private int qtd = 0;

		@Override
		public List<Funcionario> load(int first, int pageSize,

				Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {

			String clausula =

					"where 1 = 1 ";

			String valorFiltroNome = null;
			String valorFiltroCargo = null;
			if (filterBy != null) {
				for (FilterMeta meta : filterBy.values()) {
					String filterField = meta.getField();
					Object filterValue = meta.getFilterValue();
					System.out.println("Filtro(" + filterBy.size() + "): " + filterField + ": " + filterValue);
					if ("nome".equals(filterField)) {
						valorFiltroNome = (String) filterValue;
					}
					if ("cargo".equals(filterField)) {
						valorFiltroCargo = (String) filterValue;
					}
				}
			}
			if (valorFiltroCargo != null) {
				clausula += " and f.cargo like '%" + valorFiltroCargo + "%'";

			}
			if (valorFiltroNome != null) {
				clausula += " and f.nome like '%" + valorFiltroNome + "%'";

			}
			String campoSort = "";
			String ordemSort = "";

			if (sortBy != null && !sortBy.isEmpty()) {
				for (SortMeta meta : sortBy.values()) {
					campoSort = meta.getField();
					ordemSort = meta.getOrder().toString();
				}
			}

			System.out.println("Lazy... " + first + "/" + pageSize);
			System.out.println("Ordem: " + campoSort + " " + ordemSort.toString());
			System.out.println("Filtro: " + clausula);
			String ordem = "";

			if (!campoSort.isBlank()) {
				ordem = ordemSort.equals("ASCENDING") ? "ASC" : "DESC";

			}

			List<Funcionario> lista = dao.getList(first, pageSize,

					clausula, campoSort, ordem);

			qtd = (int) dao.qtdFuncionarios(clausula);
			setRowCount(qtd);
			System.out.println("qtd em load(): " + qtd);
			return lista;
		}

		@Override
		public int count(Map<String, FilterMeta> filterBy) {
			System.out.println("qtd em count(): " + qtd);
			return qtd;
		}

		@Override
		public Funcionario getRowData(String rowKey) {
			for (Funcionario f : funcionariosLazy) {
				if (f.getId() == Integer.parseInt(rowKey)) {
					return f;
				}
			}
			return null;
		}

		@Override
		public String getRowKey(Funcionario f) {
			return String.valueOf(f.getId());
		}
	}
}
