# Blog-Application
 Kendi blog uygulamam.

## Libraries Used
- Glide
- FancyToast 

## Description
Bu android uygulama, kendimi tanıttığım bir blog uygulamasıdır. Uygulama, ana ekran ve ana ekran üzerindeki butonlar üzerinden ulaşılan 4 ekran, toplamda 5 ekrandan oluşmaktadır. Ekranlar arasındaki geçiş 'Intent' üzerinden gerçekleşmektedir.

### Main Page
Satırlarını custom olarak hazırladığım bir ListView, resmimi barındıran bir ImageView, adımı-soyadımı ve ünvanımı barındıran TextView'lar vardır. ListView üzerindeki elemanlar, custom bir ArrayAdapter kullanılarak dinamik olarak yerleştirilmiştir. ListView üzerindeki her satırda, ilgili sayfaya geçiş yapmak için bir buton, ilgili sayfanın bilgilerini barındıran bir ImageView ve TextView'lar barındırmaktadır.

#### Components Used in MainActivity
- CircularImageView
- ConstraintLayout
- TextView
- ListView

### About Me Page
Özgeçmiş dosyamı görüntülemektedir. Resmimi barındıran bir ImageView ve bilgilerimi barındıran TextView'lardan oluşmaktadır. Ekranın aşağı-yukarı hareketi için ScrollView kullanışmıştır.

#### Components Used in AboutMeActivity
- ConstraintLayout
- ScrollView
- LinearLayout
- TextView
- Guideline

### Contact Page
Elemanlarını custom olarak hazırladığım bir ListView'dan oluşmaktadır. ListView satırları üzerinde ilgili iletişim yöntemini temsil eden bir ImageView, iletişim yönteminin adı ve iletişime geçilmesi yönünde Button bulunmaktadır. Button'lara tıklanıldığında bir 'Intent' ile ilgili sayfaya geçiş yapılacaktır. 
 
#### Components Used in ContactActivity
- WebView
- ConstraintLayout
- TextView
- LinearLayout
- Button
- ScrollView
 
### Gallery Page
Bu sayfada ufak bir C# eğitimi hazırladım. Açılır bir PopUpMenu ile kullanıcıdan bir konu seçmesi beklenir. Kullanıcının seçtiği konunun hem sözel anlatımı hem de ilgili konunun youtube ders videosu ekranda açılır. Sözel anlatım kısmında ekranın aşağı yukarı hareketi ScrollView ile sağlanmıştır. Youtube videosunun oynatımı ise bir WebView üzerinde gerçekleşmektedir.

#### Components Used in GalleryActivity
- ConstraintLayout
- PopUpMenu
- ListView
- AlertDialog

### Blog Page
Bir WebView üzerinde GitHub profilim görüntülenmektedir. Kullanıcı tarafından yapılan işlemler, kullanıcıyı bir web sitesinde yönlendirmez. Bunun yerine tüm işlemler uygulama üzerinden gerçekleşir.

#### Components Used in BlogActivity
- ConstraintLayout
- WebView

## App Display Image

| Home Page | About Me Page | Contact Page | Gallery Page |
| --- | --- | --- | --- |
| ![Aktivite 1](https://github.com/kursatmemis/Blog-Application/blob/main/images/img_1.jpg) | ![Aktivite 2](https://github.com/kursatmemis/Blog-Application/blob/main/images/img_2.jpg) | ![Aktivite 3](https://github.com/kursatmemis/Blog-Application/blob/main/images/img_3.jpg) | ![Aktivite 4](https://github.com/kursatmemis/Blog-Application/blob/main/images/img_4.jpg) |
| Gallery Page | Gallery Page | Gallery Page | Blog Page |
| ![Aktivite 5](https://github.com/kursatmemis/Blog-Application/blob/main/images/img_5.jpg) | ![Aktivite 6](https://github.com/kursatmemis/Blog-Application/blob/main/images/img_6.jpg) | ![Aktivite 7](https://github.com/kursatmemis/Blog-Application/blob/main/images/img_7.jpg) | ![Aktivite 8](https://github.com/kursatmemis/Blog-Application/blob/main/images/img_8.jpg) |




