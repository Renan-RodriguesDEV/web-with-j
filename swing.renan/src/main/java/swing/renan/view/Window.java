package swing.renan.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import swing.renan.entities.Endereco;
import swing.renan.services.CEPServices;

public class Window extends JFrame {

	// constantes de config para interface
	private static final Color BACKGROUND_COLOR = new Color(40, 44, 52);
	private static final Color TEXT_COLOR = new Color(200, 200, 200);
	private static final Font LABEL_FONT = new Font("Segoe UI", Font.BOLD, 12);
	private static final Font VALUE_FONT = new Font("Segoe UI", Font.PLAIN, 12);

	private static JTextField cepField;
	private static JPanel resultPanel;
	private static JLabel[] labels;
	private static JLabel[] values;

	public Window() {
		setTitle("Consulta CEP");
		setupWindowProperties();

		// Painel principal com BorderLayout
		JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
		painelPrincipal.setBackground(BACKGROUND_COLOR);
		painelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));

		// Painel de pesquisa
		JPanel searchPanel = createPainelBusca();
		painelPrincipal.add(searchPanel, BorderLayout.NORTH);

		// Painel de resultados
		resultPanel = createPainelResultado();
		painelPrincipal.add(resultPanel, BorderLayout.CENTER);

		// Inicializa arrays de labels
		String[] labelTexts = { "CEP:", "Logradouro:", "Bairro:", "UF:", "Localidade:", "Complemento:" };
		labels = new JLabel[labelTexts.length];
		values = new JLabel[labelTexts.length];

		for (int i = 0; i < labelTexts.length; i++) {
			labels[i] = createLabel(labelTexts[i], LABEL_FONT);
			values[i] = createLabel("", VALUE_FONT);
			resultPanel.add(labels[i]);
			resultPanel.add(values[i]);
		}

		add(painelPrincipal);
		pack();
	}

	private void setupWindowProperties() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(600, 400));
		setLocationRelativeTo(null);
	}

	private JPanel createPainelBusca() {
		JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.LEFT));
		painelBusca.setBackground(BACKGROUND_COLOR);

		JLabel cepLabel = createLabel("CEP:", LABEL_FONT);
		cepField = new JTextField(10);
		cepField.setFont(VALUE_FONT);

		JButton searchButton = new JButton("Consultar");
		searchButton.setFont(LABEL_FONT);
		searchButton.addActionListener(e -> consultarCEP());

		painelBusca.add(cepLabel);
		painelBusca.add(cepField);
		painelBusca.add(searchButton);

		return painelBusca;
	}

	private JPanel createPainelResultado() {
		JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
		panel.setBackground(BACKGROUND_COLOR);
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(TEXT_COLOR), "Resultado",
				TitledBorder.LEFT, TitledBorder.TOP, LABEL_FONT, TEXT_COLOR));
		return panel;
	}

	private JLabel createLabel(String text, Font font) {
		JLabel label = new JLabel(text);
		label.setFont(font);
		label.setForeground(TEXT_COLOR);
		return label;
	}

	private void consultarCEP() {
		try {
			String cep = cepField.getText().trim().replaceAll("-", "");
			if (cep.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Por favor, insira um CEP", "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}

			CEPServices query = new CEPServices();
			String response = query.query(cep);
			Endereco endereco = query.JSONparseAddress(response);

			updateResultados(endereco);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao consultar CEP: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void updateResultados(Endereco endereco) {
		SwingUtilities.invokeLater(() -> {
			values[0].setText(endereco.getCep() != null ? endereco.getCep() : "Não CEP");
			values[1].setText(endereco.getLogradouro() != null ? endereco.getLogradouro() : "Não há logradouro");
			values[2].setText(endereco.getBairro() != null ? endereco.getBairro() : "Não encontrado");
			values[3].setText(endereco.getUf() != null ? endereco.getUf() : "Não Unidade federativa");
			values[4].setText(endereco.getLocalidade() != null ? endereco.getLocalidade() : "Não há localidade");
			values[5].setText(endereco.getComplemento() != null && !endereco.getComplemento().isEmpty()
					? endereco.getComplemento()
					: "Não há complemento");

			resultPanel.revalidate();
			resultPanel.repaint();
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			new Window().setVisible(true);
		});
	}
}