package Arknights;

public class tagSearch {

	public tagSearch() {

	}

	public String search(String tag) {
		StringBuilder result = new StringBuilder();
		if (tag.contains("近战位")) {
			if (tag.contains("支援")) {
				result.append("近战位+支援：杜宾（4星）、凛冬（5星）、诗怀雅（5星）" + "\n");
			}
			if (tag.contains("减速")) {
				result.append("近战位+减速：霜叶（4星）、食铁兽（5星）" + "\n");
			}
			if (tag.contains("控场")) {
				result.append("近战位+控场：德克萨斯（5星）、红（5星）" + "\n");
			}
		}
		if (tag.contains("远程位")) {
			if (tag.contains("生存")) {
				result.append("远程位+生存：杰西卡（4星）" + "\n");
			}
			if (tag.contains("支援")) {
				result.append("远程位+支援：白面鸮（5星）、华法琳（5星）" + "\n");
			}
			if (tag.contains("控场")) {
				result.append("远程位+控场：梅尔（5星）" + "\n");
			}
		}
		if (tag.contains("输出")) {
			if (tag.contains("防护")) {
				result.append("输出+防护：雷蛇（5星）、火神（5星）" + "\n");
			}
			if (tag.contains("重装")) {
				result.append("输出+重装：雷蛇（5星）、火神（5星）" + "\n");
			}
			if (tag.contains("辅助")) {
				result.append("输出+辅助：真理（5星）" + "\n");
			}
			if (tag.contains("特种")) {
				result.append("输出+特种：狮蝎（5星）、崖心（5星）" + "\n");
			}
			if (tag.contains("支援")) {
				result.append("输出+支援：杜宾（4星）、诗怀雅（5星）" + "\n");
			}
			if (tag.contains("削弱")) {
				result.append("输出+削弱：夜烟（4星）、流星（4星）" + "\n");
			}
			if (tag.contains("减速")) {
				result.append("输出+减速：霜叶（4星）、真理（5星）" + "\n");
			}
			if (tag.contains("位移")) {
				result.append("输出+位移：崖心（5星）" + "\n");
			}
			if (tag.contains("治疗")) {
				result.append("输出+治疗：夜魔（5星）" + "\n");
			}
		}
		if (tag.contains("防护")) {
			if (tag.contains("特种")) {
				result.append("防护+特种：砾（4星）" + "\n");
			}
			if (tag.contains("位移")) {
				result.append("防护+位移：可颂（5星）" + "\n");
			}
			if (tag.contains("输出")) {
				result.append("防护+输出：雷蛇（5星）、火神（5星）" + "\n");
			}
			if (tag.contains("生存")) {
				result.append("防护+生存：火神（5星）" + "\n");
			}
		}
		if (tag.contains("生存")) {
			if (tag.contains("狙击")) {
				result.append("生存+狙击：杰西卡（4星）" + "\n");
			}
			if (tag.contains("远程")) {
				result.append("生存+远程：杰西卡（4星）" + "\n");
			}
			if (tag.contains("特种")) {
				result.append("生存+特种：狮蝎（5星）" + "\n");
			}
		}
		if (tag.contains("治疗")) {
			if (tag.contains("支援")) {
				result.append("治疗+支援：白面鸮（5星）、华法琳（5星）" + "\n");
			}
			if (tag.contains("术士")) {
				result.append("治疗+术士：夜魔（5星）" + "\n");
			}
			if (tag.contains("输出")) {
				result.append("治疗+输出：夜魔（5星）" + "\n");
			}
			if (tag.contains("减速")) {
				result.append("治疗+减速：夜魔（5星）" + "\n");
			}
		}
		if (tag.contains("支援")) {
			result.append("支援：杜宾（4星）、白面鸮（5星）、华法琳（5星）、凛冬（5星）、诗怀雅（5星）" + "\n");
			if (tag.contains("先锋")) {
				result.append("支援+先锋：凛冬（5星）" + "\n");
			}
			if (tag.contains("费用回复")) {
				result.append("支援+费用回复：凛冬（5星）" + "\n");
			}
			if (tag.contains("近卫")) {
				result.append("支援+近卫：杜宾（4星）、诗怀雅（5星）" + "\n");
			}
			if (tag.contains("医疗")) {
				result.append("支援+医疗：白面鸮（5星）、华法琳（5星）" + "\n");
			}
			if (tag.contains("近战")) {
				result.append("支援+近战：杜宾（4星）、凛冬（5星）、诗怀雅（5星）" + "\n");
			}
			if (tag.contains("远程")) {
				result.append("支援+远程：白面鸮（5星）、华法琳（5星）" + "\n");
			}
			if (tag.contains("输出")) {
				result.append("支援+输出：杜宾（4星）、诗怀雅（5星）" + "\n");
			}
		}
		if (tag.contains("快速复活")) {
			result.append("快速复活：砾（4星）、红（5星）" + "\n");
			if (tag.contains("控场")) {
				result.append("快速复活+控场：红（5星）" + "\n");
			}
			if (tag.contains("防护")) {
				result.append("快速复活+防护：砾（4星）" + "\n");
			}
		}
		if (tag.contains("召唤")) {
			result.append("召唤：梅尔（5星）" + "\n");
		}
		if (tag.contains("削弱")) {
			result.append("削弱：夜烟（4星）、流星（4星）、初雪（5星）、陨星（5星）" + "\n");
			if (tag.contains("狙击")) {
				result.append("削弱+狙击：流星（4星）、陨星（5星）" + "\n");
			}
			if (tag.contains("术士")) {
				result.append("削弱+术士：夜烟（4星）" + "\n");
			}
			if (tag.contains("辅助")) {
				result.append("削弱+辅助：初雪（5星）" + "\n");
			}
			if (tag.contains("输出")) {
				result.append("削弱+输出：夜烟（4星）、流星（4星）" + "\n");
			}
			if (tag.contains("群攻")) {
				result.append("削弱+群攻：陨星（5星）" + "\n");
			}
		}
		if (tag.contains("减速")) {
			if (tag.contains("狙击")) {
				result.append("减速+狙击：白雪（4星）" + "\n");
			}
			if (tag.contains("近卫")) {
				result.append("减速+近卫：霜叶（4星）" + "\n");
			}
			if (tag.contains("术士")) {
				result.append("减速+术士：夜魔（5星）、格雷伊（4星）" + "\n");
			}
			if (tag.contains("特种")) {
				result.append("减速+特种：食铁兽（5星）" + "\n");
			}
			if (tag.contains("近战")) {
				result.append("减速+近战：霜叶（4星）、食铁兽（5星）" + "\n");
			}
			if (tag.contains("输出")) {
				result.append("减速+输出：霜叶（4星）、真理（5星）" + "\n");
			}
			if (tag.contains("群攻")) {
				result.append("减速+群攻：白雪（4星）、格雷伊（4星）" + "\n");
			}
			if (tag.contains("治疗")) {
				result.append("减速+治疗：夜魔（5星）" + "\n");
			}
		}
		if (tag.contains("控场")) {
			result.append("控场：德克萨斯（5星）、红（5星）、梅尔（5星）" + "\n");
			if (tag.contains("先锋")) {
				result.append("控场+先锋：德克萨斯（5星）" + "\n");
			}
			if (tag.contains("辅助")) {
				result.append("控场+辅助：梅尔（5星）" + "\n");
			}
			if (tag.contains("近战")) {
				result.append("控场+近战：德克萨斯（5星）、红（5星）" + "\n");
			}
			if (tag.contains("远程")) {
				result.append("控场+远程：梅尔（5星）" + "\n");
			}
			if (tag.contains("费用回复")) {
				result.append("控场+费用回复：德克萨斯（5星）" + "\n");
			}
		}
		if (tag.contains("位移")) {
			result.append("位移：暗索（4星）、阿消（4星）、崖心（5星）、食铁兽（5星）、可颂（5星）" + "\n");
			if (tag.contains("重装")) {
				result.append("位移+重装：可颂（5星）" + "\n");
			}
			if (tag.contains("特种")) {
				result.append("位移+特种：暗索（4星）、阿消（4星）、崖心（5星）、食铁兽（5星）" + "\n");
			}
			if (tag.contains("输出")) {
				result.append("位移+输出：崖心（5星）" + "\n");
			}
			if (tag.contains("防护")) {
				result.append("位移+防护：可颂（5星）" + "\n");
			}
			if (tag.contains("减速")) {
				result.append("位移+减速：食铁兽（5星）" + "\n");
			}
		}
		if (tag.contains("爆发")) {
			result.append("爆发：守林人（5星）" + "\n");
		}
		if (tag.contains("资深干员")) {
			result.append("资深干员： 点我必出5星及以上干员" + "\n");
		}
		if (tag.contains("高级资深干员")) {
			result.append("高级资深干员： 六星干员自选包：" + "\n");
			result.append("银灰：近卫、近战、输出、支援" + "\n");
			result.append("闪灵：医疗、远程、支援、治疗：" + "\n");
			result.append("夜莺：医疗、远程、治疗、支援" + "\n");
			result.append("伊芙利特：术士、远程、群攻、削弱" + "\n");
			result.append("能天使：狙击、远程、输出" + "\n");
			result.append("星熊：重装、近战、输出、防护" + "\n");
			result.append("赛雷娅：重装、近战、支援、防护、治疗" + "\n");
			result.append("推进之王：先锋、近战、费用回复、输出" + "\n");
			result.append("斯卡蒂：近卫、近战、输出、生存" + "\n");
			result.append("陈：近卫、近战、爆发、输出" + "\n");
		}
		if (result.length() == 0) {
			result.append("error code 002: Rare Tag Not Found");
		}
		return result.toString();
	}
}