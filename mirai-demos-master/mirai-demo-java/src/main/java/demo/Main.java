package demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;

import Genshin.Simulate;
import kotlin.coroutines.CoroutineContext;
import module.FileLoader;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactoryJvm;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Events;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.QuoteReply;
import net.mamoe.mirai.network.LoginFailedException;
import net.mamoe.mirai.utils.BotConfiguration;

public class Main {

	public static void main(String[] args) throws InterruptedException, LoginFailedException, IOException {
		String path;
		if (System.getProperty("os.name").contains("Mac")) {
			path = System.getProperty("user.dir") + "/Resources/textFile/DirectoryMap.txt";
		} else {
			path = System.getProperty("user.dir") + "\\Resources\\textFile\\DirectoryMap.txt";
		}
		FileLoader fl = new FileLoader(path);
		HashMap<ArrayList<String>, String> directory = fl.ReadFileByDirMap();
		final Bot bot = BotFactoryJvm.newBot(3149920162L, "Wu135246!", new BotConfiguration() {
			{
				fileBasedDeviceInfo("deviceInfo.json");
			}
		});
//		final Bot bot = BotFactoryJvm.newBot(1411969535L, "Mirai2020826", new BotConfiguration() {
//			{
//				fileBasedDeviceInfo("deviceInfo.json");
//			}
//
//		});
		Extension ex = new Extension();
		Simulate a = new Simulate();
		bot.login();
		bot.getFriends().forEach(friend -> System.out.println(friend.getId() + ":" + friend.getNick()));

		Events.registerEvents(bot, new SimpleListenerHost() {
			@EventHandler
			public ListeningStatus onGroupMessage(GroupMessageEvent event) throws IllegalStateException, IOException {
				String msgString = Main.toString(event.getMessage());
				String type = ex.findType(msgString, directory);
				System.out.print(type);
				// at bot event
				if (msgString.contains("@" + bot.getNick()) || msgString.contains("@P·A·I·M·O·N")) {
					event.getGroup().sendMessage("processing request...");
					if (msgString.contains("at")) {
						event.getGroup().sendMessage(new At(event.getSender()));
					} else if (msgString.contains(ex.getLastSentence())) {
						ex.setLastSentence(ex.getResponse(ex.getResponse("repete")));
						event.getSender().sendMessage(ex.getLastSentence());
					} else if (msgString == "@" + bot.getNick() || msgString == "@P·A·I·M·O·N") {
						ex.setLastSentence(ex.getResponse(ex.getgreet()));
						event.getSender().sendMessage(ex.getLastSentence());
					} else if (type.equals("askforanswer")) {
						ex.setLastSentence(ex.getResponse("bookOfAnswer"));
						event.getGroup().sendMessage(ex.getLastSentence());
					} else if (type.equals("dirtywords")) {
						ex.setLastSentence(ex.getResponse("dirtyWords"));
						event.getGroup().sendMessage(ex.getLastSentence());
					} else if (msgString.contains("permission")) {
						event.getGroup().sendMessage(event.getPermission().toString());
					} else if (msgString.contains("自爆")) {
						for (int i = 0; i < 100; i++) {
							event.getGroup().sendMessage(ex.getResponse("selfDestruct"));
						}
					} else if (msgString.equals("/R")) {
						event.getGroup().sendMessage(msgString);
					} else {
						ex.setLastSentence(ex.getResponse("unknown"));
						event.getGroup().sendMessage(ex.getLastSentence());
					}
				} else {
					if (type.equals("genshin1")) {
						final QuoteReply quote = new QuoteReply(event.getSource());
						event.getGroup().sendMessage(quote.plus(a.simulate(event.getSenderName(), 1)));
					} else if (type.equals("genshin10")) {
						final QuoteReply quote = new QuoteReply(event.getSource());
						event.getGroup().sendMessage(quote.plus(a.simulate(event.getSenderName(), 10)));
					} else if (type.equals("genshin100")) {
						final QuoteReply quote = new QuoteReply(event.getSource());
						event.getGroup().sendMessage(quote.plus(a.simulate(event.getSenderName(), 100)));
					} else if (msgString.equals("/help")) {
						event.getGroup().sendMessage("自定义抽卡连数：/");
					} else if (msgString.contains("/switch")) {
						String pool = msgString.substring(msgString.indexOf("/switch") + 8, msgString.length());
						event.getGroup().sendMessage("正在将卡池设置为：" + pool);
						try {
							if (a.loadSaving(event.getSenderName()).setpool(pool)) {
								event.getGroup().sendMessage("卡池设置成功。");
							} else {
								event.getGroup().sendMessage("卡池设置失败，请检查输入是否有误，或我是否有误。");
							}
						} catch (Exception e) {
							event.getGroup().sendMessage("An error occured.");
						}
					}  else if (msgString.contains("/summon")) {
						final QuoteReply quote = new QuoteReply(event.getSource());
						int times = 0;
						try {
							times = Integer.parseInt(msgString.substring(msgString.indexOf("/summon") + 8, msgString.length()));
						} catch (Exception e) {
							event.getGroup().sendMessage(ex.getResponse("ilegalArgumentInSimulation"));
						}
						event.getGroup().sendMessage(quote.plus(a.simulate(event.getSenderName(), times)));
					}else if (msgString.contains("/summary")) {
						try {
							HashMap<String, Integer> items = new HashMap<String, Integer>();
							items = a.loadSaving(event.getSenderName()).getItems();
							StringBuilder result = new StringBuilder();
							if (items != null) {
								for (String i : items.keySet()) {
									result.append(i + ":" + items.get(i) + "\n");
								}
							}
							event.getGroup().sendMessage(result.toString());
						} catch (Exception e) {
							event.getGroup().sendMessage("An error occured.");
						}
					} else if (msgString.contains("/clear")) {
						try {
							a.loadSaving(event.getSenderName()).resetall();
							event.getGroup().sendMessage("进度已重置");
						} catch (Exception e) {
							System.out.println("An error occured.");
						}
					} else if (msgString.contains("连抽")) {
						final QuoteReply quote = new QuoteReply(event.getSource());
						int times = 0;
						try {
							times = Integer.parseInt(msgString.substring(0, msgString.indexOf("连抽")));
						} catch (Exception e) {
							event.getGroup().sendMessage(ex.getResponse("ilegalArgumentInSimulation"));
						}
						event.getGroup().sendMessage(quote.plus(a.simulate(event.getSenderName(), times)));
					} else if (msgString.contains("/mute")) {
						event.getSender().mute(100);
//					} else if (msgString.equals("我想静静")) {
//						event.getGroup().getSettings().setMuteAll(true);
//					} else if (msgString.contains("你们怎么不说话")) {
//						event.getGroup().getSettings().setMuteAll(false);
					}
				}
				return ListeningStatus.LISTENING;
			}

			@Override
			public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
				throw new RuntimeException("解析失败", exception);
			}
		});

		bot.join();
	}

	private static String toString(MessageChain chain) {
		return chain.contentToString();
	}
}