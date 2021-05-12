import java.util.concurrent.ThreadLocalRandom;

public class Goblin {
    private int health;
    private int attack;
    private int defence;

    public Goblin()
    {
        this.health = ThreadLocalRandom.current().nextInt(5, 10 + 1);
        this.attack = ThreadLocalRandom.current().nextInt(2, 3 + 1);
        this.defence = ThreadLocalRandom.current().nextInt(1, 2 + 1);
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
}
