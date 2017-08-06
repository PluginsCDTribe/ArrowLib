package com.arrowlib.core;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;

import com.arrowlib.events.OpChangeEvent;
import com.arrowlib.events.OpChangeReasonEnum;

public class OpCheckRunnable extends BukkitRunnable{
	static Set<OfflinePlayer> operators;
	PluginManager pluginManager;
	public OpCheckRunnable() {
	operators = Bukkit.getOperators();	
	pluginManager = Bukkit.getPluginManager();
	assert operators == null;
	assert pluginManager == null;
	}
	@Override
	public void run() {
	Set<OfflinePlayer> nowOperators = Bukkit.getOperators();
	int itemSize = nowOperators.size();
	int opSize = operators.size();
	for (final OfflinePlayer player : nowOperators) {
	if (!contains(player) && itemSize > opSize) {	
	this.pluginManager.callEvent(new OpChangeEvent(player, OpChangeReasonEnum.ADD_NEW_OPERATOR));
	}
	}
	for (final OfflinePlayer player : operators) {
	if (contains(player) && itemSize < opSize && !contains(nowOperators, player)) {	
	this.pluginManager.callEvent(new OpChangeEvent(player, OpChangeReasonEnum.REMOVE_A_OPERATOR));
	}
	}
	operators = nowOperators;
	}
	public boolean contains(final OfflinePlayer who) {
		for (final OfflinePlayer player : operators) {
			if (who.getName().equalsIgnoreCase(player.getName())) {
				return true;
			}
			continue;
		}
		return false;
	}
	public boolean contains(Set<OfflinePlayer> set, final OfflinePlayer target) {
		for (final OfflinePlayer player : set) {
			if (target.getName().equalsIgnoreCase(player.getName())) {
				return true;
			}
			continue;
		}
		return false;
	}
}
