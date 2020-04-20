package sll.coding.stonks.API.Objects;

import org.bukkit.entity.Player;

public class Company {

    private String name;
    private String symbol;
    private int baseStockPrice;
    private Player owner;
    private int id;
    private String icon;

    public Company(String name, String symbol, int baseStockPrice, Player owner, int id, String icon) {
        this.name = name;
        this.symbol = symbol;
        this.baseStockPrice = baseStockPrice;
        this.owner = owner;
        this.id = id;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public int getBaseStockPrice() {
        return baseStockPrice;
    }

    public Player getOwner() {
        return owner;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getId() {
        return id;
    }

    public String getIcon() {
        return icon;
    }
}
