package org.mrcastello.coinflip;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.plugin.SimplePlugin;
import java.util.Random;

public final class Coinflip extends SimplePlugin {
	@Override
	protected void onPluginStart() {
		this.getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	protected void onReloadablesStart() {
	}

	@Override
	protected void onPluginPreReload() {
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("coinflip")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				ItemStack itemInHand = player.getInventory().getItemInMainHand();

				if (itemInHand != null && itemInHand.getType() != Material.AIR) {
					Random random = new Random();
					boolean win = random.nextBoolean();
					if (win) {
						itemInHand.setAmount(itemInHand.getAmount() * 2);
						player.sendMessage("You won!!!");
					} else {
						player.getInventory().setItemInMainHand(null);
						player.sendMessage("You lost!!!");
					}
				} else {
					player.sendMessage("You aren't holding anything.");
				}
				return true;
			} else {
				sender.sendMessage("You can't use this command if you aren't a player.");
				return true;
			}
		}
		return false;
	}
	/**
	 * Return the instance of this plugin, which simply refers to a static
	 * field already created for you in SimplePlugin but casts it to your
	 * specific plugin instance for your convenience.
	 *
	 * @return
	 */
	public static Coinflip getInstance() {
		return (Coinflip) SimplePlugin.getInstance();
	}
}
