import java.util.Random;

public interface CreatedCharacters {

    //Heroes
    Hero samurai = new Hero("Samurai",1,5,21,15);
    Hero defaultSamurai = new Hero("Samurai",1,5,21,15);

    Hero archer  = new Hero("Archer",2,7,18,20);
    Hero defaultArcher  = new Hero("Archer",2,7,18,20);


    Hero knight  = new Hero("Knight",3,8,24,5);
    Hero defaultKnight  = new Hero("Knight",3,8,24,5);

    //Monsters
    Monster zombie  = new Monster("Zombie",1,3,10,4,DefaultLocations.cave);
    Monster defaultZombie = new Monster("Zombie",1,3,10,4,DefaultLocations.cave);

    Monster vampire = new Monster("Vampire",2,4,14,7,DefaultLocations.forest);
    Monster defaultVampire = new Monster("Vampire",2,4,14,7,DefaultLocations.forest);

    Monster bear  = new Monster("Bear",3,7,20,12,DefaultLocations.river);
    Monster defaultBear = new Monster("Bear",3,7,20,12,DefaultLocations.river);

    Random randomizer = new Random();
    int snakeDamage = randomizer.nextInt(4)+3;

    Monster snake = new Monster("Snake",4,snakeDamage,12,0,DefaultLocations.mine);
    Monster defaultSnake = new Monster("Snake",4,snakeDamage,12,0,DefaultLocations.mine);




}
