/**
 * 我的鍊結串列
 *
 * @param <T> 任意型態
 */

public class MyLinkedList<T> implements MyIterator<T> {

    public interface Comparator<T> {
        public int compare(T t1, T t2);
    }

    /**
     * 節點的樣本 (內部類別)
     *
     * @param <T> 任意型態
     */

    private class Node<T> {

        private T val; // 節點值
        private Node<T> next; // 下一個節點

        /**
         * 三種建構子
         */

        public Node() {
            val = null;
            next = null;
        }

        public Node(T val) {
            this.val = val;
            next = null;
        }

        public Node(T val, Node next) {
            this.val = val;
            this.next = next;
        }

        public T getVal() {
            return val;
        }

        public void setVal(T val) {
            this.val = val;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }


        public Node<T> getNext() {
            return next;
        }
    }


    private int size; // 鍊結串列大小

    private Node<T> head;// 此串列頭
    private Node<T> tail;// 此串列尾
    private Node<T> current;// 現在位置

    public MyLinkedList() {

        size = 0; // 一開始沒節點

        head = new Node<>();
        current = tail = head; // 一開始沒頭尾
    }

    /**
     * 現在有幾個節點
     *
     * @return 節點數
     */

    public int getSize() {
        return size;
    }

    /**
     * 新增一個節點在最後面
     *
     * @param val 節點值
     */

    public void add(T val) {

        //新節點
        Node<T> tmpNode = new Node(val, null);

        // 把尾巴指向他
        tail.setNext(tmpNode);

        // 再把尾巴往後移動
        tail = tail.getNext();

        // 大小+1
        size++;
    }

    public void insert(int index, T val) {

        // 新的節點
        Node<T> newNode = new Node(val, null);

        // 要插入第index個位置
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        // 先把新增節點的下一個 指向 原本要插入位置後面一個
        newNode.setNext(current.next);

        // 再把 原本要插入位置的前面一個 指向 新增節點
        current.setNext(newNode);

        // 大小 +1
        size++;

        // reset current
        current = head;
    }

    /**
     * 刪除節點
     *
     * @param index 要刪除的節點
     */
    public void delete(int index) {

        // 跑到要刪除節點的上一個
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }

        // 先把 原本的下下一個節點 記下來
        Node<T> tmpNode = current.getNext().getNext();

        // 把現在的下一個 指向 下下一個
        current.setNext(tmpNode);

        // reset current
        current = head;

        size--;
    }


    /**
     * 取得節點資料
     *
     * @param index 第幾個節點的資料
     * @return 該筆節點的資料
     */

    public T getVal(int index) {

        // 跑到要拿資料的節點
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }


        // 把資料copy下來
        T data = current.getVal();

        // reset current
        current = head;

        return data;
    }


    public Node<T> getNode(int index) {

        // 跑到要拿資料的節點
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        // 把資料copy下來
        Node<T> tmpNode = current;

        // reset current
        current = head;

        return tmpNode;
    }


    /**
     * 排序
     * @param c 我的比較器
     */

    public void sort(Comparator<T> c) {

        for (int i = 0; i < getSize() - 1; i++) {
            for (int j = 0; j < getSize() - 1 - i; j++) {
                if (c.compare(getVal(j), getVal(j + 1)) > 0) {
                    Node<T> tmp = getNode(j);
                    Node<T> tmp2 = getNode(j + 1);

                    T val = tmp.getVal();
                    tmp.setVal(tmp2.getVal());
                    tmp2.setVal(val);
                }
            }
        }
    }


    /**
     * 印出所有節點值
     */

    public void print() {

        while (hasNext()) {
            T data = next();
            System.out.println(data);
        }

        System.out.println();
    }


    /**
     * 實作MyIterator
     *
     * @return 如果還有下一個 return true 反之  return false
     */

    @Override
    public boolean hasNext() {

        // 如果到資料尾巴了
        if (current.getNext() == null) {

            //reset current
            current = head;

            return false;
        } else {
            return true;
        }
    }

    /**
     * 實作MyIterator
     *
     * @return 回傳下一個節點的資料
     */

    @Override
    public T next() {

        current = current.getNext();

        return current.val;
    }


}
