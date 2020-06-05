package com.mcmoddev.wonderfulwands;

import com.mcmoddev.wonderfulwands.init.WonderfulBlocks;
import com.mcmoddev.wonderfulwands.init.WonderfulItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
	modid = WonderfulWands.MODID,
	name = WonderfulWands.NAME,
	version = WonderfulWands.VERSION,
	acceptedMinecraftVersions = WonderfulWands.MC_VERSION,
	certificateFingerprint = WonderfulWands.FINGERPRINT,
	updateJSON = WonderfulWands.UPDATE_JSON
)
public class WonderfulWands {

	//TODO Client models, blockstates, lang files and textures are not working yet.
	//TODO Recipes and config stuff need to be added.
	//TODO Robes, and ilusion blocks need adding back in.
	//TODO EVERYTHING needs cleaning up... Code is such a mess...
	//TODO Find and catch the bugs. First one linked below. Line 62. (Hold right click the wand in game for the crash)
	/**{@link com.mcmoddev.wonderfulwands.common.entities.EntityLightWisp#writeToNBT(NBTTagCompound)}*/

	public static final String MODID = "wonderfulwands";
	public static final String NAME = "Wonderful Wands";
	public static final String VERSION = "3.0.0";
	public static final String MC_VERSION = "[1.12,)";
	public static final String FINGERPRINT = "@FINGERPRINT@";
	public static final String UPDATE_JSON =
		"https://raw.githubusercontent.com/MinecraftModDevelopmentMods/WonderfulWands/master/update.json";
	public static final Logger LOGGER = LogManager.getLogger(NAME);

	@Mod.EventHandler
	public void onFingerprintViolation(final FMLFingerprintViolationEvent event) {
		FMLLog.bigWarning("Invalid fingerprint detected! The file " + event.getSource().getName()
			+ " may have been tampered with. This jar will NOT be supported by MMD!");
	}

	public static final CreativeTabs TAB_WANDS = new CreativeTabs("tab." + MODID + ".wands") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WonderfulItems.WAND_GENERIC);
		}
	};

	public static final CreativeTabs TAB_ROBES = new CreativeTabs("tab." + MODID + ".armor") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WonderfulItems.WIZARDS_HAT);
		}
	};

	//TODO If we remove the blocks as items we can place from creative, remove this.
	public static final CreativeTabs TAB_BLOCKS = new CreativeTabs("tab." + MODID + ".blocks") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WonderfulBlocks.MAGE_LIGHT);
		}
	};

	public static ItemArmor.ArmorMaterial NONARMOR = EnumHelper.addArmorMaterial("NONARMOR",
		"empty_armor", 10, new int[]{0, 0, 0, 0},
		0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
	public static ItemArmor.ArmorMaterial WIZARDROBES = EnumHelper.addArmorMaterial("WIZARDCLOTH",
		"wizard_robes", 15, new int[]{1, 1, 1, 1},
		40, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LOGGER.info("Is this magical robe for me??");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		LOGGER.info("Ooo a wand too?!");
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LOGGER.info("I'm a real Wizard!");
	}
}


	/**
	 * Below this line is all the old code from 1.11.2. It is to be cleaned up/removed, do not enable it.






	public static boolean altRecipes = false;

	public static WizardingArmor[][] robes = new WizardingArmor[16][4]; // [color][slot]

	private static final String[] colorSuffixes = {"black", "red", "green", "brown", "blue", "purple", "cyan",
		"silver", "gray", "pink", "lime", "yellow", "light_blue", "magenta", "orange", "white"};
	private static final String[] oreDictionaryColors = {"dyeBlack", "dyeRed", "dyeGreen", "dyeBrown", "dyeBlue", "dyePurple", "dyeCyan",
		"dyeLightGray", "dyeGray", "dyePink", "dyeLime", "dyeYellow", "dyeLightBlue", "dyeMagenta", "dyeOrange", "dyeWhite"};

	private final EntityEquipmentSlot[] armorSlots = {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();

		altRecipes = config.getBoolean("alternative_recipes", "options", false,
			"If true, then robes and wands will use different recipes than normal");

		mageLight = new MageLight();
		registerBlock(mageLight, MageLight.name);
		IllusoryBlock illusion;
		illusion = new IllusoryBlock(Blocks.DIRT);
		registerBlock(illusion, illusion.name);
		illusion = new IllusoryBlock(Blocks.GRASS);
		registerBlock(illusion, illusion.name);
		illusion = new IllusoryBlock(Blocks.PLANKS.getMapColor(Blocks.PLANKS.getDefaultState()), "illusion_oak_planks", Blocks.PLANKS);
		registerBlock(illusion, illusion.name);
		illusion = new IllusoryBlock(Blocks.BOOKSHELF);
		registerBlock(illusion, illusion.name);
		illusion = new IllusoryBlock(Blocks.STONE);
		illusion.setTranslationKey("stone.stone");
		registerBlock(illusion, illusion.name);
		illusion = new IllusoryBlock(Blocks.COBBLESTONE.getMapColor(Blocks.COBBLESTONE.getDefaultState()), "illusion_cobblestone", Blocks.COBBLESTONE, "stonebrick");
		registerBlock(illusion, illusion.name);
		illusion = new IllusoryBlock(Blocks.BRICK_BLOCK);
		registerBlock(illusion, illusion.name);
		illusion = new IllusoryBlock(Blocks.HAY_BLOCK.getMapColor(Blocks.HAY_BLOCK.getDefaultState()), "illusion_hay_block", Blocks.HAY_BLOCK);
		registerBlock(illusion, illusion.name);
		illusion = new IllusoryBlock(Blocks.PUMPKIN);
		registerBlock(illusion, illusion.name);
		illusion = new IllusoryBlock(Blocks.MELON_BLOCK);
		registerBlock(illusion, illusion.name);
		illusion = new IllusoryBlock(Blocks.NETHER_BRICK.getMapColor(Blocks.NETHER_BRICK.getDefaultState()), "illusion_nether_brick", Blocks.NETHER_BRICK);
		registerBlock(illusion, illusion.name);
		illusion = new IllusoryBlock(Blocks.NETHERRACK.getMapColor(Blocks.NETHERRACK.getDefaultState()), "illusion_netherrack", Blocks.NETHERRACK);
		registerBlock(illusion, illusion.name);
		illusion = new IllusoryBlock(Blocks.END_STONE.getMapColor(Blocks.END_STONE.getDefaultState()), "illusion_end_stone", Blocks.END_STONE);
		registerBlock(illusion, illusion.name);
		illusion = new IllusoryBlock(Blocks.COBBLESTONE.getMapColor(Blocks.PRISMARINE.getDefaultState()), "illusion_prismarine", Blocks.PRISMARINE, "prismarine.rough");
		registerBlock(illusion, illusion.name);
		illusion = new IllusoryBlock(Blocks.SOUL_SAND.getMapColor(Blocks.SOUL_SAND.getDefaultState()), "illusion_soul_sand", Blocks.SOUL_SAND);
		registerBlock(illusion, illusion.name);
		illusion = new IllusoryBlock(Blocks.STONEBRICK.getMapColor(Blocks.STONEBRICK.getDefaultState()), "illusion_stonebrick", Blocks.STONEBRICK);
		registerBlock(illusion, illusion.name);
		illusion = new IllusoryBlock(Blocks.SAND);
		registerBlock(illusion, illusion.name);

		// recipes

		// Nonmagical
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(wandGeneric), " g ", " s ", " g ", 'g', "nuggetGold", 's', "stickWood"));
		// Magic Missile
		addWandRecipe(wandOfMagicMissile, Items.GOLDEN_SWORD);
		// Fire
		addWandRecipe(wandOfFire, Items.FIRE_CHARGE);
		// Death
		addWandRecipe(wandOfDeath, new ItemStack(Items.SKULL, 1, 1));
		// Growth
		addWandRecipe(wandOfGrowth, Items.BONE);
		// Harvesting
		addWandRecipe(wandOfHarvesting, Items.SHEARS);
		// Healing
		addWandRecipe(wandOfHealing, Items.GHAST_TEAR);
		// Ice
		addWandRecipe(wandOfIce, Items.SNOWBALL);
		// Digging
		addWandRecipe(wandOfMining, Items.GOLDEN_PICKAXE);
		// Teleport
		addWandRecipe(wandOfTeleportation, Items.ENDER_EYE);
		// wand of light
		OreDictionary.registerOre("torch", Blocks.TORCH);
		addWandRecipe(wandOfLight, "torch");
		addWandRecipe(wandOfGreaterLight, new ItemStack(Blocks.REDSTONE_LAMP));
		// wand of storms
		addWandRecipe(wandOfStorms, new ItemStack(Blocks.WOOL, 1, 7));
		// wand of lightning
		addWandRecipe(wandOfLightning, "gemDiamond");
		// wand of climbing
		OreDictionary.registerOre("vine", Blocks.VINE);
		addWandRecipe(wandOfClimbing, "vine");
		// wand of bridging
		addWandRecipe(wandOfBridging, "blockIron");
		addWandRecipe(wandOfBridging, "blockSteel");
		// wand of illusions
		addWandRecipe(wandOfIllusions, Items.FERMENTED_SPIDER_EYE);
		// wand of railroads
		addWandRecipe(wandOfRails, Blocks.GOLDEN_RAIL);
		// wand of webs
		addWandRecipe(wandOfWebbing, Items.SLIME_BALL);
		// wand of levitation
		addWandRecipe(wandOfLevitation, Items.FEATHER);
		// wand of tunneling
		addWandRecipe(wandOfTunneling, Items.DIAMOND_PICKAXE);

		for (int colorIndex = 0; colorIndex < 16; colorIndex++) {
			int slotIndex = 0;
			for (int i = 0; i < 4; i++) {
				EntityEquipmentSlot armorSlot = armorSlots[slotIndex];
				String color = colorSuffixes[colorIndex];
				WizardingArmor r = new WizardingArmor(WIZARDROBES, color, armorSlot);
				registerItem(r, "robes_" + color + "_" + WizardingArmor.slotName.get(armorSlot));
				OreDictionary.registerOre(WizardingArmor.slotName.get(armorSlot) + "WizardRobes", r);
				OreDictionary.registerOre("wizardRobes", r);
				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(r, 1), WizardingArmor.slotName.get(armorSlot) + "WizardRobes", oreDictionaryColors[colorIndex]));
				robes[colorIndex][slotIndex] = r;
				slotIndex++;
			}
			ItemStack cloth = new ItemStack(Blocks.WOOL, 1, 15 - colorIndex);
			// metadata for wool: white, orange, magenta, lightblue, yellow lime green, pink, gray, light gray, cyan, purple, blue, brown, green, red, black
			if (altRecipes) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(robes[colorIndex][0], 1), "ccc", "cgc", 'c', cloth, 'g', "ingotGold"));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(robes[colorIndex][1], 1), "cgc", "ccc", "ccc", 'c', cloth, 'g', "ingotGold"));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(robes[colorIndex][2], 1), "ggg", "c c", "c c", 'c', cloth, 'g', "ingotGold"));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(robes[colorIndex][3], 1), "c c", "g g", 'c', cloth, 'g', "ingotGold"));
			} else {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(robes[colorIndex][0], 1), "ccc", "c c", 'c', cloth));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(robes[colorIndex][1], 1), "c c", "ccc", "ccc", 'c', cloth));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(robes[colorIndex][2], 1), "ccc", "c c", "c c", 'c', cloth));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(robes[colorIndex][3], 1), "c c", "c c", 'c', cloth));
			}
		}

		if (config.getBoolean("allow_wizard_hat", "options", true,
			"If true, then the Wizard's Hat and Witch's Hat items will be craftable (if \n" +
				"false, the hats will not be craftable). These hats are very powerful and you \n" +
				"may want to disable them if you expect there to be troublemakers (aka \n" +
				"\"griefers\")")) {
			if (altRecipes) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(wizardHat, 1), " d ", " b ", "bbb", 'b', new ItemStack(Blocks.WOOL, 1, 11), 'd', Blocks.SKULL));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(witchHat, 1), " d ", " b ", "bbb", 'b', new ItemStack(Blocks.WOOL, 1, 15), 'd', Blocks.SKULL));
			} else {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(wizardHat, 1), " d ", " b ", "bbb", 'b', new ItemStack(Blocks.WOOL, 1, 11), 'd', Items.GHAST_TEAR));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(witchHat, 1), " d ", " b ", "bbb", 'b', new ItemStack(Blocks.WOOL, 1, 15), 'd', Items.GHAST_TEAR));
			}

		}
		GameRegistry.addRecipe(new ItemStack(topHat, 1), " b ", " l ", 'b', new ItemStack(Blocks.WOOL, 1, 15), 'l', Items.LEATHER);

		//	OreDictionary.initVanillaEntries()
		config.save();
	}

	private static void addWandRecipe(Wand output, Item specialItem) {
		if (altRecipes) {
			GameRegistry.addRecipe(new ShapedOreRecipe(wandItemStack(output), " x ", " ex", "s  ", 'x', specialItem, 'e',
				"gemEmerald", 's', wandGeneric));
		} else {
			GameRegistry.addRecipe(new ShapedOreRecipe(wandItemStack(output), "xex", " s ", " g ", 'x', specialItem, 'e',
				"gemEmerald", 's', "stickWood", 'g', "nuggetGold"));
		}
	}

	private static void addWandRecipe(Wand output, Block specialItem) {
		if (altRecipes) {
			GameRegistry.addRecipe(new ShapedOreRecipe(wandItemStack(output), " x ", " ex", "s  ", 'x', specialItem, 'e',
				"gemEmerald", 's', wandGeneric));
		} else {
			GameRegistry.addRecipe(new ShapedOreRecipe(wandItemStack(output), "xex", " s ", " g ", 'x', specialItem, 'e',
				"gemEmerald", 's', "stickWood", 'g', "nuggetGold"));
		}
	}

	private static void addWandRecipe(Wand output, ItemStack specialItem) {
		if (altRecipes) {
			GameRegistry.addRecipe(new ShapedOreRecipe(wandItemStack(output), " x ", " ex", "s  ", 'x', specialItem, 'e',
				"gemEmerald", 's', wandGeneric));
		} else {
			GameRegistry.addRecipe(new ShapedOreRecipe(wandItemStack(output), "xex", " s ", " g ", 'x', specialItem, 'e',
				"gemEmerald", 's', "stickWood", 'g', "nuggetGold"));
		}
	}

	private static void addWandRecipe(Wand output, String specialItem_oreDictionary) {
		if (altRecipes) {
			GameRegistry.addRecipe(new ShapedOreRecipe(wandItemStack(output), " x ", " ex", "s  ", 'x', specialItem_oreDictionary, 'e',
				"gemEmerald", 's', wandGeneric));
		} else {
			GameRegistry.addRecipe(new ShapedOreRecipe(wandItemStack(output), "xex", " s ", " g ", 'x', specialItem_oreDictionary, 'e',
				"gemEmerald", 's', "stickWood", 'g', "nuggetGold"));
		}
	}

	private void registerItemRenders() {
		for (int color = 0; color < robes.length; color++) {
			for (int slot = 0; slot < robes[0].length; slot++) {
				registerItemRender(robes[color][slot], "robes_" + colorSuffixes[color] + "_" + WizardingArmor.slotName.get(armorSlots[slot]));
			}
		}

		for (Map.Entry<Block, IllusoryBlock> entry : IllusoryBlock.getLookUpTable().entrySet()) {
			IllusoryBlock b = entry.getValue();
			Block r = entry.getKey();
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
				.register(net.minecraft.item.Item.getItemFromBlock(b), 0,
					new ModelResourceLocation(r.getRegistryName(), "inventory"));
		}
		IllusoryBlock pb = IllusoryBlock.getLookUpTable().get(Blocks.PLANKS);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
			.register(net.minecraft.item.Item.getItemFromBlock(pb), 0,
				new ModelResourceLocation(MODID + ":" + pb.name, "inventory"));
	}

	private void registerItemRender(Item i, String itemName) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, 0, new ModelResourceLocation(MODID + ":" + itemName, "inventory"));
	}

	public static String objectDump(Object o) throws IllegalArgumentException, IllegalAccessException {
		if (o == null) {
			return "null object";
		}
		if (o.getClass() == null) {
			return "null class";
		}
		StringBuilder sb = new StringBuilder();
		Class<?> c = o.getClass();
		sb.append(c.getName()).append("\n");
		do {
			Field[] fields = c.getDeclaredFields();
			for (Field f : fields) {
				f.setAccessible(true);
				sb.append("\t").append(f.getName()).append("=");
				if (f.getType().isArray()) {
					sb.append(arrayDump(f.get(o)));
				} else if (f.get(o) instanceof java.util.Map) {
					sb.append(mapDump((java.util.Map) f.get(o)));
				} else {
					sb.append(String.valueOf(f.get(o)));
				}
				sb.append("\n");
			}
			c = c.getSuperclass();
		} while (c != null);
		return sb.toString();
	}

	public static String mapDump(java.util.Map map) {
		StringBuilder sb = new StringBuilder();
		for (Object key : map.keySet()) {
			sb.append(String.valueOf(key)).append("->");
			if (map.get(key).getClass().isArray()) {
				sb.append(arrayDump(map.get(key)));
			} else {
				sb.append(String.valueOf(map.get(key)));
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public static String arrayDump(Object array) {
		StringBuilder sb = new StringBuilder();
		int size = Array.getLength(array);
		sb.append("[ ");
		boolean addComma = false;
		for (int i = 0; i < size; i++) {
			if (addComma) sb.append(", ");
			if (Array.get(array, i).getClass().isArray()) {
				sb.append(arrayDump(Array.get(array, i)));
			} else if (Array.get(array, i) instanceof java.util.Map) {
				sb.append(mapDump((java.util.Map) Array.get(array, i)));
			} else {
				sb.append(String.valueOf(Array.get(array, i)));
			}
			addComma = true;
		}
		sb.append(" ]");
		return sb.toString();
	}
	*/

