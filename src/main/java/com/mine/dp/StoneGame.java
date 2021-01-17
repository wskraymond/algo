package com.mine.dp;

public class StoneGame {
    public static void main(String[] args){
        System.out.println(new StoneGame().play(new int[]{4,5}));

    }

    public boolean play(int[] piles) {
        int[] newCount = new int[]{0,0};
        newCount = round(piles, 0, piles.length-1, 0, newCount);
        System.out.println(newCount[0] + " | " + newCount[1] + " = " + (newCount[0] + newCount[1]));


        return newCount[0] > newCount[1];
    }

    private int[] round(int[] piles, int head, int tail, int user, int[] count)
    {
        int[] rA = null;
        int[] rB = null;
        if(head!=tail)
        {
            int[] newCount = count.clone();
            newCount[user]+=piles[head];
            if(tail>(head+1))
            {
                rA = round(piles, head+1, tail, (user+1)%2, newCount);
            }
            else
            {
                newCount[(user+1)%2]+=piles[tail];
                rA = newCount;
            }
        }

        count[user]+=piles[tail];
        if((tail-1)>head)
        {
            rB = round(piles, head, tail-1, (user+1)%2, count);
        }
        else
        {
            count[(user+1)%2]+=piles[head];
            rB = count;
        }

        return (rA!=null
                && rA[0] - rA[1] > rB[0] - rB[1]) ? rA : rB;
    }
}
