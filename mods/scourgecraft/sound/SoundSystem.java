package mods.scourgecraft.sound;

import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;
import mods.scourgecraft.ScourgeCraftCore;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundSystem {

	@ForgeSubscribe
    public void onSound(SoundLoadEvent event)
    {
        try
        {
             String [] soundFiles = {
                     "balance.ogg",
                     "warcry.ogg",
                     "warend.ogg",
                     "warmup.ogg",
                     "construction.ogg",
                     "constructiondone.ogg",
                     "goldcollect.ogg"
                     };
             for (int i = 0; i < soundFiles.length; i++){
                 event.manager.addSound(ScourgeCraftCore.modid.toLowerCase() + ":" + soundFiles[i]);
                 FMLLog.log(Level.CONFIG, ScourgeCraftCore.modid.toLowerCase() + ": ["+soundFiles[i]+"] Sounds Registered");
             }
       
        }
        catch (Exception e)
        {
            System.err.println(ScourgeCraftCore.modid.toLowerCase() + ": Failed to register one or more sounds.");
        }
    }
}
