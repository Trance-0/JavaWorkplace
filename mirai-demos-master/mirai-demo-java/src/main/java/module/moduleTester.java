package module;

public class moduleTester {
	private final static String[] Filename = { "Aketon", "Alloy Block", "Damaged Device", "Diketon", "Ester",
			"Grindstorn Pentahydrate", "Integrated Device", "Incandenscent Alloy", "Keton Colloid", "Loxic Kohl",
			"Manganese Ore", "Optimized Device", "Orirock cluster", "Orriock Concentration", "Orirock cube",
			"Oriron Block", "Oriron Cluster", "Oriron Shard", "Polyester Pack", "Polyketon", "Polymerized Gel",
			"RMA70-12", "RMA70-24", "Sugar Lump", "Sugar Pack", "Sugar Substitude", "Trihidrate", "White Horse Kohl",
			"Coagulating Gel", "Sugar", "Device", "Orirock", "Oriron", "Polyester", "Grindstone" };
	private final static String[] Item = { "酮凝集组", "炽合金块", "破损装置", "双酮", "酯原料", "五水研磨石", "全新装置", "炽合金", "酮阵列", "扭转醇",
			"轻锰矿", "改良装置", "固原岩组", "提纯原岩", "固原岩", "异铁块", "异铁组", "异铁碎片", "聚酸酯组", "酮凝集", "聚合凝胶", "RMA70-12", "RMA70-24",
			"糖聚块", "糖组", "代糖", "三水锰矿", "白马醇", "凝胶", "糖", "装置", "原岩", "异铁", "聚酸酯", "研磨石" };

	public static void main(String[] args) {
		FileSaver fs = new FileSaver(
				"C:\\Users\\Trance\\git\\EclipseWorkplace\\eclipse-workspace\\EclipseWorkplace-Mirai.zip_expanded\\EclipseWorkplace-Mirai\\Resources\\Savings\\","ItemSearch");
		String[] name = { "Filename", "Item" };
		String[][] elements = { Filename, Item };
		fs.SaveMap(fs.makeaMap(name, elements));
	}
}
