import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Player implements DefaultItems,DefaultLocations,CreatedCharacters {

    private final Scanner keyboard = new Scanner(System.in);

    private Hero hero;
    private Location location;
    private ArrayList<Item> inventory;

    public Player(Hero hero, Location location, ArrayList<Item> inventory) {
        this.hero = hero;
        this.location = location;
        this.inventory = inventory;
    }

    public void printPlayerInfo(){

        System.out.println("Hero             : " + this.getHero().getName());
        System.out.println("Health           : " + this.getHero().getHealth());
        System.out.println("Damage           : " + this.getHero().getDamage());
        System.out.println("Current Location : " + this.getLocation().getName());
        System.out.println("Money            : " + this.getHero().getMoney());
        System.out.print("Inventory        : ");

        if (this.getInventory().size() == 0){

            System.out.print("EMPTY\n");

        }else{

            for (int i=0;i<this.getInventory().size();i++){

                System.out.print("["+this.getInventory().get(i).getName()+"]"+ " ");

            }

            System.out.println();

        }

    }

    public void move(){

        String legend = """
                
            -> Enter F for Forest
            -> Enter C for Cave
            -> Enter R for River
            -> Enter M for Mine
            -> Enter SH for Safe House
            -> Enter ST for Store
                
                """;

        System.out.println();
        this.printPlayerInfo();
        System.out.println();
        System.out.println(map);
        System.out.println(legend);

        System.out.print("Which way: ");
        String way = keyboard.nextLine().toUpperCase();

        switch (way){

            case "F":
                this.setLocation(forest);
                System.out.println("\uD83C\uDF34 You are in the Green Hell Forest! \uD83C\uDF34");
                System.out.println("Current Location : "+this.getLocation().getName()+"\n");
                break;

            case "C":
                this.setLocation(cave);
                System.out.println("⛰️You are in the Dark Hole Cave!  ⛰️");
                System.out.println("Current Location : "+this.getLocation().getName()+"\n");
                break;

            case "R":
                this.setLocation(river);
                System.out.println("\uD83D\uDC1F You are in the Blue Fall River! \uD83D\uDC1F");
                System.out.println("Current Location : "+this.getLocation().getName()+"\n");
                break;

            case "M":
                this.setLocation(mine);
                System.out.println("\uD83C\uDD7C You are in the Dark Mine! \uD83C\uDD7C");
                System.out.println("Current Location : "+this.getLocation().getName()+"\n");
                break;

            case "SH":
                this.setLocation(safehouse);
                System.out.println("\uD83D\uDECF️ You are in the Safe House! \uD83D\uDECF️");
                System.out.println("Current Location : "+this.getLocation().getName()+"\n");
                break;

            case "ST":
                this.setLocation(store);
                System.out.println("\uD83D\uDED2 You are in the Island Store! \uD83D\uDED2");
                System.out.println("Current Location : "+this.getLocation().getName());
                System.out.println("Your Balance     : "+this.getHero().getMoney()+"\n");
                break;

            default:
                this.setLocation(safehouse);
                System.out.println("Wrong way.Check the map again.");
                System.out.println("Current Location : "+this.getLocation().getName()+"\n");
                break;
        }


    }

    public void buyItem(Store store){

        Scanner keyboard = new Scanner(System.in);

        store.showStoreInventory();

        System.out.print("\nDo you want to buy a weapon or armor(Press [W]:Weapon | [A]:Armor | [Q]:Exit) :");
        String choice1 = keyboard.nextLine().toUpperCase();

        switch (choice1) {

            case "W" -> {
                System.out.print("Which weapon do you want to buy:");
                int weaponChoice = keyboard.nextInt();

                if (this.getHero().getMoney() >= store.getWeaponList()[weaponChoice - 1].getPrice()){

                    this.getInventory().add(store.getWeaponList()[weaponChoice - 1]);
                    System.out.println(store.getWeaponList()[weaponChoice - 1].getName() + " is added to your inventory.");
                    this.getHero().setMoney(this.getHero().getMoney()-store.getWeaponList()[weaponChoice - 1].getPrice());
                    int extraDamage = store.getWeaponList()[weaponChoice - 1].getDamage();
                    this.getHero().setDamage(this.getHero().getDamage() + extraDamage);

                }else{

                    System.out.println("You don't have enough money to buy this weapon!");

                }

                buyItem(store);

            }
            case "A" -> {
                System.out.print("Which armor do you want to buy:");
                int armorChoice = keyboard.nextInt();

                if (this.getHero().getMoney() >= store.getArmorList()[armorChoice - 1].getPrice()){

                    this.getInventory().add(store.getArmorList()[armorChoice - 1]);
                    System.out.println(store.getArmorList()[armorChoice - 1].getName() + " is added to your inventory.");
                    this.getHero().setMoney(this.getHero().getMoney()-store.getArmorList()[armorChoice - 1].getPrice());
                    int extraProtection = store.getArmorList()[armorChoice - 1].getProtection();
                    this.getHero().setHealth(this.getHero().getHealth() + extraProtection);

                }else{

                    System.out.println("You don't have enough money to buy this armor!");

                }

                buyItem(store);

            }
            case "Q" -> {

                System.out.println("Goodbye! Visit us again!");
                System.out.println("Current Location : "+this.getLocation().getName()+"\n");


            }
            default -> {

                System.out.println("!!! Wrong Product Input !!!");
                buyItem(store);

            }

        }


    }

    public Monster chosenEnemy(BattlegroundLocation location){

        Scanner input = new Scanner(System.in);

        //Print enemies' numbers
        if (location.getMonsters().length == 1){

            System.out.println("There is just 1 enemy in this " +location.getName());

        }else{


            System.out.println("There are "+location.getMonsters().length+" enemies in this "+location.getName());


        }

            if (location.getMonsters().length == 0){

                location.isCompleted = true;
                return null;

            }else{

                location.isCompleted = false;

                System.out.println("\nEnemies\n-------");
                for (int i=0;i<location.getMonsters().length;i++){

                    System.out.println("-> "+location.getMonsters()[i].getName()+"-"+(i+1));

                }


                System.out.print("\nWhich enemy do you choose: ");
                int enemyChoice = input.nextInt();
                input.nextLine();
                return location.getMonsters()[enemyChoice-1];

            }


    }


    public void fight(Monster enemy){

        Random randomizer = new Random();

        enemy.setLocation((BattlegroundLocation)this.getLocation());

        int heroDamage = this.hero.getDamage();
        int enemyDamage = enemy.getDamage();
        int defaultEnemyHealth = enemy.getHealth();

        Monster[] updated;

        while (true){


            int whoHitsFirst = randomizer.nextInt(2);// 0:Hero hits first or 1:Monster hits first

            if (whoHitsFirst == 0){

                    //Hero attacked
                    System.out.println("You the "+this.getHero().getName() +" attacked and hit "+heroDamage+" damage to "+enemy.getName());
                    enemy.setHealth(enemy.getHealth()-heroDamage);

                    //Is someone dead?
                    if (enemy.getHealth() <= 0 ){

                        System.out.println("\nYou killed the enemy.");
                        this.getHero().setMoney(this.getHero().getMoney()+enemy.getMoney());
                        System.out.println(enemy.getMoney()+" Coin -> Added to your inventory.");

                        //Prize for Snakes
                        if (enemy.getName().equals("Snake")){

                            int prizeChance = randomizer.nextInt(100)+1;
                            int weaponArmorMoneyChance = randomizer.nextInt(10)+1;

                            if (prizeChance <= 15){

                                //Weapon Prize
                                if (weaponArmorMoneyChance<=2){

                                    this.getInventory().add(rifle);
                                    this.getHero().setDamage(this.getHero().getDamage()+rifle.getDamage());
                                    System.out.println("Snake dropped a rifle.");

                                }else if (weaponArmorMoneyChance<=5){

                                    this.getInventory().add(sword);
                                    this.getHero().setDamage(this.getHero().getDamage()+sword.getDamage());
                                    System.out.println("Snake dropped a sword.");

                                }else{

                                    this.getInventory().add(pistol);
                                    this.getHero().setDamage(this.getHero().getDamage()+pistol.getDamage());
                                    System.out.println("Snake dropped a pistol.");

                                }

                            }else if (prizeChance<=30){

                                //Armor Prize
                                if (weaponArmorMoneyChance<=2){

                                    this.getInventory().add(heavyArmor);
                                    this.getHero().setHealth(this.getHero().getHealth()+heavyArmor.getProtection());
                                    System.out.println("Snake dropped a heavy armor.");

                                }else if (weaponArmorMoneyChance<=5){

                                    this.getInventory().add(mediumArmor);
                                    this.getHero().setHealth(this.getHero().getHealth()+mediumArmor.getProtection());
                                    System.out.println("Snake dropped a medium armor.");

                                }else{

                                    this.getInventory().add(lightArmor);
                                    this.getHero().setHealth(this.getHero().getHealth()+lightArmor.getProtection());
                                    System.out.println("Snake dropped a light armor.");

                                }

                            }else if (prizeChance<=55){

                                //Money Prize
                                if (weaponArmorMoneyChance<=2){

                                    this.getHero().setMoney(this.getHero().getMoney()+10);
                                    System.out.println("Snake dropped 10 coins.");

                                }else if (weaponArmorMoneyChance<=5){

                                    this.getHero().setMoney(this.getHero().getMoney()+5);
                                    System.out.println("Snake dropped 5 coins.");

                                }else{

                                    this.getHero().setMoney(this.getHero().getMoney()+1);
                                    System.out.println("Snake dropped 1 coin.");

                                }

                            }else{

                                System.out.println("The snake dropped nothing!");

                            }

                        }


                        updated = new Monster[enemy.getLocation().getMonsters().length - 1];

                        if (enemy.getId()==1){

                            Arrays.fill(updated,defaultZombie);


                        }else if (enemy.getId()==2){

                            Arrays.fill(updated,defaultVampire);


                        }else if (enemy.getId()==3){

                            Arrays.fill(updated,defaultBear);


                        }else if (enemy.getId()==4){

                            Arrays.fill(updated,defaultSnake);


                        }

                        enemy.getLocation().setMonsters(updated);

                        if (enemy.getLocation().getMonsters().length == 0){

                            enemy.getLocation().isCompleted = true;
                            this.getInventory().add(enemy.getLocation().getMissionItem());
                            System.out.println("\nYou found the mission item : \""+enemy.getLocation().getMissionItem().getName()+"\"");

                        }

                        break;

                    }else if (this.getHero().getHealth() <= 0){

                        System.out.println("\n\uD83D\uDD71 Your journey ends here.\n\uD83D\uDD71 You are dead.");
                        break;
                    }

                    System.out.println("Your Health: "+this.getHero().getHealth());
                    System.out.println(enemy.getName()+" Health: "+enemy.getHealth());


                    //Enemy attacked
                    System.out.println(enemy.getName() +" attacked and hit "+enemyDamage+" damage to you the "+this.getHero().getName());
                    this.getHero().setHealth(this.getHero().getHealth()-enemyDamage);

                    //Is someone dead?


            }else{

                    //Enemy attacked
                    System.out.println(enemy.getName() +" attacked and hit "+enemyDamage+" damage to you the "+this.getHero().getName());
                    this.getHero().setHealth(this.getHero().getHealth()-enemyDamage);

                    //Is someone dead?
                    if (enemy.getHealth() <= 0 ){

                        System.out.println("\nYou killed the enemy.");
                        this.getHero().setMoney(this.getHero().getMoney()+enemy.getMoney());
                        System.out.println(enemy.getMoney()+" Coin -> Added to your inventory.");

                        //Prize for Snakes
                        if (enemy.getName().equals("Snake")){

                            int prizeChance = randomizer.nextInt(100)+1;
                            int weaponArmorMoneyChance = randomizer.nextInt(10)+1;

                            if (prizeChance <= 15){

                                //Weapon Prize
                                if (weaponArmorMoneyChance<=2){

                                    this.getInventory().add(rifle);
                                    this.getHero().setDamage(this.getHero().getDamage()+rifle.getDamage());
                                    System.out.println("Snake dropped a rifle.");

                                }else if (weaponArmorMoneyChance<=5){

                                    this.getInventory().add(sword);
                                    this.getHero().setDamage(this.getHero().getDamage()+sword.getDamage());
                                    System.out.println("Snake dropped a sword.");

                                }else{

                                    this.getInventory().add(pistol);
                                    this.getHero().setDamage(this.getHero().getDamage()+pistol.getDamage());
                                    System.out.println("Snake dropped a pistol.");

                                }

                            }else if (prizeChance<=30){

                                //Armor Prize
                                if (weaponArmorMoneyChance<=2){

                                    this.getInventory().add(heavyArmor);
                                    this.getHero().setHealth(this.getHero().getHealth()+heavyArmor.getProtection());
                                    System.out.println("Snake dropped a heavy armor.");

                                }else if (weaponArmorMoneyChance<=5){

                                    this.getInventory().add(mediumArmor);
                                    this.getHero().setHealth(this.getHero().getHealth()+mediumArmor.getProtection());
                                    System.out.println("Snake dropped a medium armor.");

                                }else{

                                    this.getInventory().add(lightArmor);
                                    this.getHero().setHealth(this.getHero().getHealth()+lightArmor.getProtection());
                                    System.out.println("Snake dropped a light armor.");

                                }

                            }else if (prizeChance<=55){

                                //Money Prize
                                if (weaponArmorMoneyChance<=2){

                                    this.getHero().setMoney(this.getHero().getMoney()+10);
                                    System.out.println("Snake dropped 10 coins.");

                                }else if (weaponArmorMoneyChance<=5){

                                    this.getHero().setMoney(this.getHero().getMoney()+5);
                                    System.out.println("Snake dropped 5 coins.");

                                }else{

                                    this.getHero().setMoney(this.getHero().getMoney()+1);
                                    System.out.println("Snake dropped 1 coin.");

                                }

                            }else{

                                System.out.println("The snake dropped nothing!");

                            }

                        }

                        updated = new Monster[enemy.getLocation().getMonsters().length - 1];

                        if (enemy.getId()==1){

                            Arrays.fill(updated,defaultZombie);


                        }else if (enemy.getId()==2){

                            Arrays.fill(updated,defaultVampire);


                        }else if (enemy.getId()==3){

                            Arrays.fill(updated,defaultBear);


                        }else if (enemy.getId()==4){

                            Arrays.fill(updated,defaultSnake);

                        }

                        enemy.getLocation().setMonsters(updated);

                        if (enemy.getLocation().getMonsters().length == 0){

                            enemy.getLocation().isCompleted = true;
                            this.getInventory().add(enemy.getLocation().getMissionItem());
                            System.out.println("\nYou found the mission item : \""+enemy.getLocation().getMissionItem().getName()+"\"");

                        }

                        break;

                    }else if (this.getHero().getHealth() <= 0){

                        System.out.println("\n\uD83D\uDD71 Your journey ends here.\n\uD83D\uDD71 You are dead.");
                        break;
                    }

                    System.out.println("Your Health: "+this.getHero().getHealth());
                    System.out.println(enemy.getName()+" Health: "+enemy.getHealth());


                    //Hero attacked
                    System.out.println("You the "+this.getHero().getName() +" attacked and hit "+heroDamage+" damage to "+enemy.getName());
                    enemy.setHealth(enemy.getHealth()-heroDamage);


                    //Is someone dead?


            }

            if (enemy.getHealth() <= 0 ){

                System.out.println("\nYou killed the enemy.");
                this.getHero().setMoney(this.getHero().getMoney()+enemy.getMoney());
                System.out.println(enemy.getMoney()+" Coin -> Added to your inventory.");

                //Prize for Snakes
                if (enemy.getName().equals("Snake")){

                    int prizeChance = randomizer.nextInt(100)+1;
                    int weaponArmorMoneyChance = randomizer.nextInt(10)+1;

                    if (prizeChance <= 15){

                        //Weapon Prize
                        if (weaponArmorMoneyChance<=2){

                            this.getInventory().add(rifle);
                            this.getHero().setDamage(this.getHero().getDamage()+rifle.getDamage());
                            System.out.println("Snake dropped a rifle.");

                        }else if (weaponArmorMoneyChance<=5){

                            this.getInventory().add(sword);
                            this.getHero().setDamage(this.getHero().getDamage()+sword.getDamage());
                            System.out.println("Snake dropped a sword.");

                        }else{

                            this.getInventory().add(pistol);
                            this.getHero().setDamage(this.getHero().getDamage()+pistol.getDamage());
                            System.out.println("Snake dropped a pistol.");

                        }

                    }else if (prizeChance<=30){

                        //Armor Prize
                        if (weaponArmorMoneyChance<=2){

                            this.getInventory().add(heavyArmor);
                            this.getHero().setHealth(this.getHero().getHealth()+heavyArmor.getProtection());
                            System.out.println("Snake dropped a heavy armor.");

                        }else if (weaponArmorMoneyChance<=5){

                            this.getInventory().add(mediumArmor);
                            this.getHero().setHealth(this.getHero().getHealth()+mediumArmor.getProtection());
                            System.out.println("Snake dropped a medium armor.");

                        }else{

                            this.getInventory().add(lightArmor);
                            this.getHero().setHealth(this.getHero().getHealth()+lightArmor.getProtection());
                            System.out.println("Snake dropped a light armor.");

                        }

                    }else if (prizeChance<=55){

                        //Money Prize
                        if (weaponArmorMoneyChance<=2){

                            this.getHero().setMoney(this.getHero().getMoney()+10);
                            System.out.println("Snake dropped 10 coins.");

                        }else if (weaponArmorMoneyChance<=5){

                            this.getHero().setMoney(this.getHero().getMoney()+5);
                            System.out.println("Snake dropped 5 coins.");

                        }else{

                            this.getHero().setMoney(this.getHero().getMoney()+1);
                            System.out.println("Snake dropped 1 coin.");

                        }

                    }else{

                        System.out.println("The snake dropped nothing!");

                    }

                }

                updated = new Monster[enemy.getLocation().getMonsters().length - 1];

                if (enemy.getId()==1){

                    Arrays.fill(updated,defaultZombie);


                }else if (enemy.getId()==2){

                    Arrays.fill(updated,defaultVampire);


                }else if (enemy.getId()==3){

                    Arrays.fill(updated,defaultBear);


                }else if (enemy.getId()==4){

                    Arrays.fill(updated,defaultSnake);


                }

                enemy.getLocation().setMonsters(updated);

                if (enemy.getLocation().getMonsters().length == 0){

                    enemy.getLocation().isCompleted = true;
                    this.getInventory().add(enemy.getLocation().getMissionItem());
                    System.out.println("\nYou found the mission item : \""+enemy.getLocation().getMissionItem().getName()+"\"");

                }

                break;

            }else if (this.getHero().getHealth() <=0){

                System.out.println("\n\uD83D\uDD71 Your journey ends here.\n\uD83D\uDD71 You are dead.");
                break;
            }
            System.out.println("Your Health: "+this.getHero().getHealth());
            System.out.println(enemy.getName()+" Health: "+enemy.getHealth());


        }



    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ArrayList<Item> getInventory() { return inventory; }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }
}
