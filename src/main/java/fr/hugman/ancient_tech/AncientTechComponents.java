package fr.hugman.ancient_tech;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.level.LevelComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.level.LevelComponentInitializer;
import fr.hugman.ancient_tech.echea.IncorporealEchea;

public class AncientTechComponents implements LevelComponentInitializer {
	public static final ComponentKey<IncorporealEchea> INCORPOREAL_ECHEA = ComponentRegistry.getOrCreate(AncientTech.id("incorporeal_echea"), IncorporealEchea.class);

	@Override
	public void registerLevelComponentFactories(LevelComponentFactoryRegistry registry) {
		registry.register(INCORPOREAL_ECHEA, IncorporealEchea::new);
	}
}
