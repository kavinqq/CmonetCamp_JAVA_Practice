import java.util.*;
//主程式有一個 Student的陣列
public class StudentManageSystem
{
    public int input(String hint,int min, int max)
    {
        Scanner sc = new Scanner(System.in);
        hint = hint + "(" + min + " ~ " + max + ") :";
        do{
            System.out.println(hint);
            int inputNum = sc.nextInt();
            if(inputNum >= min && inputNum <= max){
                return inputNum;
            }
            System.out.println("請重新輸入!");
        }while(true);
    }

    public String input(String hint, int minLen)
    {
        Scanner sc = new Scanner(System.in);
        hint = hint + "(最短長度:" + minLen + "):";
        do{
            System.out.println(hint);
            String tmpStr = sc.nextLine();
            if(tmpStr.length() >= minLen){
                return tmpStr;
            }
            System.out.println("請重新輸入!");
        }while(true);
    }

    public static void main(String[] argv)
    {
        StudentArray studentArr = new StudentArray();
        StudentManageSystem studentManageSystem = new StudentManageSystem();
        Student[] students = new Student[1];
        int studentCount = 0;

        do {
            System.out.println("\n功能列表: 0.顯示所有資料 1.新增學生 2.輸入座號印出該學生資料 3.依照成績由大到小排序學生\n" +
                    "\t4.依照座號由小到大排序學生  5.更改指定座號的學生成績 6.結束程式");
            int option = studentManageSystem.input("請選擇功能", 0, 6);
            switch (option) {
                case 0: {
                    if(studentCount <= 0){
                        System.out.println("目前沒有資料!");
                        break;
                    }
                    for(Student student: students)
                    {
                        if(student == null){
                            break;
                        }
                        System.out.println(student);
                    }
                    break;
                }
                case 1: {
                    String name = studentManageSystem.input("請輸入學生姓名", 2);
                    String schoolClass = studentManageSystem.input("請輸入學生班級:", 1);
                    int score = studentManageSystem.input("請輸入成績", 0, 100);
                    if (studentCount == students.length) {
                        students = studentArr.doubleArr(students);
                    }
                    students[studentCount++] = new Student(studentCount, schoolClass, name, score);
                    break;
                }
                case 2: {
                    if(studentCount <= 0){
                        System.out.println("目前沒有資料!");
                        break;
                    }
                    int seat = studentManageSystem.input("請輸入指定的座號", 1, studentCount);
                    System.out.println(studentArr.getStudentBySeat(seat, students));
                    break;
                }
                case 3: {
                    if(studentCount > 0) {
                        studentArr.sortByScoreDESC(students, studentCount);
                        System.out.println("修改後的資料表為: ");
                        for(Student student: students)
                        {
                            if(student == null){
                                break;
                            }
                            System.out.println(student);
                        }
                        break;
                    }
                    else
                    {
                        System.out.println("目前沒有資料!");
                    }
                    break;
                }
                case 4: {
                    if(studentCount > 0) {
                        studentArr.sortBySeatASC(students, studentCount);
                        System.out.println("修改後的資料表為: ");
                        for(Student student: students)
                        {
                            if(student == null){
                                break;
                            }
                            System.out.println(student);
                        }
                        break;
                    }
                    else
                    {
                        System.out.println("目前沒有資料!");
                    }
                    break;
                }
                case 5: {
                    if(studentCount <= 0){
                        System.out.println("目前沒有資料!");
                        break;
                    }
                    int seat = studentManageSystem.input("請輸入指定的座號", 1, studentCount);
                    int score = studentManageSystem.input("成績要改成多少", 0, 100);
                    System.out.println(studentArr.setStudentScoreBySeat(seat, score, students));
                    break;
                }
                case 6: {
                    System.exit(0);
                }
            }
        }while(true);
    }
}
