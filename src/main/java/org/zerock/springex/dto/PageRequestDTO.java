package org.zerock.springex.dto;

import jdk.internal.org.jline.utils.Log;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default//기본값 지정
    @Min(value=1)//기본값 지정
    @Positive//양수인지 검증
    //현재 페이지 번호
    private int page = 1;

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    //한 페이지 당 보여주는 데이터의 수
    private int size = 10;

    private String link;

    private String[] types;
    private String keyword;
    private boolean finished;
    private LocalDate from;
    private LocalDate to;

    //건너뛰기 수
    public int getSkip(){
        return (page-1)*10;
    }

    public String getLink(){
        StringBuilder builder = new StringBuilder();
        builder.append("page="+this.page);
        builder.append("&size="+this.size);

        if(finished){
            builder.append("&finished=on");
        }

        if (types != null && types.length > 0) {
            for(int i =0; i< types.length;i++){
                builder.append("&type="+types[i]);
            }
        }

        if(keyword != null){
            try {
                builder.append("&keyword"+ URLEncoder.encode(keyword,"UTF-8"));
            } catch (UnsupportedEncodingException e ){
                e.printStackTrace();
            }
        }

        if(from != null){
            builder.append("&from"+from.toString());
        }

        if(to != null){
            builder.append("&to="+to.toString());
        }

        return builder.toString();
    }

    public boolean checkType(String type){

        if(types == null || types.length == 0){
            return false;
        }
        System.out.println("checkType..........................");
        System.out.println("type: "+type);
        System.out.println("types: "+types);

        return Arrays.stream(types).anyMatch(type::equals);
    }

}
