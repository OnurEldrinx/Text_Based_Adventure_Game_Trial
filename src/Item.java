public class Item implements DefaultItems,DefaultLocations,CreatedCharacters {

    private String name;
    private int id;
    private int price;

    public Item(String name, int id, int price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    //Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

class Weapon extends Item{

    private int damage;

    public Weapon(String name, int id, int price, int damage) {
        super(name, id, price);
        this.damage = damage;
    }

    //Getters & Setters
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}

class Armor extends Item{

    private int protection;

    public Armor(String name, int id, int price, int protection) {
        super(name, id, price);
        this.protection = protection;
    }

    //Getters & Setters
    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }
}