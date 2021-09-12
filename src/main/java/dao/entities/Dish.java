package dao.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Dish {
    private int id;
    private String name;
    private int cookingTime;
    private double cost;
    private int weight;

}
