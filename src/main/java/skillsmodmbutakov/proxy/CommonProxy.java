package skillsmodmbutakov.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;


public class CommonProxy {

	
	
	   public void Init() {
	   }
	
	   public boolean isSideClient() {
		      return false;
		   }
	
	   public EntityPlayer getPlayerEntity(MessageContext ctx)
	   {
	     return ctx.getServerHandler().playerEntity;
	   }
	   
}
