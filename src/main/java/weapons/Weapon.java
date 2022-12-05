package weapons;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Weapon {
    private int health;
    private int attack;
    private int defense;
    private int vampirism;
    private int healPower;
    private int rangeDamage;
}
