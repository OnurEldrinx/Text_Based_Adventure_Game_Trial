import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game implements CreatedCharacters,DefaultLocations,DefaultItems {

    public Hero chooseHero(){

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Available Heroes\n----------------");
        System.out.println("1.Samurai\t\uD83D\uDC58\n2.Archer\t\uD83C\uDFF9\n3.Knight\t⚔️");

        Hero chosenHero;

        while(true){

            System.out.print("\n-> Which hero do you want to choose:");
            int heroChoice = keyboard.nextInt();


            if (heroChoice == 1){

                chosenHero = samurai;
                break;

            }else if(heroChoice == 2){

                chosenHero = archer;
                break;

            }else if(heroChoice == 3){

                chosenHero = knight;
                break;

            }else{

                chooseHero();

            }


        }

        return chosenHero;

    }

    public void start(){


        Arrays.fill(zombies,zombie);
        Arrays.fill(vampires,vampire);
        Arrays.fill(bears,bear);
        Arrays.fill(snakes,snake);

        //Hero choice and login screen
        System.out.println("☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ \n☠️  ");
        System.out.println("☠️ -> Welcome to this deserted and dangerous island!\n" +
                           "☠️ -> Your journey begins here!\n" +
                           "☠️ -> This island is about to be destroyed by the eternal war between heroes and monsters.\n☠️ ");
        System.out.println("☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ ☠️ \n");

        System.out.println("☠️ -> Choose your hero and save the island, stranger!\n");



        //Create the player in safe house.
        ArrayList<Item> playerInventory = new ArrayList<>();
        Player onur = new Player(chooseHero(),safehouse,playerInventory);
        System.out.println();

        Monster enemy;

        while (true){


            if (onur.getHero().getHealth()<=0){

                break;

            }

            onur.move(); //Updates the hero's location



            if (onur.getLocation() == forest){


                enemy = onur.chosenEnemy(forest);

                if (enemy != null){

                    onur.fight(enemy);

                }else{

                    if (forest.getMissionItem() != null){

                        //onur.getInventory().add(forest.getMissionItem());
                        forest.setMissionItem(null);

                    }

                    if (forest.isCompleted){

                        System.out.println("!!! This location is completed !!!\n!!! Check other locations out  !!!\n");


                    }


                }


            }else if (onur.getLocation() == cave){

                enemy = onur.chosenEnemy(cave);

                if (enemy != null){

                    onur.fight(enemy);

                }else{

                    if (cave.getMissionItem() != null){

                        //onur.getInventory().add(cave.getMissionItem());
                        cave.setMissionItem(null);

                    }

                    if (cave.isCompleted){

                        System.out.println("!!! This location is completed !!!\n!!! Check other locations out  !!!\n");


                    }

                }



            }else if (onur.getLocation() == river){

                enemy = onur.chosenEnemy(river);

                if (enemy != null){

                    onur.fight(enemy);

                }else{

                    if (river.getMissionItem() != null){

                        //onur.getInventory().add(river.getMissionItem());
                        river.setMissionItem(null);

                    }

                    if (river.isCompleted){

                        System.out.println("!!! This location is completed !!!\n!!! Check other locations out  !!!\n");


                    }

                }


            }else if (onur.getLocation() == mine){


                enemy = onur.chosenEnemy(mine);

                if (enemy != null){

                    onur.fight(enemy);

                }else{

                    if (mine.getMissionItem() != null){

                        //onur.getInventory().add(river.getMissionItem());
                        river.setMissionItem(null);

                    }

                    if (mine.isCompleted){

                        System.out.println("!!! This location is completed !!!\n!!! Check other locations out  !!!\n");

                    }

                }



            }else if (onur.getLocation() == safehouse){

                if ((onur.getInventory().contains(firewood) && (onur.getInventory().contains(food) && onur.getInventory().contains(water))) && onur.getInventory().contains(coal)){

                    if (onur.getLocation() == safehouse){

                        System.out.println("Congratulations.\n!!! You saved the Island !!!");
                        break;

                    }

                }


                System.out.println("\nCurrent Health: "+onur.getHero().getHealth());
                System.out.println("\uD83D\uDC89 Your health is restoring! \uD83D\uDC89");
                if (onur.getHero() == samurai){

                    onur.getHero().setHealth(defaultSamurai.getHealth());

                }else if(onur.getHero() == archer){

                    onur.getHero().setHealth(defaultArcher.getHealth());

                }else if(onur.getHero() == knight){

                    onur.getHero().setHealth(defaultKnight.getHealth());

                }
                System.out.println("\uD83D\uDC89    Health is restored!    \uD83D\uDC89");
                System.out.println("Current Health: "+onur.getHero().getHealth());

            }else if (onur.getLocation() == store){

                //Buy Item,Sell Item,Is my money enough?
                onur.buyItem(store);


            }else{

                System.out.println("Wrong Way.Check the map again!");

            }



        }






    }

}
