package powersaj.skybuttons.skybuttons;

import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.UIComponents;
import io.wispforest.owo.ui.container.CollapsibleContainer;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.container.UIContainers;
import io.wispforest.owo.ui.core.*;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import powersaj.skybuttons.powersaj.StringPair;

import java.util.*;


public class ListingScreen extends BaseOwoScreen<FlowLayout> {

    static {
        SkyButtonsConfig.load();
    }

    // category states and showMvpPlusOnly are persisted via SkyButtonsConfig across sessions
    final List<UIComponent> mvpContainers = new ArrayList<>();
    FlowLayout scrollInner;

    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, UIContainers::verticalFlow);
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        mvpContainers.clear();

        rootComponent
                .surface(Surface.BLANK)
                .horizontalAlignment(HorizontalAlignment.LEFT)
                .verticalAlignment(VerticalAlignment.CENTER);

        scrollInner = UIContainers.verticalFlow(Sizing.content(), Sizing.content());

        // Specials (no MVP+ entries)
        scrollInner.child(makeCategory("Specials", List.of(
                new StringPair("Island", "warp island"),
                new StringPair("Garden", "warp garden"),
                new StringPair("Dungeon Hub", "warp dungeon_hub"),
                new StringPair("Jerry's Workshop", "warp jerry"),
                new StringPair("Rift", "warp rift")
        )));

        // The Hub
        addButtonList("The Hub",
                List.of(
                        new StringPair("Spawn (Default)", "warp hub"),
                        new StringPair("Museum", "warp museum"),
                        new StringPair("Wizard Tower", "warp wiz")
                ),
                List.of(
                        new StringPair("Sirius Shack", "warp da"),
                        new StringPair("Hub Crypts", "warp crypts"),
                        new StringPair("Castle", "warp castle")
                ));

        // The Farming Islands (no MVP+ entries)
        scrollInner.child(makeCategory("The Farming Islands", List.of(
                new StringPair("Spawn (Default)", "warp barn"),
                new StringPair("Mushroom Desert", "warp desert"),
                new StringPair("Glowing Mushroom Cave", "warp glowing"),
                new StringPair("Trapper's Den", "warp trapper")
        )));

        // The Park
        addButtonList("The Park",
                List.of(
                        new StringPair("Spawn (Default)", "warp park")
                ),
                List.of(
                        new StringPair("Howling Cave", "warp howl"),
                        new StringPair("Jungle Island", "warp jungle")
                ));

        // Singular Mines (no MVP+ entries)
        scrollInner.child(makeCategory("Singular Mines", List.of(
                new StringPair("Gold Mine", "warp gold"),
                new StringPair("Deep Caverns", "warp deep")
        )));

        // Dwarven Mines (no MVP+ entries)
        scrollInner.child(makeCategory("Dwarven Mines", List.of(
                new StringPair("Spawn (Default)", "warp dwarves"),
                new StringPair("The Forge", "warp forge"),
                new StringPair("Dwarven Base Camp", "warp basecamp")
        )));

        // Crystal Hollows (no MVP+ entries)
        scrollInner.child(makeCategory("Crystal Hollows", List.of(
                new StringPair("Spawn (Default)", "warp crystals"),
                new StringPair("Crystal Nucleus", "warp nucleus")
        )));

        // Spider's Den
        addButtonList("Spider's Den",
                List.of(
                        new StringPair("Spawn (Default)", "warp spider")
                ),
                List.of(
                        new StringPair("Top Of Nest", "warp top"),
                        new StringPair("Arachne's Sanctuary", "warp arachne")
                ));

        // The End
        addButtonList("The End",
                List.of(
                        new StringPair("Spawn (Default)", "warp end")
                ),
                List.of(
                        new StringPair("Dragon's Nest", "warp drag"),
                        new StringPair("Void Sepulture", "warp void")
                ));

        // Crimson Isle
        addButtonList("Crimson Isle",
                List.of(
                        new StringPair("Spawn (Default)", "warp isle"),
                        new StringPair("The Wasteland", "warp wasteland"),
                        new StringPair("Dragontail", "warp dragontail"),
                        new StringPair("Scarleton", "warp scarleton")
                ),
                List.of(
                        new StringPair("Forgotten Skull", "warp skull"),
                        new StringPair("Smoldering Tomb", "warp smold")
                ));

        // Waters (no MVP+ entries)
        scrollInner.child(makeCategory("Waters", List.of(
                new StringPair("Galatea", "warp galatea"),
                new StringPair("Murkwater Depths", "warp murkwater"),
                new StringPair("Backwater Bayou", "warp bayou")
        )));

        var scrollContent = UIContainers.verticalScroll(Sizing.fill(35), Sizing.fill(), scrollInner)
                .margins(Insets.of(0, 0, 0, 0))
                .alignment(HorizontalAlignment.LEFT, VerticalAlignment.CENTER)
                .surface(Surface.VANILLA_TRANSLUCENT);

        // --- Toggle button at bottom-right with distance from edge ---
        var useMvpKey = "showMvpPlusOnly";
        var toggleButton = UIComponents.button(
                Text.literal(SkyButtonsConfig.get(useMvpKey, false) ? "✓ Switch MVP+ only" : "Switch MVP+ only"),
                button -> {
                    var now = !SkyButtonsConfig.get(useMvpKey, false);
                    SkyButtonsConfig.set(useMvpKey, now);
                    button.setMessage(Text.literal(now ? "✓ Switch MVP+ only" : "Switch MVP+ only"));
                    toggleMvpContainers(now);
                }
        );

        // Positioning.relative(100, 100) pushes to bottom-right; margins create distance from screen edge
        toggleButton.margins(Insets.of(0, 10, 0, 15));
        toggleButton.positioning(Positioning.relative(100, 100));

        rootComponent.child(scrollContent);
        rootComponent.child(toggleButton);

        if (SkyButtonsConfig.get(useMvpKey, false)) {
            toggleMvpContainers(true);
        }
    }

    private void toggleMvpContainers(boolean hide) {
        if (hide) {
            for (var container : mvpContainers) {
                scrollInner.removeChild(container);
            }
        } else {
            for (var container : mvpContainers) {
                scrollInner.child(container);
            }
        }
    }

    private CollapsibleContainer makeCategory(String name, List<StringPair> entries) {
        var key = "cat_" + name;
        boolean defaultOpen = SkyButtonsConfig.get(key, true);
        var c = UiTooling.makeButtonList(name, entries, defaultOpen);
        c.onToggled().subscribe(nowExpanded -> SkyButtonsConfig.set(key, nowExpanded));
        return c;
    }

    private void addButtonList(String listName, List<StringPair> regular, List<StringPair> mvpOnly) {
        scrollInner.child(makeCategory(listName, regular));
        if (!mvpOnly.isEmpty()) {
            var mvpList = UiTooling.makeButtonList(listName + " (MVP+)", mvpOnly, false);
            mvpContainers.add(mvpList);
            scrollInner.child(mvpList);
        }
    }
}
