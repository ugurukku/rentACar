package kodlama.io.rentACar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "cars")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "plate",unique = true)
    private String plate;

    @Column(name = "daily_price")
    private BigDecimal dailyPrice;

    @Column(name = "model_year")
    private Integer modelYear;

    @Column(name = "state")
    private Integer state;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
}
