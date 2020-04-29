package com.sundehui.test;

import java.util.Arrays;
import java.util.Scanner;

public class Main{
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        String paramsHelp1 = scanner.nextLine();
        String[] paramsHelp2 = paramsHelp1.split(" ");
        int[] paramsArg = new int[paramsHelp2.length];
        for(int i=0; i<paramsArg.length; i++){
            paramsArg[i] = Integer.parseInt(paramsHelp2[i]);
        }

        String paramsOfMonster1 = scanner.nextLine();
        String[] paramsOfMonster2 = paramsOfMonster1.split(" ");
        int[] paramsHp = new int[paramsOfMonster2.length];
        for(int i=0; i<paramsHp.length; i++){
            paramsHp[i] = Integer.parseInt(paramsOfMonster2[i]);
        }

        // 获取参数值
        int hit = paramsArg[1];
        int t = paramsArg[2];
        int count = 0;


        Arrays.sort(paramsHp);

        int j = 0;
        while(t>0){

            if (hit>paramsHp[j]){
                j++;
                t--;
                count++;
            }else{
                paramsHp[j] = paramsHp[j]-hit;
                t--;
            }
        }

        System.out.println(count);

    }

}
