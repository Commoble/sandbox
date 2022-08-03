package commoble.sandbox;

import com.mojang.serialization.Codec;

import net.minecraft.core.Registry;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod(Sandbox.MODID)
public class Sandbox
{
	public static final String MODID = "sandbox";
	
	private static final DeferredRegister<Codec<? extends ChunkGenerator>> CHUNK_GENERATORS = DeferredRegister.create(Registry.CHUNK_GENERATOR_REGISTRY, MODID);
	public static final RegistryObject<Codec<? extends ChunkGenerator>> TEST_CHUNK_GENERATOR = CHUNK_GENERATORS.register("test", TestChunkGenerator::makeCodec);

	public Sandbox() // invoked by forge due to @Mod
	{
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		IEventBus forgeBus = MinecraftForge.EVENT_BUS;
		
		CHUNK_GENERATORS.register(modBus);
	}
}
