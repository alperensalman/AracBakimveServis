# Araç Bakım ve Servis Takip API

Bu proje, Orta Düzey Programlama dersi final projesi kapsamında geliştirilmiş, katmanlı mimariye sahip bir Spring Boot RESTful API uygulamasıdır. Sistem, araç kayıtlarının tutulmasını ve bu araçlara ait servis/bakım geçmişinin güvenli bir şekilde yönetilmesini sağlar.

## Kullanılan Teknolojiler

* **Java 17**
* **Spring Boot 3.x** (Web, Data JPA, Security, Validation)
* **H2 Database** (In-Memory Veritabanı)
* **Maven** (Proje Yönetim Aracı)

## Mimari ve Tasarım Kararları

Proje, sürdürülebilirliği ve okunabilirliği artırmak amacıyla katmanlı mimari (N-Tier Architecture) prensiplerine uygun olarak geliştirilmiştir:
* **Entity:** Veritabanı tabloları (`Vehicle`, `ServiceRecord`).
* **Repository:** Spring Data JPA ile veri erişimi ve `Derived Query Methods` kullanımı.
* **Service:** Çift taraflı kontrollerin ve iş mantığının yürütüldüğü katman.
* **Controller:** Dış dünyadan (Postman/Tarayıcı) gelen HTTP isteklerini karşılayan katman.
* **DTO & Validation:** Dışarıdan gelen verilerin `@NotBlank`, `@Size`, `@Positive` gibi anotasyonlarla doğrulanması.
* **Exception:** Özel hata sınıfları (Örn: `VehicleNotFoundException`) ve `@ControllerAdvice` ile merkezi hata yönetimi.

## Kurulum ve Çalıştırma

Proje, H2 in-memory veritabanı kullandığı için bilgisayarınızda ekstra bir veritabanı kurulumu gerektirmez.

1. Projeyi bilgisayarınıza indirin veya klonlayın.
2. IntelliJ IDEA veya tercih ettiğiniz bir IDE ile projeyi açın.
3. `AracBakimveServisApplication` ana sınıfını çalıştırın.
4. Proje varsayılan olarak `http://localhost:8080` portunda ayağa kalkacaktır.

## Güvenlik ve Test Kullanıcıları (RBAC)

Sistemde Spring Security kullanılarak Rol Bazlı Erişim Kontrolü (RBAC) uygulanmıştır. Uç noktaları test etmek için Basic Auth ile aşağıdaki kullanıcıları kullanabilirsiniz:

| Kullanıcı Adı | Şifre  | Rol   | Yetkiler                                      |
|---------------|--------|-------|-----------------------------------------------|
| `admin`       | 12345  | ADMIN | GET, POST, PUT, DELETE — tüm işlemleri yapabilir. |
| `personel`    | 12345  | USER  | Sadece GET istekleri yapabilir, listeleme ve sorgulama. |

## API Uç Noktaları (Endpoints)

### Araç (Vehicle)
* `GET /api/vehicles` - Tüm araçları listeler (USER, ADMIN).
* `GET /api/vehicles/{id}` - ID'ye göre araç getirir (USER, ADMIN).
* `GET /api/vehicles/brand/{brand}` - Markaya göre araç filtreler (USER, ADMIN).
* `POST /api/vehicles` - Yeni araç ekler (Sadece ADMIN).
* `PUT /api/vehicles/{id}` - Araç günceller (Sadece ADMIN).
* `DELETE /api/vehicles/{id}` - Araç siler (Sadece ADMIN).

### Servis Kaydı (ServiceRecord)
* `GET /api/services` - Tüm servis kayıtlarını listeler (USER, ADMIN).
* `GET /api/services/vehicle/{vehicleId}` - Araca ait servis geçmişini listeler (USER, ADMIN).
* `GET /api/services/cost?min=100&max=500` - Maliyet aralığına göre filtreler (USER, ADMIN).
* `POST /api/services` - Yeni servis kaydı ekler (Sadece ADMIN).
* `PUT /api/services/{id}` - Servis kaydı günceller (Sadece ADMIN).
* `DELETE /api/services/{id}` - Servis kaydı siler (Sadece ADMIN).