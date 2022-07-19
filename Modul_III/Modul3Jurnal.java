package Algodat;
import java.util.Scanner;
/*class NodeTree di bawah ini merupakan Node untuk menyimpan
data-data yang berkaitan dengan node. node merupakan sebuah data
yang mewakili data seseorang(anggota keluarga)
*/
class NodeTree {//mendeklarasikan kelas tree
    int posisi; //variabel posisi ini berfungsi untuk menentukan posisi saat memasukkan data
    String name;    //merupakan sebuah variabel yang menyimpan nama si anggota keluarga(Node)
    int level;  //level ini menyimpan nilai level, level merupakan urutan/ berapa pada keturunan ke berapa si Node berada
    NodeTree leftChild,rightChild,parent;   //setiap node memiliki kemungkinan untuk memiliki leftChild
                                            //rightChild dan parent, tapi sifatnya tidak harus artinya bisa saja
                                            //salah satu dari ketiga variabel tersebut bernilai null
    //berikut ini merupakan sebuah konstraktor untuk kelas NodeTree
    NodeTree (String name,int posisi){ //ketika pembuat objek NodeTree harus adanama dan posisinya dalam silsilah
        this.posisi=posisi; //menyimpan posisi yang di input sebagai posisi si objek
        this.name = name; //menyimpan nama yang di berikan sebagai nama untuk si objek
        parent=null; // default parent adalah null dan akan di update nanti ketika di masukkan dalam silsilah
        level = 1; //default level adalah satu dan akan di update ketika dimasukkan kedalam silsilah sesuai dengan tingkatan
    }
}
/*class binary tree berikut merupakan sebuah class yang menyimpan urutan silsilah dan hubungan antar anggota keluarga
class ini bisa di ibaratkan sebagai sebuah format pendataan keluarga besar dimana kita bisa menambahkan data
dan mencari hubungan satu sama lain, dan bisa juga melihat semua anggota keluarga
*/
class Binary_Tree{
    NodeTree root;  //merupakan sebuah variabel yang menyimpan data paling atas(paling tua dalam silsilah)
    private int total; //variabel untuk menyimpan total anggota keluarga
    private String hubungan; //hubungan ini merupakan sebuah variabel untuk menyimpan hubungan
                             //satu sama lain, digunakan dalam proses pencaarian hubungan sehingga nilai nya berubah-ubah tergantung
                             //data yang di inputkan nantinya
    //method add di bawah ini merupakan sebuah method untuk menambahkan sebuah anggota keluarga kedalam silsilah
    public void add(NodeTree node){ //deklarasi nama method
        total++; //setiap terjadi penambahan anggota keluarrga, total anggota keluarga bertambah 1
        if(root==null){ //jika belum ada anggota keluarga yang terdaftar, maka data yang dimasukkan akan disimpan sebagai root
            root=node; 
        }else{ //jika ternyata sudah ada yang terdaftar maka data akan dimasukkan dengan method insert
            insert(root, node);
        }
    }
    //method inssert dibawah ini digunakan untuk memasukkan si anggota keluarga ke dalam silsilah sesuai dengan posisi
    //yang sudah ditentukan dalam pembuat NodeTree
    //berdasarkan algoritma method inilah kita sebagai pengguna(yang menambhkan data ) bisa menentukan posisi si node
    private void insert(NodeTree parent, NodeTree child){//deklarasi nama class dan parameter
        child.level++;//ketika dilakukan proses proses insert, level node yang di insert akan bertambah satu
        //kondisi di bawah ini jika posisi si anak lebih kecil dari posisi parent
        //maka anak tersebut akan di masukkan ke kiri
        if(child.posisi<parent.posisi){
            //ketika sudah masuk ke kiri akan dilakukakn pengecekan apakah posisi tersebut kosong
            //jika kosong maka ia akan di letakkan di posisi tersebut
            if(parent.leftChild==null){
                parent.leftChild=child;
                child.parent=parent;
            //jika posisi tersebut tidak kosong maka akan dilakukan proses pencarian posisi(insert)
            }else{
                insert(parent.leftChild, child);
            }
        //kondisi di bawah ini jika posisi si anak lebih besar dari posisi parent
        //maka anak tersebut akan di masukkan ke kanan
        }else{
            //ketika sudah masuk ke kanan akan dilakukakn pengecekan apakah posisi tersebut kosong
            //jika kosong maka ia akan di letakkan di posisi tersebut
            if(parent.rightChild==null){
                parent.rightChild=child;
                child.parent=parent;
            //jika posisi tersebut tidak kosong maka akan dilakukan proses pencarian posisi(insert)
            }else{
                insert(parent.rightChild,child);
            }
        }
    }
    NodeTree sv;    //sv ini hanyalah variabel bantu yang digunakan untuk menyimpan data node dalam proses searching
    //method ini adalah method untuk mencari node tertentu berdasarkan nama dan akan mengembalikan nilai NodeTree
    public NodeTree searchNode(NodeTree root,String name) {
        if(root !=  null) { //proses dibawah ini akan dilakukan jika root tidak null
            searchNode(root.leftChild,name);    //melakukan pencarian di anak bagian kiri secara rekursif sampai root bernilai null
            searchNode(root.rightChild,name);   //melakukan pencarian di anak bagian kanan secara rekursif sampai root bernilai null
            // untuk setiap node yang dikunjungi akan dilakukan pengecekan apakah nama dari node sama dengan nama yang di cari
            if(root.name.equalsIgnoreCase(name)){        //jika nama nya sama maka node akan di simpan
                sv=root;    //menyimpan node
            }
        }
        return sv;  //mengembalikan nilai berupa node yang di cari
    }
    //method di bawah ini adalah method untuk menampilkan semua anggota keluarga yang terdaftar
    //algoritma dari method ini sama dengan methode searchNode. hanya saja setiap node yang dikunjungi akan di tampilkan namanya
    public void anggotaKeluarga(NodeTree root) {
        if(root !=  null) {//proses dibawah ini akan dilakukan jika root tidak null
            anggotaKeluarga(root.leftChild); //melakukan pencarian di anak bagian kiri secara rekursif sampai root bernilai null
            anggotaKeluarga(root.rightChild);   //melakukan pencarian di anak bagian kanan secara rekursif sampai root bernilai null
            System.out.println(" -- "+root.name+" -- ");    //setiap node yang di cek akan di tampilkan namanya
        }
    }
    //method di bawah ini merupakan sebuah method untuk menentukan hubungan antar dua anggota keluarga
    void Hubungan(String nama1,String nama2){
        sv=null; //method ini mengatur nilai dari sv ke null-->mereset data yang tersimpan sebelumnya
        boolean hub=false;
        NodeTree A=searchNode(root,nama1); //mecari data untuk nama1 yang dimasukkan dan menyimpannya dalam NodeTree A
        NodeTree B=searchNode(root,nama2); //mecari data untuk nama2 yang dimasukkan dan menyimpannya dalam NodeTree B
        int ortuB=0;    //variabel ini untuk menyimpan berapa tingkat ke atas orang tua kedua node sama dan akan digunakan untuk penentuan hubungan 
        
        //akan dilakukan pengecekan terlebih dahulu apakah nama yang dimasukkan ada dalam daftar, jika tidak maka tidak perlu melakukan pencarian hubungan
        if(A!=null && B!=null){
            if(A.level>B.level){ //melakukan pengecekan apakah level A>B, jika iya maka akan dilakukan tukar nilai
                NodeTree tukar=A;
                A=B;
                B=tukar;
                //penukaran nilai tersebut bertujuan untuk mengurangi kemungkinan kemungknn yang ada dalam penentuan hubungan
                //dengan adanya penukaran tersebut dapat di pastikan A lebih tua dari B
                //yang ditukar adalah  A dan B yang merupakan sebuah variabel bantu sehingga tidak berdampak pada silsilah
            }
            NodeTree currentA=A; //menyimpan A kedalam variabel bantu current
            NodeTree currentB=B;    //menyimpan B kedalam variabel bantu current
            //perulangan while di bawah ini untuk mencari kesamaan parent atau nenk moyang
            while(currentA!=null && hub==false){
                currentB=B;
                ortuB=0;
                //akan ditelusuri satu persatu parent dari B sampai nama parent nya sama dengan A atau parent-nya 
                while (currentB!=null && hub==false){
                    if(currentA.name.equalsIgnoreCase(currentB.name)){
                        hub=true;   //jika namnya sudah sama hub akan bernilai true bertujuan untuk menghentikan penelusuran/perulangan
                    }
                    if(hub==false){
                            currentB=currentB.parent;// jika belum ketemu maka akan di cek lagi parent yang lebih atas
                            ortuB++;    //ortuB akan di increment tiap kali data tidak di temukan
                    }
                }
                currentA=currentA.parent;//jika nama A tidak sama dengan B dan parent2nya maka akan di cek lagi dengan parent2 si A
            }
            int selisih=A.level-B.level; //selisih ini merupakan selisih level si A
            //berikut berapa kemungkinan hubungan yang terjadi dari data sebanyak 15(full Binary_tree )
            //yang pertama di tinjau adalah urutan/level
            //keadaan pertama jika posisnya sejajar(selisih==0)
            if(selisih==0){
                if(ortuB==1){
                    hubungan="Saudara"; //jika satu tingkat diatas mereka bernilai sama maka maka hubungan meraka adalah saudara
                                        //memilki bapak yang sama
                }else if(ortuB==2){
                    hubungan="Misan";//jika dua tingkat diatas mereka bernilai sama maka maka hubungan meraka adalah saudara jauh
                                                     //memiliki kakek yang sama 
                }else if(ortuB==3){
                    hubungan="Sepupu";//jika tiga tingkat diatas mereka bernilai sama maka maka hubungan meraka adalah saudara dan lebih jauh dari yang sebelumnya
                                                     //memiliki buyut yang sama
                }
            //keadaan keduaa jika posisi A satu tingkat diatas B(selisih==-1)-->A lebih tua dari A
            }else if(selisih==-1){
                if(ortuB==1){
                    hubungan="Anak"; //jika satu tingkat diatas B bernilai sama(sementara A satu tingkat diatas B) maka B adalah Anak
                }else if(ortuB==2){
                    hubungan="Keponakan"; //jika dua tingkat diatas B bernilai sama(sementara A satu tingkat diatas B) maka B adalah keponakan
                }else if(ortuB==3){
                    hubungan="Keponakan Jauh";//jika tiga tingkat diatas B bernilai sama(sementara A satu tingkat diatas B) maka B adalah keponakan jauh
                }
            //keadaan ketiga jika posisi A dua tingkat diatas B(selisih==-2)-->A lebih tua dari B
            }else if(selisih==-2){
                if(ortuB==2){
                    hubungan="cucu";//jika dua tingkat diatas B bernilai sama(sementara A dua tingkat diatas B) maka B adalah cucu
                }else if(ortuB==3){
                    hubungan="cucu jauh";//jika tiga tingkat diatas B bernilai sama(sementara A dua tingkat diatas B) maka B adalah cucu jauh
                }
            //keadaan keempat jika posisi A tiga tingkat diatas B(selisih==-3)-->A lebih tua dari B
            }else if(selisih==-3){
                hubungan="cicit";// satu satu nya kemungkinan adalah B cicit dari A
            }
            //ada beberapa kemungkinan lain yang ada jika data di tambah
            System.out.println(B.name+" adalah "+hubungan+" dari "+A.name); //menampilkan hubungan
        }else{
            //keadaan jika data yang dimasukkan saat mencari hubungan tidak ada dalam silsilah
            System.out.println("Nama yang anda masukkan tidak ada dalam silsilah keluarga");
        }
    }
}
public class Modul3Jurnal {
    static Scanner input=new Scanner(System.in);
    public static void main(String[]args){ //main method
        Binary_Tree keluarga=new Binary_Tree(); //membuat sebuah objek yang diibaratkan sebagai keluarga besar
        //dibawah ini merupakan code untuk mendaftarkan setiap anggota keluarga dan 
        keluarga.add(new NodeTree("Qasim",8));
        keluarga.add(new NodeTree("Mahsip",4));
        keluarga.add(new NodeTree("Mas'ud",12));
        keluarga.add(new NodeTree("Muzniati",2));
        keluarga.add(new NodeTree("Mahfuz",6));
        keluarga.add(new NodeTree("Anto",10));
        keluarga.add(new NodeTree("Yus",14));
        keluarga.add(new NodeTree("Ryan",1));
        keluarga.add(new NodeTree("Sinta",3));
        keluarga.add(new NodeTree("Alawi",5));
        keluarga.add(new NodeTree("Gibran",7));
        keluarga.add(new NodeTree("Alya",9));
        keluarga.add(new NodeTree("Zahra",11));
        keluarga.add(new NodeTree("Silva",13));
        keluarga.add(new NodeTree("Ulfa",15));
        
        System.out.println("======KELUARGA BESAR=======");
        menuUtama(keluarga);      //memanggil menu utama
    }
    static void menuUtama(Binary_Tree k){ //menu utama ini untuk menampilkan pilihan2 sehingga program bisa berjalan dinamis
        System.out.println("Menu Utama");   //menampilkan tulisan
        System.out.println("1. Anggota Keluarga");  //menampilkan tulisan
        System.out.println("2. Cari Hubungan"); //menampilkan tulisan
        System.out.println("3. Exit");  //menampilkan tulisan
        System.out.print("Pilih :");    //menampilkan tulisan
        String a=input.nextLine();  //meminta masukkan pilihan 
        switch(Integer.valueOf(a)){
//            kemungkinan 1 : user ingin melihat anggota keluarga
            case 1:
                k.anggotaKeluarga(k.root);  //memanggil method anggotakeluarga untuk menampilkan nama-nama anggota keluarga
                menuUtama(k);   //memanggil menu utama
                break;
//            kemungkinan 2 : user ingin mencari hubungan dua orang dalam keluarga besar
            case 2:
                System.out.print("Nama 1 : ");//menampilkan tulisan
                String b=input.nextLine();  //meminta masukan nama1(orang yang dicari hubungannnya)
                System.out.print("Nama 2 : ");//menampilkan tulisan
                String c=input.nextLine();//meminta masukan nama2(orang yang dicari hubungannnya)
                k.Hubungan(b, c);//memanggil method anggotakeluarga untuk menampilkan hubungan anggota keluarga
                menuUtama(k); //memanggil menu utama
                break;
        }
    }
}
