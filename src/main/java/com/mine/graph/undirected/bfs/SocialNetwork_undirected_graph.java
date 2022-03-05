package com.mine.graph.undirected.bfs;

public class SocialNetwork_undirected_graph {
    public SocialNetwork_undirected_graph(){
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
