package Algodat;
import java.util.*;
//class node berikut untuk menyimpan variabel vertex dan digunakan sebagai node dalam linkedlist, stack dan queue
class Node{
    Vertex data;
    Node next;
    //berikut merupakan konstraktor yang otomatis akan dipannggil ketika objek dibuat
    Node(Vertex data){
        this.data=data;
    }
}
//class linkedlist ini berfungsi sebagai abstrak(tapi bukan class abstrak) yang nanti akan di extends oleh class stack dan queue
class linkedlist{
    Node head,tail; //class ini memiliki variabel head yang merupakan node terdepan dan tail sebagai node paling belakang
    //method di bawah ini adalah method untuk menmbah data di bagian head dimana data yang di tambahkan akan menjadi head
    void addFirst(Vertex newVertex){
        Node baru =new Node(newVertex); //membuat node dengan verteks yang dimasukkan
        //jika linkedlist kosong maka node yang dimasukkan akan menjadi head dan tail
        if(head==null){
            head=tail=baru;
        }else{//jika linkedlist tidak kosong maka data akan ditambahkan sebagai head dan memiliki next yaitu head sebelumnya
            baru.next=head;
            head=baru;
        }
    }
}
//class stack ini digunakan untuk menyimpan sementara data ketika dilakukan DFS
class stack extends linkedlist{//class stack ini akan mewarisi semua method dan atribut dari class linkedlist
    Node temp;//variable ini digunakan untuk menyimpan node yang akan di pop nanti 
    //method push ini untuk menambahkan data di bagian head, sama dengan method addFirst pada linkedlist
    void push(Vertex data){
        addFirst(data);
    }
    //method pop digunakan untuk mengambil data dari stack dimana data yang di ambil adalah node head
    Node pop(){
        temp=head; //menyimpan head kedalam node temp
        head=head.next; //melepas head dari urutan stack
        return temp; //mengembalikan nilai yuang tersimpan dalam temp
    }
}
//class stack ini digunakan untuk menyimpan sementara data ketika dilakukan BFS
class queue extends linkedlist{//class queue ini akan mewarisi semua method dan atribut dari class linkedlist
    Node temp;//variable ini digunakan untuk menyimpan node yang akan di pop nanti
    Node current;//sebagai penunjuk node
    void push(Vertex data){ //method ini sama dengan method push pada stack yaitu menambah data di bagian head
        addFirst(data);
    }
    //method pop ini di gunakan untuk mengambil data tail pada queue
    Node pop(){
        Node current=head;//menyimpan head ke dalam current
        if(head==tail){ //jika queue hanya memiliki satu node maka data yang di pop adalah data tersebut sehingga queue akan kosong
            temp=head;
            head=null;
            tail=null;
        }else{
            //jika queue memiliki node libih dari satu maka akan dilakukan perulangan berikut
            //perulangan ini digunakan untuk mencari data sebelum tail yang nanti akan di jadikan sebagai tail saat tail di pop
            //perulangan ini dilakukan karena stack ini menggunakan prinsip single linkedlist (tidak bisa menunjuk data lain dari tail)
            while(current.next!=tail){
                current=current.next;
            }
            //ketika data tersebut sudah di temukan maka
            temp=tail;//tail akan di simpan dalam tail
            tail=current;//mengatur current sebagai tail
            current.next=null;//melepas tail sebelumnya dari queue
        }
        return temp;//mengembalikan nilai berupa node yang di simpan dalam temp
    }
}
//class vertex ini adalah class untuk menyimpan atribut dan method dari vertex
//di mana vertex adalah sebuah node dalam graph
class Vertex{
    String name; // variabel nama untuk menyimpan data. dalam hal ini berupa nama tempat(mataram, selong....)
    boolean visited; //menandai apakah vertex sudah di kunjung dalam satu iterasi DFS atau BFS
    ArrayList<Vertex> tetangga=new ArrayList<Vertex>(); // menyimpan vertex yang merupakan tetangga dari vertex ini
    //constractor yang meminta data String dan akan disimpan sebagai name
    public Vertex(String name){
        this.name=name;
    }
    //method di bawah ini berfungsi untuk menampilkan tetangga dari vertex ini
    void Daftartetangga(){
        for(int i=tetangga.size()-1; i>=0;i--){ //perulangan sebanyak tetangga 
            System.out.print(tetangga.get(i).name+" "); //menampilkan nama tiap tetangga
        }
    }
}
// class graph ini adalah class yang di dalam nya terdapat kumpulan verteks yang memiliki hubungan ketetanggaan
class Graph{
    ArrayList<Vertex> vertex=new ArrayList<Vertex>(); //Arraylist yang berisi verteks-verteks dalam sebuah graph 
    void addVertex(Vertex ver){ //method untuk menambahkan verteks ke dalam graph.
        vertex.add(ver);
    }
    //method ini akan mengatur ulang status visited menjadi false
    //digunakan pada method dfs dan bfs agar bisa melakukan bfs dan dfs lebih dari satu kali
    void setVisitStatus(){
        for(int i=0;i<vertex.size();i++){ //perulangan untuk menelusuri setiap vertex dalam grap
            vertex.get(i).visited=false;
        }
    }
    //method add edge ini digunakan untuk menambah tetangga verteks
    void addEdge(Vertex asal,Vertex Tetangga){
        asal.tetangga.add(Tetangga);    //dimana ketika a diatur sebagai tetangga dari b
        Tetangga.tetangga.add(asal);    //maka b juga adalah tetangga dari a'
    }
    //method adj list ini digunakan untuk menampilkan tetangga tiap vertex
    void AdjList(){
        for(int i=0;i<vertex.size();i++){   //perulangan sebanyak vertex digunakn untuk mengunjungi vertex 
            System.out.print("\nVertex :"+vertex.get(i).name+ " -->");
            vertex.get(i).Daftartetangga(); //Setiap vertex yang dikunjungi akan menampilkan tetangganya dengan method daftarTetangga
        }
    }
    //method bfs
    void bfs(Vertex start){
        setVisitStatus(); //sebelum melakukan pengunjungan vertex dengan method ini, maka setiap vertex diatur sebagai belum dikunjungi
        queue q=new queue(); //membuat queue baru untuk menyimpan vertex 
        q.push(start);  //menyimpan data pertama ke queue
        start.visited=true; //data yang di push akan dianggap sebagai sudah dikunjnungi (visited=true)
        while(q.tail!=null){ //perulangan untuk mengambil data dari queue
            Node current=q.pop();   //mengambil data dari queue dan menyimpannya dalam Node current
            for(int i=current.data.tetangga.size()-1;i>=0;i--){ //perulangan for untuk melakukan pproses push tiap tetangga dari vertex dalam node current
                if(!current.data.tetangga.get(i).visited){ //mengecek apakah vertex tersebut sudah dikunjungi
                    q.push(current.data.tetangga.get(i));   //jika tidak pernah dikunjungi, maka akan di masukkan ke dalam queue
                    current.data.tetangga.get(i).visited=true; //dan tiap data yang di push akan berstatus sudah di kunjungi
                }       /*sehingga tiap vertex akan masuk ke dalam queue tetap 1 kali*/
            }
            System.out.print(current.data.name+"  "); //menampilkan nama vertex yang ada dalam node current
        }
    }
    //method bfs
    void dfs(Vertex start){
        setVisitStatus();//sebelum melakukan pengunjungan vertex dengan method ini, maka setiap vertex diatur sebagai belum dikunjungi
        stack s=new stack();//membuat stack baru untuk menyimpan vertex
        s.push(start);//menyimpan data pertama ke stack
        start.visited=true;//data yang di push akan dianggap sebagai sudah dikunjnungi (visited=true)
        while(s.head!=null){ //perulangan untuk mengambil data dari stack
            Node current=s.pop();//mengambil data dari stack dan menyimpannya dalam Node current
            for(int i=0;i<current.data.tetangga.size();i++){//perulangan for untuk melakukan proses push tiap tetangga dari vertex dalam node current
                if(!current.data.tetangga.get(i).visited){//mengecek apakah vertex tersebut sudah dikunjungi
                    s.push(current.data.tetangga.get(i));//jika tidak pernah di kunjungi, maka akan di masukkan ke dalam queue
                    current.data.tetangga.get(i).visited=true;//dan tiap data yang di push akan berstatus sudah di kunjungi
                }
            }
            System.out.print(current.data.name+"  ");//menampilkan nama vertex yang ada dalam node current
        }
    }
}
class Modul4Jurnal{
    public static void main(String[]args){
        //membuat verteks dengan data sesuai dengan soal jurnal
        Vertex v1=new Vertex("Mataram");//indeks dalam arraylist-> 0
        Vertex v2=new Vertex("gerung");//indeks dalam arraylist-> 1
        Vertex v3=new Vertex("Selong");//indeks dalam arraylist-> 2
        Vertex v4=new Vertex("Praya");//indeks dalam arraylist-> 3
        Vertex v5=new Vertex("Kopang");//indeks dalam arraylist-> 4
        Vertex v6=new Vertex("Masbagik");//indeks dalam arraylist-> 5
        Vertex v7=new Vertex("Sembalun");//indeks dalam arraylist-> 6
        
        //membuat objek dari class Graph dengan nama graph
        Graph graph=new Graph();
        
        //menambah vertex yang sudah di buat ke dalam graph
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);
        graph.addVertex(v7);
        
        //menambah edge atau menambah tetangga untuk tiap vertex yang ada sesuai dengan soal jurnal
        graph.addEdge(graph.vertex.get(0),graph.vertex.get(1)); //vertex 0 bertetangga dengan vertex 1 dan berlaku sebaliknya sesuai method
        graph.addEdge(graph.vertex.get(0),graph.vertex.get(2)); //vertex 0 bertetangga dengan vertex 2 .....
        graph.addEdge(graph.vertex.get(0),graph.vertex.get(3)); //vertex 0 bertetangga dengan vertex 3 ....
        graph.addEdge(graph.vertex.get(2),graph.vertex.get(5)); //vertex 2 bertetangga dengan vertex 5 ...
        graph.addEdge(graph.vertex.get(2),graph.vertex.get(6)); //vertex 2 bertetangga dengan vertex 6 ..
        graph.addEdge(graph.vertex.get(1),graph.vertex.get(4)); //vertex 1 bertetangga dengan vertex 4 .
        graph.addEdge(graph.vertex.get(3),graph.vertex.get(4)); //vertex 3 bertetangga dengan vertex 4 ..
        graph.addEdge(graph.vertex.get(5),graph.vertex.get(6)); //vertex 5 bertetangga dengan vertex 6 ...
        graph.addEdge(graph.vertex.get(5),graph.vertex.get(4)); //vertex 5 bertetangga dengan vertex 4 ....
        
        //memanggil method adjlist untuk graph
        graph.AdjList();
        //menampilkan dan memanggil method bfs untuk grafh dimulai dari v1
        System.out.println("\n=======BFS=======");
        graph.bfs(v1);
        //menampilkan dan memanggil method dfs untuk grafh dimulai dari v1
        System.out.println("\n=======DFS=======");
        graph.dfs(v1);
    }
}
