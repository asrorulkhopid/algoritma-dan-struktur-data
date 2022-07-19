package Algodat;
import java.util.Scanner;
//deklarasi kelas pertandingan1 dengan prinsip double linked list
//dan extens ke class pertandingan sebelumnya agar beberapa method dan attribut bisa digunakan tanpa di tulis
class Pertandingan1 extends Pertandingan{  
    Pemain p1,p2,p3;    //atribut ini nantinya akan digunakan untuk menyimpan nama pemain yang akan mengganti dan diganti
    void add(Pemain pemain){      //merupakan method untuk menambahkan pemain tunggal
        Node newNode=new Node(pemain);  //membuat node baru dan memasukkan pemain kedalamnya
            if(isEmpty()){  //jika urutan pertandingan masih kosong maka pemain yang ditambahkan berada pada urutan pertama(indeks=0)
                newNode.index=0;
                head=newNode;   //sebagai yang bermain pertama 
                tail=newNode;   //sekaligus bermain terakhir-jika tidak ditambhkan lagi
            }else{  //dibawah ini adalah posisi penambahan jika urutan sudah ada
                newNode.index=tail.index+1;    // mengatur indeksnya yaitu 1 lebih besar dari indeks tail
                tail.next=newNode;  // berada pada urutan setelah tail pada urutan yang ada
                newNode.previous=tail;  //atau dengan kata lain tail adalah data sebelumnya
                tail=newNode;   //dan sekarang data tersebut adalah tail
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
    //sama halnya dengan method diatas, namun pemain disini adalah ganda
    void add(Pemain pemain1,Pemain pemain2){    //menambahkan pemain ganda kedalam urutan
        Node newNode=new Node(pemain1,pemain2); //membuat node untuk menyimpan data si pemain dan pasangannya
            if(isEmpty()){  //jika urutan pertandingan masih kosong, pemain akan bermain pertama dan terakhir
                newNode.index=0; //urutan ke-0
                head=newNode;   //akan bermain pertama
                tail=newNode;   //sekaligus yang terakhir jika pemain tidak di tambahkan lagi
            }else{ //kondisi lain jika urutan pertandingan sudah ada maka data ditampahkan pada:
                newNode.index=tail.index+1; //indeks diatas indeks data terakhir pada urutan
                tail.next=newNode;  //pemain yang ditambah kan akan bermain setelah pemain terakhir pada urutan yang sudah ada
                newNode.previous=tail;  // dan pemain terakhir tersebut adalah yang bermain sebelum pemain yang di tambhakan
                tail=newNode;   //pemain adalah data terakhir(tail)
            }
    }
    Pemain ganti(Pemain pengganti,Pemain yang_diGanti ){ //method ini berfungsi untuk mengganti pemain dengan pemain yang lain yang sudah ada dalam urutan
        current=head; //mengatur head sebagai current untuk mulai pengecekaan
        while(current!=null){   //mengecek data si pengganti dan yang di ganti dan menyimpan nya dalam node temp 
            if(current.pemain1==pengganti){ //jika pemain yang ditunjuk sekatang adalah pengganti maka:
                p1=current.pemain1;     // simpan di dalam variabel p1
            }else if(current.pemain1.pasangan==pengganti){ //begitu juga jika yang pengganti adalah pasangannya maka :
                p1=current.pemain1.pasangan;    //simpan pasangan pemain tersebut kedalam varable p1
            }else if(current.pemain1==yang_diGanti){ //dan jika yang ditunjuk sekarang ini adalah yang akan di ganti maka :
                temp1=current;  //simpan node nya kedalam node current
                p2=current.pemain1; //simpan pemain kedalam p2
                p3=current.pemain1.pasangan;    //dan simpan pasangannya ke dalam p3
            }else if(current.pemain1.pasangan==yang_diGanti){   //kemudian jika yang pasngannya yang akan di ganti maka:
                temp1=current;  //simpan nodenya kedalam node current agar lebih mudah mengganti pemain di dalamnya
                p2=current.pemain1.pasangan;    //simpan pasangan si pemain ke dalam p2
                p3=current.pemain1;         // simpan pemain kedalam p3
            }
            current=current.next;
        }
        //p1-pemain pengganti
        //p2-yang akan diganti
        //p3-pasangan pemain yang akan diganti jika ada
        if(p3!=null){   //jika yang akan diganti memiliki pasangan maka
            temp1.pemain1=p3; //dia sebagai pemain dan
            temp1.pemain1.setPasangan1(p1);     //pemain pengganti(p1) sebagai pasangannya
            //setPasangan1 tersebut untuk mengatur agar p3 memiliki pasangan p1, dan p1 tetap bisa bermain sendiri tanpa pasangan
        }else{  //jika pemain tidak memiliki pasangan maka
            temp1.pemain1=p1;   //pemain tersebut mengganti posisinya pada node tersebut
        }
        return p2;  //mengembalikan nilai berupa pemain yang di ganti
    }
}
public class Modul1_Nomer2 {    //deklarasi kelas ini dimaana di dalamnya terdapat main method
    static Scanner input=new Scanner(System.in);    //Deklarasi scanner agar bisa digunakan
    public static void main(String[] args){     //main methid tempat program yang akan di run
        Pertandingan1 Uber=new Pertandingan1(); //Membuat sebuah objek dari kelas pertandingan1 dimana di dalamnya terdapat urutan bermain
        Pemain [] pemain=new Pemain[7]; //dekalarasi array bertife Pemain
        //perulangan untuk memasukkan nama-nama si pemain
        System.out.println("Masukkan nama dan strip (-) jika tidak ada");
        for(int i=0;i<pemain.length;i++){
            System.out.print("Masukkan nama pemain   [id :"+(i+1)+"] :");
            String nama=input.nextLine();
            pemain[i]=new Pemain(nama);
            i++; //penyesuaian id dan perulangan
            System.out.print("Masukkan nama pasangan [id :"+(i+1)+"] :");
            String nama1=input.nextLine();
            if(nama1.equals("-")){
                i--;    //penyesuain id dan perulangan
                Uber.add(pemain[i]);
            }else{
                pemain[i]=new Pemain(nama1);
                Uber.add(pemain[i-1],pemain[i] );
            }
        }
        Uber.print(); //memanggil method print untuk menampilkan urutan
        System.out.println(" Ganti pemain ");
        System.out.print(" [id_Pengganti] :");
        int a=input.nextInt();  //memasukkan indeks node pemain pengganti
        System.out.print(" [id_yang di_ganti] :");
        int b=input.nextInt();  //memasukkan indeks node pemain yang akan di ganti
        Uber.ganti(pemain[a-1], pemain[b-1]); 
        Uber.print();
    }

}
