import java.util.Random;

public interface DefaultLocations extends DefaultItems,CreatedCharacters {

    SafeHouse safehouse = new SafeHouse("Safe House");
    Store store = new Store("Store");


    Random randomizer = new Random();
    int howManyZombies = randomizer.nextInt(3)+1;
    int howManyVampires = randomizer.nextInt(3)+1;
    int howManyBears = randomizer.nextInt(3)+1;
    int howManySnakes = randomizer.nextInt(5)+1;

    Monster[] zombies = new Monster[howManyZombies];
    Monster[] vampires = new Monster[howManyVampires];
    Monster[] bears = new Monster[howManyBears];
    Monster[] snakes = new Monster[howManySnakes];




    Cave cave = new Cave("Cave",food,zombies);
    Forest forest = new Forest("Forest",firewood,vampires);
    River river = new River("River",water,bears);
    Mine mine = new Mine("Mine",coal,snakes);



    //Map
    String map = """
                        
            ---------- GEOMAP ----------\s
                  
                       FOREST         \s
                         \uD83E\uDC81         \s
               CAVE   \uD83E\uDC80    \uD83E\uDC82   RIVER
                       \uD83E\uDC87   \uD83E\uDC86          \s
               SAFE H.   🢃    STORE\s
                        MINE

            -----------------------------
            """;


}
