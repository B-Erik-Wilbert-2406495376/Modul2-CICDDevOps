Deployment URL: [url](https://modul2-cicddevops-production.up.railway.app)

# Reflection module 2
1. Issue yang saya temukan dengan sonarcloud adalah meggunakan temporary variable yang tidak memiliki manfaat jelas sehingga saya langsung return hasil operasinya tanpa letak di temporary variable untuk operasi mudah.
2. Ya, implementasi saat ini sudah termasuk Continuous Integration (CI) dan Continuous Deployment (CD). Setiap push ke repository secara otomatis menjalankan workflow GitHub Actions untuk melakukan build dan menjalankan seluruh test. Aplikasi juga otomatis ter-deploy ke PaaS tanpa proses manual setiap kali terdapat perubahan pada branch yang dipantau. Selain itu, pipeline yang digunakan juga membantu menjaga kualitas code secara konsisten karena dilengkapi dengan proses code scanning.

# Reflection module 3
1. Saya apply semua principle SOLID di project saya.
    - SRP: di controller hanya handle http requests dan responses dan product dan car dipisah, di service handle logicnya, repository handle menyimpan data.
    - OCP: menggunakan abstract untuk base model, interface base repositorynya dan juga base service CRUD, sehingga mudah untuk ditambah dan tertutup untuk modifikasi.
    - LSP: di service dan repository menggunakan Impl yang implement interfacenya sebelum digunakan di masing-masing fungsi sehingga superclass bisa direplace subclass.
    - ISP: interface kecil saja, di service hanya untuk handle CRUD di 1 interface yang saya buat.
    - DIP: ProductController bergantung dengan ProductService (interface) bukan ProductServiceImpl dan ProductServiceImpl bergantung ke Repository(interface) bukan RepositoryImpl.
2. Advantage SOLID:
    - Mudah maintanancenya: semisal kita perlu ganti logic data storage atau logic servicenya, hanya perlu ganti layernya saja.
    - Scalability dan extensibility: mudah untuk menambahkan fitur baru, entitas baru, dan bisa extend tanpa menulis ulang code sebelumnya, semisal kita ingin tambah entitas Rumah, tinggal implements base modelnya, dan untuk repository dan servicenya juga tinggal implements.
    - Reduced coupling: bergantung dengan abstraction dan bukan concrete class, menghindar sistem rusak semua saat mengganti 1 part, saya bisa ganti implementasinya tanpa ubah di controller.
3. Disadvantage ga SOLID:
    - Tight coupling: kebergantungan yang terlalu kuat dengan concrete class membuat susah untuk mengubah implementasinya, jika ubah semuanya ubah, semisal ingin ganti implementasi, saya butuh ganti di controllernya juga.
    - Susah di maintain: jika semisal ada class handle banyak sekaligus, misal repository handle logic juga, maka akan sulit untuk di maintain dan 1 ubah bisa merusak semuanya.
    - Poor scalability: saat menambah fitur baru perlu mengubah class sebelumnya dan berpotensi membuat bug di code yang sudah stabil sebelumnya, misal kita ingin tambah method di service, kita malah gantiin class di yang sebelumnya sudah stabil.

# Reflection module 4
1. Menurut saya, Test-Driven Development (TDD) memberikan pengaruh signifikan dalam pengembangan perangkat lunak. Pendekatan ini mendorong pengembang untuk merumuskan kebutuhan fungsional secara menyeluruh sebelum menulis kode implementasinya. Dengan demikian, setiap fitur yang dikembangkan dapat dipastikan berfungsi sesuai dengan spesifikasi, yang pada akhirnya meminimalkan potensi munculnya bug di kemudian hari. Selain itu, refactoring juga mendukung praktik clean code, karena proses refactoring yang dilakukan setelah seluruh pengujian berhasil memastikan struktur kode tetap terjaga keterbacaannya tanpa menurunkan fungsionalitas.
2. Sebagian besar unit test yang saya buat sudah mengikuti prinsip FIRST (Fast, Independent, Repeatable, Self-Validating, dan Timely). Test dijalankan dengan cepat karena hanya menguji satu unit kecil, dan tidak bergantung pada komponen lain seperti database. Namun, pada beberapa bagian masih bisa diperbaiki terutama dalam memastikan semua test benar-benar independen dan mencakup lebih banyak edge cases agar kualitas pengujian menjadi lebih baik.