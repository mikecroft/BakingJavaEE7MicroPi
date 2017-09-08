package fish.payara.demo.BakingJavaEE8MicroPi;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.Serializable;

/**
 * @author Mike Croft
 */
public class Stock implements Serializable {

    private String symbol;

    private String description;

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

    public JsonObject toJson() {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                .add("symbol", symbol)
                .add("description", description)
                .add("price", price);
        return objectBuilder.build();
    }

    @Override
    public String toString() {
        return this.toJson().toString();
    }
}