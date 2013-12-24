package net.lomeli.ec.entity.addon;

import cofh.api.energy.IEnergyHandler;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import net.lomeli.ec.entity.EntityBaseCreeper;
import net.lomeli.ec.lib.ECVars;

public class EntityRFCreeper extends EntityBaseCreeper {

    public EntityRFCreeper(World par1World) {
        super(par1World);
        this.explosionRadius = ECVars.rfCreeperRadius;
    }

    @Override
    public void explosion(int power, boolean flag) {
        int radius = getPowered() ? (int) (this.explosionRadius * power) : this.explosionRadius;
        for (int x = -radius; x <= radius; x++)
            for (int y = -radius; y <= radius; y++)
                for (int z = -radius; z <= radius; z++) {
                    TileEntity tile = worldObj.getBlockTileEntity(x, y, z);
                    if (tile != null) {
                        if (tile instanceof IEnergyHandler)
                            ((IEnergyHandler) tile).extractEnergy(null, ((IEnergyHandler) tile).getEnergyStored(null) / 2, false);
                    }
                }
    }

}
