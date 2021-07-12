package net.dohaw.magic101core.profiles;

import net.dohaw.magic101core.utils.ALL_ITEMS;
import net.dohaw.magic101core.utils.ALL_PROFILES;
import net.dohaw.magic101core.utils.DisplayHealthUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public class Profile {

    private String profileName;
    private String characterName;
    private Schools school;
    private int level;
    private Health health;
    private ProfileCreationSession session;
    private boolean active;
    private ItemStack[] equippedArmor;
    private ItemStack[] storageItems;
    private ItemStack[] extraItems;
    private Location logoutLocation;


    public Profile(String profileName, String characterName, Schools school, int level, Health health, ProfileCreationSession session, boolean active){
        this.profileName = profileName;
        this.characterName = characterName;
        this.school = school;
        this.level = level;
        this.health = health;
        this.session = session;
        this.active = active;
        this.logoutLocation = Bukkit.getWorld("world").getSpawnLocation();
    }

    //constructor for laoading
    public Profile(String profileName, String characterName, Schools school, int level,
                   Health health, ProfileCreationSession session, Location logoutLocation,
                   ItemStack[] equippedArmor, ItemStack[] storageItems, ItemStack[] extraItems) {
        this.profileName = profileName;
        this.characterName = characterName;
        this.school = school;
        this.level = level;
        this.health = health;
        this.session = session;
        this.logoutLocation = logoutLocation;
        this.equippedArmor = equippedArmor;
        this.storageItems = storageItems;
        this.extraItems = extraItems;
    }

    //eventually add inventory
    public static Profile loadProfileFromConfig(String profileName, String characterName, Schools school,
                                                int level, int maxHealth, int currentHealth, Location logoutLocation,
                                                ItemStack[] equippedArmor, ItemStack[] storageItems,  ItemStack[] extraItems){
        Health profileHealth = Health.loadHealthFromConfig(maxHealth, currentHealth);
        ProfileCreationSession session = new ProfileCreationSession(profileName,characterName,school);
        return new Profile(profileName,characterName,school,level,profileHealth,session,logoutLocation, equippedArmor, storageItems, extraItems);
    }

    public void saveProfile(Player player){
        setActive(false);
        setLogoutLocation(player.getLocation());
        setEquippedArmor(player.getEquipment().getArmorContents());
        setStorageItems(player.getInventory().getStorageContents());
        setExtraItems(player.getInventory().getExtraContents());
        player.getInventory().clear();
    }

    public void loadProfile(Player player){
        setActive(true);
        player.getEquipment().setArmorContents(equippedArmor);
        player.getInventory().setStorageContents(storageItems);
        player.getInventory().setExtraContents(extraItems);
        player.teleport(logoutLocation);
        DisplayHealthUtil.updateHealth(this, player);
    }

    public ProfileCreationSession getSession() {
        return session;
    }

    public void setSession(ProfileCreationSession session) {
        this.session = session;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Schools getSchool() {
        return school;
    }

    public void setSchool(Schools school) {
        this.school = school;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public Location getLogoutLocation() {
        return logoutLocation;
    }

    public void setLogoutLocation(Location logoutLocation) {
        this.logoutLocation = logoutLocation;
    }

    public ItemStack[] getEquippedArmor() {
        return equippedArmor;
    }

    public void setEquippedArmor(ItemStack[] equippedArmor) {
        this.equippedArmor = equippedArmor;
    }

    public ItemStack[] getStorageItems() {
        return storageItems;
    }

    public void setStorageItems(ItemStack[] storageItems) {
        this.storageItems = storageItems;
    }

    public ItemStack[] getExtraItems() {
        return extraItems;
    }

    public void setExtraItems(ItemStack[] extraItems) {
        this.extraItems = extraItems;
    }


}
