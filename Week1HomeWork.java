import java.util.*;

public class PolymorphismPracticeAnimal
{
    public static void main(String[] argv)
    {
        Scanner sc = new Scanner (System.in);
        AnimalArray animalArray = new AnimalArray();
        char keepUsing ;
        System.out.println("歡迎使用簡易寵物紀錄表 !");
        System.out.println("目前可記錄的寵物類型只有三種: 狗 / 貓 / 老鼠");
        System.out.println("可記錄的資料欄位 : 姓名 / 體重\n");

        do{
            System.out.println("新增資料請按 1 / 插入資料請按 2 / 刪除資料請按 3");
            int mode = sc.nextInt();
            if(mode < 0 || mode > 3){
                System.out.println("請輸入 1 ~ 3之間的數字! 麻煩重新操作一遍。");
                keepUsing = 'y';
                continue;
            }

            if(mode == 3 && animalArray.animals.size() > 0){
                System.out.println("請輸入要刪除的資料欄位: ");
                int index = sc.nextInt() - 1; // index range: 0 ~ animals.size() - 1
                if(index < 0 || index >= animalArray.animals.size()){
                    System.out.println("該位置沒有存放資料!");
                    System.out.println("繼續操作請按(Y/y): ");
                    sc.nextLine();
                    keepUsing = sc.nextLine().charAt(0);
                    continue;
                }
                // If index in range 0 ~ animals.size() - 1
                animalArray.delAnimal(index);
                // show ArrayList
                if(animalArray.animals.size() == 0)
                {
                    System.out.println("目前沒有資料!");
                }
                else
                {
                    animalArray.showInfo();
                }

                System.out.println("繼續操作請按(Y/y): ");
                sc.nextLine();
                keepUsing = sc.nextLine().charAt(0);
                continue;
            }
            //no data or index OutOfBounds
            else if(mode == 3){
                System.out.println("目前沒有可以刪除的資料!");
                System.out.println("繼續操作請按(Y/y): ");
                sc.nextLine();
                keepUsing = sc.nextLine().charAt(0);
                continue;
            }

            System.out.println("記錄: 狗 請按 1 || 貓 請按 2 || 老鼠 請按 3");
            int animalType = sc.nextInt();
            sc.nextLine();
            if(animalType < 0 || animalType > 3){
                System.out.println("請輸入 1 ~ 3之間的數字! 麻煩重新操作一遍。");
                keepUsing = 'y';
                continue;
            }
            System.out.println("請輸入姓名: ");
            String name = sc.nextLine();
            if(!animalArray.nameCheck(name))
            {
                System.out.println("該名字已被使用過! 麻煩重新操作一遍。");
                keepUsing = 'y';
                continue;
            }
            System.out.println("請輸入體重(公斤): ");
            float weight = sc.nextFloat();

            if(mode == 1)
            {
                animalArray.addAnimal(animalType,name,weight);
                animalArray.showInfo();
            }
            else if(mode == 2 && animalArray.animals.size() == 0){
                System.out.println("由於目前沒有儲存資料，自動幫您把插入位置設為 1");
                animalArray.insertAnimal(animalType,name,weight,0);
                animalArray.showInfo();
            }
            else if(mode == 2)
            {
                animalArray.showInfo();
                System.out.printf("請輸入要插入的位置:(%2d 到% 2d) ", 1, animalArray.animals.size());
                int index = sc.nextInt() - 1;
                if(index < 0 || index > animalArray.animals.size())
                {
                    System.out.println("抱歉! 不能插入資料到這個位置!");
                }
                else {
                    animalArray.insertAnimal(animalType, name, weight, index);
                    animalArray.showInfo();
                }
            }

            if(animalArray.animals.size() >= 2)
            {
                System.out.printf("目前資料數為: %2d筆。%n",animalArray.animals.size());
                System.out.println("要按照 姓名排序 請按1 / 體重排序 請按2 / 不排序請按3");
                int chooseSort = sc.nextInt();
                animalArray.sortByNameOrWeight(chooseSort);
                animalArray.showInfo();
            }
            System.out.println("繼續操作請按(Y/y): ");
            sc.nextLine();
            keepUsing = sc.nextLine().charAt(0);
        }while (keepUsing == 'y' || keepUsing == 'Y');
        animalArray.showFinalInfo();
    }
}

class Animal{
    private String name;
    private float weight;

    public String getName()
    {
        return name;
    }
    public double getWeight()
    {
        return weight;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setWeight(float weight){
        this.weight = weight;
    }
}

class Dog extends Animal
{
    Dog(String name, float weight)
    {
        setName(name);
        setWeight(weight);
    }
}

class Cat extends Animal
{
    Cat(String name, float weight)
    {
        setName(name);
        setWeight(weight);
    }
}

class Mouse extends Animal
{
    Mouse(String name, float weight)
    {
        setName(name);
        setWeight(weight);
    }
}

class AnimalArray
{
    ArrayList<Animal> animals = new ArrayList<>();
    //animal's name is uniqueness
    public boolean nameCheck(String name)
    {
        for(Animal a: animals)
        {
            if(a.getName().equals(name))
            {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param animalType (參數用來做什麼)
     * @param name
     * @param weight
     */
    public void addAnimal(int animalType,String name,float weight)
    {
        if(animalType == 1)
        {
            animals.add(new Dog(name, weight));
        }
        else if(animalType == 2)
        {
            animals.add(new Cat(name, weight));
        }
        else if(animalType == 3)
        {
            animals.add(new Mouse(name, weight));
        }
    }

    public void insertAnimal(int animalType,String name,float weight,int index)
    {
        if(animalType == 1)
        {
            animals.add(index, new Dog(name, weight));
        }
        else if(animalType == 2)
        {
            animals.add(index, new Cat(name, weight));
        }
        else if(animalType == 3)
        {
            animals.add(index, new Mouse(name, weight));
        }
    }

    public void delAnimal(int index)
    {
        animals.remove(index);
    }

    public void showInfo()
    {
        System.out.printf("%n目前的儲存的資料為: %n");
        for(int i = 0; i < animals.size(); i++)
        {
            System.out.printf("%d. 種類: %-5s\t姓名: %-10s 體重: %.1f(kg)\t%n", (i + 1),
                    animals.get(i).getClass().getSimpleName(),animals.get(i).getName(),animals.get(i).getWeight());
        }
    }

    public void showFinalInfo()
    {
        System.out.printf("您儲存的資料為: %n");
        for(int i = 0; i < animals.size(); i++)
        {
            System.out.printf("%d. 種類: %-5s\t姓名: %-10s 體重: %.1f(kg)\t%n", (i + 1),
                    animals.get(i).getClass().getSimpleName(),animals.get(i).getName(),animals.get(i).getWeight());
        }
    }

    public void sortByNameOrWeight(int mode)
    {
        if(mode == 1)
        {
            System.out.println("選擇 : 按 姓 名 排 序 !");
            Collections.sort(animals, new comparatorName());
        }
        else if(mode == 2)
        {
            System.out.println("選擇 : 按 體 重 排 序 !");
            Collections.sort(animals, new comparatorWeight());
        }
        else
        {
            System.out.println("選擇 : 不 排 序 !");
        }
    }

    public void searchByName(String name)
    {
        for(Animal a : animals)
        {
            if(a.getName() == name)
            {
                System.out.printf("種類: %-5s\t姓名: %-10s 體重: %.1f(kg)\t%n",
                    a.getClass().getSimpleName(),a.getName(),a.getWeight());
                break;
            }
        }
    }
}//End class AnimalArray

class comparatorName implements Comparator<Animal>
{
    @Override
    public int compare(Animal first, Animal second)
    {
        return first.getName().compareTo(second.getName());
    }
}

class comparatorWeight implements Comparator<Animal>
{
    @Override
    public int compare(Animal first, Animal second)
    {
        return (int)(first.getWeight() - second.getWeight());
    }
}
