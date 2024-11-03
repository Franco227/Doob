package com.franco227.doob;

import com.franco227.doob.entity.DoobEntities;
import com.franco227.doob.event.DoobEvents;
import com.franco227.doob.item.DoobItems;
import com.franco227.doob.sound.DoobSounds;
import com.franco227.doob.worldgen.DoobEntitiesSpawn;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Doob implements ModInitializer {
    public static final String MOD_ID = "doob";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        DoobEntities.initialize();
        DoobEntitiesSpawn.initialize();
        DoobItems.initialize();
        DoobEvents.initialize();
        DoobSounds.initialize();
        LOGGER.info("[Doob] Initialized!");
    }
}
