package neo.ehsanodyssey.java.spring.dto;

import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Setter
public class PageRequestImpl {

    private int page = 0;
    private int size = 3;
    private String sort = "age";

    public PageRequest getInstance() {
        return PageRequest.of(this.page, this.size, Sort.by(new Sort.Order(Sort.Direction.DESC, sort)));
    }

//    public PageRequest getOrderedByCreateDateInstance() {
//        return PageRequest.of(this.page, this.size, new Sort(new Sort.Order(Sort.Direction.DESC, "createdDate")));
//    }
}
