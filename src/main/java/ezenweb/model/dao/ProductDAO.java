package ezenweb.model.dao;


import ezenweb.model.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDAO extends DAO{

    // 1. 등록 서비스/기능처리 요청
    public boolean postProductRegister(ProductDTO productDTO){
        System.out.println("productDTO = " + productDTO);
        System.out.println("ProductDAO.poostProductRegister");
        try {
            String sql = "insert into product (pname,pprice,pcontent,plat,plng,mno) values(?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,productDTO.getPname());
            ps.setInt(2,productDTO.getPprice());
            ps.setString(3,productDTO.getPcontent());
            ps.setString(4,productDTO.getPlat());
            ps.setString(5,productDTO.getPlng());
            ps.setInt(6,productDTO.getMno());
            int count = ps.executeUpdate();
            if( count == 1){
                rs = ps.getGeneratedKeys();
                if (rs.next()){
                    productDTO.getPimg().forEach( (pimg) -> {
                        try {
                            System.out.println("pimg = " + pimg);
                            String subsql = "insert into productimg(pimg,pno) values(?,?)";
                            ps = conn.prepareStatement(subsql);
                            ps.setString(1,pimg);
                            ps.setInt(2,rs.getInt(1));
                            ps.executeUpdate();
                        }
                        catch (Exception e){
                            System.out.println("e = " + e);
                        }
                    });

                    return true;
                }
            }
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }
    // 2. 제품 출력(지도) 요청
    public List<ProductDTO> getProductList(){
        System.out.println("ProductDAO.getProductList");
        List<ProductDTO> result = new ArrayList<>();
        try {
            String sql = "select * from product p inner join member m on p.mno = m.mno;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                ProductDTO productDTO = ProductDTO.builder()
                        .pno(rs.getInt("pno"))
                        .pname(rs.getString("pname"))
                        .pprice(rs.getInt("pprice"))
                        .pstate(rs.getByte("pstate"))
                        .plat(rs.getString("plat"))
                        .plng(rs.getString("plng"))
                        .pdate(rs.getString("pdate"))
                        .mid(rs.getString("id"))
                        .pcontent(rs.getString("pcontent"))
                        .build();

                //제품 이미지
                List<String > imgList = new ArrayList<>();
                //제품 이미지 등록
                String sql2 = "select * from productimg where pno = "+rs.getInt("pno");
                ps = conn.prepareStatement(sql2);
                ResultSet rs2 = ps.executeQuery();
                while(rs2.next()){
                    imgList.add(rs2.getString("pimg"));
                }
                productDTO.setPimg(imgList);
                result.add(productDTO);
            }
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }

        return result;
    }
    //3.해당 제품의 찜하기 등록 // 로그인했고 찜하기 버튼 클릭시 매개변수:pno , 리턴 : boolean
    public boolean getPlikeWrite(int pno , int mno){
        System.out.println("ProductDAO.getPlikeWrite");
        System.out.println("pno = " + pno + ", mno = " + mno);
        try {
            String sql = "insert into plike values(?,?)";
            ps =conn.prepareStatement(sql);
            ps.setInt(1,mno);
            ps.setInt(2,pno);
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }
    //4.해당 제품의 찜하기 상태 출력 // 로그인 했고 찜하기 버튼 출력시 ,매개변수:pno ,리턴 boolean
    public boolean getPlikeView(int pno , int mno){
        System.out.println("ProductDAO.getPlikeView");
        System.out.println("pno = " + pno + ", mno = " + mno);
        try {
            String sql = "select * from plike where pno =? and mno = ?";
            ps =conn.prepareStatement(sql);
            ps.setInt(1,pno);
            ps.setInt(2,mno);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }

        return false;
    }
    //5.해당 제품의 찜하기 취소/삭제 // 로그인했고 찜하기 버튼 클릭시,매개변수:pno ,리턴 boolean
    public boolean getPlikeDelete(int pno , int mno){
        System.out.println("ProductDAO.getPlikeDelete");
        System.out.println("pno = " + pno + ", mno = " + mno);
        try {
            String sql = "delete from plike where pno =? and mno = ?";
            ps =conn.prepareStatement(sql);
            ps.setInt(1,pno);
            ps.setInt(2,mno);
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }

        return false;
    }
}
