console.log("member.js")

/*
    정규표현식: 특정한 규칙을 가진 문자열의 집합을 표현할때 사용하는 형식 언어
        - 주로 문자열 데이터 검사할때 사용 - 유효성 검사
        -메소드

        -형식규칙
            /^:정규표현식 시작
            &/: 정규표현식 끝 알림
            {} : 반복(문자길이 규칙)
            /^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]]%/
            (?=.*[1개이상문자패턴])
        ():패턴의 그룹
        예6) 문자열@문자.문자
        [a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z]+$/
 */
let checkArray = [false,false,false,false,false] // 아이디 , pw , 이름 , 전화번호 ,이메일 

//1.회원가입
function signup(){
    //1.html 입력값 호출
//    let id = document.querySelector("#id").value
//    let pw = document.querySelector("#pw").value
//    let name = document.querySelector("#name").value
//    let phone = document.querySelector("#phone").value
//    let email = document.querySelector("#email").value
//    let img = document.querySelector("#img").value
    if(checkArray.indexOf(false) != -1){
        alert("체크하지않은 검사가있습니다");
        return
    }
    let signUpform = document.querySelector(".signUpform");
    console.log(signUpform)
    let signUpformData = new FormData(signUpform)
    console.log(signUpformData)

    //2.객체화
//    let info = {
//        id : id, pw:pw,name:name,phone:phone,email:email,img:img
//    }
//    console.log(info)
    //3.객체를 배열에 저장 ---> 스프링 컨트롤러 서버와 연동
    //4.결과
    $.ajax({
            url : '/member/signup',
            method : 'POST',
//            data :info,
            data: signUpformData,
            contentType: false,
            processData : false,
            success : function result( result){
            if(result){
                alert("회원가입 성공")
                location.href = '/member/login'
            }
            else{
                alert("회원가입실패")
            }
         }
    })
}

function login(){
    //1.html 입력값 호출
    let id = document.querySelector("#id").value
    let pw = document.querySelector("#pw").value
    console.log([id,pw])
    //2.객체화
    let info = {
        id : id, pw:pw

    }
    console.log(info)
    //3.객체를 배열에 저장 ---> 스프링 컨트롤러 서버와 연동
    //4.결과
    $.ajax({
            url : '/member/login',
            method : 'POST',
            data :info,
            success : (result) => {
            if(result){
                alert("로그인 성공")
                location.href = "/" // 로그인 성공시 메인페이지로
            }
            else{
                alert("로그인실패")
            }
         }
    })
}

function preimg(event){
    console.log(event.files); //현재 input의 첨부파일들
    console.log(event.files[0]);
    // 1. 파일 읽기 객체 생성
    let fileReader = new FileReader();
    fileReader.readAsDataURL(event.files[0])
    console.log(fileReader)
    console.log(fileReader.result)
    fileReader.onload = e =>{
        console.log(e);
        console.log(e.target);
        console.log(e.target.result);
        document.querySelector("#preimg2").src = e.target.result
    }

}
//4.아이디 유효성 검사(아이디 입력 할때마다)

function idCheck(){
    let id = document.querySelector("#id").value;
    console.log(id);
    // 정규표현식 : 영소문자+숫자 조합의 5~30글자 사이 규칙
    let 아이디규칙 = /^[a-zA-Z0-9가-힣]{5,30}$/;
    console.log(아이디규칙.test(id))
    if(아이디규칙.test(id)){
            //유성검사 출력
        //id 중복체크

        $.ajax({
            method: "get", // HTTP BODY -> 없다 . -> 쿼리스트링
            url: "/member/find/idcheck",
            data: {"id" : id},
            success: (r) => {
                //true 중복있다 ,  false 중복없다
                if(r){
                    document.querySelector(".idcheckbox").innerHTML = "중복검사 실패"
                    checkArray[0] = false
                }
                else{
                    document.querySelector(".idcheckbox").innerHTML = "통과"
                    checkArray[0] = true
                }
            }
        });
    }
    else{
        document.querySelector(".idcheckbox").innerHTML = "소문자영어와 숫자로 이루어진 5~30글자로 구성해주세요"
        checkArray[0]= false
    }
}

function pwcheck(){
    console.log("pwcheck()");
    let pw = document.querySelector("#pw").value;
    let pwcheck = document.querySelector("#pwconfirm").value;

    //2. 유효성검사
    let msg = "";
        //1.비밀번호에 대한 정규표현식
    let 비밀번호규칙 = /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{5,30}$/
        //2.비밀번호와 비밀번호 확인 동일한지 비교
    if(비밀번호규칙.test(pw)){
        if(비밀번호규칙.test(pwcheck)){
            if(pw==pwcheck){
                msg="통과"
                checkArray[1] = true
            }
            else{
                msg="패스워드 불일치"
                checkArray[1] = false
            }
        }
        else{
            msg="표현식 불일치"
            checkArray[1] = false
        }
    }
    else{
        msg="표현식 불일치"
        checkArray[1] = false
    }
    console.log(msg);
    document.querySelector(".pwcheckbox").innerHTML = msg;
    
}

function namecheck(){
    let name = document.querySelector("#name").value;
    let 이름규칙 =/^[가-힣]{5,20}$/
    let msg = "한글5~25글자"
    if(이름규칙.test(name)){
        checkArray[2] = true;
        msg = "통과"
    }
    else{
        checkArray[2]=false;
    }
    document.querySelector(".namecheckbox").innerHTML = msg;

}
//7.전화번호 유효성검사 
function phonecheck(){
    let phone = document.querySelector("#phone").value;
    let 전화번호규칙 =/^([0-9]{2,3})+[-]+([0-9]{3,4})+[-]+([0-9]{4})+$/
    let msg = "010-0000-0000또는 00-000-0000입력"
    if(전화번호규칙.test(phone)){
        checkArray[3] = true;
        msg = "통과"
    }
    else{
        checkArray[3] = false;
    }
    document.querySelector(".phonecheckbox").innerHTML = msg;

}

//8.이메일 유효성 검사 문자@문자.문자

function emailcheck(){
    let email = document.querySelector("#email").value;
    let 이메일규칙 =/^[a-zA-z0-9_-]+@[a-zA-Z0-9_-]+\.[a-zA-Z]+$/
    let msg = "형식에 맞지않는 이메일입니다"
    authreqbtn.disabled= true;
    if(이메일규칙.test(email)){
        authreqbtn.disabled = false;
    }
    else{
        checkArray[4] =false;
    }
    document.querySelector(".emailcheckbox").innerHTML = msg;

}
let time = 0;
let authbox = document.querySelector(".authbox");
let authreqbtn = document.querySelector(".authreqbtn");
let timeInter = null;

//9.인증 요청
function authreq(){
    let html =`<span class="timebox"> </span>
    <input type="text" class="ecodeinput" />
    <button onclick="auth()" type="button"> 인증</button>`

    authbox.innerHTML=html;
    $.ajax({
        url : "/auth/email/req",
        method : "get",
        data : {"email" : document.querySelector("#email").value},
        success : (r) => {
            if(r){
            //4.타이머 함수 실행
            time = 100;
            ontimer();
            authreqbtn.disabled= true// 해당 버튼 사용 금지
            }
            else{
                alert("관리자에게 문의")
            }
        }
    })
}
//==================================

//==================================
//10 타이머 함수
function ontimer(){
    timeInter = setInterval(()=>{
        let m = parseInt(time/ 60);
        let s = parseInt(time% 60);
        m = m< 10? "0"+m : m;
        s = s<10? "0"+s : s;
    
        document.querySelector(".timebox").innerHTML = `${m} : ${s}`
        time--;
        if(time < 0){
            clearInterval(timeInter);
            authreqbtn.disabled=false;
            authbox.innerHTML = "";
        }
    }   
    ,1000)
}

//11 인증함수
function auth(){
    //1.내가 입력한 인증번호
    let ecodeinput = document.querySelector(".ecodeinput").value;
    //2.내가 입력한 인증번호를 자바에게 보내기
    $.ajax({
        method: "get",
        url: "/auth/email/check",
        data: {"ecodeinput":ecodeinput},
        success: (r) => {
            if(r){
                checkArray[4] = true;
                document.querySelector(".emailcheckbox").innerHTML = "통과"
                
                clearInterval(timeInter); //버튼 종료
                authreqbtn.disabled=false;
                authbox.innerHTML = "";
            }
            else{
                alert("잘못된 인증번호입니다")
            }
        }
    });
}