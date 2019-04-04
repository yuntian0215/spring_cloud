package com.yuntian.utils.menu;

import com.yuntian.utils.AccessToken;
import com.yuntian.utils.WeixinUtil;

/**
 * <p>创建微信公众号菜单</p>
 * 2019年4月3日下午4:33:20
 * @author lvjie
 */
public class MenuCreate {
	static final String appID = "wx9a4adf39803ba67e";
	static final String appsecret = "bd916329727e6e595792897e90746067";
	public static void main(String[] args) {
		// 调用接口获取access_token
        AccessToken at = WeixinUtil.getAccessToken(appID, appsecret);

        if (null != at) {
            // 调用接口创建菜单
            int result = WeixinUtil.createMenu(getMenu(), at.getAccessToken());

            // 判断菜单创建结果
            if (0 == result) {
                System.out.println("菜单创建成功！");
            } else {
                System.out.println("菜单创建失败，错误码：" + result);
            }
        }else{
            System.out.println("获取token失败！");
        }
	}
	
	/**
     * 组装菜单数据
     * 
     * @return
     */
    private static Menu getMenu() {
        CommonButton btn1 = new CommonButton();
        btn1.setName("海外网");
        btn1.setType("view");
        btn1.setUrl("http://www.cnblogs.com/x-99/");
        
        CommonButton btn2 = new CommonButton();
        btn2.setName("我的博客");
        btn2.setType("view");
        btn2.setUrl("http://www.cnblogs.com/x-99/");

        CommonButton btn3 = new CommonButton();
        btn3.setName("个人中心");
        btn3.setType("view");
        btn3.setUrl("http://www.cnblogs.com/x-99/");

        CommonButton btn4 = new CommonButton();
        btn4.setName("人工服务");
        btn4.setType("view");
        btn4.setUrl("http://www.cnblogs.com/x-99/");

        CommonButton btn5 = new CommonButton();
        btn5.setName("附近美食");
        btn5.setType("click");
        btn5.setKey("23");

        CommonButton btn6 = new CommonButton();
        btn6.setName("周边住宿");
        btn6.setType("click");
        btn6.setKey("24");
        

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("博客概况");
        mainBtn1.setSub_button(new CommonButton[] { btn2, btn3, btn4 });

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("周边服务");
        mainBtn2.setSub_button(new CommonButton[] { btn5, btn6 });

        /**
         * 封装整个菜单
         */
        Menu menu = new Menu();
        menu.setButton(new Button[] { btn1, mainBtn1, mainBtn2 });
        return menu;
    }
}
