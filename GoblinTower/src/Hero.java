import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Hero {
    private ArrayList<Integer> slots;
    private int maxSlots;
    private int maxHealth;
    private int health;
    private int attack;
    private int defence;
    private int gold;
    private int level;

    public Hero() {
        this.slots = new ArrayList<Integer>(Arrays.asList(2,2,2,2,2));
        this.maxHealth = ThreadLocalRandom.current().nextInt(20, 30 + 1);
        this.health = maxHealth;
        this.attack = ThreadLocalRandom.current().nextInt(1, 3 + 1);
        this.defence = ThreadLocalRandom.current().nextInt(1, 5 + 1);
        this.gold = 0;
        this.level = 0;
        this.maxSlots = 5;
    }
    public Hero(int gold) {
        this.slots = new ArrayList<Integer>(Arrays.asList(2,2,2,2,2));
        this.maxHealth = ThreadLocalRandom.current().nextInt(20, 30 + 1);
        this.health = maxHealth;
        this.attack = ThreadLocalRandom.current().nextInt(1, 3 + 1);
        this.defence = ThreadLocalRandom.current().nextInt(1, 5 + 1);
        this.gold = gold;
        this.level = 0;
        this.maxSlots = 5;
    }
    public boolean aliveAfterDamage(int damage)
    {
        int damageComputed = Math.max(damage - this.defence, 1);
        if(this.health - damageComputed > 0)
        {
            this.health -= damageComputed;
            return true;
        }
        else return false;
    }
    public int returnAttackPower()
    {
        return this.attack;
    }
    public int returnNumPotions()
    {
        return this.slots.size();
    }
    public int returnMaxSlots()
    {
        return this.maxSlots;
    }
    public int returnHeroGold()
    {
        return this.gold;
    }
    public void setHeroGold(int cost)
    {
        this.gold -= cost;
    }
    public void addPotions(int n)
    {
        for(int i = 0; i < n; i++)
        {
            this.slots.add(2);
        }
    }
    public void incrementLevel()
    {
        this.level += 1;
    }
    public void incrementHeroLevel(){
        this.level += 1;
    }
    public int returnHeroLevel()
    {
        return this.level;
    }
    public int returnHeroHealth()
    {
        return this.health;
    }
    public void usePotion()
    {
        this.slots.remove(this.slots.size() - 1);
        this.health = this.maxHealth;
        System.out.println("++++Health restored to: " + this.health);
    }
    public int returnMaxHealth()
    {
        return this.maxHealth;
    }
}
