package skillsmodmbutakov.proxy;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import skillsmodmbutakov.main;
import skillsmodmbutakov.handlers.client.PlayerRenderHandler;


@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	   public static KeyBinding KeySkillUse;
	   public static PlayerRenderHandler playerRenderHandler;
	   
	   public void Init() {        
		      Minecraft mc = Minecraft.getMinecraft();
		      MinecraftForge.EVENT_BUS.register(new PlayerRenderHandler(mc)); 
		      playerRenderHandler = new PlayerRenderHandler(mc);
		      FMLCommonHandler.instance().bus().register(playerRenderHandler); 
		      KeySkillUse = new KeyBinding("Использовать умение брони", Keyboard.KEY_V, "MB_SKILLSMOD");
			  ClientRegistry.registerKeyBinding(KeySkillUse);
	   }
	   
	   public EntityPlayer getPlayerEntity(MessageContext ctx)
	   {
	     return ctx.side.isClient() ? (EntityPlayer) Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx);
	   }
}
