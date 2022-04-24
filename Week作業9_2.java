public class Main {
    public static void main(String[] argv) {

        final int MinIndex = 1; // 最小index = 1 (限制最小為 第一筆資料 or 第一個節點)

        Input input = new Input(); // 輸入class
        int index; // 插入/新增/刪除 要輸入的index
        int data; // 新增節點要輸入的值

        MyLinkedList<Integer> myLinkedListInt = new MyLinkedList<>();
        MyLinkedList<Character> myLinkedListChar = new MyLinkedList<>();

        // 加資料到兩個LinkedList
        for(int i = 5; i > 0 ; i--){
            myLinkedListInt.add(i);
            myLinkedListChar.add((char)(65 + i));
        }

        // 印出 char版本
        System.out.println("char版本------------");
        myLinkedListChar.print();

        // 印出 int版本
        System.out.println("int版本------------");
        myLinkedListInt.print();

        System.out.println("以Int版本 作為操控");

        index = input.inputInt("請輸入要刪除的節點: ", MinIndex, myLinkedListInt.getSize());
        myLinkedListInt.delete(index);
        // 印出 刪除後int版本 LinkedList
        myLinkedListInt.print();

        index = input.inputInt("請輸入要插入的節點位置: ", MinIndex, myLinkedListInt.getSize());
        data = input.inputInt("請輸入要插入的節點的值: ", 0, 9999);

        myLinkedListInt.insert(index, data);// 印出 插入後int版本 LinkedList
        myLinkedListInt.print();


        // 排序
        // lambda => 由小到大排
        System.out.println("int版本 排序之後(由小到大排)");

        myLinkedListInt.sort(((t1, t2) -> {
            return t1 - t2;
        }));

        myLinkedListInt.print();


        System.out.println("char版本 排序之後(由小到大排)");

        myLinkedListChar.sort(((t1, t2) -> {
            return t1 - t2;
        }));
        myLinkedListChar.print();

    }
}
