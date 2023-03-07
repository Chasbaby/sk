package com.ruoyi.web.controller;

import java.util.*;


public class test {
    public static void main(String[] args) {

        List<baseRoad> roads = new LinkedList<>();
        baseRoad road = new baseRoad("A","B",10.0);
        baseRoad road1 = new baseRoad("A","C",20.0);
        baseRoad road2 = new baseRoad("A","D",5.8);
        baseRoad road3 = new baseRoad("B","C",8.8);
        baseRoad road4= new baseRoad("B","E",14.4);
        baseRoad road5 = new baseRoad("C","E",20.1);
        baseRoad road6 = new baseRoad("E","D",15.4);

        roads.add(road);
        roads.add(road1);
        roads.add(road2);
        roads.add(road3);
        roads.add(road4);
        roads.add(road5);
        roads.add(road6);

        /**
         *     A     B     C     D     E
         *  A  0     10    20    5.8   INF
         *
         *  B  10    0     8.8   INF   14.4
         *
         *  C  20    8.8   0     INF   20.1
         *
         *  D  5.8   INF   INF   0     15.4
         *
         *  E  INF   14.4  20.1  15.4  0
         *
         *  Map<String, List<baseRoad>>
         */

        Set<String> set = new HashSet<>();
        roads.forEach(e->{
            set.add(e.getA());
            set.add(e.getB());
        });
        Set<String> store = new HashSet<>();
        List<baseRoad> baseRoads = new LinkedList<>();
        Map<String,List<baseRoad>> map = new HashMap<>(); 
        set.forEach(e-> System.out.println(e));
        for (String s : set) {
            // 如果 自身到自身设置为0
            baseRoads.add(new baseRoad(s,s,0.0));

            roads.forEach(baseRoad -> {
                String A = baseRoad.getA();
                String B = baseRoad.getB();
                //两地不同
                if (s.equals(A) && !s.equals(B)){
                    baseRoads.add(new baseRoad(s,B,baseRoad.distance));
                }
                if (s.equals(B) && !s.equals(A)){
                    baseRoads.add(new baseRoad(s,A,baseRoad.distance));
                }
            });
            baseRoads.forEach(baseRoad -> {

            });
            map.put(s,baseRoads);
            baseRoads.clear();
        }


//        Stream<Object> objectStream = roads.stream().flatMap(baseRoad -> {
//            return null;
//        });


    }
}
