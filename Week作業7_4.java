import java.util.ArrayList;


public class Main {
    /**
     * 選擇要使用哪個排序法
     */

    public enum SortOption{
        BubbleSort,
        SelectionSort,
        InsertionSort,
        QuickSort,
        MergeSort,
        Exit
    }

    /**
     * 選擇要使用哪一比 資料測試排序
     */

    public enum DataOption {
        Data1,
        Data2,
        Data3,
        CUSTOMIZE,
    }

    public static void main(String[] argv){
        final int[] data1 = {1,5,9,2,4,0,8,10,3,7,6,-1,-2,-3,-4}; //簡單測試組
        final int[] data2 = {25,80,94,80,36,25,70,80,59,63,48,100,20,3,0}; //很多重覆組
        final int[] data3 = {80,24,11,47,19,91,2,32,85,7,16,36,99,52,41};//亂打一通組
        ArrayList<Integer> testData = new ArrayList<>();

        Input input = new Input(); // 新增Input類別的物件
        Sort sortDemo = new BubbleSort(); // 排序法的樣板，初始化為氣泡排序法
        int option; // 每一個數字選項都可以使用
        boolean isEnd = false; // 是否可以結束
        do{
            option = input.inputInt("要選擇哪種排序法?\n" +
                    "1. BubbleSort\t" +
                    "2. SelectionSort\t" +
                    "3. InsertionSort\n" +
                    "4. QuickSort\t" +
                    "5. MergeSort\t" +
                    "6. Exit", 1, 6);
            //把選項換成 列舉常數值,選擇哪一種排序法
            SortOption sortOption= SortOption.values()[option - 1];
            switch(sortOption){
                case BubbleSort:{
                    sortDemo = new BubbleSort();
                    break;
                }
                case SelectionSort:{
                    sortDemo = new SelectionSort();
                    break;
                }
                case InsertionSort:{
                    sortDemo = new InsertionSort();
                    break;
                }
                case QuickSort:{
                    sortDemo = new QuickSort();
                    break;
                }
                case MergeSort:{
                    sortDemo = new MergeSort();
                    break;
                }
                case Exit:{
                    isEnd = true;
                    continue;
                }
            }
            option = input.inputInt("要選擇哪一組測試資料?\n" +
                    "1. [1,5,9,2,4,0,8,10,3,7,6,-1,-2,-3,-4]\n" +
                    "2. [25,80,94,80,36,25,70,80,59,63,48,100,20,3,0]\n" +
                    "3. [80,24,11,47,19,91,2,32,85,7,16,36,99,52,41]\n" +
                    "4. 自己輸入一筆整數資料\n",1,4);
            DataOption dataOption = DataOption.values()[option - 1];
            //把選項換成 列舉常數值，選擇哪一組資料去排序
            boolean isCorrectFormat = true;
            switch(dataOption){
                case Data1:{
                    for(int i: data1){
                        testData.add(i);
                    }
                    break;
                }
                case Data2:{
                    for(int i: data2){
                        testData.add(i);
                    }
                    break;
                }
                case Data3:{
                    for(int i: data3){
                        testData.add(i);
                    }
                    break;
                }
                case CUSTOMIZE:{
                    //是否能離開輸入
                    boolean canExit = false;
                    System.out.println("請輸入數字:");
                    do {
                        String customizeData = input.getScanner().nextLine();
                        //遇到分隔符號前的總和
                        int sum = 0;
                        //位元左移
                        int digitMoveLeft = 1;
                        boolean isNatureNum = true;
                        for(int i = 0; i < customizeData.length() ; i++){
                            // 當字元不是 1. 0 ~ 9  2. 空格 3. 負號 跳出錯誤
                            if(!((customizeData.charAt(i) >= 48 && customizeData.charAt(i) <= 57) ||
                                    customizeData.charAt(i) == ' ' || customizeData.charAt(i) == '-')){
                                System.out.println("格式錯誤 請輸入全數字(負數也可以) 用空格分開");
                                isCorrectFormat = false;
                                break;
                            }
                            // 當字元不是  空格 和 負號 計算該值到sum
                            if(customizeData.charAt(i) != ' ' && customizeData.charAt(i) != '-'){
                                sum *= digitMoveLeft;
                                digitMoveLeft *= 10;
                                sum += (customizeData.charAt(i) - 48);
                                //如果index是最後一個了， 輸入sum 並跳出
                                if(i == customizeData.length() - 1) {
                                    if (!isNatureNum) {
                                        sum *= -1;
                                    }
                                    testData.add(sum);
                                    sum = 0;
                                    digitMoveLeft = 1;
                                    isNatureNum = true;
                                    canExit = true;
                                }
                            }else if(customizeData.charAt(i) == '-'){//是否是負數
                                isNatureNum = false;
                            }else{ // 字串還有資料 遇到分隔符號 輸入這筆sum，sum和相關計算變數 重置
                                if(!isNatureNum){
                                    sum *= -1;
                                }
                                testData.add(sum);
                                sum = 0;
                                digitMoveLeft = 1;
                                isNatureNum = true;
                            }


                        }
                    } while (!canExit);
                }
            }

            if(!isCorrectFormat){
                continue;
            }

            //這一次用哪一種排序法
            System.out.printf("This is %s Demo!\n",sortDemo.getClass().getSimpleName());
            //排序前的資料
            System.out.print("testArr before :");
            for(int i : testData){
                System.out.printf(" %2d ", i);
            }
            System.out.println();
            System.out.println("------------------------------------------------------------");

            /*  QuickSort 另外寫 是因為 在arr[left] = arr[pivot] && arr[right] = arr[pivot]時
                left也要++ && right也要-- (不然有數字重複的狀況會出錯)
                第一組會變成 → 0   3  20  25  25  36  48  59  63  70  80  100  80  94  80
                其他排序重複狀況並不需要納入交換判斷 結果不變。
             */
            if(sortOption == SortOption.QuickSort){
                sortDemo.sort(testData, (num1, num2) ->{
                    return (num1 - num2 >= 0);
                });
            }else{
                sortDemo.sort(testData, (num1, num2) ->{
                    return (num1 - num2) > 0;
                });
            }

            //排序之後的 輸出
            System.out.print("\ntestArr after  :");
            for(int i : testData){
                System.out.printf(" %2d ", i);
            }
            testData.clear();
            System.out.println();
            System.out.println("------------------------------------------------------------");
        }while(!isEnd);

        System.out.println("Bye!");
    }
}
