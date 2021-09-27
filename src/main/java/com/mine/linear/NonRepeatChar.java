package com.mine.linear;

import java.util.HashMap;
import java.util.Map;

public class NonRepeatChar {
    public Character play(String s){
        /**
         * The capacity is the number of buckets in the hash table
         * Default initial capacity: 1 << 4 = 16 => from 0000 to 1111
         * key.hashCode() -> hashing -> hashValue -> index for bucket
         * hash code:        (char)95=00000000000000000000000001011111
         * hashing(95) :    hashValue=00000000000000000000000001011010
         * index for bucket:    index= 1010 hashValue & 00000000000000000000000000001111  (AND Gate)
         * put operation:  put 95 into Bucket with index: 10 (=1010)
         */
        Map<Character,Integer> nc = new HashMap<Character,Integer>(256); //8 bit = 0 to 256 -1

        for(int i=0;i<s.length();i++){ //O(N)
            Character c = s.charAt(i);
            if(nc.containsKey(c))
            {
                nc.put(c, nc.get(c)+1);
            } else {
                nc.put(c, 1);
            }
        }

        for(int i=0;i<s.length();i++){ //O(N)
            if(nc.get(s.charAt(i))==1)  //O(1)
                return s.charAt(i);
        }

        //Time: O(2N)
        //Space: O(256)  //because 1 char = 1 byte = 8 bit
        return null;
    }
}
