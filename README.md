# Responsi 1 Mobile

## Identitas
| Informasi | Detail |
| :--- | :--- |
| **Nama** | **M Edwi Tsanystya Raihan** |
| **NIM** | **H1D023087** |
| Shift Asal | A |
| Shift Baru | F |

## Demo Aplikasi
![](demoaplikasi.gif)

## Alur Data 
### 1. Definisi API (Interface `ApiService`)
* **Permintaan API** didefinisikan dalam sebuah **Interface Kotlin** (`ApiService.kt`) menggunakan anotasi dari library **Retrofit**.
* Setiap *endpoint* dideklarasikan sebagai fungsi dengan metode HTTP (`@GET`) dan parameter yang dibutuhkan.
* Fungsi ini menentukan struktur data (Model POKO/Kotlin Data Class seperti `Team`, `Player`, `Coach`) yang diharapkan sebagai respons.

### 2. Konfigurasi Klien Retrofit (`ApiClient`)
* Sebuah `object singleton` **`ApiClient`** dibuat untuk mengelola instance Retrofit.
* Klien dikonfigurasi dengan **Base URL** dan **`GsonConverterFactory`**.
* Tugas utama converter ini adalah mengubah respons **JSON** dari server menjadi **Objek Kotlin** secara otomatis.

### 3. Inisiasi Pemanggilan Data (di dalam `Activity`)
* Pada metode `onCreate()` di Activity (misalnya `HeadCoachActivity` atau `TeamSquadActivity`), fungsi dari `ApiService` dipanggil melalui `ApiClient`.
* Retrofit mengeksekusi pemanggilan ini secara **asynchronous** (di *background thread*) menggunakan metode **`enqueue()`** untuk menghindari *UI freeze*.

### 4. Penanganan Respons (`Callback onResponse` dan `onFailure`)
Metode `enqueue` menerima sebuah objek `Callback` dengan dua metode utama:
| Metode | Keterangan |
| :--- | :--- |
| **`onResponse`** | Dipanggil jika permintaan berhasil dan server memberikan respons (Kode 200 OK atau *Server Error*). |
| **`onFailure`** | Dipanggil jika terjadi kegagalan di level jaringan (misalnya, tidak ada koneksi internet atau server tidak dapat dijangkau). Aplikasi akan menampilkan pesan `Toast` (*"Failed to load data"*). |

### 5. Penyajian Data ke UI
Jika `onResponse` dipanggil dan responsnya sukses:
* **Data Tunggal (contoh: `HeadCoachActivity`):** Informasi diekstrak langsung dari objek Kotlin dan ditampilkan ke komponen `TextView` yang sesuai.
* **Data Daftar (contoh: `TeamSquadActivity`):**
    * Daftar pemain (`List<Player>`) dari respons API diserahkan ke **`PlayerAdapter`**.
    * Di dalam Adapter, daftar diurutkan berdasarkan **posisi pemain**.
    * Adapter kemudian memberitahu `RecyclerView` bahwa datanya telah diperbarui melalui **`notifyDataSetChanged()`** agar data dapat digambar ulang secara efisien.

---

## 🛠️ Teknologi yang Digunakan

* **Bahasa Pemrograman:** Kotlin
* **Library Jaringan:** Retrofit
* **Library Parsing Data:** Gson
* **Komponen UI:** RecyclerView, CardView

---



