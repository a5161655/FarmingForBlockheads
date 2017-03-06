package net.blay09.mods.farmingforblockheads.compat;

import com.google.common.collect.Lists;
import net.blay09.mods.farmingforblockheads.FarmingForBlockheads;
import net.blay09.mods.farmingforblockheads.registry.MarketEntry;
import net.blay09.mods.farmingforblockheads.registry.MarketRegistry;
import net.blay09.mods.farmingforblockheads.registry.MarketRegistryDefaultHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;
import java.util.List;

public class AgriCraftAddon {

	public AgriCraftAddon() {
		MarketRegistry.INSTANCE.registerDefaultHandler("AgriCraft Seeds", new MarketRegistryDefaultHandler() {
			@Override
			public void apply(MarketRegistry registry) {
				Item seedItem = Item.REGISTRY.getObject(new ResourceLocation(Compat.AGRICRAFT, "agri_seed"));
				if(seedItem != null) {
					CreativeTabs agriTab = Arrays.stream(CreativeTabs.CREATIVE_TAB_ARRAY).filter(tab -> tab.getTabLabel().equals("agricraft_seeds")).findFirst().orElse(null);
					if(agriTab != null) {
						List<ItemStack> stackList = Lists.newArrayList();
						seedItem.getSubItems(seedItem, agriTab, stackList);
						for (ItemStack itemStack : stackList) {
							registry.registerEntry(itemStack, new ItemStack(Items.EMERALD, 3), MarketEntry.EntryType.SEEDS);
						}
					} else {
						FarmingForBlockheads.logger.warn("Could not find AgriCraft Seeds creative tab. AgriCraft seeds will not be available in the market.");
					}
				} else {
					FarmingForBlockheads.logger.warn("Could not find AgriCraft seed item. AgriCraft seeds will not be available in the market.");
				}
			}

			@Override
			public boolean isEnabledByDefault() {
				return false;
			}
		});
	}

}
