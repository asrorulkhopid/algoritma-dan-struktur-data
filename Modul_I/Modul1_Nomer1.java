package Modul_I;   //nama package tempat file ini berada
import java.util.Scanner;   //bagian ini berfungsi ini memasukkan class scanner dari library agar bisa digunakan
//class pemain
class Pemain{       //bagian ini adalah bagian untuk mendeklarasikan class pemain yang berisi hal2 yang berkaitan dengan pemain
    String name;        //ini adalah deklarasi variable name yang merupakan attribute dari class pemain
    Pemain pasangan;        //ini adalah deklarasi variabel pasangan untuk menyimpan data dari pemain pasangan-nya
    Pemain(String name){    //merupakan method konstraktor dari class pemain yang akan di panggil otomatis saat pembuatan objek
        this.name=name;     //menyimpan nilai variabel name yang ada di parameter ke dalam attribut class pemain yaitu "name"
    }
    void setPasangan(Pemain p){     //deklarasi method untuk mengatur pasangan dari pemain -> saling berpasangan
        pasangan=p;     //menyimpan nilai p ke dalam pasangan sehingga p adalah pasangan dari "si objek"
        p.pasangan=this;    //mengatur hal sebaliknya agar tebaca bahwa "si objek" adalah pasangan dari p 
    }
    void setPasangan1(Pemain p){    //sama dengan method sebelumnya - tetapi hubungan pasangannya searah
        pasangan=p;
    }                   //contoh    A adalah pasangan dari B. tapi B bukan pasangan dari A 
}
//class node
class Node{     //deklarasi class Node untuk mendeskripsikan hal-hal yang berkaitan dengan node
    Pemain pemain1;     //salah satu attribut Node yaitu pemain yang berisi data pemain
    int index;      // deklarasi variable index tempat menyimpan indeks untuk tiap node
    Node next;      //node next adalh sebuah node yang berfungsi untuk menunjuk node setelahnya
    Node previous;  //node previous adalah sebuah node untuk menunjuk node sebelumnya
    Node(Pemain p1,Pemain p2){      //sebuah konstraktor yang mengatur nilai dari pemain dan pasangannya (ganda)
        pemain1=p1;
        pemain1.setPasangan(p2);
    }
    Node(Pemain p1){    //konstraktor untuk mengatur nilai pemain tanpa pasangan (tunggal putra/putri)
        pemain1=p1;
    }
    void print(){   //merupakan sebuah method untuk menampilkan indeks node dan nama si pemain
        if(pemain1.pasangan==null){     //baris ini dan berikutnya agar jika sang pemain tidak memiliki pasangan maka
            System.out.println(index+1+". "+pemain1.name);  //yang di tampilkan adalah namanya saja
        }else{  //keadaanlain jika si pemain memiliki pasangan maka :
            System.out.println(index+1+". "+pemain1.name+" & "+pemain1.pasangan.name);  //yang di tampilkan adalah nama si pemain dan pasangannya
        }
    }
}
// class pertandingan
//class ini untuk menyimpan urutan si pemain. urutan dalam kelas ini menggunakan urutan seperti pada linkedlist
class Pertandingan{ //deklarasi kelas
    Node head;  //deklarasi variable head untuk menunjuk pada pemain yang akan bermain pertama
    Node tail;  //deklarasi variabel tail untuk menunujuk pada pemain yang akan bermain terakhir
    Node current;   //varoiabel node yang berfungsi untuk menunjuk node tertentu nantinya
    int size;   //variabel untuk menyimpan ukuran urutan /banyaknya data
    Node temp1,temp2,temp3; //variabel untuk menyimpan node tertentu untuk digunakan ataupun di buang
    public boolean isEmpty(){   //method untuk mengecek apakah linkedlist/pertandingan kosong
        if(head==null){ 
            return true;
        }else{
            return false;
        }
                //jika head bernilai null method ini akan mengembalikan nilai true dan sebaliknya
    }
    void add(Pemain pemain){    //method add berfungsi untuk menambah pemain tunggal ke dalam urutan pertandingan-urutan terkahir
        Node newNode=new Node(pemain);  //membuat node yang isinya adalah pemain yang akan ditambhkan
            if(isEmpty()){  //jika pertandingan masih kosong maka si pemain akan bertanding pertama
                newNode.index=0;    //mengatur indeks menjadi 0 yaitu urutan si pemain bermain
                head=newNode;       //pertama bermain
                tail=newNode;       //sekaligus terakhir
            }else{  //untuk keadaan lain jika urutan sudah terisi maka pemain akan bermain terakhir
                newNode.index=tail.index+1; //yaitu pada urutan setelah yang teakhir pada urutan yang ada 
                tail.next=newNode;
                tail=newNode;
            }
    }
    void add(Pemain pemain1,Pemain pemain2){    //method add berfungsi untuk menambah pemain ganda ke dalam urutan pertandingan-urutan terkahir
        Node newNode=new Node(pemain1,pemain2); //membuat node untuk menyimpan nama si pemain dan pasangannya
            if(isEmpty()){  // jika urutan pertandingan masih kosong maka pemain yang ditambahkan akan bermain pertama
                newNode.index=0;    
                head=newNode;
                tail=newNode;
            }else{  //jika urutan pertandingan sudah terisi maka pemain yang ditambahkan akan bermain terakhir
                newNode.index=tail.index+1;
                tail.next=newNode;  // yaitu setelah si pemain terkahir pada urutan
                tail=newNode;
            }
    }
    void tukar(int indek1,int indek2){  //Method yang berfungsi untuk menukan urutan bermain pada pertandingan
        current=head;
        // statement while berikut untuk mencari urutan yang akan di tukar
        while(current.next.index!=indek2){  //perulangan selama indeks yang dicari belun ketemu
            if(current.next.index==indek1){
                temp1=current;   // data sebelum node yang akan ditukar di simpan dalam temp
            }
            current=current.next;   //mengupdate node yang di cek 
        }
        temp2=current;  //  Menyimpan node yang akan di tukar dengan node yang sebelumnya telah di simpan
        //beberapa keadaan yang muncul dari algoritma ini
        //karena terdapat keadaan khusus yang tidak terselesaikan olrh algoritma
        if(indek1==0){  // jika indeks yang ditukar adalah nol
            if(indek2==1){  // dengan indeks lain nya yaitu 1 
                // mengatur indeks urutan bermain 
                temp2.index=indek2;
                temp2.next.index=indek1;
                //menukar posisi head dengan data berikutnya
                temp3=temp2.next;
                temp2.next=temp2.next.next;
                temp3.next=temp2;
                head=temp3;
            }else{  //indeks=0 dan indeks lainnya selain 0&1
                //code berikut untuk mengatur indeks akar tetap pada urutan
                temp2.next.index=indek1;
                head.index=indek2;
                //di bawah ini code untuk menukar posisi atau mengganti nilai next
                temp1=temp2.next.next;
                temp2.next.next=head.next;
                temp3=head;
                head=temp2.next;
                temp2.next=temp3;
                temp3.next=temp1;
            }
        }else{
            //untuk mengatur indeks urutan permainan
            temp1.next.index=indek2;
            temp2.next.index=indek1;
            //menukar posisi akar sesuai dengan indeks yang ada
            //dengan mengganti nilai next
            temp3=temp1.next.next;
            temp1.next.next=temp2.next.next;
            temp2.next.next=temp3;
            temp3=temp1.next;
            temp1.next=temp2.next;
            temp2.next=temp3;
        }
    }
    void print(){   //method untuk menampilkan nama si pemain secara berurutan
        current=head;
        if(isEmpty()){  // jika tidak ada data dalam urutan
            System.out.println("= =KOSONG= =");
        }else{  //jika terdapat data pada urutan maka
            while(current!=null){   //maka dengan perulangan while data akan di tampilkan satu-satu sesuai urutan
                current.print();    //memanggil method print untek menampilkan nama pemain yang ada dalam node
                current=current.next;
            }
        }
    }
}
public class Modul1_Nomer1 {
    static Scanner input=new Scanner(System.in);    //deklarasi scanner
    public static void main(String[] args){ //main method-tempat program yang akan di run
        Pertandingan thomas=new Pertandingan(); //membuat sebuah objek baru yang bernama thomas yang akan menyipan permainan dan urutannya
        Pemain [] pemain=new Pemain[7]; //deklarasi variabel objek dari kelas pemain yang untuk menampung data pemain
        for(int i=0;i<pemain.length;i++){      //sebuah perulangan for yang di dalamnya terdapat perintah untuk memasukkan nama pemain dan pasangannya 
            System.out.println("Masukkan nama dan strip (-) jika tidak ada");
            System.out.print("Masukkan nama pemain   :");
            String nama=input.nextLine();   //memasukkan nama pemain secara dinamis
            pemain[i]=new Pemain(nama); //membuat objek dari kelas pemain sekaligus memberi nilai
            System.out.print("Masukkan nama pasangan :");
            String nama1=input.nextLine();
            System.out.println("");
            if(nama1.equals("-")){
                thomas.add(pemain[i]);  //menambah pemain tunggal ke dalam urutan
            }else{
                i++;
                pemain[i]=new Pemain(nama1);
                thomas.add(pemain[i-1],pemain[i] ); //menambah pemain tunggal ke dalam urutan
            }
        }
        thomas.print();     //memanggil method print() untuk menampilkan urutan yang sudah terbentuk
        System.out.println(" Tukar Posisi[Urutan] (A <=> B)");
        System.out.print(" [Urutan A] :");
        int a=input.nextInt();      //memasukkan indeks node yang akan di tukar
        System.out.print(" [Urutan B] :");
        int b=input.nextInt();      // memasukkan indeks node yang akan di tukar dengan indeks sebelumnya
        thomas.tukar(a-1, b-1);     //memanggil method tukar untuk menukar urutan pemain.
        thomas.print();
    }
}