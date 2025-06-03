package mbeans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;
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
	private Funcionario funcionario;
	private Funcionario funcionarioSelecionado;
	private List<Funcionario> funcionarios;
	private FuncionarioDAO dao = new FuncionarioDAO();
	private UploadedFile arquivoFoto;
	private String cmd = "adicionar";
	private LazyDataModel<Funcionario> funcionariosLazy;

	public FuncionarioPrimeMBean() {
		funcionariosLazy = new ModeloFuncionarioLazy<Funcionario>();
	}

	@PostConstruct
	public void init() {
		if (funcionario == null) {
			funcionario = new Funcionario();
		}
		if (funcionarioSelecionado == null) {
			funcionarioSelecionado = new Funcionario();
		}
	}

	// Getters e Setters com verificação de null
	public Funcionario getFuncionario() {
		if (funcionario == null) {
			funcionario = new Funcionario();
		}
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Funcionario getFuncionarioSelecionado() {
		if (funcionarioSelecionado == null) {
			funcionarioSelecionado = new Funcionario();
		}
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
		// Quando selecionar um funcionário, copia para edição
		if (funcionarioSelecionado != null && funcionarioSelecionado.getId() != null) {
			this.funcionario = new Funcionario();
			this.funcionario.setId(funcionarioSelecionado.getId());
			this.funcionario.setNome(funcionarioSelecionado.getNome());
			this.funcionario.setCargo(funcionarioSelecionado.getCargo());
			this.funcionario.setSalario(funcionarioSelecionado.getSalario());
			this.funcionario.setDataAdm(funcionarioSelecionado.getDataAdm());
			this.funcionario.setAtivo(funcionarioSelecionado.isAtivo());
			this.funcionario.setFoto(funcionarioSelecionado.getFoto());
		}
	}

	public UploadedFile getArquivoFoto() {
		return arquivoFoto;
	}

	public void setArquivoFoto(UploadedFile arquivoFoto) {
		this.arquivoFoto = arquivoFoto;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	// Método para upload de foto
	public void uploadFoto(FileUploadEvent evt) {
		try {
			arquivoFoto = evt.getFile();
			if (arquivoFoto != null && arquivoFoto.getContent() != null) {
				if (funcionario == null) {
					funcionario = new Funcionario();
				}
				funcionario.setFoto(arquivoFoto.getContent());

				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"✅ Sucesso!", "Foto carregada com sucesso!"));
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"❌ Erro!", "Erro ao carregar a foto: " + e.getMessage()));
		}
	}

	// Lista de funcionários
	public List<Funcionario> getFuncionarios() {
		if (this.funcionarios == null) {
			this.funcionarios = dao.getList();
		}
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	// Método para novo funcionário
	public void limpaFuncionario() {
		cmd = "adicionar";
		funcionario = new Funcionario();
		arquivoFoto = null;

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"ℹ️ Novo Funcionário", "Formulário limpo para novo cadastro."));
	}

	// Método para editar funcionário
	public void copiaFuncionario() {
		cmd = "editar";
		if (funcionarioSelecionado != null && funcionarioSelecionado.getId() != null) {
			// Criar uma nova instância para evitar problemas de referência
			funcionario = new Funcionario();
			funcionario.setId(funcionarioSelecionado.getId());
			funcionario.setNome(funcionarioSelecionado.getNome());
			funcionario.setCargo(funcionarioSelecionado.getCargo());
			funcionario.setSalario(funcionarioSelecionado.getSalario());
			funcionario.setDataAdm(funcionarioSelecionado.getDataAdm());
			funcionario.setAtivo(funcionarioSelecionado.isAtivo());
			funcionario.setFoto(funcionarioSelecionado.getFoto());

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"✏️ Editar", "Funcionário carregado para edição."));
		} else {
			funcionario = new Funcionario();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"⚠️ Atenção!", "Selecione um funcionário para editar."));
		}
		arquivoFoto = null;
	}

	// Método para salvar funcionário
	public void salvar() {
		try {
			if (funcionario == null) {
				funcionario = new Funcionario();
			}

			// Se há arquivo de foto, adiciona ao funcionário
			if (arquivoFoto != null && arquivoFoto.getContent() != null) {
				funcionario.setFoto(arquivoFoto.getContent());
			}

			// Validação básica
			if (funcionario.getNome() == null || funcionario.getNome().trim().isEmpty()) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"❌ Erro!", "Nome é obrigatório."));
				return;
			}

			if (cmd == null || cmd.equals("adicionar")) {
				dao.inserir(funcionario);
				if (funcionarios != null) {
					funcionarios.add(funcionario);
				}
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"✅ Sucesso!", "Funcionário cadastrado com sucesso!"));
			} else {
				dao.salvar(funcionario);
				// Atualizar a lista
				funcionarios = null; // Força recarregamento
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"✅ Sucesso!", "Funcionário atualizado com sucesso!"));
			}

			// Limpa o formulário após salvar
			funcionario = new Funcionario();
			arquivoFoto = null;
			cmd = "adicionar";

		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"❌ Erro!", "Erro ao salvar funcionário: " + e.getMessage()));
		}
	}

	// Método para excluir funcionário
	public void excluir() {
		try {
			if (funcionarioSelecionado != null && funcionarioSelecionado.getId() != null) {
				dao.excluir(funcionarioSelecionado.getId());

				if (this.funcionarios != null) {
					this.funcionarios.remove(funcionarioSelecionado);
				}

				// Limpa seleção
				funcionarioSelecionado = new Funcionario();
				funcionario = new Funcionario();

				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"✅ Sucesso!", "Funcionário excluído com sucesso!"));
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"⚠️ Atenção!", "Selecione um funcionário para excluir."));
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"❌ Erro!", "Erro ao excluir funcionário: " + e.getMessage()));
		}
	}

	// Lazy DataModel
	public LazyDataModel<Funcionario> getFuncionariosLazy() {
		return funcionariosLazy;
	}

	public void setFuncionariosLazy(LazyDataModel<Funcionario> funcionariosLazy) {
		this.funcionariosLazy = funcionariosLazy;
	}

	// Classe interna para LazyDataModel
	class ModeloFuncionarioLazy<T> extends LazyDataModel<Funcionario> {
		private static final long serialVersionUID = 1L;
		private int qtd = 0;

		@Override
		public List<Funcionario> load(int first, int pageSize,
				Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {

			String clausula = "where 1 = 1 ";
			String valorFiltroNome = null;
			String valorFiltroCargo = null;

			// Processa filtros
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

			// Constrói cláusula WHERE
			if (valorFiltroCargo != null && !valorFiltroCargo.trim().isEmpty()) {
				clausula += " and f.cargo like '%" + valorFiltroCargo + "%'";
			}
			if (valorFiltroNome != null && !valorFiltroNome.trim().isEmpty()) {
				clausula += " and f.nome like '%" + valorFiltroNome + "%'";
			}

			// Processa ordenação
			String campoSort = "";
			String ordemSort = "";
			if (sortBy != null && !sortBy.isEmpty()) {
				for (SortMeta meta : sortBy.values()) {
					campoSort = meta.getField();
					ordemSort = meta.getOrder().toString();
				}
			}

			System.out.println("Lazy... " + first + "/" + pageSize);
			System.out.println("Ordem: " + campoSort + " " + ordemSort);
			System.out.println("Filtro: " + clausula);

			String ordem = "";
			if (campoSort != null && !campoSort.isBlank()) {
				ordem = ordemSort.equals("ASCENDING") ? "ASC" : "DESC";
			}

			// Busca dados paginados
			List<Funcionario> lista = dao.getList(first, pageSize, clausula, campoSort, ordem);
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
			try {
				if (rowKey != null && !rowKey.isEmpty()) {
					int id = Integer.parseInt(rowKey);
					// Busca na lista atual carregada
					List<Funcionario> currentData = (List<Funcionario>) getWrappedData();
					if (currentData != null) {
						for (Funcionario f : currentData) {
							if (f.getId() != null && f.getId() == id) {
								return f;
							}
						}
					}
					// Se não encontrar na lista atual, busca no banco
					return dao.buscarPorId(id);
				}
			} catch (NumberFormatException e) {
				System.err.println("Erro ao converter rowKey: " + rowKey);
			}
			return null;
		}

		@Override
		public String getRowKey(Funcionario f) {
			return f != null && f.getId() != null ? String.valueOf(f.getId()) : null;
		}
	}
}