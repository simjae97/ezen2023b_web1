package ezenweb.service;


import ezenweb.model.dao.ProductDAO;
import ezenweb.model.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductDAO productDAO;
    @Autowired
    FileService fileService;
    // 1. 등록 서비스/기능처리 요청
    public boolean postProductRegister( ProductDTO productDto){  System.out.println("ProductService.postProductRegister");
        // - 1. 첨부파일 업로드 처리
        List<String> list = new ArrayList<>();
        productDto.getUploadFiles().forEach( ( uploadFile ) -> {
            if(!uploadFile.isEmpty()) {
                String fileName = fileService.fileUpload(uploadFile);
                if (fileName != null) {
                    list.add(fileName);
                }
            }
        });
        for (int i = 0; i < list.size(); i++) {
            System.out.println("list = " + list.get(i));
        }
        productDto.setPimg( list );

        return productDAO.postProductRegister( productDto );
    }
    // 2. 제품 출력(지도) 요청
    public List<ProductDTO> getProductList(){
        System.out.println("ProductService.getProductList");
        return productDAO.getProductList();
    }
    //3.해당 제품의 찜하기 등록 // 로그인했고 찜하기 버튼 클릭시 매개변수:pno , 리턴 : boolean
    public boolean getPlikeWrite(int pno , int mno){
        return productDAO.getPlikeWrite(pno,mno);
    }
    //4.해당 제품의 찜하기 상태 출력 // 로그인 했고 찜하기 버튼 출력시 ,매개변수:pno ,리턴 boolean
    public boolean getPlikeView(int pno , int mno){
        return productDAO.getPlikeView(pno,mno);
    }
    //5.해당 제품의 찜하기 취소/삭제 // 로그인했고 찜하기 버튼 클릭시,매개변수:pno ,리턴 boolean
    public boolean getPlikeDelete(int pno , int mno){
        return productDAO.getPlikeDelete(pno,mno);
    }
}
