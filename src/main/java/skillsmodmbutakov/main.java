package skillsmodmbutakov;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import skillsmodmbutakov.network.PacketPipeline;
import skillsmodmbutakov.proxy.CommonProxy;

@Mod(
		   modid = "skillmod",
		   name = "skillmod",
		   version = "0.0.1"
		)

public class main {
	
	public static final String MODID = "skillmod";
	public static final String NAME = "skillmod";
	public static final String VERSION = "0.0.1";
	public static final String MC_VERSION = "1.7.10";	

	 public static PacketPipeline packetPipeline;

	
 @SidedProxy(
	clientSide = "skillsmodmbutakov.proxy.ClientProxy",
	serverSide = "skillsmodmbutakov.proxy.CommonProxy"
	)
 public static CommonProxy proxy; 
	
 
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		packetPipeline = new PacketPipeline();
	    packetPipeline.registerPackets();
	}
	@EventHandler
	public void Init(FMLInitializationEvent e){
		proxy.Init();
	}
	

}
