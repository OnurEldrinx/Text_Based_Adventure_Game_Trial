public interface DefaultItems {

    //Weapons
    Weapon pistol = new Weapon("Pistol",1,25,2);
    Weapon sword  = new Weapon("Sword",2,35,3);
    Weapon rifle  = new Weapon("Rifle",3,45,7);

    //Armors
    Armor lightArmor  = new Armor("Light Armor",1,15,1);
    Armor mediumArmor = new Armor("Medium Armor",2,25,3);
    Armor heavyArmor  = new Armor("Heavy Armor",3,40,5);

    //MissionItems
    Item food     = new Item("Food",1,0);
    Item firewood = new Item("Firewood",2,0);
    Item water    = new Item("Water",3,0);
    Item coal     = new Item("Coal",4,0);











}
