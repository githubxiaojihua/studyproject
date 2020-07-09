package com.xiaojihua.datastructure;

import javax.swing.text.html.Option;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * 1、各类map的使用
 * 要求：找出可以通过单个字母替换可以变为至少15个其他单词的单词
 * 2、图论算法的使用（广度优先的无权最短路径算法实现）
 * 要求：基于上一步生成的map来实现单字母替换链路，即从一个单词每次只替换一个字母变成另一个单词。
 */
public class C16WordLadder {

    public static void main(String[] args) {

        BufferedReader bufferedReader = null;
        List<String> wordList = null;
        Map<String,List<String>> adjMap = null;
        long start, end;
        try {
            bufferedReader = new BufferedReader(new FileReader("G:\\test\\text1.txt"));
            wordList = readWord(bufferedReader);

            start = System.currentTimeMillis();
            adjMap = computeAdjacentWordsSlow(wordList);// 普通遍历
            end = System.currentTimeMillis();
            System.out.println("普通遍历耗费时间：" + (end - start));
            printHighChangeables(adjMap,1);

            start = System.currentTimeMillis();
            adjMap = computeAdjacentWordsMedium(wordList);// 避免了不同长度之间的比较
            end = System.currentTimeMillis();
            System.out.println("分组后的时间：" + (end - start));
            printHighChangeables(adjMap,2);

            start = System.currentTimeMillis();
            adjMap = computeAdjacentWords(wordList);// 分组后在按照特定字母在分组
            end = System.currentTimeMillis();
            System.out.println("两次分组后的时间：" + (end - start));
            printHighChangeables(adjMap,2);

            System.out.println("======图论算法广度优先遍历==============================");
            findChain(adjMap, "zero", "five").ifPresent(list ->{
                System.out.println(list);
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 一种比较低效率的实现方式，通过遍历。
     * @param words
     * @return
     */
    public static Map<String,List<String>> computeAdjacentWordsSlow(List<String> words){

        Map<String,List<String>> adjMap = new HashMap<>();
        String[] wordsArray = new String[words.size()];// 定义String数组
        words.toArray(wordsArray);// 转换成String数组
        // 依次遍历比较每两个字符看是否满足条件，满足的话就都加入到adjMap中
        for(int i=0; i<wordsArray.length-1; i++){
            // 此处必须是j=i+1
            for(int j=i+1; j<wordsArray.length; j++){
                if(oneCharDiff(wordsArray[i],wordsArray[j])){
                    update(adjMap,wordsArray[i],wordsArray[j]);
                    update(adjMap,wordsArray[j],wordsArray[i]);
                }
            }
        }
        return adjMap;
    }

    /**
     * 通过增加一个MAP来将所有words按照长度分组，然后同组之间进行比较。
     * 这样避免了比较长度不一致的words，加快了速度
     * @param words
     * @return
     */
    public static Map<String,List<String>> computeAdjacentWordsMedium(List<String> words){

        Map<String,List<String>> groupedWords = new HashMap();// 分组MAP
        Map<String,List<String>> adjMap = new HashMap<>();
        // 根据length进行分组
        for(String word : words){
            update(groupedWords,word.length()+"",word);
        }

        // 对每一个分组进行遍历比较
        for(Map.Entry<String,List<String>> entry : groupedWords.entrySet()){
            List<String> groupedList = entry.getValue();
            for(int i=0; i<groupedList.size(); i++){
                String[] strings = new String[groupedList.size()];
                groupedList.toArray(strings);
                for(int j=i+1; j<strings.length; j++){
                    if(oneCharDiff(strings[i],strings[j])){
                        update(adjMap,strings[i],strings[j]);
                        update(adjMap,strings[j],strings[i]);
                    }
                }

            }
        }
        return adjMap;
    }

    /**
     * 先按照长度进行了分组，
     * 遍历每一长度的分组
     * 将分组中的所有单词，一次按照去掉i（0---长度）位置处字母为key存储剩下值相同的字符列表，然后遍历所有size>=2的列表，进行更新最终map
     * 返回最终Map
     * @param words
     * @return
     */
    private static Map<String,List<String>> computeAdjacentWords(List<String> words){

        Map<String,List<String>> adjMap = new TreeMap();
        Map<Integer,List<String>> groupedList = new TreeMap<>();

        // 按照长度分组
        for(String word : words){
            update(groupedList,word.length(),word);
        }

        // 遍历每一长度分组
        for(Map.Entry<Integer,List<String>> entry : groupedList.entrySet()){

            // 按照长度依次处理,依次去掉第i个字符作为key，value为剩下部分相等的字符列表
            for(int i=0; i<entry.getKey(); i++){
                Map<String,List<String>> repoMap = new HashMap<>();
                // 依据key进行分组
                for(String word : entry.getValue()){
                    update(repoMap,word.substring(0,i)+word.substring(i+1),word);
                }
                // 对于长度一直，去掉第i个字符相同的所有字符列表进行遍历，若列表长度大于等于2则更新至最终map中
                for(List<String> wordsList : repoMap.values()){
                    if(wordsList.size() >= 2){
                        for(String temp1 : wordsList){
                            for(String temp2 : wordsList){
                                if(temp1 != temp2){
                                    update(adjMap,temp1,temp2);
                                }
                            }
                        }
                    }
                }
            }
        }
        return adjMap;
    }

    /**
     * 判断两个字符串是否长度一致并且只相差一位字母
     * @param str1
     * @param str2
     * @return
     */
    private static boolean oneCharDiff(String str1, String str2){

        if(str1.length() != str2.length()){
            return false;
        }

        int diffCon = 0;
        for(int i=0; i<str1.length(); i++){
            if(str1.charAt(i) != str2.charAt(i)){

                if(++diffCon > 1){
                    return false;
                }
            }
        }
        // 比直接写 return true;要好一点，增加了一层保障，避免了两个相同的字符说
        return diffCon == 1;
    }

    /**
     * 从Map中存储的各个列表里面打印value数量为minPrintNum的列表
     * Map中已 单词为键，值为通过替换一个字符所变成的单词
     * @param adjacentWords
     * @param minWords
     */
    private static void printHighChangeables(Map<String,List<String>> adjacentWords, int minWords){

        if(adjacentWords != null){

            for(Map.Entry<String,List<String>> entry : adjacentWords.entrySet()){
                List<String> words = entry.getValue();
                if(words.size() >= minWords){
                    System.out.print( entry.getKey( ) + " (" + words.size( ) + "):" );
                    for(String value : words){
                        System.out.print(value + " ");
                    }
                    System.out.println( );
                }
            }
        }
    }

    /**
     * 根据key和value更新map
     * 注意keyType的使用，使用泛型可以提高update的复用
     * 比如：按字符长度分组以及单字母变化分组都可以用此方法。90、101、102行
     * @param m
     * @param key
     * @param value
     * @param <KeyType>
     */
    private static <KeyType> void update(Map<KeyType,List<String>> m, KeyType key, String value){

        List<String> lst = m.get(key);
        if(lst == null){
            lst = new ArrayList();
            m.put(key,lst);
        }
        lst.add(value);
    }

    /**
     * 通过输入流读取文件中的字符串并且装配到List中
     * 每个word一行。
     * @param bufferedReader
     * @return
     * @throws IOException
     */
    private static List<String> readWord(BufferedReader bufferedReader) throws IOException {

        List<String> wordList = new ArrayList<>();
        String str = null;
        while((str = bufferedReader.readLine()) != null){
            wordList.add(str);
        }

        return wordList;
    }

    //=================图论的相关算法=========================================================

    /**
     * 基于广度优先遍历的无权最短路径算法实现，同时运用了Optional的一些特性来处理NPE
     * 1、定义一个List<String>作为返回值。
     * 2、定义一个队列来进行广度优先遍历
     * 3、定义一个map来存放广度优先遍历的计算结果，即路径记录 p(w)
     * 4、根据frist进行广度优先算法计算
     * 5、根据adjacentWords和second进行结果输出。
     * @param adjacentWords
     * @param first
     * @param second
     * @return 返回一个Optional来让调用者处理NPE
     */
    public static Optional<List<String>> findChain(Map<String,List<String>> adjacentWords,String first,String second){
        Queue<String> q = new LinkedList<>();
        Map<String,String> previousWord = new HashMap<>();
        q.offer(first);
        while(!q.isEmpty()){
            String current = q.remove();
            //使用Optional避免NPE
            Optional<List<String>> opt = Optional.ofNullable(adjacentWords.get(current));
            opt.ifPresent(list -> {
                for(String adj : list){
                    if(previousWord.get(adj) == null){
                        previousWord.put(adj,current);
                        q.offer(adj);
                    }
                }
            });

        }
        previousWord.put(first,null);
        return getChainFromPreviousMap(previousWord, second);
    }

    /**
     * 返回具体的路径
     * @param previousWord
     * @param second
     * @return 返回一个Optional来让调用者处理NPE
     */
    private static Optional<List<String>> getChainFromPreviousMap(Map<String,String> previousWord,String second){
        LinkedList<String> result = new LinkedList<>();
        Optional<Map<String, String>> optMap = Optional.ofNullable(previousWord);
        optMap.ifPresent(map -> {
            for(String str = second; str != null; str = map.get(str)){
                result.addLast(str);
            }
        });
        return Optional.ofNullable(result);
    }


}
