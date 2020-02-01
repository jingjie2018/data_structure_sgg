package com.iss.algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class GreedyAlgorithm {

    public static void main(String[] args) {
        //创建广播电台,放入到Map
        Map<String, Set<String>> broadcasts = new LinkedHashMap<>(8);
        //将各个电台放入到broadcasts
        Set<String> k1 = new HashSet<>();
        k1.add("北京");
        k1.add("上海");
        k1.add("天津");

        Set<String> k2 = new HashSet<>();
        k2.add("广州");
        k2.add("北京");
        k2.add("深圳");

        Set<String> k3 = new HashSet<>();
        k3.add("成都");
        k3.add("上海");
        k3.add("杭州");


        Set<String> k4 = new HashSet<>();
        k4.add("上海");
        k4.add("天津");

        Set<String> k5 = new HashSet<>();
        k5.add("杭州");
        k5.add("大连");

        //加入到map
        broadcasts.put("K1", k1);
        broadcasts.put("K2", k2);
        broadcasts.put("K3", k3);
        broadcasts.put("K4", k4);
        broadcasts.put("K5", k5);

        //allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.addAll(k1);
        allAreas.addAll(k2);
        allAreas.addAll(k3);
        allAreas.addAll(k4);
        allAreas.addAll(k5);

        //创建ArrayList, 存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        //定义一个临时的集合， 在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();

        //定义给maxKey ， 保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的key
        //如果maxKey 不为null , 则会加入到 selects
        String maxKey;
        while (allAreas.size() != 0) { // 如果allAreas 不为0, 则表示还没有覆盖到所有的地区
            //每进行一次while,需要
            maxKey = null;
            //遍历 broadcasts, 取出对应key
            for (Map.Entry<String, Set<String>> entry : broadcasts.entrySet()) {
                String key = entry.getKey();
                //每进行一次for
                tempSet.clear();
                //当前这个key能够覆盖的地区
                Set<String> areas = entry.getValue();
                tempSet.addAll(areas);
                //求出tempSet 和   allAreas 集合的交集, 交集会赋给 tempSet
                tempSet.retainAll(allAreas);
                //如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的集合地区还多
                //就需要重置maxKey
                // tempSet.size() >broadcasts.get(maxKey).size()) 体现出贪心算法的特点,每次都选择最优的
                if (tempSet.size() > 0 &&
                        (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            //maxKey != null, 就应该将maxKey 加入selects
            if (maxKey != null) {
                selects.add(maxKey);
                //将maxKey指向的广播电台覆盖的地区，从 allAreas 去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("得到的选择结果是" + selects);//[K1,K2,K3,K5]
    }
}