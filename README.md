Swagger -> localhost:8080/swagger-ui.html
In Memory DB -> http://localhost:8080/h2-console

Kullanılan Teknolojiler
  Spring Core
	Spring Boot
	Spring Data
	Restful Webservices
	Mockito
	H2

- Uygulama 10 tane yeri olan bir garajdaki araçların girişe en yakın olacak şekilde gelen araçlara tahsis edilmesinin kaydını tutar.
- Uygulamadaki girişe en yakın sıralama 1 den başlayıp 10 kadar olan yer numaraları şeklindedir.
- Uygulama 3 araç tipindeki araçların garaja girmesini düzenler. (CAR(1), JEEP(2), TRUCK(4))
- Her bir araç kendi tipine göre farklı alanları kaplar.
- Loglama debug seviyesinde olup C:logs içerisinde debug diye bir dosya oluşturur.
- Validasyonda alınan hata ve uygulama içerisindeki genel hatalar için Controller Advice kullanıldı.

--> GET -> /garage-slot  (Garajda bulunması gereken 10 tane alanı ekler)
--> GET -> /garage-slot/status (Garajda bulunan araçların plakasına göre renk ve garajdaki kapladığı alanları döner)
--> POST -> /ticket (Garaja giren araçların kapladığı yeri ve karşılığında bir bilet numarası verir.)
--> GET -> /ticket/leave (Garajdan çıkan araçların kaydını günceller)
