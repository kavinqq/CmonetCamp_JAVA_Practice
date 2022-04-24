public class MyLinkedList {
    private static class Node{
        /**
         * 學號(studentID is primary Key)
         */
        private final int studentID;
        /**
         * 英文成績
         */
        private int englishScore;
        /**
         * 數學成績
         */
        private int mathScore;
        /**
         * 下一個節點的位址
         */
        private Node next;

        /**
         * 建構子
         * @param studentID 學號
         * @param englishScore 英文成績
         * @param mathScore 數學成績
         * @param next 下一個節點位址
         */
        public Node(int studentID, int englishScore, int mathScore, Node next){
            this.studentID = studentID;
            this.englishScore = englishScore;
            this.mathScore = mathScore;
            this.next = next;
        }

        /**
         * 回傳學號
         * @return 學號
         */
        public int getStudentID(){
            return studentID;
        }

        /**
         * 回傳平均成績
         * @return 平均成績
         */
        public int getAvgScore(){
            return (int)((double)(englishScore + mathScore) / 2);
        }

        /**
         * 回傳英文成績
         * @return 英文成績
         */
        public int getEnglishScore(){
            return englishScore;
        }

        /**
         * 回傳數學成績
         * @return 數學成績
         */
        public int getMathScore(){
            return mathScore;
        }
    }

    /**
     * 鍊結創鍊的頭
     */
    private Node head;
    /**
     * 鍊結串列尾節點，方便新增
     */
    private Node tail;
    /**
     * 計算總共有幾筆資料
     */
    private int count;

    /**
     * 建構子
     */
    public MyLinkedList(){
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * 新增一個學生資料節點
     * @param studentID 學號
     * @param englishScore 英文成績
     * @param mathScore 數學成績
     */
    public void add(int studentID, int englishScore, int mathScore){
        Node tmp =  new Node(studentID, englishScore, mathScore, null);
        if(head == null){
            head = tmp;
            tail = tmp;
        }else{
            tail.next = tmp;
            tail = tail.next;
        }
        count++;
    }

    /**
     * 依照平均成績大小排序學生資料 ，想法: 用選擇排序 (每輪挑最大)
     */
    public void sortByAvgScoreOrderByDesc(){
        int max,
            maxIndex = 0;

        Node tmpNode;
        // 每一輪取最大放第 sortCount + i 個，最多跑 count - 1次
        for(int i = 0; i < count - 1; i++){
            tmpNode = get(i);
            max = tmpNode.getAvgScore();
            // 從 下一個節點 開始比較平均分數 誰比較大 到 鍊結串列的尾巴
            for(int j = i + 1; j < count; j ++){
                if(max < get(j).getAvgScore()){
                    maxIndex = j;
                    tmpNode = get(maxIndex);
                    max = tmpNode.getAvgScore();
                }
            }
            // 如果tmpNode 不是 第i個節點 表示有更換過 → 就是後面有更大的值
            if(tmpNode != get(i)){
                //把原本有最大資料的節點 移除
                remove(maxIndex);
                //再把存有最大資料的節點 插入第i個位置
                insert(tmpNode, i);
            }
            if(i == 0){
                head = tmpNode;
            }
        }
        // 去尋找新的尾節點是誰
        tmpNode = head;
        // 總共有 count 個資料 (假設count = 3)  口→口→口 從第一個head 跑到 尾節點 要跑 count - 1次(從0開始所以 < count - 1)
        for(int i = 0 ; i < count - 1 ; i++){
            tmpNode = tmpNode.next;
        }
        tail = tmpNode;
    }

    /**
     * 移除節點
     * @param index 移除第index個節點
     */
    public void remove(int index){
       //如果移除頭，那就直接把頭往後移動
       if(index == 0){
            head = head.next;
       }else {
           // 要刪除的那一個節點
           Node targetNode;
           // 要刪除的那一個節點的前一個節點
           Node preNode = head;
           // 如果(index-- > 0)表示剛好到要刪除的節點， 所以設為 > 1
           while (index-- > 1) {
               preNode = preNode.next;
           }
           // 此時要刪除的目標節點 為下一個
           targetNode = preNode.next;
           // 前一個節點 連到 要刪除的目標節點的下一個節點 (繞過去)
           preNode.next = targetNode.next;
       }
       //總資料數 - 1
       count--;
    }

    /**
     * 獲得節點
     * @param index 獲得第index個節點
     * @return 回傳該節點
     */
    public Node get(int index){
        // 建立一個節點指向head 避免head移動
        Node tmpNode = head;
        // 一步步移動到第index個 節點
        while(index-- > 0){
            tmpNode = tmpNode.next;
        }
        // 回傳這個節點
        return tmpNode;
    }

    /**
     * 插入節點的 Node版本
     * @param node 要插入的節點
     * @param index 要插入的位置
     */
    public void insert(Node node, int index){
        //如果要插入最前面，直接把節點的next指向head，再把head指向節點即可
        if(index == 0){
            node.next = head;
            head = node;
        }else{
            // 建立一個 節點 用來存放 要插入節點的前一個節點
            Node preNode = head;
            Node tmpNode;
            // 找到 前一個節點的位置
            while (index-- > 1) {
                // 記錄下來
                preNode = preNode.next;
            }
            // 先把原本的下一個節點紀錄起來
            tmpNode = preNode.next;
            // 然後把目標位置 的前一個節點 指向要插入的新節點
            preNode.next = node;
            // 再把新節點的next 指向原本前一個節點 的 next
            node.next = tmpNode;
        }
        //總資料數 + 1
        count++;
    }

    /**
     * 插入節點的 資料版本
     * @param studentID 學號
     * @param englishScore 英文成績
     * @param mathScore 數學成績
     * @param index 要插入的位置
     */
    public void insert(int studentID, int englishScore, int mathScore, int index){
        //建立一個新節點
        Node newNode = new Node(studentID, englishScore, mathScore, null);
        //如果要插入最前面，直接把新節點的next指向head，再把head指向新節點即可
        if(index == 0){
            newNode.next = head;
            head = newNode;
        }else {
            // 建立一個 節點 用來存放 要插入節點的前一個節點
            Node preNode = head;
            Node tmpNode;
            // 找到 前一個節點的位置
            while (index-- > 1) {
                // 記錄下來
                preNode = preNode.next;
            }
            // 先把原本的下一個節點紀錄起來
            tmpNode = preNode.next;
            // 然後把目標位置 的前一個節點 指向要插入的新節點
            preNode.next = newNode;
            // 再把新節點的next 指向原本前一個節點 的 next
            newNode.next = tmpNode;
        }
        //總資料數 + 1
        count++;
    }

    /**
     * 這個學號是否可以用
     * @param studentId 學號
     * @return true 可以 false 不行
     */
    public boolean canUseID(int studentId){
        if(head == null){
            return true;
        }
        Node tmpNode = head;
        for(int i = 0; i < count; i++){
            if(tmpNode.getStudentID() == studentId){
                return false;
            }
            tmpNode = tmpNode.next;
        }
        return true;
    }

    /**
     * 獲取總資料數
     * @return 總資料數
     */
    public int getCount(){
        return count;
    }

    /**
     * 顯示此鍊結串列存的所有學生資料
     * @return 此鍊結串列存的所有學生資料
     */
    public String showInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("SN\tENG.\tMATH.\tAVG.\n");
        sb.append("---------------------------\n");
        for(int i = 0; i < count; i++){
            sb.append(get(i).getStudentID()).append("\t");
            sb.append(get(i).getEnglishScore()).append("\t\t");
            sb.append(get(i).getMathScore()).append("\t\t");
            sb.append(get(i).getAvgScore()).append("\n");
        }
        return sb.toString();
    }

}
