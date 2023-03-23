public class trie{
    static class Node{
        Node[] children;
        boolean endOfWord;

        public Node(){
            children = new Node[26];
            for(int i = 0; i < 26; i++){
                children[i] = null;
            }
            endOfWord = false;
        }
    }



    static Node root = new Node();

    public static void insert(String word){
        Node curr = root;
        for(int i = 0;  i< word.length(); i++){
            int idx = word.charAt(i)-'a';


            if(curr.children[idx]== null){
                //add new node
                curr.children[idx]= new Node();
            }
            if(i== word.length()-1){
                curr.children[idx].endOfWord = true;
            }
            curr = curr.children[idx];
        }
    }

    public static boolean search(String key){
        Node curr = root;
        for(int i = 0; i < key.length(); i++){
            int idx = key.charAt(i)-'a';
            Node node = curr.children[idx];
            if(node==null){
                return false;
            }
            if(curr.children[idx]==null){
                return false;
            }
            if(i == key.length()-1){
                if(curr.children[idx].endOfWord==false){
                    return false;
                }
                else{
                    return true;
                }
            }
            
            
            curr = curr.children[idx];
            
        }
        return true;
    }

    public static boolean wordBreak(String key){
        if(key.length()==0){
            return true;
        }

        for(int i = 1; i <=key.length(); i++){
            String firstPart = key.substring(0, i);
            String secPart = key.substring(i);
            if(search(firstPart) && wordBreak(secPart)){
                return true;
            }
        }
        return false;
    }
    public static boolean startsWith(String key){
        Node curr = root;
        for(int i = 0; i < key.length(); i++){
            int idx = key.charAt(i)-'a';
            Node node = curr.children[idx];
            if(node==null){
                return false;
            }
            if(i == key.length()-1){
                return true;
            }
            curr = curr.children[idx];
        }
        return true;
    }
    //unique substrings of a string are all prefix of a suffic and all suffix of a prefix
    //all unique prefix of all suffix or all unique suffix of all prefix
    
    static Node node2 = new Node();
    public static void insertSubstring(String word){
        Node curr = node2;
        for(int i = 0; i < word.length(); i++){
            int idx = word.charAt(i)-'a';
            Node node = curr.children[idx];
            if(node==null){
                curr.children[idx] = new Node();
            }
            if(i == word.length()-1){
                curr.children[idx].endOfWord= true;
            }
            curr = curr.children[idx];
        }
    }
    public static int countNodes(Node root){
        if(root==null){
            return 0;
        }
        int sum = 0;
        for(int i = 0; i < 26; i++){
            if(root.children[i]!=null){
                sum += countNodes(root.children[i]);
            }
        }
        return 1+sum;
    }
    static Node node3 = new Node();
    public static void insert3(String word){
        Node curr = node3;
        for(int i = 0; i < word.length(); i++){
            int idx = word.charAt(i)-'a';
            if(curr.children[idx]==null){
                curr.children[idx] = new Node();
            }
            if(i == word.length()-1){
                curr.children[idx].endOfWord=true;
            }
            curr = curr.children[idx];
        }
    }
    //creating another function for recursion
    public static void longestWord(Node root, StringBuilder temp, String ans){
        if(root==null){
            return;
        }

        for(int i = 0; i< 26; i++){
            if(root.children[i]!=null && root.children[i].endOfWord==true){
                temp.append((char)(i+'a'));

                if(temp.length()> ans.length()){
                    ans = temp.toString();
                }
                longestWord(root.children[i], temp, ans);

                temp.deleteCharAt(i);
            }
        }
    }

    public static String longestWordWithAllPrefix(){
        String[] words2 = {"a", "banana", " app", " appl", " ap", "apply", "apple"};
        String ans= "";
        for(int i = 0; i< words2.length;i++){
            insert3(words2[i]);
        }
        Node root2 = node3;
        StringBuilder temp = new StringBuilder();
        
        longestWord(root2, temp, ans);





        return ans;
    }

    
    public static void main(String args[]){
        String words[] = {"apple", "app", "mango", "man", "woman"};
        for(String word : words){
            insert(word);
        }

        String key = "app";
        String key2 = "man";
        System.out.println(startsWith(key));
        System.out.println(startsWith(key2));
        System.out.println(startsWith("hello"));
        
        
        //to count unique substrings 
        //find all suffix of string and create a trie from those suffix
        //total nodes of that tree = count of all unique substrings
        String str = "apple";
        for(int i = 0; i<= str.length()-1; i++){
            for(int j  = i; j<= str.length();j++){
                String s = str.substring(i, j);
                insertSubstring(s);
            }
        }
        Node root = node2;
        System.out.println(countNodes(root));

        //Find the longest string in words such that every prefix of it is also in words
       
        
        System.out.println(longestWordWithAllPrefix());
        
        
        //add all words to a trie and count to all nodes where all endboolean before it are true
        


    }
}