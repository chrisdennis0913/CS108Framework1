package inventory;

public interface EquipItemInterface
{
    void use();
    void equip();
    void unequip();
    void drop();
    boolean isEquipped();
}
