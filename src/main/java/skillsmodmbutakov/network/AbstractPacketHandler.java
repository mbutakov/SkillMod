package skillsmodmbutakov.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import skillsmodmbutakov.main;

public abstract class AbstractPacketHandler<T extends IMessage>
implements IMessageHandler<T, IMessage>
{
@SideOnly(Side.CLIENT)
public abstract IMessage handleClientMessage(EntityPlayer paramEntityPlayer, T paramT, MessageContext paramMessageContext);

public abstract IMessage handleServerMessage(EntityPlayer paramEntityPlayer, T paramT, MessageContext paramMessageContext);

public IMessage onMessage(T message, MessageContext ctx)
{
  if (ctx.side.isClient()) {
    return handleClientMessage(main.proxy.getPlayerEntity(ctx), message, ctx);
  }
  return handleServerMessage(main.proxy.getPlayerEntity(ctx), message, ctx);
}
}
