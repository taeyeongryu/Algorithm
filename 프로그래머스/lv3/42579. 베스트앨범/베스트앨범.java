import java.util.*;
class Solution {
    static class Music implements Comparable<Music>{
        String genre;
        int play;
        int index;
        Music(String genre, int play, int index){
            this.genre = genre;
            this.play = play;
            this.index = index;
        }
        @Override
        public int compareTo(Music m){
            return -(this.play-m.play);
        }
        
    }
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i<genres.length; i++){
            String key = genres[i];
            int value = plays[i];
            value+=map.getOrDefault(key,0);
            map.put(key,value);
        }
        List<String> genrelist = new ArrayList<>();
        while(map.size()!=0){
            int max_value = 0;
            String max_key = "";
            for(String key : map.keySet()){
                
                if(max_value<map.get(key)){
                    max_value = map.get(key);
                    max_key = key;
                }    
            }
            genrelist.add(max_key);
            map.remove(max_key);
        }
        System.out.println(genrelist);
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < genrelist.size(); i++){
            String genre = genrelist.get(i);
            PriorityQueue<Music> pq = new PriorityQueue<>();
            for(int j = 0; j<genres.length; j++){
                if(genres[j].equals(genre)){
                    pq.offer(new Music(genre,plays[j],j));
                }
            }
            if(!pq.isEmpty()){
                result.add(pq.poll().index);
            }
            if(!pq.isEmpty()){
                result.add(pq.poll().index);
            }
            // System.out.println(pq);
        }
        System.out.println(result);
        int[] answer = new int[result.size()];
        for(int i = 0; i<answer.length; i++){
            answer[i]=result.get(i);
        }
        return answer;
    }
}