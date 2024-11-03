package com.franco227.doob.event;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;

public class DoobEvents {

    public static void initialize() {
        UseBlockCallback.EVENT.register(ShearSaplingListener::callback);
    }
}
