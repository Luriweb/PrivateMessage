package com.github.luriel0228.privatemessage;

import com.github.luriel0228.privatemessage.command.PrivateMessageCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class PrivateMessage extends JavaPlugin {

    @Override
    public void onEnable() {

        setExecutor();

    }

    private void setExecutor() {
        getCommand("귓속말").setExecutor(new PrivateMessageCommand());
        getCommand("귓").setExecutor(new PrivateMessageCommand());
    }

}
