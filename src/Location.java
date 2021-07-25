import java.util.Arrays;

public class Location implements DefaultLocations,DefaultItems,CreatedCharacters {

    private String name;

    public Location(String name) {
        this.name = name;
    }

    //Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

class NormalLocation extends Location{

    public NormalLocation(String name) {
        super(name);
    }
}
class SafeHouse extends NormalLocation{

    public SafeHouse(String name) {
        super(name);
    }


}
class Store extends NormalLocation implements DefaultItems{

    private Weapon[] weaponList = {pistol,sword,rifle};
    private Armor[] armorList  = {lightArmor,mediumArmor,heavyArmor};

    public void showStoreInventory(){


        System.out.println("Armors\n------------------");
        for (int i=0;i<this.getArmorList().length;i++){

            System.out.println((i+1)+"."+this.getArmorList()[i].getName()+" \t->\t"+this.getArmorList()[i].getPrice()+" Coin");

        }
        System.out.println("\nWeapons\n------------------");
        for (int i=0;i<this.getWeaponList().length;i++){

            System.out.println((i+1)+"."+this.getWeaponList()[i].getName()+" \t->\t"+this.getWeaponList()[i].getPrice()+" Coin");

        }

    }

    public Store(String name) {
        super(name);
    }

    public Weapon[] getWeaponList() {
        return weaponList;
    }

    public void setWeaponList(Weapon[] weaponList) {
        this.weaponList = weaponList;
    }

    public Armor[] getArmorList() {
        return armorList;
    }

    public void setArmorList(Armor[] armorList) {
        this.armorList = armorList;
    }
}

class BattlegroundLocation extends Location{

    private Item missionItem;
    private Monster[] monsters;
    public boolean isCompleted;

    public BattlegroundLocation(String name, Item missionItem, Monster[] monsters) {
        super(name);
        this.missionItem = missionItem;
        this.monsters = monsters;
    }

    //Getters & Setters
    public Item getMissionItem() {
        return missionItem;
    }

    public void setMissionItem(Item missionItem) {
        this.missionItem = missionItem;
    }

    public Monster[] getMonsters() {
        return monsters;
    }

    public void setMonsters(Monster[] monsters) {
        this.monsters = monsters;
    }
}

class Cave extends BattlegroundLocation{

    public Cave(String name, Item missionItem, Monster[] monsters) {
        super(name, missionItem, monsters);

        //Arrays.fill(monsters, zombie);

    }

}

class Forest extends BattlegroundLocation{

    public Forest(String name, Item missionItem, Monster[] monsters) {
        super(name, missionItem, monsters);

        //Arrays.fill(monsters, vampire);


    }
}

class River extends BattlegroundLocation{

    public River(String name, Item missionItem, Monster[] monsters) {
        super(name, missionItem, monsters);

        //Arrays.fill(monsters, bear);


    }
}

class Mine extends BattlegroundLocation{

    public Mine(String name, Item missionItem, Monster[] monsters) {
        super(name, missionItem, monsters);

        //Arrays.fill(monsters,snake);

    }

}
