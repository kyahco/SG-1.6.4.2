package mods.scourgecraft.config;

import java.io.File;
import java.io.IOException;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import mods.scourgecraft.ScourgeCraftCore;
import mods.scourgecraft.blocks.BlockCannon;
import mods.scourgecraft.blocks.BlockDefenseCannon;
import mods.scourgecraft.blocks.BlockGoldProducer;
import mods.scourgecraft.blocks.BlockGoldStorage;
import mods.scourgecraft.blocks.BlockHomeHall;
import mods.scourgecraft.blocks.BlockRaidCenter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.Configuration;

public class ConfigBlocks {
	public static int homeHallID;
	public static Block homeHall;
	public static int goldProducerID;
	public static Block goldProducer;
	public static int goldStorageID;
	public static Block goldStorage;
	public static int cannonID;
	public static Block cannon;
	public static int raidCenterID;
	public static Block raidCenter;
	public static int defenseCannonID;
	public static Block defenseCannon;
	
	public void initConfig()
    {
        File var0 = new File(ScourgeCraftCore.proxy.getMinecraftDir() + "/config/ScourgeCraft");
        var0.mkdir();
        File var1 = new File(ScourgeCraftCore.proxy.getMinecraftDir() + "/config/ScourgeCraft/Blocks.cfg");

        try
        {
            var1.createNewFile();
        }
        catch (IOException var5)
        {
            System.out.println(var5);
        }
        Configuration config = new Configuration(var1);
        
		homeHallID = config.get("Blocks", "Home Hall", 500).getInt();
		goldProducerID = config.get("Blocks", "Gold Producer", 501).getInt();
		goldStorageID = config.get("Blocks", "Gold Storage", 502).getInt();
		cannonID = config.get("Attack Blocks", "Cannon", 503).getInt();
		raidCenterID = config.get("Spawn Blocks", "Raid Center", 504).getInt();
		defenseCannonID = config.get("blocks", "Defense Cannon", 505).getInt();
		
		
        config.save();
    }
	
	public void load()
    {
		homeHall = (new BlockHomeHall(homeHallID)).setCreativeTab(ScourgeCraftCore.tabBlocks).setUnlocalizedName("homeHall");
		goldProducer = (new BlockGoldProducer(goldProducerID, Material.iron)).setCreativeTab(ScourgeCraftCore.tabBlocks).setUnlocalizedName("goldProducer");
		goldStorage = (new BlockGoldStorage(goldStorageID, Material.iron)).setCreativeTab(ScourgeCraftCore.tabBlocks).setUnlocalizedName("goldStorage");
		cannon = (new BlockCannon(cannonID, Material.iron)).setCreativeTab(ScourgeCraftCore.tabBlocks).setUnlocalizedName("cannon");
		raidCenter = (new BlockRaidCenter(raidCenterID, Material.iron)).setCreativeTab(ScourgeCraftCore.tabBlocks).setUnlocalizedName("raidCenter");
		defenseCannon = (new BlockDefenseCannon(defenseCannonID, Material.iron)).setCreativeTab(ScourgeCraftCore.tabBlocks).setUnlocalizedName("defenseCannon");
    }
	
	public void register()
    {
		GameRegistry.registerBlock(homeHall, ScourgeCraftCore.modid + "homeHall");
		GameRegistry.registerBlock(goldProducer, ScourgeCraftCore.modid + "goldProducer");
		GameRegistry.registerBlock(goldStorage, ScourgeCraftCore.modid + "goldStorage");
		GameRegistry.registerBlock(cannon, ScourgeCraftCore.modid + "cannon");
		GameRegistry.registerBlock(raidCenter, ScourgeCraftCore.modid + "raidCenter");
		GameRegistry.registerBlock(defenseCannon, ScourgeCraftCore.modid + "defensecannon");
    }
	
	public void languageRegister()
	{
		LanguageRegistry.instance().addStringLocalization("itemGroup.ScourgeCraft : Blocks", "ScourgeCraft: Blocks");
		
		LanguageRegistry.addName(homeHall, "Home Hall");
		LanguageRegistry.addName(goldProducer, "Gold Producer");
		LanguageRegistry.addName(goldStorage, "Gold Storage");
		LanguageRegistry.addName(cannon, "Cannon");
		LanguageRegistry.addName(raidCenter, "Raid Center");
		LanguageRegistry.addName(defenseCannon, "Defense Cannon");
	}
}
