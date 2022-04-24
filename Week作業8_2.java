import java.util.Scanner;

public class Main {
    public static void main(String[] argv){
        Scanner sc = new Scanner(System.in);
        Slime slimeA = new Slime();
        Slime slimeB = new Slime();
        //第二支史萊姆是被攻擊方
        boolean secondSlimeIsUnderAttack = true;
        //史萊姆的 輸出值
        int slimeOutput;
        //總回合數
        int round = 1;
        System.out.print(slimeA.showInfo());
        System.out.println("-------");
        System.out.print(slimeB.showInfo());

        System.out.println("------  請按下Enter 開始戰鬥  ------");
        sc.nextLine();

        while(slimeA.getHp() >= 0 && slimeB.getHp() >= 0){
            System.out.printf("現在是第 %d 回合: \n\n", round);
            System.out.printf("史萊姆%d號 HP: %d   VS  史萊姆%d號 HP: %d\n",slimeA.getNum(),slimeA.getHp(),slimeB.getNum(), slimeB.getHp());
            if(secondSlimeIsUnderAttack){
                System.out.printf("史萊姆%d號 出招!\n", slimeA.getNum());
                Slime.Skill skill = slimeA.randomChooseSkill();
                slimeOutput = slimeA.calculateByRandomSkill(skill);
                if(slimeOutput < 0){
                    System.out.printf("史萊姆%d號使用了 %s! 恢復了 %d 點生命!\n",slimeA.getNum(), skill.getSkillName(), slimeOutput * -1);
                    slimeA.setHp(slimeOutput);
                    System.out.printf("史萊姆%d號 的生命值上生成: %d\n\n", slimeA.getNum(),slimeA.getHp());
                }
                else{
                    System.out.printf("史萊姆%d號使用了 %s! 造成 %d 點傷害!\n",slimeA.getNum(), skill.getSkillName(), slimeOutput);
                    slimeB.setHp(slimeOutput);
                    System.out.printf("史萊姆%d號 的生命值剩下: %d\n\n",slimeB.getNum(), slimeB.getHp());
                }
            }
            else{
                System.out.printf("史萊姆%d號 出招!\n", slimeB.getNum());
                Slime.Skill skill = slimeB.randomChooseSkill();
                slimeOutput = slimeB.calculateByRandomSkill(skill);
                if(slimeOutput < 0){
                    System.out.printf("史萊姆%d號使用了 %s! 恢復了 %d 點生命!\n",slimeB.getNum(), skill.getSkillName(), slimeOutput * -1);
                    slimeB.setHp(slimeOutput);
                    System.out.printf("史萊姆%d號 的生命值上生成: %d\n\n", slimeB.getNum(),slimeB.getHp());
                }
                else{
                    System.out.printf("史萊姆%d號使用了 %s! 造成 %d 點傷害!\n",slimeB.getNum(), skill.getSkillName(), slimeOutput);
                    slimeA.setHp(slimeOutput);
                    System.out.printf("史萊姆%d號 的生命值剩下: %d\n\n",slimeA.getNum(), slimeA.getHp());
                }
            }
            secondSlimeIsUnderAttack = !secondSlimeIsUnderAttack;
            round++;
            System.out.println("----------請按Enter 進行下一回合--------------");
            String stopToSHowInfo = sc.nextLine();

        }
        if(slimeA.getHp() != 0){
            System.out.printf("史萊姆%d號 獲勝!", slimeA.getNum());
        }
        else{
            System.out.printf("史萊姆%d號 獲勝!", slimeB.getNum());
        }
    }
}
