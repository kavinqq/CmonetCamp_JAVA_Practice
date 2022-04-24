import java.util.ArrayList;
import java.util.Collections;

public class QuickSort extends Sort{
    /**
     * 快速排序法的遞迴主體 (呼叫 partition() 後 呼叫下一個q_sort )
     * @param aList 要排序的陣列
     * @param start 陣列開始位置
     * @param end 陣列結束位置
     * @param comparator 比較器
     */

    private void q_sort(ArrayList<Integer> aList, int start, int end, Sort.Comparator comparator){
        if(start >= end){
            return;
        }
        int pivot = start; // 選定第一個點當pivot
        int left = start + 1; // 因為 我是選第一個點當pivot 所以左邊開頭是start + 1
        int right = end - 1;// 因為 第一次傳arr.length 要 -1 而後面回傳的位置 也是 pivot最後交換過去的位置=>右邊新開頭 ;-1之後 => 左邊新結尾;
        // 當 左index <= 右index 還沒有檢查完
        while(left <= right){
            // 左index 由左到右 目標找到你arr[pivot] 大的值， 比它小本來就該放左邊(由小到大排 && compare()定義為 num1 - num2 > 0)所以看到也不用動它，略過
            while(left <= right && comparator.compare(aList.get(pivot), aList.get(left))){
                left++;
            }
            // 右index 由右到左 目標找到你arr[pivot] 小的值， 比它大本來就該放右邊(由小到大排 && compare()定義為 num1 - num2 > 0)所以看到也不用動它，略過
            while(right >= left && comparator.compare(aList.get(right), aList.get(pivot))){
                right--;
            }
            /*
                左index 由左到右 在符合16行while()所有條件下 找到了一個 值比arr[pivot]的"大"的值
                右index 由右到左 在符合20行while()所有條件下 找到了一個 值比arr[pivot]的"小"的值
                把 這兩個 數值 交換
                ↑ Why?
                 A:因為QuickSort的概念就是 Divide and Conquer 先把要排序的大資料 簡單地 把小於pivot的 放左邊 大於pivot的放右邊 最後pivot 放中間
                   然後 左邊 和 右邊 再繼續分下去 (左邊的左邊 左邊的pivot 左邊的右邊) 原本的pivot (右邊的左邊 右邊的pivot 右邊的右邊)
                   一直持續到 一帶進去q_sort的時候 就是 start <= end (只有一個數字 && 沒東西 → 兩者都不用排序)
            */
            if(left < right){
                Collections.swap(aList, left, right);
                //要記得 交換完 要兩個移動一下 不然會無窮迴圈
                left++;
                right--;
            }
            //顯示一下 每一輪交換的狀況
            showEachRound(aList);
        }

        /* 整個檢查過了 而且簡單的分成 小於pivot的左邊 大於pivot的放右邊 最後把 pivot 插進去中間。
           問題來了: 為什麼 是跟right交換? 跟left換不行嗎?
           A: 不行! 原因是→跑while()的順序是left先。
           舉幾個例子(以免之後自己看忘記):
           狀況1:   80 90 100 110 60 50 40
              當 !(left >= right)的時候: 80 40 50 60 110 90
              left 在 110時 因為 > pivot 停止， right 跑第一次 就會因為 left > right 停止。(left停在110/right停在60)
              所以要交換的對象是right(60) → 60 40 50 80 110 90

           狀況2:   80 60 50  40  100 110 90
              這個比較簡單，left會順跑直到 遇到100， right也會順跑直到 40
              由於left直接> right 所以跟pivot交換，對象是 right(40) → 40 60 50 80 100 110 90
           狀況3:   80 90 100 80 80 110 60 50 40
              當 !(left >= right)的時候: 80 40 50 80 80 60 110 100 90 ↓
              right 60 left 110 交換完後 各自 + 1 → 這個時候left < right
              這個時候 還是要交換 right(60)
         */
        Collections.swap(aList, right, pivot);
        //左邊 start ~ right(right 傳進去會 - 1)
        q_sort(aList, start, right,comparator);
        //右邊 right + 1 ~ end(end 傳進去會 - 1)
        q_sort(aList, right + 1, end, comparator);
    }

    /**
     * 藉由sort 去呼叫 q_sort
     * @param aList 要排序的陣列
     * @param comparator 比較器
     */

    public void sort(ArrayList<Integer> aList, Sort.Comparator comparator){
        q_sort(aList, 0, aList.size(), comparator);
    }
}

