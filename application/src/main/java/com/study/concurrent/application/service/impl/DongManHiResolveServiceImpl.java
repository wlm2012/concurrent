package com.study.concurrent.application.service.impl;

import com.study.concurrent.application.service.ResolveService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class DongManHiResolveServiceImpl implements ResolveService<List<String>, String> {
    @Override
    public List<String> resolve(final String s) {

        ArrayList<String> result = new ArrayList<>();

        Document document = Jsoup.parse(s);
        Element cpImg = document.getElementById("cp_img");
        if (cpImg == null) {
            log.info("cpImg为空");
            return Collections.emptyList();
        }
        Elements allElements = cpImg.getAllElements();

        Element first1 = allElements.getFirst();
        Elements children = first1.children();
        for (Element child : children) {
            if (child.childNodeSize() > 0) {
                Node node = child.childNode(1);
                String src = node.attr("data-original");
                result.add(src);
//                Elements title = element.getElementsByAttribute("title");
            }
        }
        return result;
    }
}
