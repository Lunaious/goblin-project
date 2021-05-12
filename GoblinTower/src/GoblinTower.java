import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;
public class GoblinTower {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        Hero h1 = new Hero();
        int goblinsSlain = 0, steps = 0;
        boolean continuePlaying = true, isAlive = true;
        while(continuePlaying)
        {
            while (isAlive)
            {
                if(steps > 0 && steps % 10 == 0)
                {
                    h1.incrementLevel();
                    System.out.println("You currently have " + h1.returnNumPotions() + " potions & your capacity is " + h1.returnMaxSlots());
                    System.out.println("Would you like to visit the potion shop? (Enter yes or no) ");
                    String toThePotionShop = input.next();
                    toThePotionShop = toThePotionShop.toLowerCase(Locale.ROOT);

                    if(toThePotionShop.equals("yes"))   potionShop(h1);
                    h1.incrementLevel();
                }
                int encounteredGoblin = ThreadLocalRandom.current().nextInt(1, 2 + 1);
                if(encounteredGoblin == 1)  // Encountered goblin
                {
                    System.out.println("You've encountered a goblin" + (goblinsSlain + 1) + " FIGHT!");
                    Goblin g1 = new Goblin();
                    isAlive = fight(h1, g1);
                    if(isAlive)
                    {
                        goblinsSlain++;
                        System.out.println("You survived! ");
                        System.out.println("Your max health is: " + h1.returnMaxHealth());
                        System.out.println("Your hero's health is: " + h1.returnHeroHealth());
                        if(h1.returnNumPotions() > 0)
                        {
                            System.out.println("You currently have: " + h1.returnNumPotions() + " potions");
                            System.out.println("Would you like to use a potion? (Yes or No)");
                            String responseToUsingPotion = input.next();
                            responseToUsingPotion = responseToUsingPotion.toLowerCase(Locale.ROOT);
                            if(responseToUsingPotion.equals("yes")) h1.usePotion();
                            System.out.println();
                        }
                    }
                }
                if(isAlive)
                    steps++;
            }
            if(isAlive == false)
            {
                System.out.println("You died... Would you like to continue playing? ");
                String playAgain = input.next();
                playAgain = playAgain.toLowerCase(Locale.ROOT);

                if(playAgain.equals("yes"))
                {
                    int prevHeroGold = h1.returnHeroGold();
                    h1 = new Hero(prevHeroGold);
                    isAlive = true;
                }
                else
                    continuePlaying = false;
            }
        }
        System.out.println("~Game Over~");
        System.out.println("You reached level: " + h1.returnHeroLevel() + " and you slayed " + goblinsSlain + " globins!");
    }
    public static boolean fight(Hero h1, Goblin g1)
    {
        boolean heroIsAlive = true, goblinIsAlive = true;
        while(heroIsAlive && goblinIsAlive)
        {
            goblinIsAlive = g1.aliveAfterDamage(h1.returnAttackPower());
            if(goblinIsAlive)
                heroIsAlive = h1.aliveAfterDamage(g1.returnAttackPower());
        }
        return heroIsAlive;
    }

    public static void potionShop(Hero h1)
    {
        if(h1.returnHeroGold() > 4)
            System.out.println("Whoops.. you don't have enough gold to buy any potions. Back to the battle we go.");
        else if(h1.returnNumPotions() == h1.returnMaxSlots())
            System.out.println("Your inventory is full, you can't buy anymore potions. Back to the battle we go.");
        else
        {
            int numPotions = Math.min(h1.returnHeroGold() % 4, h1.returnMaxSlots() - h1.returnNumPotions());
            h1.addPotions(numPotions);
            System.out.println("You bought: " + numPotions);
            System.out.println("Back to battle!");
        }
    }
}
