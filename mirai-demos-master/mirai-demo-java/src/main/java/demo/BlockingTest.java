package demo;

import java.io.File;
import java.io.IOException;
import java.io.IOException;
import java.util.concurrent.CancellationException;

import org.jetbrains.annotations.NotNull;

import Arknights.ItemSearch;
import Arknights.Simulate;
import Arknights.tagSearch;
import demo.Extension;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Job;
import module.music;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactoryJvm;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Events;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.AtAll;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageUtils;
import net.mamoe.mirai.network.LoginFailedException;
import net.mamoe.mirai.message.data.QuoteReply;
import net.mamoe.mirai.utils.BotConfiguration;

public class BlockingTest {

	private final static String[] Item = { "酮凝集组", "炽合金块", "凝胶", "破损装置", "装置", "双酮", "酯原料", "研磨石", "五水研磨石", "炽合金",
			"酮阵列", "扭转醇", "轻锰矿", "改良装置", "原岩", "固原岩组", "提纯原岩", "固原岩", "异铁", "异铁块", "异铁组", "异铁碎片", "聚酸酯", "聚酸酯组", "酮凝集",
			"聚合凝胶", "RMA70-12", "RMA70-24", "糖", "糖聚块", "糖组", "代糖", "三水锰矿", "白马醇" };
	private final static String[] arknights10 = { "十连", "测欧气", "干员寻访", "寻访干员", "招募干员", "抽保底", "10连" };
	private final static String[] arknights1 = { "单抽", "一连" };
	private final static String[] arknights100 = { "一百连", "100连", "保底六星" };
	private final static String[] tagsearch = { "公招", "tag" };
	private final static String[] askforanswer = { "要不要", "怎么办", "os搞", "吗", "会不会", "要是", "?" };
	private final static String[] study = { "等我搞完学习", "我去写作业了", "我去学习了", "我在写作业", "等我写完作业" };
	private final static String[] keytag = { "近战位", "远程位", "输出", "防护", "生存", "治疗", "支援", "快速复活", "控场", "召唤", "削弱", "位移",
			"爆发", "资深干员", "近卫", "先锋", "费用回复", "医疗", "术士", "辅助", "重装", "狙击", "特种" };
	private final static String[] dirtywords = { "死", "要你何用", "你妈", "垃圾", "你不行啊", "丢了", "日", "废物", "杂修", "我抽你个",
			" 我抽几个", "菜", "就这", "sb", "傻逼", "憨批" };
	private final static String[] images = { "来张好康的", "来图", "图", "好看的", "好康的" };
	private final static String[] music = { "点歌", "来首歌", "推歌" };

	public static void main(String[] args) throws InterruptedException,LoginFailedException, IOException {
//		final Bot bot = BotFactoryJvm.newBot(3149920162L, "Wu135246!", new BotConfiguration() {
//			{
//				fileBasedDeviceInfo("deviceInfo.json");
//			}
//		});
		final Bot bot = BotFactoryJvm.newBot(1411969535L, "Mirai2020826", new BotConfiguration() {
			{
				fileBasedDeviceInfo("deviceInfo.json");
			}

		});
		Extension ex = new Extension();
		Simulate a = new Simulate();
		ItemSearch is = new ItemSearch();
//		ImageGrab ig = new ImageGrab();
		bot.login();
		bot.getFriends().forEach(friend -> System.out.println(friend.getId() + ":" + friend.getNick()));

		Events.registerEvents(bot, new SimpleListenerHost() {
			@EventHandler
			public ListeningStatus onGroupMessage(GroupMessageEvent event) {
				String msgString = BlockingTest.toString(event.getMessage());
				// at bot event
				if (msgString.contains("@" + bot.getNick()) || msgString.contains("@Labman.Robert")) {
					event.getGroup().sendMessage("processing request...");
					if (msgString.contains("at")) {
						event.getGroup().sendMessage(new At(event.getSender()));
					} else if (msgString.contains(ex.getLastSentence())) {
						ex.setLastSentence(ex.getResponse(ex.getResponse("repete")));
						event.getSender().sendMessage(ex.getLastSentence());
					} else if (msgString == "@" + bot.getNick() || msgString == "@Labman.Robert") {
						ex.setLastSentence(ex.getResponse(ex.getgreet()));
						event.getSender().sendMessage(ex.getLastSentence());
					} else if (ex.containKeywords(msgString, askforanswer)) {
						ex.setLastSentence(ex.getResponse("bookOfAnswer"));
						event.getGroup().sendMessage(ex.getLastSentence());
					} else if (ex.containKeywords(msgString, dirtywords)) {
						ex.setLastSentence(ex.getResponse("dirtyWords"));
						event.getGroup().sendMessage(ex.getLastSentence());
					} else if (ex.containKeywords(msgString, keytag)) {
						final QuoteReply quote = new QuoteReply(event.getSource());
						tagSearch a = new tagSearch();
						ex.setLastSentence(a.search(msgString));
						event.getGroup().sendMessage(quote.plus(ex.getLastSentence()));
					} else if (ex.containKeywords(msgString, music)) {
						music m = new music();
						ex.setLastSentence(m.pickaSong());
						event.getGroup().sendMessage(ex.getLastSentence());
					} else if (ex.containKeywords(msgString, Item)) {
						File file = new File(is.getAddress(msgString));
						if (file.exists()) {
							final Image image = event.getGroup().uploadImage(new File(is.getAddress(msgString)));
							// 上传一个图片并得到 Image 类型的 Message
							final String imageId = image.getImageId(); // 可以拿到 ID
							final Image fromId = MessageUtils.newImage(imageId); // ID 转换得到 Image
							event.getGroup().sendMessage(image); // 发送图片
						}
//					} else if (ex.containKeywords(msgString, Images)) {
//						event.getGroup().sendMessage("processing request...");
//						File file = new File(ig.getAddress());
//						if (file.exists()) {
//							final Image image = event.getGroup().uploadImage(new File(is.getAddress(msgString)));
//							// 上传一个图片并得到 Image 类型的 Message
//							final String imageId = image.getImageId(); // 可以拿到 ID
//							final Image fromId = MessageUtils.newImage(imageId); // ID 转换得到 Image
//							event.getGroup().sendMessage(image.plus("From Arknights Official Account")); // 发送图片
//						}
					} else if (msgString.contains("permission")) {
						event.getGroup().sendMessage(event.getPermission().toString());
					} else if (msgString.contains("自爆")) {
						for (int i = 0; i < 100; i++) {
							event.getGroup().sendMessage(ex.getResponse("selfDestruct"));
						}
					} else if (msgString.equals("/R")) {
						event.getGroup().sendMessage(msgString);
					} else if (msgString.equals("/testImage")) {
						event.getGroup()
								.sendMessage(MessageUtils.newImage("Aketon.png")
										.plus(MessageUtils.newImage("Alloy Block.png")).plus("Hello")
										.plus(new At(event.getSender())).plus(AtAll.INSTANCE));
					} else if (msgString.contains("/recall1")) {
						event.getGroup().sendMessage("你看不见这条信息").recall();

					} else if (msgString.equals("/recall2")) {
						final Job job = event.getGroup().sendMessage("3秒后撤回").recallIn(3000);
						job.cancel(new CancellationException());
//					} else if (msgString.equals("/help")) {
//						event.getGroup().sendMessage("processing request...");
//						event.getGroup().sendMessage("自定义抽卡连数：/Ori times");
					} else {
						ex.setLastSentence(ex.getResponse("unknown"));
						event.getGroup().sendMessage(ex.getLastSentence());
					}
				} else {
					// statusCode1;
					if (ex.containKeywords(msgString, arknights1)) {
						final QuoteReply quote = new QuoteReply(event.getSource());
						event.getGroup().sendMessage(quote.plus(a.simulate(event.getSenderName(), 1)));
					} else if (ex.containKeywords(msgString, arknights10)) {
						final QuoteReply quote = new QuoteReply(event.getSource());
						event.getGroup().sendMessage(quote.plus(a.simulate(event.getSenderName(), 10)));
					} else if (ex.containKeywords(msgString, arknights100)) {
						final QuoteReply quote = new QuoteReply(event.getSource());
						event.getGroup().sendMessage(quote.plus(a.simulate(event.getSenderName(), 100)));
					} else if (msgString.contains("/image")) {
						File file = new File(
								"C:\\Users\\Trance\\eclipse-workspace\\mirai-demos-master.zip_expanded\\mirai-demos-master\\mirai-demo-java\\src\\main\\java\\demo\\test.png");

						final Image image = event.getGroup().uploadImage(new File(
								"C:\\Users\\Trance\\eclipse-workspace\\mirai-demos-master.zip_expanded\\mirai-demos-master\\mirai-demo-java\\src\\main\\java\\demo\\test.png"));
						// 上传一个图片并得到 Image 类型的 Message
						final String imageId = image.getImageId(); // 可以拿到 ID
						final Image fromId = MessageUtils.newImage(imageId); // ID 转换得到 Image
						event.getGroup().sendMessage(image); // 发送图片
					} else if (msgString.contains("连抽")) {
						final QuoteReply quote = new QuoteReply(event.getSource());
						int times = 0;
						try {
							times = Integer.parseInt(msgString.substring(0, msgString.indexOf("连抽")));
						} catch (Exception e) {
							event.getGroup().sendMessage(ex.getResponse("ilegalArgumentInSimulation"));
						}
						event.getGroup().sendMessage(quote.plus(a.simulate(event.getSenderName(), times)));
					} else if (ex.containKeywords(msgString, tagsearch)) {
						final QuoteReply quote = new QuoteReply(event.getSource());
						tagSearch a = new tagSearch();
						event.getGroup().sendMessage(quote.plus(a.search(msgString)));
					} else if (ex.containKeywords(msgString, study)) {
						event.getSender().mute(100);
					} else if (msgString.equals("我想静静")) {
						event.getGroup().getSettings().setMuteAll(true);
					} else if (msgString.contains("你们怎么不说话")) {
						event.getGroup().getSettings().setMuteAll(false);
					}
//				} else if (msgString.contains("发送图片")) {
//					File file = new File("myImage.jpg");
//					if (file.exists()) {
//						final Image image = event.getGroup().uploadImage(new File("myImage.jpg"));
//						final String imageId = image.getImageId(); 
//						final Image fromId = MessageUtils.newImage(imageId);
//						event.getGroup().sendMessage(image); 
//					}
//				} else if (msgString.contains("friend")) {
//					final Future<MessageReceipt<Contact>> future = event.getSender().sendMessageAsync("Async send");
//					try {
//						future.get();
//					} catch (InterruptedException | ExecutionException e) {
//						e.printStackTrace();
//					}
//					
//				} else if (msgString.startsWith("convert")) {
//					StringBuilder stringBuilder = new StringBuilder("图片ID");
//					event.getMessage().forEachContent(msg -> {
//						if (msg instanceof Image) {
//							stringBuilder.append(((Image) msg).getImageId());
//							stringBuilder.append("\n");
//						}
//						return Unit.INSTANCE;
//					});
//					event.getGroup().sendMessage(stringBuilder.toString());

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