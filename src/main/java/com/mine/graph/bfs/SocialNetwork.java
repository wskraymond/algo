package com.mine.graph.bfs;

public class SocialNetwork {
    public SocialNetwork(){
        //construct network
    }

    static class Person{
        private final Person[] directFriends;

        public Person(Person[] directFriends){
            this.directFriends = directFriends;
        }
        public Person[] getDirectFriends(){
            return null;
        }

        /**
         * friends of friends up to nth separations
         * @param n
         * @return
         */
        public Person[] getFriends(int n){
            return null;
        }
    }



}
