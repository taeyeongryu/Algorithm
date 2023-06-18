import java.util.*;
class Solution {
    static ArrayList<String> enrolls;
    static String[] referrals;
    static int[] referralsidx;
    static String[] sellers;
    static int[] amounts;
    static int[] result;
    //누가 얼마를 벌었느냐
    static void income(int idx, int price){
        int upincome = (int)(price*(0.1));
        int myincome = price-upincome;
        // System.out.println("name : "+enrolls.get(idx)+", income : "+myincome);
        
        //일단 내돈 챙기고
        result[idx]+=myincome;
        if(referrals[idx].equals("-")){
            return;
        }
        //부모가 있다면
        else{
            if(upincome>0){
            int upidx = referralsidx[idx];
            income(upidx,upincome);
            }
        }
    }
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        enrolls = new ArrayList<>(Arrays.asList(enroll));
        referrals = Arrays.copyOf(referral,referral.length);
        // i 인덱스의 부모의 인덱스를 저장하는 배열
        referralsidx = new int[referral.length];
        for(int i=0; i<enroll.length; i++){
            int j = enrolls.indexOf(referral[i]);
            referralsidx[i]=j;
        }
        // System.out.println(Arrays.toString(referralsidx));
        sellers = Arrays.copyOf(seller,seller.length);
        amounts = Arrays.copyOf(amount,amount.length);
        result = new int[enroll.length];
        // HashMap<String,Integer> map = new HashMap<>();
        // for(int i = 0; i<seller.length; i++){
        //     map.put(seller[i],map.getOrDefault(seller[i],0)+amount[i]);
        // }
        // System.out.println(map);
        for(int i =0; i<seller.length; i++){
            income(enrolls.indexOf(seller[i]),(amount[i]*100));
        }
        int[] answer = result;
        return answer;
    }
}