package com.github.luriel0228.privatemessage.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PrivateMessageCommand implements CommandExecutor, TabCompleter {

        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if(!(sender instanceof Player senderPlayer)) {
                sender.sendMessage("플레이어만 사용할 수 있는 명령어입니다.");
                return true;
            }

            if(args.length == 0){
                sender.sendMessage("사용법: /귓속말 <플레이어> <메시지>");
                return true;
            }

            if(args.length == 1){
                sender.sendMessage("메세지를 입력해주세요.");
                return true;
            }

            String targetPlayerName = args[0];
            Player targetPlayer = Bukkit.getPlayerExact(targetPlayerName);

            if (targetPlayer == null || !targetPlayer.isOnline()) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[!] &f" + targetPlayerName + " 플레이어가 오프라인이거나 존재하지 않습니다."));
                return true;
            }

            if (targetPlayerName.equals(senderPlayer.getName())) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[!] &f자신에게는 귓속말을 보낼 수 없습니다."));
                return true;
            }

            StringBuilder messageBuilder = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                messageBuilder.append(args[i]).append(" ");
            }

            String message = messageBuilder.toString().trim();

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f나 &7-> &f" + targetPlayer.getDisplayName() + "&f: " + message));
            targetPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', ((Player) sender).getDisplayName() + " &7-> &f나" + "&f: " + message));

            return true;
        }

        public @NotNull List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

            List<String> completions = new ArrayList<>();

            if(args.length==1) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    completions.add(player.getDisplayName());
                }
            }

            return StringUtil.copyPartialMatches(args[0], completions, new ArrayList<>());
        }

}
