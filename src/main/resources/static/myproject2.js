
function onChangeImg(img){
    console.log("hello")
    let fileReader = new FileReader();
    fileReader.readAsDataURL( img.files[0] );
            
    fileReader.onload = e =>{
        let imgElement = document.createElement("img"); // 이부분 querySelector로 해보려 하니 null값이 들어간다함.
        imgElement.src = e.target.result;
        document.querySelector('#preimg').src = e.target.result
        //이부분을 함수빼서 다르게 나오게 바꾸면 될듯? 
        imgElement.onload = () => {
            let canvas = document.querySelector("#canvas");
            let ctx = canvas.getContext("2d");
            canvas.width = imgElement.width;
            canvas.height = imgElement.height;
            ctx.drawImage(imgElement, 0, 0);
              
        };
    }
}

function grayscale() {
    
    let img = document.querySelector("#preimg");
    let canvas = document.querySelector("#canvas");
    let ctx = canvas.getContext('2d');
    
    // 캔버스 크기를 이미지의 크기로 설정
    canvas.width = img.width;
    canvas.height = img.height;

    // 캔버스에 이미지 그리기
    ctx.drawImage(img, 0, 0, img.width, img.height);

    // 이미지를 그레이스케일로 변환
    let imageData = ctx.getImageData(0, 0, canvas.width, canvas.height);
    let data = imageData.data;
    for (let i = 0; i < data.length; i += 4) {
        let avg = (data[i] + data[i + 1] + data[i + 2]) / 3;
        data[i] = avg;
        data[i + 1] = avg;
        data[i + 2] = avg;
    }

    ctx.putImageData(imageData, 0, 0);

}

function binary(){
    let img = document.querySelector("#preimg");
    let canvas = document.querySelector("#canvas");
    let ctx = canvas.getContext('2d');
    
    // 캔버스 크기를 이미지의 크기로 설정
    canvas.width = img.width;
    canvas.height = img.height;
    
    // 캔버스에 이미지 그리기
    ctx.drawImage(img, 0, 0, img.width, img.height);
    
    let threshold = 128; // 임계값 설정
    imageData = ctx.getImageData(0, 0, canvas.width, canvas.height);
    data = imageData.data;
    for (let i = 0; i < data.length; i += 4) {
        let avg = (data[i] + data[i + 1] + data[i + 2]) / 3;
        let binaryValue = avg > threshold ? 255 : 0;
        data[i] = binaryValue;
        data[i + 1] = binaryValue;
        data[i + 2] = binaryValue;
    }
    ctx.putImageData(imageData, 0, 0);
    
    ctx.putImageData(imageData, 0, 0);

}


