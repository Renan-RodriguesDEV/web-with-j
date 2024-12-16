package models;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("Pistola")
@Entity
public class Pistola extends Arma{
	private boolean silenciador;
}
