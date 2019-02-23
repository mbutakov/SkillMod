package skillsmodmbutakov.handlers.client;

import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;

import akka.actor.FSM.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import skillsmodmbutakov.proxy.ClientProxy;
import skillsmodmbutakov.skills.skillManager;


public class PlayerRenderHandler {
	
    private static Minecraft mc;
    private static int tick;
    public static boolean isKilled = false;
    public static boolean  startCooldown = false;
    public static int armor;
    private static skillManager SS = new skillManager();
    public static boolean isfullset = false;
    private int cooldown = 0;
	   public PlayerRenderHandler(Minecraft mc) {
		      this.mc = mc;

		   }

	   
	   
		@SubscribeEvent
		public void key(KeyInputEvent event){
			if(ClientProxy.KeySkillUse.isPressed()){
				if(isfullset == true) {
					SS.isPressedKey(true, armor);
					startCooldown = true;
				}
			}
		}
   
		@SubscribeEvent
		public void onClientTick(ClientTickEvent e){
		if(startCooldown && SS.getCooldown() <= 201) {
			cooldown++;
			SS.isCooldown = true;
			SS.setAndGetCooldown(cooldown);
		}
		
		if(SS.getCooldown() >= 201) {
			startCooldown = false;
			cooldown = 0;
			SS.isCooldown = false;
			SS.setAndGetCooldown(0);		
		}

	} 
		@SubscribeEvent
		public void onPlayerRenderTick(PlayerTickEvent event){
			if(event.player != null){
				EntityPlayer player = (EntityPlayer)event.player;
	
				
					if(player.getCurrentArmor(0) != null &&  player.getCurrentArmor(1) != null && player.getCurrentArmor(2) != null && player.getCurrentArmor(3) != null) {				
						isfullset = true;
						if( player.getCurrentArmor(0).getItem() == Items.diamond_boots && player.getCurrentArmor(1).getItem() == Items.diamond_leggings && player.getCurrentArmor(2).getItem() == Items.diamond_chestplate &&player.getCurrentArmor(3).getItem() == Items.diamond_helmet) {
							this.armor = 1;
						}else if( player.getCurrentArmor(0).getItem() == Items.golden_boots && player.getCurrentArmor(1).getItem() == Items.golden_leggings && player.getCurrentArmor(2).getItem() == Items.golden_chestplate &&player.getCurrentArmor(3).getItem() == Items.golden_helmet) {
							this.armor = 2;
							
							
						}else{
							this.armor = 0;
							}

				} else {
					isfullset = false;
				
				}
			}
		
				
			
		}
}
