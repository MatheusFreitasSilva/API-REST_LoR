package org.example.entities;

public class Card extends _BaseEntity {
    private String name;
    private String type;
    private String region;
    private String flavor;
    private String description;
    private String levelUp;
    private String rarity;
    private int power;
    private int health;
    private int cost;
    private int setId;

    public Card(){}

    public Card(String name, String type, String region, String flavor, String description, String levelUp,
                String rarity, int power, int health, int cost, int setId) {
        this.name = name;
        this.type = type;
        this.region = region;
        this.flavor = flavor;
        this.description = description;
        this.levelUp = levelUp;
        this.rarity = rarity;
        this.power = power;
        this.health = health;
        this.cost = cost;
        this.setId = setId;
    }

    public Card(int id, String name, String type, String region, String flavor,
                String description, String levelUp, String rarity, int power, int health,
                int cost, int setId) {
        super(id);
        this.name = name;
        this.type = type;
        this.region = region;
        this.flavor = flavor;
        this.description = description;
        this.levelUp = levelUp;
        this.rarity = rarity;
        this.power = power;
        this.health = health;
        this.cost = cost;
        this.setId = setId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevelUp() {
        return levelUp;
    }

    public void setLevelUp(String levelUp) {
        this.levelUp = levelUp;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getSetId() {
        return setId;
    }

    public void setSetId(int setId) {
        this.setId = setId;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", region='" + region + '\'' +
                ", flavor='" + flavor + '\'' +
                ", description='" + description + '\'' +
                ", levelUp='" + levelUp + '\'' +
                ", rarity='" + rarity + '\'' +
                ", power=" + power +
                ", health=" + health +
                ", cost=" + cost +
                ", setId=" + setId +
                "} ";
    }
}
