package fi.dy.masa.litematica.config;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.google.common.collect.ImmutableList;
import fi.dy.masa.litematica.Reference;
import fi.dy.masa.litematica.data.DataManager;
import fi.dy.masa.litematica.selection.CornerSelectionMode;
import fi.dy.masa.litematica.util.BlockInfoAlignment;
import fi.dy.masa.litematica.util.InventoryUtils;
import fi.dy.masa.litematica.util.ReplaceBehavior;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigColor;
import fi.dy.masa.malilib.config.options.ConfigDirectory;
import fi.dy.masa.malilib.config.options.ConfigDouble;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import fi.dy.masa.malilib.config.options.ConfigOptionList;
import fi.dy.masa.malilib.config.options.ConfigString;
import fi.dy.masa.malilib.config.options.IConfigBase;
import fi.dy.masa.malilib.config.values.HudAlignment;
import fi.dy.masa.malilib.config.values.InfoType;
import fi.dy.masa.malilib.util.FileUtils;

public class Configs implements IConfigHandler
{
    public static class Generic
    {
        public static final ConfigBoolean       AREAS_PER_WORLD         = new ConfigBoolean(    "areaSelectionsPerWorld", true, "Use per-world or server root directories for the area selections\n§6NOTE: Don't switch this OFF while you are live streaming,\n§6as then the Area Selection browser will show the server IP\n§6in the navigation widget and also in the current selection name/path\n§6until you change the current directory and selection again");
        public static final ConfigBoolean       BETTER_RENDER_ORDER     = new ConfigBoolean(    "betterRenderOrder", true, "If enabled, then the schematic rendering is done\nby injecting the different render call into the vanilla\nrendering code. This should result in better translucent block\nrendering/ordering and schematic blocks not getting rendered\nthrough the client world blocks/terrain.\nIf the rendering doesn't work (for example with Optifine),\ntry disabling this option.");
        public static final ConfigBoolean       CHANGE_SELECTED_CORNER  = new ConfigBoolean(    "changeSelectedCornerOnMove", true, "If true, then the selected corner of an area selection\nis always set to the last moved corner,\nwhen using the set corner hotkeys");
        public static final ConfigBoolean       CLONE_AT_ORIGINAL_POS   = new ConfigBoolean(    "cloneAtOriginalPosition", false, "If enabled, then using the Clone Area hotkey will create\nthe placement at the original area selection position,\ninstead of at the player's current position");
        public static final ConfigBoolean       CUSTOM_SCHEMATIC_DIRECTORY_ENABLED = new ConfigBoolean("customSchematicDirectoryEnabled", false, "If enabled, then the directory set in 'customSchematicDirectory'\nwill be used as the root/base schematic directory,\ninstead of the normal '.minecraft/schematics/' directory");
        public static final ConfigDirectory     CUSTOM_SCHEMATIC_DIRECTORY = new ConfigDirectory("customSchematicDirectory", FileUtils.getCanonicalFileIfPossible(new File(FileUtils.getMinecraftDirectory(), "schematics")), "The root/base schematic directory to use, if 'customSchematicDirectoryEnabled' is enabled");
        public static final ConfigBoolean       EASY_PLACE_CLICK_ADJACENT = new ConfigBoolean(  "easyPlaceClickAdjacent", false, "If enabled, then the Easy Place mode will try to\nclick on existing adjacent blocks. This may help on Spigot\nor similar servers, which don't allow clicking on air blocks.");
        public static final ConfigBoolean       EASY_PLACE_MODE         = new ConfigBoolean(    "easyPlaceMode", false, "When enabled, then simply trying to use an item/place a block\non schematic blocks will place that block in that position");
        public static final ConfigBoolean       EASY_PLACE_HOLD_ENABLED = new ConfigBoolean(    "easyPlaceHoldEnabled", false, "When enabled, then simply holding down the use key\nand looking at different schematic blocks will place them");
        public static final ConfigBoolean       EASY_PLACE_DEEP_ENABLED = new ConfigBoolean(    "easyPlaceDeepEnabled", false, "When enabled, the Easy Place Mode will attempt to place further away blocks first.");
        public static final ConfigBoolean       EXECUTE_REQUIRE_TOOL    = new ConfigBoolean(    "executeRequireHoldingTool", true, "Require holding an enabled tool item\nfor the executeOperation hotkey to work");
        public static final ConfigBoolean       FIX_RAIL_ROTATION       = new ConfigBoolean(    "fixRailRotation", true, "If true, then a fix is applied for the vanilla bug in rails,\nwhere the 180 degree rotations of straight north-south and\neast-west rails rotate 90 degrees counterclockwise instead >_>");
        public static final ConfigBoolean       GENERATE_LOWERCASE_NAMES = new ConfigBoolean(   "generateLowercaseNames", true, "If enabled, then by default the suggested schematic names\nwill be lowercase and using underscores instead of spaces");
        public static final ConfigBoolean       LOAD_ENTIRE_SCHEMATICS  = new ConfigBoolean(    "loadEntireSchematics", false, "If true, then the entire schematic is always loaded at once.\nIf false, then only the part that is within the client's view distance is loaded.");
        public static final ConfigBoolean       MATERIALS_FROM_CONTAINER = new ConfigBoolean(   "materialListFromContainer", true, "WHen enabled, the schematic-based Material List is\nfetched directly from the block state container. Normally you want this.\nOnly disable this if there is an issue where it gets it wrong for some reason\n(and then also report the issue and send the affected schematic).");
        public static final ConfigInteger       PASTE_COMMAND_INTERVAL  = new ConfigInteger(    "pasteCommandInterval", 1, 1, 1000, "The interval in game ticks the Paste schematic task runs at,\nin the command-based mode");
        public static final ConfigInteger       PASTE_COMMAND_LIMIT     = new ConfigInteger(    "pasteCommandLimit", 64, 1, 1000000, "Max number of commands sent per game tick,\nwhen using the Paste schematic feature in the\ncommand mode on a server");
        public static final ConfigString        PASTE_COMMAND_SETBLOCK  = new ConfigString(     "pasteCommandNameSetblock", "setblock", "The setblock command name to use for the\nPaste schematic feature on servers, when\nusing the command-based paste mode");
        public static final ConfigBoolean       PICK_BLOCK_AUTO         = new ConfigBoolean(    "pickBlockAuto", false, "Automatically pick block before every placed block");
        public static final ConfigBoolean       PICK_BLOCK_ENABLED      = new ConfigBoolean(    "pickBlockEnabled", true, "Enables the schematic world pick block hotkeys.\nThere is also a hotkey for toggling this option to toggle those hotkeys... o.o", "Pick Block Hotkeys");
        public static final ConfigBoolean       PICK_BLOCK_IGNORE_NBT   = new ConfigBoolean(    "pickBlockIgnoreNBT", true, "Ignores the NBT data on the expected vs. found items for pick block.\nAllows the pick block to work for example with renamed items.");
        public static final ConfigString        PICK_BLOCKABLE_SLOTS    = new ConfigString(     "pickBlockableSlots", "1-9", "The hotbar slots that are allowed to be\nused for the schematic pick block.\nCan use comma separated individual slots and dash\nseparated slot ranges (no spaces anywhere).\nExample: 2,4-6,9");
        public static final ConfigBoolean       PLACEMENT_RESTRICTION   = new ConfigBoolean(    "placementRestriction", false, "When enabled, the use key can only be used\nwhen holding the correct item for the targeted position,\nand the targeted position must have a missing block in the schematic", "Placement Restriction");
        public static final ConfigBoolean       PLACEMENTS_INFRONT      = new ConfigBoolean(    "placementInfrontOfPlayer", false, "When enabled, created placements or moved placements are\npositioned so that they are fully infront of the player,\ninstead of the placement's origin point being at the player's location");
        public static final ConfigBoolean       RENDER_MATERIALS_IN_GUI = new ConfigBoolean(    "renderMaterialListInGuis", true, "Whether or not the material list should\nbe rendered inside GUIs");
        public static final ConfigBoolean       RENDER_THREAD_NO_TIMEOUT = new ConfigBoolean(   "renderThreadNoTimeout", true, "Removes the timeout from the rendering worker threads.\nIf you get very stuttery rendering when moving around\nor dealing with large schematics, try disabling this. It will however make\nthe schematic rendering a lot slower in some cases.");
        public static final ConfigBoolean       SIGN_TEXT_PASTE         = new ConfigBoolean(    "signTextPaste", true, "Automatically set the text in the sign GUIs from the schematic");
        public static final ConfigString        TOOL_ITEM               = new ConfigString(     "toolItem", "minecraft:stick", "The item to use as the \"tool\" for selections etc.");
        public static final ConfigBoolean       TOOL_ITEM_ENABLED       = new ConfigBoolean(    "toolItemEnabled", true, "If true, then the \"tool\" item can be used to control selections etc.", "Tool Item Enabled");

        public static final ConfigOptionList<ReplaceBehavior>           PASTE_REPLACE_BEHAVIOR  = new ConfigOptionList<ReplaceBehavior>( "pasteReplaceBehavior", ReplaceBehavior.NONE, "The behavior of replacing existing blocks\nin the Paste schematic tool mode");
        public static final ConfigOptionList<CornerSelectionMode>       SELECTION_CORNERS_MODE  = new ConfigOptionList<CornerSelectionMode>( "selectionCornersMode", CornerSelectionMode.CORNERS, "The Area Selection corners mode to use (Corners, or Expand)");

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                AREAS_PER_WORLD,
                BETTER_RENDER_ORDER,
                CHANGE_SELECTED_CORNER,
                CLONE_AT_ORIGINAL_POS,
                CUSTOM_SCHEMATIC_DIRECTORY_ENABLED,
                EASY_PLACE_CLICK_ADJACENT,
                EASY_PLACE_MODE,
                EASY_PLACE_HOLD_ENABLED,
                EASY_PLACE_DEEP_ENABLED,
                EXECUTE_REQUIRE_TOOL,
                FIX_RAIL_ROTATION,
                GENERATE_LOWERCASE_NAMES,
                LOAD_ENTIRE_SCHEMATICS,
                MATERIALS_FROM_CONTAINER,
                PICK_BLOCK_AUTO,
                PICK_BLOCK_ENABLED,
                PICK_BLOCK_IGNORE_NBT,
                PLACEMENT_RESTRICTION,
                PLACEMENTS_INFRONT,
                RENDER_MATERIALS_IN_GUI,
                RENDER_THREAD_NO_TIMEOUT,
                SIGN_TEXT_PASTE,
                TOOL_ITEM_ENABLED,

                PASTE_REPLACE_BEHAVIOR,
                SELECTION_CORNERS_MODE,

                CUSTOM_SCHEMATIC_DIRECTORY,
                PASTE_COMMAND_INTERVAL,
                PASTE_COMMAND_LIMIT,
                PASTE_COMMAND_SETBLOCK,
                PICK_BLOCKABLE_SLOTS,
                TOOL_ITEM
        );
    }

    public static class Visuals
    {
        public static final ConfigBoolean       ENABLE_AREA_SELECTION_RENDERING     = new ConfigBoolean("enableAreaSelectionBoxesRendering", true, "Enable Area Selection boxes rendering", "Area Selection Boxes Rendering");
        public static final ConfigBoolean       ENABLE_PLACEMENT_BOXES_RENDERING    = new ConfigBoolean("enablePlacementBoxesRendering", true, "Enable Schematic Placement boxes rendering", "Schematic Placement Boxes Rendering");
        public static final ConfigBoolean       ENABLE_RENDERING                    = new ConfigBoolean("enableRendering", true, "Main rendering toggle option. Enables/disables ALL mod rendering.", "All Rendering");
        public static final ConfigBoolean       ENABLE_SCHEMATIC_BLOCKS             = new ConfigBoolean("enableSchematicBlocksRendering",  true, "Enables schematic block rendering.\nDisabling this allows you to only see the color overlay", "Schematic Blocks Rendering");
        public static final ConfigBoolean       ENABLE_SCHEMATIC_OVERLAY            = new ConfigBoolean("enableSchematicOverlay",  true, "The main toggle option for the schematic\nblock overlay rendering", "Schematic Overlay Rendering");
        public static final ConfigBoolean       ENABLE_SCHEMATIC_RENDERING          = new ConfigBoolean("enableSchematicRendering", true, "Enable rendering the schematic and overlay", "Schematic Rendering");
        public static final ConfigDouble        GHOST_BLOCK_ALPHA                   = new ConfigDouble( "ghostBlockAlpha", 0.5, 0, 1, "The alpha value of the ghost blocks,\nwhen rendering them as translucent.\n§6Note: §7You also need to enable the translucent rendering separately,\nusing the 'renderBlocksAsTranslucent' option!");
        public static final ConfigBoolean       IGNORE_EXISTING_FLUIDS              = new ConfigBoolean("ignoreExistingFluids", false, "If enabled, then any fluid blocks are ignored as \"extra blocks\"\nand as \"wrong blocks\", ie. where the schematic has air or other blocks.\nBasically this makes building stuff under water a whole lot less annoying.\nNote: You will most likely also want to enable the 'renderCollidingSchematicBlocks'\noption at the same time, to allow the blocks to get rendered inside fluids.");
        public static final ConfigBoolean       OVERLAY_REDUCED_INNER_SIDES         = new ConfigBoolean("overlayReducedInnerSides", false, "If enabled, then the adjacent/touching inner sides\nfor the block overlays are removed/not rendered");
        public static final ConfigDouble        PLACEMENT_BOX_SIDE_ALPHA            = new ConfigDouble( "placementBoxSideAlpha", 0.2, 0, 1, "The alpha value of the sub-region boxes' side");
        public static final ConfigBoolean       RENDER_AREA_SELECTION_BOX_SIDES     = new ConfigBoolean("renderAreaSelectionBoxSides", true, "If enabled, then the area selection boxes will\nhave their side quads rendered");
        public static final ConfigBoolean       RENDER_BLOCKS_AS_TRANSLUCENT        = new ConfigBoolean("renderBlocksAsTranslucent", false, "If enabled, then the schematics are rendered\nusing translucent \"ghost blocks\"", "Translucent Schematic Block Rendering");
        public static final ConfigBoolean       RENDER_COLLIDING_BLOCK_AT_CURSOR    = new ConfigBoolean("renderCollidingBlockAtCursor", false, "If enabled, then the expected block in the schematic is rendered\nat the hovered block, if it's not currently correct");
        public static final ConfigBoolean       RENDER_COLLIDING_SCHEMATIC_BLOCKS   = new ConfigBoolean("renderCollidingSchematicBlocks", false, "If enabled, then blocks in the schematics are rendered\nalso when there is already a (wrong) block in the client world.\nProbably mostly useful when trying to build\nsomething where there are snow layers or water in the way.");
        public static final ConfigBoolean       RENDER_ERROR_MARKER_CONNECTIONS     = new ConfigBoolean("renderErrorMarkerConnections", false, "Render connecting lines between subsequent verifier hilight box corners.\nThis was a rendering bug that some people experienced, but at least some players\nliked it and requested for it to stay, so this options \"restores\" it");
        public static final ConfigBoolean       RENDER_ERROR_MARKER_SIDES           = new ConfigBoolean("renderErrorMarkerSides", true, "If enabled, then the error markers in the Schematic Verifier\nwill have (translucent) sides rendered instead of just the outline");
        public static final ConfigBoolean       RENDER_PLACEMENT_BOX_SIDES          = new ConfigBoolean("renderPlacementBoxSides", false, "If enabled, then the placed schematic sub-region boxes\nwill have their side quads rendered");
        public static final ConfigBoolean       RENDER_PLACEMENT_ENCLOSING_BOX      = new ConfigBoolean("renderPlacementEnclosingBox", true, "If enabled, then an enclosing box is rendered around\nall the sub-regions in a schematic (placement)");
        public static final ConfigBoolean       RENDER_PLACEMENT_ENCLOSING_BOX_SIDES= new ConfigBoolean("renderPlacementEnclosingBoxSides", false, "If enabled, then the enclosing box around\na schematic placement will have its side quads rendered");
        public static final ConfigBoolean       RENDER_TRANSLUCENT_INNER_SIDES      = new ConfigBoolean("renderTranslucentBlockInnerSides", false, "If enabled, then the model sides are also rendered\nfor inner sides in the translucent mode");
        public static final ConfigBoolean       SCHEMATIC_OVERLAY_ENABLE_OUTLINES   = new ConfigBoolean("schematicOverlayEnableOutlines",  true, "Enables rendering a wire frame outline for\nthe schematic block overlay", "Schematic Overlay Outlines");
        public static final ConfigBoolean       SCHEMATIC_OVERLAY_ENABLE_SIDES      = new ConfigBoolean("schematicOverlayEnableSides",     true, "Enables rendering translucent boxes/sides for\nthe schematic block overlay", "Schematic Overlay Sides");
        public static final ConfigBoolean       SCHEMATIC_OVERLAY_MODEL_OUTLINE     = new ConfigBoolean("schematicOverlayModelOutline",    true, "If enabled, then the schematic overlay will use the\nblock model quads/vertices instead of the\ntraditional full block overlay");
        public static final ConfigBoolean       SCHEMATIC_OVERLAY_MODEL_SIDES       = new ConfigBoolean("schematicOverlayModelSides",      true, "If enabled, then the schematic overlay will use the\nblock model quads/vertices instead of the\ntraditional full block overlay");
        public static final ConfigDouble        SCHEMATIC_OVERLAY_OUTLINE_WIDTH     = new ConfigDouble( "schematicOverlayOutlineWidth",  1.0, 0.000001, 64, "The line width of the block (model) outlines");
        public static final ConfigDouble        SCHEMATIC_OVERLAY_OUTLINE_WIDTH_THROUGH = new ConfigDouble( "schematicOverlayOutlineWidthThrough",  3.0, 0.000001, 64, "The line width of the block (model) outlines,\nwhen the overlay is rendered through blocks");
        public static final ConfigBoolean       SCHEMATIC_OVERLAY_RENDER_THROUGH    = new ConfigBoolean("schematicOverlayRenderThroughBlocks", false, "If enabled, then the schematic overlay will be rendered\nthrough blocks. This is probably only useful once you are\nfinished building and want to see any errors easier");
        public static final ConfigBoolean       SCHEMATIC_OVERLAY_TYPE_EXTRA        = new ConfigBoolean("schematicOverlayTypeExtra",       true, "Enables the schematic overlay for extra blocks");
        public static final ConfigBoolean       SCHEMATIC_OVERLAY_TYPE_MISSING      = new ConfigBoolean("schematicOverlayTypeMissing",     true, "Enables the schematic overlay for missing blocks");
        public static final ConfigBoolean       SCHEMATIC_OVERLAY_TYPE_WRONG_BLOCK  = new ConfigBoolean("schematicOverlayTypeWrongBlock",  true, "Enables the schematic overlay for wrong blocks");
        public static final ConfigBoolean       SCHEMATIC_OVERLAY_TYPE_WRONG_STATE  = new ConfigBoolean("schematicOverlayTypeWrongState",  true, "Enables the schematic overlay for wrong states");
        public static final ConfigBoolean       SCHEMATIC_VERIFIER_BLOCK_MODELS     = new ConfigBoolean("schematicVerifierUseBlockModels", false, "Forces using blocks models for everything in the Schematic Verifier\nresult list. Normally item models are used for anything\nthat has an item, and block models are only used for blocks\nthat don't have an item, plus for Flower Pots to see the contained item.");

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                ENABLE_RENDERING,
                ENABLE_SCHEMATIC_RENDERING,

                ENABLE_AREA_SELECTION_RENDERING,
                ENABLE_PLACEMENT_BOXES_RENDERING,
                ENABLE_SCHEMATIC_BLOCKS,
                ENABLE_SCHEMATIC_OVERLAY,
                IGNORE_EXISTING_FLUIDS,
                OVERLAY_REDUCED_INNER_SIDES,
                RENDER_AREA_SELECTION_BOX_SIDES,
                RENDER_BLOCKS_AS_TRANSLUCENT,
                RENDER_COLLIDING_BLOCK_AT_CURSOR,
                RENDER_COLLIDING_SCHEMATIC_BLOCKS,
                RENDER_ERROR_MARKER_CONNECTIONS,
                RENDER_ERROR_MARKER_SIDES,
                RENDER_PLACEMENT_BOX_SIDES,
                RENDER_PLACEMENT_ENCLOSING_BOX,
                RENDER_PLACEMENT_ENCLOSING_BOX_SIDES,
                RENDER_TRANSLUCENT_INNER_SIDES,
                SCHEMATIC_OVERLAY_ENABLE_OUTLINES,
                SCHEMATIC_OVERLAY_ENABLE_SIDES,
                SCHEMATIC_OVERLAY_MODEL_OUTLINE,
                SCHEMATIC_OVERLAY_MODEL_SIDES,
                SCHEMATIC_OVERLAY_RENDER_THROUGH,
                SCHEMATIC_OVERLAY_TYPE_EXTRA,
                SCHEMATIC_OVERLAY_TYPE_MISSING,
                SCHEMATIC_OVERLAY_TYPE_WRONG_BLOCK,
                SCHEMATIC_OVERLAY_TYPE_WRONG_STATE,
                SCHEMATIC_VERIFIER_BLOCK_MODELS,

                GHOST_BLOCK_ALPHA,
                PLACEMENT_BOX_SIDE_ALPHA,
                SCHEMATIC_OVERLAY_OUTLINE_WIDTH,
                SCHEMATIC_OVERLAY_OUTLINE_WIDTH_THROUGH
        );
    }

    public static class InfoOverlays
    {
        public static final ConfigBoolean       BLOCK_INFO_LINES_ENABLED            = new ConfigBoolean(    "blockInfoLinesEnabled", true, "If enabled, then MiniHUD-style block info overlay\nis rendered for the looked-at block");
        public static final ConfigDouble        BLOCK_INFO_LINES_FONT_SCALE         = new ConfigDouble(     "blockInfoLinesFontScale", 0.5, 0, 10, "The font scale for the block info lines");
        public static final ConfigInteger       BLOCK_INFO_LINES_OFFSET_X           = new ConfigInteger(    "blockInfoLinesOffsetX", 4, 0, 2000, "The x offset of the block info lines from the selected edge");
        public static final ConfigInteger       BLOCK_INFO_LINES_OFFSET_Y           = new ConfigInteger(    "blockInfoLinesOffsetY", 4, 0, 2000, "The y offset of the block info lines from the selected edge");
        public static final ConfigInteger       BLOCK_INFO_OVERLAY_OFFSET_Y         = new ConfigInteger(    "blockInfoOverlayOffsetY", 6, -2000, 2000, "The y offset of the block info overlay from the selected edge");
        public static final ConfigBoolean       BLOCK_INFO_OVERLAY_ENABLED          = new ConfigBoolean(    "blockInfoOverlayEnabled", true, "Enable Block Info Overlay rendering to show info\nabout the looked-at block or verifier error marker,\nwhile holding the 'renderInfoOverlay' key", "Block Info Overlay Rendering");
        public static final ConfigInteger       INFO_HUD_MAX_LINES                  = new ConfigInteger(    "infoHudMaxLines", 10, 1, 128, "The maximum number of info lines to show on the HUD at once");
        public static final ConfigInteger       INFO_HUD_OFFSET_X                   = new ConfigInteger(    "infoHudOffsetX", 1, 0, 32000, "The X offset of the Info HUD from the screen edge");
        public static final ConfigInteger       INFO_HUD_OFFSET_Y                   = new ConfigInteger(    "infoHudOffsetY", 1, 0, 32000, "The Y offset of the Info HUD from the screen edge");
        public static final ConfigDouble        INFO_HUD_SCALE                      = new ConfigDouble(     "infoHudScale", 1, 0.1, 4, "Scale factor for the generic Info HUD text");
        public static final ConfigInteger       MATERIAL_LIST_HUD_MAX_LINES         = new ConfigInteger(    "materialListHudMaxLines", 10, 1, 128, "The maximum number of items to show on\nthe Material List Info HUD at once");
        public static final ConfigDouble        MATERIAL_LIST_HUD_SCALE             = new ConfigDouble(     "materialListHudScale", 1, 0.1, 4, "Scale factor for the Material List Info HUD");
        public static final ConfigBoolean       MATERIAL_LIST_HUD_STACKS            = new ConfigBoolean(    "materialListHudStacks", true, "Whether or not the number of stacks should be shown\non the Material List HUD, or only the total count");
        public static final ConfigBoolean       MATERIAL_LIST_SLOT_HIGHLIGHT        = new ConfigBoolean(    "materialListSlotHighlight", true, "Highlight inventory slots containing items that are\ncurrently missing or running low in the player's inventory\naccording to the currently active Material List");
        public static final ConfigBoolean       STATUS_INFO_HUD                     = new ConfigBoolean(    "statusInfoHud", false, "Enable a status info HUD renderer,\nwhich renders a few bits of status info, such as\nthe current layer mode and renderers enabled state");
        public static final ConfigBoolean       STATUS_INFO_HUD_AUTO                = new ConfigBoolean(    "statusInfoHudAuto", true, "Allow automatically momentarily enabling the status info HUD \"when needed\",\nfor example when creating a placement and having rendering disabled");
        public static final ConfigBoolean       TOOL_HUD_ALWAYS_VISIBLE             = new ConfigBoolean(    "toolHudAlwaysVisible", false, "Whether or not the tool HUD should always be rendered,\neven when not holding the tool item");
        public static final ConfigInteger       TOOL_HUD_OFFSET_X                   = new ConfigInteger(    "toolHudOffsetX", 1, 0, 32000, "The X offset of the Info HUD from the screen edge");
        public static final ConfigInteger       TOOL_HUD_OFFSET_Y                   = new ConfigInteger(    "toolHudOffsetY", 1, 0, 32000, "The X offset of the Info HUD from the screen edge");
        public static final ConfigDouble        TOOL_HUD_SCALE                      = new ConfigDouble(     "toolHudScale", 1, 0.1, 4, "Scale factor for the Tool HUD text");
        public static final ConfigDouble        VERIFIER_ERROR_HILIGHT_ALPHA        = new ConfigDouble(     "verifierErrorHilightAlpha", 0.2, 0, 1, "The alpha value of the error marker box sides");
        public static final ConfigInteger       VERIFIER_ERROR_HILIGHT_MAX_POSITIONS = new ConfigInteger(   "verifierErrorHilightMaxPositions", 1000, 1, 1000000, "The maximum number of mismatched positions to render\nat once in the Schematic Verifier overlay.");
        public static final ConfigBoolean       VERIFIER_OVERLAY_ENABLED            = new ConfigBoolean(    "verifierOverlayEnabled", true, "Enable Schematic Verifier marker overlay rendering", "Verifier Overlay Rendering");
        public static final ConfigBoolean       WARN_DISABLED_RENDERING             = new ConfigBoolean(    "warnDisabledRendering", true, "Should the warning message about being in a layer mode\nor having some of the rendering options disabled\nbe shown when loading a new schematic\nor creating a new placement");

        public static final ConfigOptionList<HudAlignment>          BLOCK_INFO_LINES_ALIGNMENT      = new ConfigOptionList<HudAlignment>( "blockInfoLinesAlignment", HudAlignment.TOP_RIGHT, "The alignment of the block info lines overlay");
        public static final ConfigOptionList<BlockInfoAlignment>    BLOCK_INFO_OVERLAY_ALIGNMENT    = new ConfigOptionList<BlockInfoAlignment>( "blockInfoOverlayAlignment", BlockInfoAlignment.TOP_CENTER, "The alignment of the Block Info Overlay");
        public static final ConfigOptionList<InfoType>              EASY_PLACE_WARNINGS             = new ConfigOptionList<InfoType>( "easyPlaceWarnings", InfoType.MESSAGE_OVERLAY, "Whether to show the \"Action prevented by *\"\nwarnings for the Easy Place and Placement Restriction modes");
        public static final ConfigOptionList<HudAlignment>          INFO_HUD_ALIGNMENT              = new ConfigOptionList<HudAlignment>( "infoHudAlignment", HudAlignment.BOTTOM_RIGHT, "The alignment of the \"Info HUD\",\nused for the Material List, Schematic Verifier mismatch positions etc.");
        public static final ConfigOptionList<HudAlignment>          TOOL_HUD_ALIGNMENT              = new ConfigOptionList<HudAlignment>( "toolHudAlignment", HudAlignment.BOTTOM_LEFT, "The alignment of the \"tool HUD\", when holding the configured \"tool\"");

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                BLOCK_INFO_LINES_ENABLED,
                BLOCK_INFO_OVERLAY_ENABLED,
                MATERIAL_LIST_HUD_STACKS,
                MATERIAL_LIST_SLOT_HIGHLIGHT,
                STATUS_INFO_HUD,
                STATUS_INFO_HUD_AUTO,
                TOOL_HUD_ALWAYS_VISIBLE,
                VERIFIER_OVERLAY_ENABLED,
                WARN_DISABLED_RENDERING,

                BLOCK_INFO_LINES_ALIGNMENT,
                BLOCK_INFO_OVERLAY_ALIGNMENT,
                EASY_PLACE_WARNINGS,
                INFO_HUD_ALIGNMENT,
                TOOL_HUD_ALIGNMENT,

                BLOCK_INFO_LINES_OFFSET_X,
                BLOCK_INFO_LINES_OFFSET_Y,
                BLOCK_INFO_LINES_FONT_SCALE,
                BLOCK_INFO_OVERLAY_OFFSET_Y,
                INFO_HUD_MAX_LINES,
                INFO_HUD_OFFSET_X,
                INFO_HUD_OFFSET_Y,
                INFO_HUD_SCALE,
                MATERIAL_LIST_HUD_MAX_LINES,
                MATERIAL_LIST_HUD_SCALE,
                TOOL_HUD_OFFSET_X,
                TOOL_HUD_OFFSET_Y,
                TOOL_HUD_SCALE,
                VERIFIER_ERROR_HILIGHT_ALPHA,
                VERIFIER_ERROR_HILIGHT_MAX_POSITIONS
        );
    }

    public static class Colors
    {
        public static final ConfigColor AREA_SELECTION_BOX_SIDE_COLOR       = new ConfigColor("areaSelectionBoxSideColor",              "0x30FFFFFF", "The color of the area selection boxes, when they are unselected");
        public static final ConfigColor MATERIAL_LIST_HUD_ITEM_COUNTS       = new ConfigColor("materialListHudItemCountsColor",         "0xFFFFAA00", "The color of the item count text in the Material List info HUD");
        public static final ConfigColor MATERIAL_LIST_SLOT_HL_LT_STACK      = new ConfigColor("materialListSlotHighlightLessThanStack", "0x80FF40D0", "The color for the \"less than one stack available\" slot highlight");
        public static final ConfigColor MATERIAL_LIST_SLOT_HL_NONE          = new ConfigColor("materialListSlotHighlightNone",          "0x80FF2000", "The color for the \"completely out\" slot highlight");
        public static final ConfigColor MATERIAL_LIST_SLOT_HL_NOT_ENOUGH    = new ConfigColor("materialListSlotHighlightNotEnough",     "0x80FFE040", "The color for the \"not enough\" slot highlight");
        public static final ConfigColor REBUILD_BREAK_OVERLAY_COLOR         = new ConfigColor("schematicRebuildBreakPlaceOverlayColor", "0x4C33CC33", "The color of Schematic Rebuild mode's break or place blocks selector overlay");
        public static final ConfigColor REBUILD_REPLACE_OVERLAY_COLOR       = new ConfigColor("schematicRebuildReplaceOverlayColor",    "0x4CF0A010", "The color of Schematic Rebuild mode's replace selector overlay");
        public static final ConfigColor SCHEMATIC_OVERLAY_COLOR_EXTRA       = new ConfigColor("schematicOverlayColorExtra",             "0x4CFF4CE6", "The color of the blocks overlay for extra blocks");
        public static final ConfigColor SCHEMATIC_OVERLAY_COLOR_MISSING     = new ConfigColor("schematicOverlayColorMissing",           "0x2C33B3E6", "The color of the blocks overlay for missing blocks");
        public static final ConfigColor SCHEMATIC_OVERLAY_COLOR_WRONG_BLOCK = new ConfigColor("schematicOverlayColorWrongBlock",        "0x4CFF3333", "The color of the blocks overlay for wrong blocks");
        public static final ConfigColor SCHEMATIC_OVERLAY_COLOR_WRONG_STATE = new ConfigColor("schematicOverlayColorWrongState",        "0x4CFF9010", "The color of the blocks overlay for wrong block states");

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                AREA_SELECTION_BOX_SIDE_COLOR,
                MATERIAL_LIST_HUD_ITEM_COUNTS,
                MATERIAL_LIST_SLOT_HL_LT_STACK,
                MATERIAL_LIST_SLOT_HL_NONE,
                MATERIAL_LIST_SLOT_HL_NOT_ENOUGH,
                REBUILD_BREAK_OVERLAY_COLOR,
                REBUILD_REPLACE_OVERLAY_COLOR,
                SCHEMATIC_OVERLAY_COLOR_EXTRA,
                SCHEMATIC_OVERLAY_COLOR_MISSING,
                SCHEMATIC_OVERLAY_COLOR_WRONG_BLOCK,
                SCHEMATIC_OVERLAY_COLOR_WRONG_STATE
        );
    }

    // Configs that are not shown in the config GUI
    public static class Internal
    {
        public static final ConfigBoolean       CREATE_PLACEMENT_ON_LOAD        = new ConfigBoolean("createPlacementOnLoad",        true, "A Schematic Placement is created automatically when loading a schematic");
        public static final ConfigBoolean       PLACEMENT_LIST_ICON_BUTTONS     = new ConfigBoolean("placementListIconButtons",     false, "Show smaller, icon-only buttons in the Schematic Placements list");
        public static final ConfigBoolean       SCHEMATIC_LIST_ICON_BUTTONS     = new ConfigBoolean("schematicListIconButtons",     false, "Show smaller, icon-only buttons in the Loaded Schematics list");

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                CREATE_PLACEMENT_ON_LOAD,
                PLACEMENT_LIST_ICON_BUTTONS,
                SCHEMATIC_LIST_ICON_BUTTONS
        );
    }

    @Override
    public String getModName()
    {
        return Reference.MOD_NAME;
    }

    @Override
    public String getConfigFileName()
    {
        return Reference.MOD_ID + ".json";
    }

    @Override
    public Map<String, List<? extends IConfigBase>> getConfigsPerCategories()
    {
        Map<String, List<? extends IConfigBase>> map = new LinkedHashMap<>();

        map.put("Generic", Generic.OPTIONS);
        map.put("InfoOverlays", InfoOverlays.OPTIONS);
        map.put("Internal", Internal.OPTIONS);
        map.put("Visuals", Visuals.OPTIONS);
        map.put("Colors", Colors.OPTIONS);
        map.put("Hotkeys", Hotkeys.HOTKEY_LIST);

        return map;
    }

    @Override
    public boolean shouldShowCategoryOnConfigGuis(String category)
    {
        return category.equals("Internal") == false;
    }

    @Override
    public void onPostLoad()
    {
        DataManager.setToolItem(Generic.TOOL_ITEM.getStringValue());
        InventoryUtils.setPickBlockableSlots(Generic.PICK_BLOCKABLE_SLOTS.getStringValue());
    }
}
