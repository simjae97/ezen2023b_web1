console.log("안녕")

    var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center : new kakao.maps.LatLng( 37.321877 , 126.830884), // 지도의 중심좌표
        level : 6 // 지도의 확대 레벨33.450701, 126.570667
    });

    // 마커 클러스터러를 생성합니다
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 8 // 클러스터 할 최소 지도 레벨
    });

    // 데이터를 가져오기 위해 jQuery를 사용합니다
    // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
    $.get("https://api.odcloud.kr/api/15109590/v1/uddi:3e550608-d205-411b-a92d-e7fd2278b7bc?page=1&perPage=100&serviceKey=To%2Baj7g3UQT9b52hpWu7PDbqmXaz%2FHz3yFr%2Fcz7XmCFgGCkL1CwLO9nMSKP4GVRiq1ykfLpd7Kl0NodiNhzuYg%3D%3D"
    , function(r) {
        // 데이터에서 좌표 값을 가지고 마커를 표시합니다
        // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
        var markers = r.data.map( (obj) => {
            return new kakao.maps.Marker({
                position : new kakao.maps.LatLng(obj.식당위도 , obj.식당경도)
            });
        });

        // 클러스터러에 마커들을 추가합니다
        clusterer.addMarkers(markers);
    });

//3. 카카오 지도 api
//var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
//var options = { //지도를 생성할 때 필요한 기본 옵션
//	center: new kakao.maps.LatLng(126.8308848, 37.3218778), //지도의 중심좌표.
//	level: 3 //지도의 레벨(확대, 축소 정도)
//};
//
//var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

//2. 안산시 원곡동 일반 음식점
$.ajax({
    url : "https://api.odcloud.kr/api/15109590/v1/uddi:3e550608-d205-411b-a92d-e7fd2278b7bc?page=1&perPage=100&serviceKey=To%2Baj7g3UQT9b52hpWu7PDbqmXaz%2FHz3yFr%2Fcz7XmCFgGCkL1CwLO9nMSKP4GVRiq1ykfLpd7Kl0NodiNhzuYg%3D%3D",
    method : "get",
    success : (r) =>{
        console.log(r)
        let apitable2 = document.querySelector(".apitable2");
        let html ="";
        r.data.forEach( (obj) => {
        html +=`<tr>
                     <th> ${obj.사업장명} </th>
                     <th> ${obj.도로명전체주소} </th>
                     <th> ${obj.대표메뉴1} </th>
                     <th> ${obj.메뉴가격1.toLocaleString()} </th>
                     <th> ${obj.대표전화} </th>
                     <th> ${obj['주차 가능']} </th>
               </tr>`
        })
        apitable2.innerHTML = html;
    }
})


//1.안산시 강우량 api
$.ajax({
    url : "https://api.odcloud.kr/api/15111852/v1/uddi:71ee8321-fea5-4818-ade4-9425e0439096?page=1&perPage=100&serviceKey=To%2Baj7g3UQT9b52hpWu7PDbqmXaz%2FHz3yFr%2Fcz7XmCFgGCkL1CwLO9nMSKP4GVRiq1ykfLpd7Kl0NodiNhzuYg%3D%3D",
    method : "get",
    success : (r) => {
        console.log(r);
        let apitable1 = document.querySelector(".apitable1");
        let html = ""
        r.data.forEach( (obj) => {
            html +=                 `<tr>
                                        <th> ${obj.관리기관명} </th>
                                        <th> ${obj.날짜} </th>
                                        <th> ${obj.시도명} ${obj.시군구명} ${obj.읍면동} </th>
                                        <th> ${obj['우량(mm)']} </th>
                                    </tr>`
        })
        apitable1.innerHTML = html;
    }
})