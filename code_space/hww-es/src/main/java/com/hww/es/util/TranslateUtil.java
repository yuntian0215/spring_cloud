package com.hww.es.util;

import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;
/**
 * <p>百度翻译工具</p>
 * 2018年12月5日下午2:29:44
 * @author lvjie
 */
public class TranslateUtil {
    static final String APP_ID = "20180821000197208";//20180611000174910
    static final String SECURITY_KEY = "TTE0Xfc0UPzlFa2iOY07";//QPgoigC56sP5LCNPOGjL
    
	public static String result(String q) {
		TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = q;
        String str="";
        
        //处理段落
        String[] split = query.split("\n");
        StringBuilder sb1 = new StringBuilder();
        for(int i=0;i<split.length;i++){
        	sb1.append(split[i]);
        }
        
		try {
			str = api.getTransResult(sb1.toString(), "auto", "zh");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
        
        JSONObject json=JSONObject.fromObject(str.toString());  
        
        String str1=json.get("trans_result").toString();  
        System.out.println("trans_result:"+str1);
        //去掉[]  
         str1=str1.replace("[", "");  
         str1=str1.replace("]", ""); 
         
         
         JSONObject json1=JSONObject.fromObject(str1);  
        
    	return (String) json1.get("dst");
	}
	public static String resultEN(String q) {
		TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = q;
        String str="";
		try {
			str = api.getTransResult(query, "auto", "en");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
        
        JSONObject json=JSONObject.fromObject(str.toString());  
        String str1=json.get("trans_result").toString();  
        //去掉[]  
         str1=str1.replace("[", "");  
         str1=str1.replace("]", "");  
         JSONObject json1=JSONObject.fromObject(str1);  
        
    	return (String) json1.get("dst");
	}
	public static void main(String[] args) {
		String str = "Recently, there has been some propaganda purporting that the United Nations should “open its doors to Taiwan,” propaganda which is filled with the ignorance of history and the distortion of facts. The Chinese Embassy in Israel finds it necessary to make some justifications and to set the record straight."+
    "When we talk about the Taiwan issue, there is one essential prerequisite – the “One-China Principle.” To be specific, the principle emphasizes that there is only one China in the world; the Government of the People’s Republic of China (PRC) is the only legitimate government representing the whole of China; and Taiwan is an inalienable part of China’s territory."+
    "The One-China Principle is the foundation stone for China’s Taiwan policy, and a binding commitment of all countries that have diplomatic ties with China, including Israel. It includes the idea that countries around the globe maintain and develop friendship and cooperation with China."+
    "Over the years, the One-China Principle has been followed by almost every country in the world and has been established as a basic norm of international relations. Recently, a group of the remaining countries have successively severed their “diplomatic ties” with Taiwan and normalized relations with China. The phenomenon fully testified that the Principle is the shared aspiration of the international community and an irresistible trend of our times."+
    "Regarding Taiwan’s participation in the UN and other international organizations, the One-China principle is also the essential foundation."+
    "In October 1971, the 26th session of the UN General Assembly adopted Resolution No. 2758. It announced in clear and definite language that “the representative of the Government of the People’s Republic of China is the sole legal representative of China in the United Nations... and [that member states] recognize the representatives of the PRC government as the sole legal representatives of China in the United Nations Organization.” From then on, China’s representation in the United Nations has been thoroughly resolved politically and legally, in a manner strictly adhering to the UN procedures."+
    "However, considering the needs of Taiwan compatriots and their welfare, the Chinese government, on the basis of the One-China Principle, has made arrangements for Taiwan’s participation in some international organizations that accept regional membership in a case-by-case manner, according to the nature, regulations and actual conditions of these organizations."+
    "As a region of China, Taiwan is participating in the Asian Development Bank (ADB) and the Asia-Pacific Economic Cooperation (APEC), under the names “Taipei, China” or “Chinese Taipei.” Similarly, the World Trade Organization (WTO), based on an agreement with China and on the principle of one China, allows Taiwan to participate as “a separate Taiwan-Peng- hu-Jinmen-Mazu tariff zone” (abbreviated as “Chinese Taipei”). It must be pointed out that while these are ad hoc arrangements, they do constitute a model applicable to other intergovernmental organizations or international gatherings for sovereign states."+
    "Taking into account the aforementioned facts, no one could deny that there is broad economic and cultural space for Taiwan, as long as the One-China Principle is upheld. The issue of “Taiwan’s international space” or “Taiwan’s participation in the UN” is nothing but distorted and misleading propaganda by some political figures. Their intention is only to create a false impression of “two Chinas” or “one China, one Taiwan,” and separate Taiwan from the motherland. The Chinese government, like all other governments, has zero tolerance for such separatist moves."+
    "China and Israel have a good tradition of understanding each other’s national interests. The One-China Principle is also an important part in the Joint Communiqué establishing China-Israel diplomatic rela- tions. The Taiwan issue is a core concern for China on its sovereignty and territorial integrity, and has significant influence on the sentiments of the Chinese people. We hope this article will help our friends in Israel to have a better understanding of the Taiwan issue."+
    "The author is the spokesperson and political counselor at the Embassy of the People’s Republic of China in Israel."+
    "Join Jerusalem Post Premium Plus now for just $5 and upgrade your experience with an ads-free website and exclusive content. "+
    "Click here>>"+
    "Tags:"+
    "Israel"+
    "United Nations"+
    "china"+
    "Share on facebook"+
    "Share on twitter";
		
		String str1 = "最近，有一些宣传声称联合国应该“向台湾敞开大门”，这些宣传充斥着对历史的无知和对事实的歪曲。中国驻以色列大使馆认为，有必要提出一些理由，并澄清事实。在谈到台湾问题时，有一个基本前提——“一个中国原则”，具体来说，这一原则强调世界上只有一个中国，中华人民共和国政府是代表整个中国的唯一合法政府，台湾是不可分割的一部分。中国的领土。一个中国原则是中国台湾政策的基石，也是包括以色列在内的所有与中国有外交关系的国家的有约束力的承诺。其中包括世界各国维护和发展同中国的友谊与合作。多年来，一个中国原则几乎被世界各国所遵循，并被确立为国际关系的基本准则。最近，一批剩余国家相继断绝了与台湾的“外交关系”，与中国关系正常化。这一现象充分证明了这一原则是国际社会的共同愿望，是我们这个时代不可抗拒的潮流。就台湾参与联合国和其他国际组织而言，一个中国原则也是必不可少的基础。1971年10月，联合国大会第26届会议通过了第2758号决议。它以明确明确的语言宣布：“中华人民共和国政府代表是中国在联合国的唯一法定代表人……并且该成员国承认中华人民共和国政府的代表是中国在联合国组织中的唯一法定代表人。“从那时起，中国在联合国的代表已通过严格遵守联合国程序的政治和法律彻底解决。但是，考虑到台湾同胞的需要和福祉，中国政府根据一个中国原则，根据这些原则的性质、规定和实际情况，对台湾参加一些接受地区性会员资格的国际组织作出了安排。组织。作为中国的一个地区，台湾以“台北，中国”或“中国台北”的名义参与亚洲开发银行（ADB）和亚太经合组织（APEC），同样，世界贸易组织（WTO）根据与中国达成的协议和一个中国的原则，允许台湾以“SEPA”的身份参与。台湾澎湖金门马祖关税区（简称“中国台北”）。必须指出的是，虽然这些是特别安排，但它们确实构成了适用于主权国家的其他政府间组织或国际集会的模式。考虑到上述事实，只要坚持一个中国原则，任何人都不能否认台湾有广阔的经济和文化空间。“台湾国际空间”或“台湾参与联合国”的问题，只是一些政治人物歪曲和误导的宣传。他们的目的只是制造一个“两个中国”或“一个中国，一个台湾”的错误印象，把台湾从祖国中分离出来。中国政府和其他所有政府一样，对这种分裂主义行动没有丝毫容忍。中国和以色列有了解对方国家利益的良好传统。一个中国原则也是建立中以外交关系联合公报的重要组成部分。台湾问题是中国主权和领土完整的核心关切，对中国人民的感情有着重大影响。希望本文能帮助以色列朋友更好地了解台湾问题。作者是中华人民共和国驻以色列大使馆的发言人和政治参赞。现在只需5美元就可以加入耶路撒冷邮政优惠，并通过免费的广告网站和独家内容升级您的体验。点击此处>>标签：以色列联合国中国在Facebook上分享Twitter上分享";
		String cn = TranslateUtil.result(str);
		String sn = TranslateUtil.resultEN(str1);
		System.out.println(sn);
	}

}
