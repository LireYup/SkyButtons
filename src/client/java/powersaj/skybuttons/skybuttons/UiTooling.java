package powersaj.skybuttons.skybuttons;

import powersaj.skybuttons.powersaj.StringPair;
import io.wispforest.owo.ui.component.UIComponents;
import io.wispforest.owo.ui.container.CollapsibleContainer;
import io.wispforest.owo.ui.container.UIContainers;
import io.wispforest.owo.ui.core.UIComponent;
import io.wispforest.owo.ui.core.Insets;
import io.wispforest.owo.ui.core.Sizing;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class UiTooling {

    static UIComponent makeButtonListEntry(String buttonName, String commandExecute) {
        return UIComponents.button(Text.literal(buttonName), button -> {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.networkHandler.sendChatCommand(commandExecute);
        }).margins(Insets.of(0, 3, 0, 9));
    }

    static UIComponent makeButtonEntry(String buttonName, String commandExecute) {
        return UIComponents.button(Text.literal(buttonName), button -> {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.networkHandler.sendChatCommand(commandExecute);
        }).margins(Insets.of(2));
    }

    static CollapsibleContainer makeButtonList(String listName, List<StringPair> entries, boolean openOnStartup) {
        var collapsible = UIContainers.collapsible(Sizing.content(), Sizing.content(), Text.literal(listName), openOnStartup);
        var list = new ArrayList<UIComponent>();
        for (var pair : entries) {
            list.add(makeButtonListEntry(pair.getFirst(), pair.getSecond()));
        }
        collapsible.child(UIContainers.verticalFlow(Sizing.content(), Sizing.content()).children(list));
        return collapsible;
    }

    static CollapsibleContainer makeButtonList(String listName, List<StringPair> entries) {
        return makeButtonList(listName, entries, true);
    }
}
