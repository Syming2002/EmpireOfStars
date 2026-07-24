package com.syming.empireofstars.entity.sarlacc;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundAddExperienceOrbPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraftforge.entity.PartEntity;

public class SarlaccTentacleTipEntity extends PartEntity<SarlaccTentacleEntity> {
    private final EntityDimensions dimensions;

    public SarlaccTentacleTipEntity(SarlaccTentacleEntity parent, float pWidth, float pHeight) {
        super(parent);

        this.dimensions = EntityDimensions.fixed(pWidth, pHeight);
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        return this.dimensions;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    public static void assignPartIDs(Entity pParent) {
        PartEntity<?>[] parts = pParent.getParts();

        for (int i = 0; i < parts.length; i++) {
            PartEntity<?> part = parts[i];
            part.setId(pParent.getId() + i);
        }
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket pPacket) {
        super.recreateFromPacket(pPacket);

        assignPartIDs(this);
    }

    @Override
    public void setId(int pId) {
        super.setId(pId + 1);
    }

    @Override
    public void tick() {
        super.tick();
    }


    @Override
    public boolean is(Entity pEntity) {
        return this == pEntity || getParent() == pEntity;
    }
}
