package models;

import java.util.Date;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dType",discriminatorType = DiscriminatorType.STRING)
public class Arma {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(precision=10, scale= 2)
	private int calibre;
	
	private int qtde_municao;
	
	@Temporal(TemporalType.DATE)
	private Date fabricacao;
	
	private String nome;
	
}
