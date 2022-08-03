package commoble.sandbox;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep.Carving;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.structure.StructureSet;

public class TestChunkGenerator extends ChunkGenerator
{
	public static Codec<? extends ChunkGenerator> makeCodec()
	{
		Codec<TestChunkGenerator> codec = RecordCodecBuilder.create(builder -> builder.group(
				RegistryOps.retrieveRegistry(Registry.STRUCTURE_SET_REGISTRY).forGetter(generator -> generator.structureSets),
				RegistryOps.retrieveRegistry(Registry.BIOME_REGISTRY).forGetter(generator -> generator.biomes))
			.apply(builder, TestChunkGenerator::new));
		return codec;
	}
	
	private final Registry<Biome> biomes;

	public TestChunkGenerator(Registry<StructureSet> structures, Registry<Biome> biomes)
	{
		super(structures, Optional.empty(), new FixedBiomeSource(biomes.getOrCreateHolderOrThrow(Biomes.PLAINS)));
		this.biomes = biomes;
	}

	@Override
	protected Codec<? extends ChunkGenerator> codec()
	{
		return Sandbox.TEST_CHUNK_GENERATOR.get();
	}

	@Override
	public void applyCarvers(WorldGenRegion p_223043_, long p_223044_, RandomState p_223045_, BiomeManager p_223046_, StructureManager p_223047_, ChunkAccess p_223048_,
		Carving p_223049_)
	{
	}

	@Override
	public void buildSurface(WorldGenRegion p_223050_, StructureManager p_223051_, RandomState p_223052_, ChunkAccess p_223053_)
	{
	}

	@Override
	public void spawnOriginalMobs(WorldGenRegion p_62167_)
	{
	}

	@Override
	public int getGenDepth()
	{
		return 384;
	}

	@Override
	public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender blender, RandomState randomState, StructureManager structureManager, ChunkAccess chunkAccess)
	{
		BlockState state = Blocks.SANDSTONE.defaultBlockState();
		for (int x=0; x<16; x++)
		{
			for (int z=0; z<16; z++)
			{
				chunkAccess.setBlockState(new BlockPos(x, 0, z), state, false);
			}
		}
		return CompletableFuture.completedFuture(chunkAccess);
	}

	@Override
	public int getSeaLevel()
	{
		return 63;
	}

	@Override
	public int getMinY()
	{
		return 0;
	}

	@Override
	public int getBaseHeight(int p_223032_, int p_223033_, Types p_223034_, LevelHeightAccessor p_223035_, RandomState p_223036_)
	{
		return 0;
	}

	@Override
	public NoiseColumn getBaseColumn(int p_223028_, int p_223029_, LevelHeightAccessor p_223030_, RandomState p_223031_)
	{
		return new NoiseColumn(1, new BlockState[] {Blocks.SANDSTONE.defaultBlockState()});
	}

	@Override
	public void addDebugScreenInfo(List<String> p_223175_, RandomState p_223176_, BlockPos p_223177_)
	{
	}

}
