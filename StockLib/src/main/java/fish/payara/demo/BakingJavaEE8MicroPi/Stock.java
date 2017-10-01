package fish.payara.demo.BakingJavaEE8MicroPi;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbNillable;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import java.io.Serializable;

/**
 * @author Mike Croft
 */
@JsonbNillable
public class Stock implements Serializable {

    private String symbol;

    @JsonbTransient
    private String description;

    @JsonbProperty("RandomPrice")
    private double price;

    public Stock() { }

    public Stock(String symbol, String description, double price) {
        this.symbol = symbol;
        this.description = description;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private JsonObject toJson() {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                .add("symbol", symbol)
                .add("description", description)
                .add("price", price);
        return objectBuilder.build();
    }

    @Override
    public String toString() {
        JsonbConfig nillableConfig = new JsonbConfig().withNullValues(true);
        return JsonbBuilder.create(nillableConfig).toJson(this);
    }
}