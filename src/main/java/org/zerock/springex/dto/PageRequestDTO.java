package org.zerock.springex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.time.LocalDate;


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
        if(link == null){
            StringBuilder builder = new StringBuilder();
            builder.append("page="+this.page);
            builder.append("&size="+this.size);
            link = builder.toString();
        }
        return link;
    }


}
