package codeutilities.notcoded.commands;

import java.util.ArrayList;
import java.util.List;

import codeutilities.notcoded.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

//  ________________
// |                |
// |    Made by:    |
// |  ~ NotCoded ~  |
// |                |
// | Code Utilities |
// | - Ver 1.0.1 -  |
// |________________|
//

public class fly implements TabExecutor {

    String prefix = Config.PREFIX;
    String noperms = Config.NOPERMS;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("fly")) {
            if (args.length == 0) {
                flyMethod(player, player);
                return true;
            }
            if (args.length == 1) {
                if (player.hasPermission("codeutilities.fly.others")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        flyMethod(target, player);
                        return true;
                    } else {
                        player.sendMessage(prefix + " §6That player isn't online!");
                        return false;
                    }
                } else {
                    player.sendMessage(noperms);
                }
            }else {
                return false;
            }
        }
        return true;
    }

    private void flyMethod(Player player, Player executor) {
        if (!player.getAllowFlight()){
            player.setAllowFlight(true);
            if (executor == player) {
                player.sendMessage(prefix + " §6Your flight mode has now been set to true.");
            } else {
                player.sendMessage(prefix + " §6Your flight mode has now been set to true.");
                executor.sendMessage(prefix + " §6You have set flight mode of " + player.getName() + " §6to true.");
            }
        } else if (player.getAllowFlight()){
            player.setAllowFlight(false);
            if (executor == player) {
                player.sendMessage(prefix + " §6Your flight mode has now been set to false.");
            } else {
                player.sendMessage(prefix + " §6Your flight mode has now been set to false.");
                executor.sendMessage(prefix + "§6You have set flight mode of " + player.getName() + " §6to false.");
            }
        }
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1){
            List<String> playerNames = new ArrayList<>();
            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(players);
            for (int i = 0; i < players.length; i++){
                playerNames.add(players[i].getName());
            }

            return playerNames;
        }
        return null;
    }
}