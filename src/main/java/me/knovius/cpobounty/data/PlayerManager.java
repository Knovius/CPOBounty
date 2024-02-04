package me.knovius.cpobounty.data;

import me.knovius.cpobounty.CPOBounty;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class PlayerManager {

    private CPOBounty plugin;

    private HashMap<UUID, Double> bountyMap;

    private File file;
    private YamlConfiguration configuration;

    public PlayerManager(CPOBounty plugin) {
        this.plugin = plugin;
        bountyMap = new HashMap<>();
        file = new File(plugin.getDataFolder(), "data.yml");
        configuration = YamlConfiguration.loadConfiguration(file);
        loadData();
    }

    //methods
    public void addBounty(Player player, Double amount) {
        if (bountyMap.containsKey(player.getUniqueId())) {
            double currentBounty = bountyMap.get(player.getUniqueId());
            bountyMap.put(player.getUniqueId(), currentBounty+amount);
            saveData(player);
            return;
        }
        bountyMap.put(player.getUniqueId(), amount);
        saveData(player);
    }

    public void removeBounty(Player player, Double amount) {
        if (!(bountyMap.containsKey(player.getUniqueId()))) return;
        double currentBounty = bountyMap.get(player.getUniqueId());
        if (currentBounty - amount < 0) {
            bountyMap.put(player.getUniqueId(), 0.0);
            return;
        }
        bountyMap.put(player.getUniqueId(), currentBounty -amount);
    }

    public void setBounty(Player player, Double amount) {
        bountyMap.put(player.getUniqueId(), amount);
        saveData(player);
    }

    public HashMap<UUID, Double> getBountyMap(){
        return bountyMap;
    }

    //Data

    public void saveData(Player player) {
        if (!(bountyMap.containsKey(player.getUniqueId()))) return;
        configuration.set(player.getUniqueId().toString(), bountyMap.get(player.getUniqueId()));
        try {
            configuration.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void loadData() {
        for (String section: configuration.getKeys(false)) {
            bountyMap.put(UUID.fromString(section), configuration.getDouble(section));
            Bukkit.broadcastMessage(section + ": " + configuration.getDouble(section));
        }
    }
    public List<Map.Entry<UUID, Double>> getTopBounties(int count) {
        List<Map.Entry<UUID, Double>> bountyList = new ArrayList<>(CPOBounty.getInstance().getPlayerManager().getBountyMap().entrySet());
        bountyList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return bountyList.subList(0, Math.min(count, bountyList.size()));
    }
    public String format(double value) {
        DecimalFormat formatter;

        if(value - (int)value > 0.0)
            formatter = new DecimalFormat("###,###,###,###,###,###,###.###"); // Here you can also deal with rounding if you wish..
        else
            formatter = new DecimalFormat("###,###,###,###,###,###,###");

        return formatter.format(value);
    }
}
