package powersaj.skybuttons.skybuttons;

import powersaj.skybuttons.powersaj.StringPair;
import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.container.UIContainers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static powersaj.skybuttons.skybuttons.UiTooling.makeButtonList;


public class ListingScreen extends BaseOwoScreen<FlowLayout> {

    boolean ifOpenAllOnStartup = true;

    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, UIContainers::verticalFlow);
    }

    @Override
    protected void build(FlowLayout rootComponent) {

        rootComponent
                .surface(Surface.BLANK)
                .horizontalAlignment(HorizontalAlignment.LEFT)
                .verticalAlignment(VerticalAlignment.CENTER);

        rootComponent
                .child(UIContainers.verticalScroll(Sizing.fill(35),Sizing.fill(), UIContainers.verticalFlow(Sizing.content(),Sizing.content())

                        .child(UiTooling.makeButtonList("Specials", List.of(
                                new StringPair("Island", "warp island"),
                                new StringPair("Garden", "warp garden"),
                                new StringPair("Dungeon Hub", "warp dungeon_hub"),
                                new StringPair("Jerry's Workshop", "warp jerry"),
                                new StringPair("Rift", "warp rift")
                        )))

                        .child(UiTooling.makeButtonList("The Hub", List.of(
                                new StringPair("Spawn (Default)", "warp hub"),
                                new StringPair("Museum", "warp museum"),
                                new StringPair("Sirius Shack", "warp da"),
                                new StringPair("Hub Crypts", "warp crypts"),
                                new StringPair("Wizard Tower", "warp wiz"),
                                new StringPair("Community Center","warp elizabeth")
                        )))

                        .child(UiTooling.makeButtonList("The Farming Islands", List.of(
                                new StringPair("Spawn (Default)", "warp barn"),
                                new StringPair("Mushroom Desert", "warp desert"),
                                new StringPair("Trapper's Den", "warp trapper")
                        )))

                        .child(UiTooling.makeButtonList("The Park", List.of(
                                new StringPair("Spawn (Default)", "warp park"),
                                new StringPair("Howling Cave", "warp howl"),
                                new StringPair("Jungle Island", "warp jungle")
                        )))

                        .child(UiTooling.makeButtonList("Singular Mines", List.of(
                                new StringPair("Gold Mine", "warp gold"),
                                new StringPair("Deep Caverns", "warp deep")
                        )))

                        .child(UiTooling.makeButtonList("Dwarven Mines", List.of(
                                new StringPair("Spawn (Default)", "warp dwarves"),
                                new StringPair("The Forge", "warp forge"),
                                new StringPair("Dwarven Base Camp", "warp basecamp")
                        )))

                        .child(UiTooling.makeButtonList("Crystal Hollows", List.of(
                                new StringPair("Spawn (Default)", "warp crystals"),
                                new StringPair("Crystal Nucleus", "warp nucleus")
                        )))

                        .child(UiTooling.makeButtonList("Spider's Den", List.of(
                                new StringPair("Spawn (Default)", "warp spider"),
                                new StringPair("Top Of Nest", "warp top"),
                                new StringPair("Arachne's Sanctuary", "warp arachne")
                        )))

                        .child(UiTooling.makeButtonList("The End", List.of(
                                new StringPair("Spawn (Default)", "warp end"),
                                new StringPair("Dragon's Nest", "warp drag"),
                                new StringPair("Void Sepulture", "warp void")
                        )))

                        .child(UiTooling.makeButtonList("Crimson Isle", List.of(
                                new StringPair("Spawn (Default)", "warp isle"),
                                new StringPair("Forgotten Skull", "warp skull"),
                                new StringPair("Smoldering Tomb", "warp tomb"),
                                new StringPair("The Wasteland", "warp wasteland"),
                                new StringPair("Dragontail", "warp dragontail"),
                                new StringPair("Scarleton", "warp scarleton")
                        )))

                        .child(UiTooling.makeButtonList("Waters", List.of(
                                new StringPair("Galatea", "warp galatea"),
                                new StringPair("Backwater Bayou", "warp bayou")
                        )))

        ).margins(Insets.of(0,0,0,0)).alignment(HorizontalAlignment.LEFT,VerticalAlignment.CENTER).surface(Surface.VANILLA_TRANSLUCENT));
}}


