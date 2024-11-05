package com.franco227.doob.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static com.franco227.doob.Doob.MOD_ID;

public class DoobSounds {

    public static final SoundEvent ENTITY_JERBOA_AMBIENT = registerSoundEvent("entity.jerboa.ambient");
    public static final SoundEvent ENTITY_JERBOA_DEATH = registerSoundEvent("entity.jerboa.death");
    public static final SoundEvent ENTITY_JERBOA_HURT = registerSoundEvent("entity.jerboa.hurt");
    public static final SoundEvent ENTITY_JERBOA_JUMP = registerSoundEvent("entity.jerboa.jump");

    public static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void initialize() {
    }
}
