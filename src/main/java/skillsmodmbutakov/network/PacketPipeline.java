package skillsmodmbutakov.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import io.netty.channel.ChannelHandler.Sharable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import skillsmodmbutakov.main;


public class PacketPipeline
{
  protected int packetID;
  public SimpleNetworkWrapper dispatcher;
  
  public PacketPipeline()
  {
    this.dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel(main.MODID);
    this.packetID = 0;
  }
  
  public void registerPackets()
  {

    registerPacket(PacketActionSkillSpeed.ServerHandler.class, PacketActionSkillSpeed.class);
  }
  
  public <REQ extends IMessage, REPLY extends IMessage> void registerPacket(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType)
  {
    try
    {
      Side side = AbstractClientPacketHandler.class.isAssignableFrom(messageHandler) ? Side.CLIENT : Side.SERVER;
      this.dispatcher.registerMessage(messageHandler, requestMessageType, this.packetID++, side);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public <REQ extends IMessage, REPLY extends IMessage> void registerBiPacket(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType)
  {
    if (AbstractBiPacketHandler.class.isAssignableFrom(messageHandler))
    {
      this.dispatcher.registerMessage(messageHandler, requestMessageType, this.packetID, Side.CLIENT);
      this.dispatcher.registerMessage(messageHandler, requestMessageType, this.packetID++, Side.SERVER);
    }
    else
    {
      throw new IllegalArgumentException("Cannot register " + messageHandler.getName() + " on both sides - must extend AbstractBiMessageHandler!");
    }
  }
  
  public void sendToServer(IMessage message)
  {
    this.dispatcher.sendToServer(message);
  }
  
  public void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point)
  {
    this.dispatcher.sendToAllAround(message, point);
  }
  
  public void sendToAllAround(IMessage message, int dimention, double x, double y, double z, double range)
  {
    this.dispatcher.sendToAllAround(message, new NetworkRegistry.TargetPoint(dimention, x, y, z, range));
  }
  
  public void sendToAllAround(IMessage message, EntityPlayer player, double range)
  {
    this.dispatcher.sendToAllAround(message, new NetworkRegistry.TargetPoint(player.worldObj.provider.dimensionId, player.posX, player.posY, player.posZ, range));
  }
  
  public void sendToAllAround(IMessage message, TileEntity tileEntity, double range)
  {
    this.dispatcher.sendToAllAround(message, new NetworkRegistry.TargetPoint(tileEntity.getWorldObj().provider.dimensionId, tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord, range));
  }
  
  public void sendTo(IMessage message, EntityPlayerMP player)
  {
    this.dispatcher.sendTo(message, player);
  }
  
  public void sendToDimention(IMessage message, int dimention)
  {
    this.dispatcher.sendToDimension(message, dimention);
  }
  
  public void sendToDimention(IMessage message, World world)
  {
    sendToDimention(message, world.provider);
  }
  
  public void sendToDimention(IMessage message, WorldProvider worldProvider)
  {
    this.dispatcher.sendToDimension(message, worldProvider.dimensionId);
  }
}
