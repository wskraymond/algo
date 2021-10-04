package com.mine.binarysearch;

public class Sqrt {
    /**
     * case 1
     * input:
     * 1 2 3 4 5 6 ......................36
     * 1 4 9 16 25 36 .....................
     *
     * sqrt(36) <= 36^36
     * @param x
     * @return
     */
    public int play(int x){
        if(x==0)
            return 0;

        if(x==1)
            return 1;

        int i = 1, j = x;
        int middle = i + (j -i) / 2 ;
        while(j-i > 1){
            if(x >= middle*middle) { //middle..........j
                i = middle;
            } else { //i..........middle-1
                j = middle -1;
            }
            middle = i + (j - i) / 2;
        }

        int result = 0;
        if(x>=i*i)
            result = i;

        if(x>=j*j)
            result = j;

        return result;
    }

    /**
     *
     * 30 , 25
     * 4*4 4*5 4*6....4*9 5*5 5*6 ....5*9
     * 16  20  24 ....36  25  30 .....45
     * @param x
     * @return
     */
    public int play2(int x) {
        if (x == 0)
            return 0;
        int left = 1, right = Integer.MAX_VALUE;
        while (true) {
            int mid = left + (right - left)/2;
            if (mid > x/mid) {
                right = mid - 1;
            } else {
                if (mid + 1 > x/(mid + 1))
                    return mid;
                left = mid + 1;
            }
        }
    }
}
