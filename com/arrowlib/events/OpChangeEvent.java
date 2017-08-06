package com.arrowlib.events;

import org.bukkit.OfflinePlayer;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
 
public class OpChangeEvent extends Event{
	private static final HandlerList handlerList = new HandlerList();	
	private OfflinePlayer player;
	private OpChangeReasonEnum reason;
	@Override
	public HandlerList getHandlers() {
		return handlerList;	
	}
	public static HandlerList getHandlerList() {
	    return handlerList;
	}
	public OpChangeEvent(OfflinePlayer player,OpChangeReasonEnum reason) {
	this.player = player;	
	this.reason = reason;
	}
	public OfflinePlayer getPlayer() {
		return this.player;
	}
	public OpChangeReasonEnum getReason() {
		return this.reason;
	}
	public void setCancelled(boolean isCancelled) {
	if (isCancelled) {
		boolean isOp = this.player.isOp();
		this.player.setOp(!isOp);
		return;
	}
	return;
	}
	public boolean equals(OpChangeEvent another) {
		return another.player.getName().equalsIgnoreCase(this.player.getName()) && 
				another.reason.equals(this.reason);
	}
	@Override
	public String toString() {
	return this.player.getName()+"#"
			+this.reason.toString();
	}
}

