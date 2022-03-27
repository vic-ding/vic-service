package com.vic.es.utils;

import com.vic.es.entity.jd.JdGoodsResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class HtmlParseUtil {

    public static List<JdGoodsResponse> parseJD(String keywords) throws IOException {
        //获取请求:https://search.jd.com/Search?keyword=java&enc=utf-8&wq=java&pvid=f807c58b66dc4baab4c7ed71834c36be
        //前提,联网,ajax不能获得
        String url = "https://search.jd.com/Search?keyword=" + keywords + "&enc=utf-8";
        //解析网页。(Jsoup返 回Document就是浏览器Document对象)
        Document document = Jsoup.parse(new URL(url), 30000);
        //所有你在js中可以使用的方法，这里都能用!
        Element element = document.getElementById("J_goodsList");
        //获取所有的Li元素
        Elements elements = null;
        if (element != null) {
            elements = element.getElementsByTag("li");
        }
        List<JdGoodsResponse> contents = new ArrayList<>();

        //获取元素中的内容
        if (elements != null) {
            for (Element el : elements) {
                String goodsName = el.getElementsByClass("p-name").eq(0).text();
                String shopName = el.getElementsByClass("curr-shop hd-shopname").eq(0).text();
                String price = el.getElementsByTag("i").eq(0).text();
                if (!"".equals(goodsName) && !"".equals(shopName) && !"".equals(price)) {
                    contents.add(new JdGoodsResponse(goodsName, shopName, price));
                }
            }
        }
        return contents;
    }

}

