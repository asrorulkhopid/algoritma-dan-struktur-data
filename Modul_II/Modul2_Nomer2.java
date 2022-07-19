package Algodat;
import java.util.Scanner;
/*class Novel ini merupakan sebuah class yang di dalamnya menyimpan sebuah data berupa
judul buku, penunjuk ke buku selanjutnya,indeks dan node yang menunjuk ke buku sebelumnya
*/
class Novel{     //deklarasi class
    String judul;   //deklarasi variabel judul, untuk menyimpan judul
    int indeks;     //variabel indeks untuk menyimpan indeks atau nomer urut buku pada susunan
    Novel next,previous;     //menunjuk pada buku yang berada sebekum & sesudahnya
    Novel(String judul){     //sebuah konstraktor yang akan dipanggil ketika ada buku yang didaftarkan(saat instansiasi)
        this.judul=judul;   //memberi nilai pada judul tiap buku
    }
}
/*class Susunan novel ini merupakan sebuah class yang berisi novel-novel yang disusun dengan
prinsip double linkedlist di dalamnya terdapat beberapa variabel*/
class Susunan_Novel{    //deklarasi nama class
    int size;
    Novel head,tail,current;
    String temp;   //head dan tail adalah penunjuk novel pada urutan pertama dan terakhir.
    int index=0,i;     //indeks adalah variabel bantu untuk menyimpan data pada beberapa method yang ada
    void add(String judul){      //merupakan method untuk menambahkan data pada data di bagian tail
        size++;
        Novel novel=new Novel(judul);  //membuat novel baru dengan judul yang sudah di tentukan
            //berikut beberapa keadaan yang perlu di tinjau dalam menambah data
            if(head==null){  // keadaan apabila belm ada novel dalam urutan maka:
                head=novel;   // novel tersebut akan berada pada urutan pertama(head)
                tail=novel;   // sekaligus berada pada urutan terakhir(tail)
                novel.indeks=0; //indeks dari novel tersebut adalah 0 sesuai posisi/urutannya
            }else{  //  keadaan apabila sudah terdapat beberapa novel dalam urutan
                tail.next=novel;  // mengatur posisis novel sehingga berada pada urutan setelah tail pada urutan yang ada
                novel.indeks=tail.indeks+1; // mengatur nilai indeks sesuai posisi/urutannya
                novel.previous=tail;  //mengatur node yang berada sebelum si novel yaitu tail
                tail=novel;   //    selnjutnya novel yang di tambahkan akan menempati posisi sebagai tail
            }
    }
    /*method sequential_search di bawah ini digunakan untuk mencari data yang dicari yaitu dalam kasus ini 
    adalah judul buku. adapun dalam prosesnya, method ini akan mengecek satu per satu data yang ada dalam linkedlist*/
    int sequential_search(String judul){    //deklarasi mana method dan parameternya
        current=head;   //menyimpan node yang berada pada head ke dalam current bertujuan sebagai posisi awal pengecekan
        while(current.next!=null && !judul.equalsIgnoreCase(current.judul)){    // jika data yang dicari tidak sesuai dengan buku yang di cek
            current=current.next;                                               // maka akan dilanjutkan ke buku selanjutnya
        }                                                                       // sampai buku terakhir 
        if(current.judul.equalsIgnoreCase(judul)){  //Selanjutnya akan dilakukakn pengecekan apabila judul yang dicari sama dengan judul buku
            return current.indeks;                  // yang artinya data berhasil ditemukan dan method ini akan mengembalikan nilai indeks buku tersebut
        }else{          
            return -1;          // keadaan lain jika buku tidak ditemukan maka method akan mengembalikan nilai -1
        }
    }
    /*method binary_search dibawah ini akan digunakan untuk mencari data. dimana dalam prosesnya data yang dicari akan dicocokkan dengan data
      tengah dalam linkedlist. jika tidak ditemukan akan dicari pada pertengahan yang lain begitu seterusnya sampai data ditemukan
    */
    int binary_search(String judul){    // deklarasi method dan variabelnya
        int start=0;
        int end=size-1;
        int tengah = (end-start) / 2;    // berfungsi untuk menyimpan indeks tengah linked list
        boolean ketemu = false;         //  variable ini akan digunakan untuk mengecek apakah data ditemukan      
        //while berikut ini untuk mengecek data2 tertentu
        while (start <= end && !ketemu) {
            if (judul.equalsIgnoreCase(getNovel(tengah).judul)) { //jika data sama dengan data yang dicek sekarang maka data ditemukan
                ketemu = true;              //menandakan bahwa data ditemukan sekaligus penghentian while
            } else {
                if (getNovel(tengah).judul.compareToIgnoreCase(judul) < 0) {  //namun jika data tidak ditemukan maka akan dicek pada pertengahn berikutnya
                    start=tengah+1;//indeks pertengahan pada setengah kedua 
                } else {
                    end=tengah-1;   //indeks pertengahan pada setengah pertama
                }
            }
            tengah=(end+start)/2;
        }
//        berikut adalah nilai yang dikemblikan oleh method ini
        if(ketemu){
            return tengah;
        }else{
            return -1;
        }
    }
    //method di bawah ini untuk menampilkan data satu per satu mulai dari head
    void print(){  
        current=head;
        if(head==null){  // jika tidak ada data dalam urutan
            System.out.println("= =KOSONG= =");
        }else{  //jika terdapat data pada urutan maka
            while(current!=null){   //maka dengan perulangan while data akan di tampilkan satu-satu sesuai urutan
                System.out.println("["+current.indeks+"] "+current.judul);    //menampilakan indeks/urutan dan judul buku
                current=current.next;
            }
        }
    }
    //method getNode() ini untuk mengambil novel tertentu sesuai ineks
    Novel getNovel(int indeks){
        current=head;
        while(current!=null && current.indeks!=indeks){
            current=current.next;
        }
        return current;
    }
    //      method dibawah ini digunakan untuk mwngatur indeks2 data2 berikutnya-digunakan ketika penyisipan dan remove data
    //    pada posisi tertentu bersifat private karena bisa dilakukan set indeks dari dalam kelas ini saja
    private void setNextIndex(Novel node){
        current=node;
        while(current.next!=null){
            current.next.indeks=current.indeks+1;
            current=current.next;
        }
    }
    //mengurutkan novel sesuai dengan abjad judulnya
    //dibutuhkan proses sorting ini untuk mengurutkan novel sebelum melakukan binary_search
    void bubble_sort(){
        boolean cek;
        do{
            cek=false;
            for(int i=0; i<tail.indeks;i++){
                if(getNovel(i).judul.compareToIgnoreCase(getNovel(i+1).judul)>0){
                    temp=getNovel(i+1).judul;
                    getNovel(i+1).judul=getNovel(i).judul;
                    getNovel(i).judul=temp;
                    cek=true;
                }
            }
        }while(cek);    //mengecek apakah masih terjadi pertukaran posisi
    }
}
public class Modul2_Nomer2 {
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        Susunan_Novel perpus=new Susunan_Novel();   //membuat objek yang berisi data yang bersusun
        //dibawah ini merupakan code untuk menambahkan buku kedalam urutan
        perpus.add("Suratan si Tangan Kecil");
        perpus.add("Dibalik Dua Hati");
        perpus.add("Air Mata Bahagia");
        perpus.add("Apakah Ia Mengerti?");
        perpus.add("Senyuman Pujaan");
        perpus.add("Seorang Bidadari");
        perpus.add("Cakrawala Petualang");
        perpus.add("Kodingan Cinta");
        perpus.add("Lihatlah yang di Bawah");
        perpus.add("Menyerah atau Kalah?");
        perpus.add("Berjuang Walau Tertebas Pedang");
        perpus.add("Teman Tak Tergantikan");
        perpus.add("Lawakan Pinggiran Kota");
        perpus.add("Samudera Perjuangan");
        perpus.add("Mahasiswa Kocak");
        perpus.add("Jangan Tersandung Kerikil");
        perpus.add("Butiran Semangat dan Embun Kemenangan");
        perpus.print();
        System.out.println("========Sequential Search========");
        System.out.println("Masukkan judul buku yang anda cari :");
        String cari = input.nextLine();             //memasukkan judul buku yang dicari
        //dibawah ini merupakan proses pencarian dengan sequential search dan pengecekan hasil pencarian 
        if(perpus.sequential_search(cari)!=-1){
            System.out.println("Buku berjudul "+cari+" ada pada urutan ke-"+perpus.sequential_search(cari));
        }else{
            System.out.println("Buku berjudul "+cari+" tidak ditemukan");
        }
        System.out.println("========Binary Search========");
        perpus.bubble_sort();
        perpus.print();
        System.out.println("Masukkan judul buku yang anda cari :");

        cari = input.nextLine();
        //dibawah ini merupakan proses pencarian dengan binary search dan pengecekan hasil pencarian 
        if(perpus.binary_search(cari)!=-1){
            System.out.println("Buku berjudul "+cari+" ada pada urutan ke-"+perpus.binary_search(cari));
        }else{
            System.out.println("Buku berjudul "+cari+" tidak ditemukan");
        }
    }
}
