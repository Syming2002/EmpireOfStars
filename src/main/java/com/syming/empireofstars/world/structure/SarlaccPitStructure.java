package com.syming.empireofstars.world.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.syming.empireofstars.datagen.tag.EOSBiomeTagProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.DimensionPadding;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasBinding;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasLookup;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SarlaccPitStructure extends Structure {

    public static final DimensionPadding DEFAULT_DIMENSION_PADDING = DimensionPadding.ZERO;
    public static final LiquidSettings DEFAULT_LIQUID_SETTINGS = LiquidSettings.APPLY_WATERLOGGING;

    public static final MapCodec<SarlaccPitStructure> CODEC = RecordCodecBuilder.<SarlaccPitStructure>mapCodec(pInstance ->
            pInstance.group(SarlaccPitStructure.settingsCodec(pInstance),
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.BOOL.fieldOf("use_expansion_hack").forGetter(structure -> structure.useExpansionHack),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter),
                    Codec.list(PoolAliasBinding.CODEC).optionalFieldOf("pool_aliases", List.of()).forGetter(p_307187_ -> p_307187_.poolAliases),
                    DimensionPadding.CODEC
                            .optionalFieldOf("dimension_padding", DEFAULT_DIMENSION_PADDING)
                            .forGetter(p_348455_ -> p_348455_.dimensionPadding),
                    LiquidSettings.CODEC.optionalFieldOf("liquid_settings", DEFAULT_LIQUID_SETTINGS).forGetter(p_352036_ -> p_352036_.liquidSettings))
                            .apply(pInstance, SarlaccPitStructure::new));

    private final Holder<StructureTemplatePool> startPool;
    private final Optional<ResourceLocation> startJigsawName;
    private final int size;
    private final HeightProvider startHeight;
    private final Optional<Heightmap.Types> projectStartToHeightmap;
    private final boolean useExpansionHack;
    private final int maxDistanceFromCenter;
    private final List<PoolAliasBinding> poolAliases;
    private final DimensionPadding dimensionPadding;
    private final LiquidSettings liquidSettings;

    public SarlaccPitStructure(StructureSettings pSettings, Holder<StructureTemplatePool> startPool, Optional<ResourceLocation> startJigsawName, int size, HeightProvider startHeight, Optional<Heightmap.Types> projectStartToHeightmap, boolean useExpansionHack, int maxDistanceFromCenter, List<PoolAliasBinding> poolAliases, DimensionPadding dimensionPadding, LiquidSettings liquidSettings) {
        super(pSettings);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.size = size;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.useExpansionHack = useExpansionHack;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
        this.poolAliases = poolAliases;
        this.dimensionPadding = dimensionPadding;
        this.liquidSettings = liquidSettings;
    }

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext pContext) {
        ChunkPos chunkPos = pContext.chunkPos();

        int centerX = chunkPos.getMiddleBlockX();
        int centerZ = chunkPos.getMiddleBlockZ();

        int structureWidth = 9;
        int structureHeight = 24;
        int structureDepth = 9;

        int highestSurface = Integer.MIN_VALUE;
        int lowestSurface = Integer.MAX_VALUE;


        for (int x = -structureWidth / 2; x < structureWidth / 2; x++) {

            for (int z = -structureDepth / 2; z < structureDepth / 2; z++) {


                int height = pContext.chunkGenerator().getBaseHeight(
                        centerX + x,
                        centerZ + z,
                        Heightmap.Types.WORLD_SURFACE_WG,
                        pContext.heightAccessor(),
                        pContext.randomState()
                );


                highestSurface = Math.max(highestSurface, height);
                lowestSurface = Math.min(lowestSurface, height);
            }
        }

        if (highestSurface - lowestSurface > 3) {
            return Optional.empty();
        }

        BlockPos startPos = new BlockPos(
                centerX,
                highestSurface - structureHeight + 1,
                centerZ
        );

        return JigsawPlacement.addPieces(pContext, this.startPool, this.startJigsawName, this.size, startPos, false, this.projectStartToHeightmap, this.maxDistanceFromCenter, PoolAliasLookup.create(this.poolAliases, startPos, pContext.seed()), this.dimensionPadding, this.liquidSettings);
    }

    @Override
    public StructureType<?> type() {
        return EOSStructureTypes.SARLACC_PIT.get();
    }

    public static SarlaccPitStructure buildStructureConfig(BootstrapContext<Structure> context) {
        return new SarlaccPitStructure(new StructureSettings(
                context.lookup(Registries.BIOME).getOrThrow(EOSBiomeTagProvider.IS_TATOOINE),
                Map.of(),
                GenerationStep.Decoration.UNDERGROUND_STRUCTURES,
                TerrainAdjustment.NONE
        ),
                context.lookup(Registries.TEMPLATE_POOL).getOrThrow(EOSTemplatePool.SARLACC_PIT),
                Optional.empty(),
                30,
                ConstantHeight.of(VerticalAnchor.absolute(0)),
                Optional.empty(),
                false,
                128,
                List.of(),
                JigsawStructure.DEFAULT_DIMENSION_PADDING,
                JigsawStructure.DEFAULT_LIQUID_SETTINGS

        );
    }
}
