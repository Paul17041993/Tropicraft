package net.tropicraft.core.common.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tropicraft.Info;

public class ItemRiverFish extends ItemColoredFood {

    public static final int[] FISH_COLORS = {
            0xb5e1e7, 0x3e375f
    };

    private String cookedStatus;

    public ItemRiverFish(int healAmount, float saturation, String itemName, String cookedStatus) {
        super(healAmount, saturation, itemName);
        this.cookedStatus = cookedStatus;
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
        if (isInCreativeTab(tab)) {
            for (int i = 0; i < FISH_COLORS.length; i++) {
                list.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public int getColor(ItemStack stack, int pass) {
        if (cookedStatus == "raw") {
            return FISH_COLORS[stack.getItemDamage()] | 0x222;
        } else {
            return (FISH_COLORS[stack.getItemDamage()] & 0xfefefe) >> 1;
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        final String fish = itemstack.getItemDamage() == 0 ? "sardine" : "piranha";
        return String.format("item.%s.%s.%s.%s", Info.MODID, this.itemName, fish, cookedStatus);
    }

}
