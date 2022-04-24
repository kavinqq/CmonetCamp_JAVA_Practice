import java.util.ArrayList;

public class MergeSort extends Sort{
    /**
     * 合併排序法的"合併"動作  → 合併左右兩邊的資料
     * @param aList 要排序的陣列
     * @param start 開始位置
     * @param mid 左邊資料的最後一個資料
     * @param end 右邊資料的最後一個資料
     * @param comparator 比較器
     */

    private void merge(ArrayList<Integer> aList, int start, int mid, int end, Sort.Comparator comparator){
        //新增一個用來裝 拼湊出來陣列的 臨時陣列， 大小為目前要比較區段的資料數
        int[] tmp = new int[end - start + 1];
        //左邊資料的index
        int left = start;
        //右邊資料的index
        int right = mid + 1;
        //有多少資料 跑多少次
        for(int i = 0; i < (end - start) + 1; i++){
            if(left > mid){
                //如果左邊沒資料了，取右邊
                tmp[i] = aList.get(right);
                right++;
            }else if(right > end){
                //如果右邊沒資料了，取左邊
                tmp[i] = aList.get(left);
                left++;
            }else if(comparator.compare(aList.get(left), aList.get(right))){
                //如果 comparator條件為true (left > right 或 right > left...) 放入右邊
                tmp[i] = aList.get(right);
                right++;
            }else{
                //如果 comparator條件為false (left > right 或 right > left...) 放入左邊
                tmp[i] = aList.get(left);
                left++;
            }
        }
        //從 tmp 把資料從 tmp[0] 開始 傳入 tmp.length筆資料到aList中，aList從aList[start]開始放資料
        int tmpStart = start;
        for(int i = 0; i < tmp.length; i++){
            aList.set(tmpStart, tmp[i]);
            tmpStart++;
        }

        //顯示資料每一輪 的 變化
        showEachRound(aList);
    }

    /**
     * 合併排序法本體
     * @param aList 要排序的陣列
     * @param start 第一個資料的index
     * @param end 最後一個資料的index
     * @param comparator 比較器
     */

    private void mergeSort(ArrayList<Integer> aList, int start, int end, Sort.Comparator comparator){
        //如果一直往下拆分到 只有一筆資料 或 沒資料了 直接return 不再遞迴呼叫自己
        if(start >= end){
            return;
        }
        //抓出 目前資料index的中間數
        int mid = (start + end) / 2;

        //把資料從 start 到 mid 分成左半邊 → 左半邊會再找目前資料數的中間index 再頗半 → 直到都剩下一筆 → merge回來 → 左半邊會先跑完。
        mergeSort(aList, start, mid, comparator);
        //把資料從 mid + 1 到 end 分成右半邊，再把右半邊排序完
        mergeSort(aList, mid + 1, end, comparator);
        //把兩邊 排序好的陣列 抓近來比較，根據條件左邊(小/大)抓左邊 右邊(小/大)抓右邊...
        merge(aList, start, mid, end, comparator);
    }

    /**
     * 呼叫mergeSort
     * @param aList 要排序的陣列
     * @param comparator 比較器
     */

    public void sort(ArrayList<Integer> aList, Sort.Comparator comparator){
        mergeSort(aList, 0, aList.size() - 1, comparator);
    }
}
