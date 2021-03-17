# WeatherForecastApp

## การทำงานของ Weather Foreecasy App

การเข้าใช้งานแอปเมื่อเปิดเข้าครั้งแรก

### 1. Splash Screen : แสดงในส่วนของ Icon app และ version ปัจจุบันด้านบนขวา

[<img src="https://i.imgur.com/fo0EbHy.jpg" width="250"/>](image.png)

### 2. Main Screen จะขอแบ่งเป็น 3 ส่วนด้วยกัน

ส่วนที่ 1 | ส่วนที่ 2 | ส่วนที่ 3 |
-------- | ------- | ------- |
![Image](https://i.imgur.com/f8lDDEc.jpg) | ![Image](https://i.imgur.com/aHDhcNi.jpg) | ![Image](https://i.imgur.com/l2tenb8.jpg) |
 

- ส่วนที่ 1 : 
1. แสดง "สถานที่" ที่เราค้นหา ณ ปัจจุบัน Default เป็น "Bangkok" เมื่อเข้ามาครั้งแรก จะเปลี่ยนก็ต่อเมื่อกดค้นหาใน (ส่วนที่ 3)
2. แสดง icon บรรยายถึงสภาพอากาศ โดยจะแสดงอยู่ 7 แบบด้วยกัน [thunderstorm, drizzle, rain, snow, clear, clouds, mist]
3. แสดง "อุณหภูมิ" ซึ่ง default จะเป็น Celsius สามารถเปลี่ยนเป็น Fahrenheit ได้ โดยกดที่ตัว °F หรือถ้าจะเปลี่ยนกลับเป็น Celsius ก็กดที่ °C
4. แสดง "ความชื้น" ตรง icon หยดน้ำ ด้านล่างซ้ายมือ โดยแสดงเป็น (percent)%

- ส่วนที่ 2 :
1. เมื่อกดที่ปุ่ม จะเป็นหน้าที่ใช้สำหรับแสดง "การคาดการณ์พยากรณ์อากาศของทั้งวัน" โดย รูปภาพจะอยู่ในส่วนถัดไป

- ส่วนที่ 3 :
1. ทำการค้นหา ณ พื้นที่ที่เราต้องการ โดยจะแบ่งเป็น 3 หัวข้อ
    - City name [require field*] : สามารถกรอกด้วยตัวหนังสือได้ เช่น London, Bankkok, Barcelona, ...
    - State code [optional] : สามารถกรอกด้วยตัวหนังสือ หรือตัวเลขได้ เช่น uk, us, 01, 02 ,1 ,2 , ...
    - Counttry code [optional] : สามารถกรอกด้วยตัวหนังสือ หรือตัวเลขได้ เช่น AFG, BRN , ...  
2. กดปุ่ม "forecast" จะนำข้อมูลที่กรอกไปทั้ง 3 ช่องไปค้นหา และพยากรณ์สภาพอากาศ จะถูกนำไปแสดงผลในส่วนที่ 1 

### 3. หน้าแสดงในส่วนของการพยากรณ์สภาพอากาศสำหรับทั้งวัน 24 ชั่วโมง โดยจะแบ่งแสดงเวลาเป็น AM และ PM

[<img src="https://i.imgur.com/LsSRtPH.jpg" width="250"/>](image.png)

1. ปุ่มด้านบนซ้ายมือ เพื่อบ่งบอกว่าเป็นการพยากรณ์สำหรับทั้งวัน และ**ทำรองรับสำหรับในอนาคตมีการ พยากรณ์สำหรับ 7 วันหรือ 1 เดือน เป็นต้น** ก็เพียงแค่เพิ่มปุ่มและทำ viewtype ให้กับ adapter ในการเปลี่ยนตัว design ui
2. หน้านี้จะแสดง เวลา อุณหภูมิ °C/°F และ ความชื้น โดยแบ่งเป็น column
 

# Structure & Build
- Android MVP Design Pattern

![Image](https://i.imgur.com/zHy6ols.png)

- Project Structure
ใช้ Activity 2 [splash screen, main screen] และ fragment 2 [main current weather, whole-day screen]

![Image](https://imgur.com/kdB9yPQ.png)

- Build Variants

จะแบ่งเป็น Alpha กับ Production สำหรับ Alpha จะรองรับการเชื่อมต่อกับ ProxyMan และ Charles เพื่อที่จะสามารถ mock ตัวของ api ได้
 
![Image](https://imgur.com/Ra2kbjE.jpg)

# Libraries used in the project
1. SwiperefreshLayout
2. Retrofit 2
3. RxJava RxAndroid
4. Wrap Parcel
5. Dagger 2

# Build & Test
1. Clone or Download Zip the project from this repository >> https://github.com/Pokkao/WeatherForecastApp <<
2. Open the project with Android Studio
3. Select Build Variants as alphaDebug
4. Build and run!
