package Algodat;
//class noode 2 ini merupakan sebuah class yang di dalamnya terdapat anggota,node nest, previoius dan indeks
class Node2{     //deklarasi class Node untuk mendeskripsikan hal-hal yang berkaitan dengan node
    Anggota_tim anggota;     //salah satu attribut Node yaitu anggota_tim yang berisi data tiap orang
    Node2 next;      //node next adalh sebuah node yang berfungsi untuk menunjuk node setelahnya
    Node2 previous;  //node previous adalah sebuah node untuk menunjuk node sebelumnya
    int indeks;
    Node2(Anggota_tim anggota){      //sebuah konstraktor yang mengatur nilai dari si anggota tim
        this.anggota=anggota;
    }
}
//class anggota tim ini menyimpan data si pemain berupa nama tinggi dan kekuaran
class Anggota_tim{
    String nama;    //deklarasi variable nama menandakan bahwa si anggota mempunyai nama
    int power;      //deklarasi variable power menandakan bahwa si anggota mempunyai power
    int tinggi;     ////deklarasi variable tinggi menandakan bahwa si anggota mempunyai tinggi
    Anggota_tim(String nama,int tinggi, int power){ //sebuah konstraktor yang akan di panggil saat pembuatan objek
        this.nama=nama;                             // dimana String, dan dua nilai yang di inputkan diatur sebagai nama, tinggi dan power
        this.tinggi=tinggi;
        this.power=power;
    }
}
//kelas tarik tambang ini berisi anggota dan susunan posisinya. dimana susunan posisinya diatur dengan menggunakan konsep double linkedlist
class tarik_tambang{    // deklarasi nama kelas yang di dalamnya disimpan atribut dan method
    Node2 head,tail,current;   //beberapa node yang dibutuhkan : Head->data pertama, tail-> data terakhir, current->data tertentu,temp->menyimpan
    Anggota_tim temp;
    void add(Anggota_tim pemain){      //merupakan method untuk menambahkan data anggota tim pada data di bagian tail
        Node2 baru=new Node2(pemain);  //membuat node baru yang datanya berupa pemain yang sudah di tentukan
            //berikut beberapa keadaan yang perlu di tinjau dalam menambah data
            if(head==null){  // keadaan apabila belm ada node dalam urutan maka:
                head=baru;   // node tersebut akan berada pada urutan pertama(head)
                tail=baru;   // sekaligus berada pada urutan terakhir(tail)
                baru.indeks=0; //indeks dari node tersebut adalah 0 sesuai posisi/urutannya
            }else{  //  keadaan apabila sudah terdapat beberapa node dalam urutan
                tail.next=baru;  // mengatur posisi node sehingga berada pada urutan setelah tail pada urutan yang ada
                baru.indeks=tail.indeks+1; // mengatur nilai indeks sesuai posisi/urutannya
                baru.previous=tail;  //mengatur node yang berada sebelum si node yaitu tail
                tail=baru;   //    selnjutnya node yang di tambahkan akan menempati posisi sebagai tail
            }
    }
    void print(){   //method untuk menampilkan nama si pemain secara berurutan
        current=head;
        if(head==null){  // jika tidak ada data/node dalam urutan
            System.out.println("= =KOSONG= =");
        }else{  //jika terdapat data pada urutan maka
            while(current!=null){   //maka dengan perulangan while data akan di tampilkan satu-satu sesuai urutan
                //adapun data yang akan ditampilkan adalah nama, tinggi, dan power tiap anggota tim, seperti pada code berikut
                System.out.print(current.anggota.nama+" ( "+current.anggota.tinggi+"cm , "+current.anggota.power+"p ) <-- ");
                current=current.next;
            }
        }
    }
    //method berikut untuk memperbaiki kembali urutan indeks pada linked list
    private void setNextIndex(Node2 node){
        current=node;
        while(current.next!=null){
            current.next.indeks=current.indeks+1;
            current=current.next;
        }
    }
    //method berikut untuk mengambil nilai node dengan indeks tertentu
    Node2 getNode(int indeks){
        current=head;
        //perulangan berikut  untuk mengecek dan mencari posisi indeks yang dimaksud
        while(current!=null && current.indeks!=indeks){
            current=current.next;
        }
        //jika data sudah ditemukan method akan mengembalikan nilai tersebut sebagai output
        return current;
    }
    //method berikut untuk melakukan pengurutan data berdasarkan urutan abjad(absen). dengan menerapkan metode bubble short
    void bubble_sort(){
        boolean cek;
//        pengecekan akan terus dilakukan selana cek bernilai true
//cek akan bernilai true apabila masih terdapat data yang belum berurutan
        int j=0;
        do{
            cek=false;
//           melakukan perulangan dari data pertama sampai data sebelum terakhir
            for(int i=0; i<tail.indeks;i++){
//                membandingkan data ke i dengan data ke j sampai data terakhir
//               jika data pertama lebih besar dari data berikutnya maka tukar posisi (dengan remove dan add)
                if(getNode(i).anggota.nama.compareTo(getNode(i+1).anggota.nama)>0){
                    temp=getNode(i+1).anggota;
                    getNode(i+1).anggota=getNode(i).anggota;
                    getNode(i).anggota=temp;
                    cek=true;
                }
            }
        }while(cek);
    }
//method berikut digunakan untuk melakukan pengurutan berdasarkan tingi badan dengan metode selection sort
//dimana akan dicari data terkecil kemudian diletakkan di awal
//kemudian dilanjutkan dengan data terkecil berikutya sampai data terkecil terakhir
    void selection_sort(){
        int k=0;
        for (int i = 0; i <=tail.indeks; i++){  
            int index = i;  //meyimpan nilai i ke dalam indeks sebagai anggapan bahwa data ke i adalah data terkecil
            //kemudian dengan menggunakan pengulangan berikut, data ke i akan dibandingkan dengan data berikutnya
            for (int j = i + 1; j <= tail.indeks; j++){  
                //jika data berikutnya lebih kecil dari data terkecil sebelumnya
                if (getNode(j).anggota.tinggi < getNode(index).anggota.tinggi){ 
                    //nilai terkecil akan diupdate
                    index = j; 
                }  
            }  
//            setelah data terkecil ditemukan, akan disisipkan pda posisi yang sesuai
            if(index!=i){
                temp=getNode(index).anggota;
                getNode(index).anggota=getNode(i).anggota;
                getNode(i).anggota=temp;
            }
        }  
    }
//insertion sort berikut ini untuk mengurutkan data berdasarkan power
    //data akan ditinjau satu per satu dari data ke 2 sampai seterusnya dan tiap data akan ditempatkan pada posisi yang seharusnya
    void insertion_sort(){
        int k=0;
        //perulangan berikut digunakan untuk mengambil data satu per satu dari  data kedua sampai data terakhir
        for(int i=1;i<=tail.indeks;i++){
            //perulangan dibawah digunakan untuk menempatkan data pada posisi yang seharusnya
            for(int j=i;j>0;j--){
                if(getNode(j).anggota.power<getNode(j-1).anggota.power){
                    temp=getNode(j).anggota;
                    getNode(j).anggota=getNode(j-1).anggota;
                    getNode(j-1).anggota=temp;
                }else{
                    j=0; //menghentikan pengecekan posisi karena sudah pada posisi yang benar
                }
            }
        }
    }
}
public class Modul2_nomer1 {
    public static void main(String[] args){
        //berikut code untuk membuat objek dari anggota tim
        //dengan inputan nama, tinggi dan power tertentu
        Anggota_tim a1=new Anggota_tim("Mukhlis",180,50);
        Anggota_tim a2=new Anggota_tim("Anto",176,40);
        Anggota_tim a3=new Anggota_tim("Kaka",155,33); 
        Anggota_tim a4=new Anggota_tim("Joko",162,39); 
        Anggota_tim a5=new Anggota_tim("Faris",167,41); 
        Anggota_tim a6=new Anggota_tim("Kiki",158,35); 
        Anggota_tim a7=new Anggota_tim("Dika",179,45); 
        Anggota_tim a8=new Anggota_tim("Brian",185,20); 
        //memuat objek dari class tarik_tambang untuk menyimpan data anggota tim
        tarik_tambang tim_A=new tarik_tambang();
        //memasukkan data tiap anggota tim ke dalam urutan
        tim_A.add(a1);
        tim_A.add(a2);
        tim_A.add(a3);
        tim_A.add(a4);
        tim_A.add(a5);
        tim_A.add(a6);
        tim_A.add(a7);
        tim_A.add(a8);
        System.out.println("\nPemain Tim A");
        //menapilkan urutan
        tim_A.print();
        System.out.println("\n\n---------------------");
        System.out.println("         Ronde 1      ");
        System.out.println("---------------------");
        System.out.println("Berdasarkan Absen");     
        //melakukan pengurutan berdasarkan absen dengan method yang sudah dibuat
        System.out.println("\nHasil Akhir Berdasarkan Absen : "); 
        tim_A.bubble_sort();
        //menampilkan urutan setelah dilakukakn sorting
        tim_A.print();
        System.out.println("\n\n---------------------");
        System.out.println("         Ronde 2      ");
        System.out.println("---------------------");
        //melakukan pengurutan berdasarkan tinggi badan dengan method yang sudah dibuat
        System.out.println("Hasil Akhir Berdasarkan Tinggi Badan : ");
        tim_A.selection_sort(); 
        System.out.println("\nBerdasarkan Tinggi Badan");
       //menampilkan urutan setelah dilakukakn sorting
        tim_A.print();
        System.out.println("\n\n---------------------");
        System.out.println("         Ronde 3      ");
        System.out.println("---------------------");
        System.out.println("Berdasarkan Kekuatan");        
        //melakukan pengurutan berdasarkan power dengan method yang sudah dibuat
        tim_A.insertion_sort();
        //menampilkan urutab setelah di sorting
        System.out.println("\nHasil Akhir Berdasarkan Power : ");
        tim_A.print();
    }
}
