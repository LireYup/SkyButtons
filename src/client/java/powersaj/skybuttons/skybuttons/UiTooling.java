package powersaj.skybuttons.skybuttons;

import powersaj.skybuttons.powersaj.StringPair;
import io.wispforest.owo.ui.component.UIComponents;
import io.wispforest.owo.ui.container.UIContainers;
import io.wispforest.owo.ui.core.UIComponent;
import io.wispforest.owo.ui.core.Insets;
import io.wispforest.owo.ui.core.Sizing;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.List;

public class UiTooling {

    /**
     * @param buttonName Player-facing button text
     * @param commandExecute Command to execute, "/" not needed.
     * @return Returns button component. Button style: List format.
     */
    static UIComponent makeButtonListEntry(String buttonName, String commandExecute){
        return UIComponents.button(Text.literal(buttonName), button -> {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.networkHandler.sendChatCommand(commandExecute);
        }).margins(Insets.of(0,3,0,9));
    }

    /**
     * @param buttonName Player-facing button text
     * @param commandExecute Command to execute, "/" not needed.
     * @return Returns button component. Standard button.
     */
    static UIComponent makeButtonEntry(String buttonName, String commandExecute){
        return UIComponents.button(Text.literal(buttonName), button -> {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.networkHandler.sendChatCommand(commandExecute);
        }).margins(Insets.of(2));
    }

    /**
     * @param listName Name of the list
     * @param buttonNamesListWithCommands List of button names and commands
     * @param openOnStartup If the list should be open on startup
     * @return Returns a list of buttons in a collapsible container.
     * @see #makeButtonList(String, List)
     */
    static UIComponent makeButtonList(String listName, List<StringPair> buttonNamesListWithCommands, boolean openOnStartup){
        return UIContainers.collapsible(Sizing.content(),Sizing.content(),Text.literal(listName), openOnStartup)
                .child(UIContainers.verticalFlow(Sizing.content(),Sizing.content())
                        .children(buttonNamesListWithCommands.stream()
                                .map(pair -> makeButtonListEntry(pair.getFirst(), pair.getSecond()))
                                .toList()
                        )
                );
    }

    /**
     * @param listName Name of the list
     * @param buttonNamesListWithCommands List of button names and commands
     * @return Returns a list of buttons in a collapsible container.
     * @see #makeButtonList(String, List, boolean)
     */
    static UIComponent makeButtonList(String listName, List<StringPair> buttonNamesListWithCommands){
        return makeButtonList(listName, buttonNamesListWithCommands, true);
}}
