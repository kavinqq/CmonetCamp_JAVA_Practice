import java.util.ArrayList;

abstract  public class Sort {
    //note: 介面是不用寫()的，每次都被IDE提醒，要記住
    public interface Comparator{
        public boolean compare(int num1, int num2);
    }
    public void showEachRound(ArrayList<Integer> aList) {
        System.out.printf("This is %s move each round : ", this.getClass().getSimpleName());
        for(int i: aList){
            System.out.printf(" %2d ", i);
        }
        System.out.println();
    }

    abstract public void sort(ArrayList<Integer> aList, Comparator c);

}
