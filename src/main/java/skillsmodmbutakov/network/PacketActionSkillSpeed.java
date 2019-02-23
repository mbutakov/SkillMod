package skillsmodmbutakov.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PacketActionSkillSpeed extends PacketAbstract {
	
	 int type;
	


	@Override
	public void fromBytes(ByteBuf buf) {
		// TODO Auto-generated method stub

		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		// TODO Auto-generated method stub

		
	}

	  public static class ServerHandler
	    extends AbstractServerPacketHandler<PacketActionSkillSpeed>
	  {
	    public IMessage handleServerMessage(EntityPlayer player, PacketActionSkillSpeed message, MessageContext ctx)
	    {
	    	
	            player.addPotionEffect(new PotionEffect(Potion.invisibility.getId(),105,5));
	            

	            return null;
	      }
	  }
	}

