package com.example.Pegination.services;

import com.example.Pegination.models.Product;
import com.example.Pegination.repo.ProductRepository;
import com.example.Pegination.utils.OffsetBasedPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findProducts(Integer companyId, int offset, int limit, String sort, String sortBy) {

        try {
            Sort.Order order = new Sort.Order(Sort.Direction.fromString(sort), sortBy);

            Pageable pageable = new OffsetBasedPageRequest(offset, limit, Sort.by(order));

            // if companyId is null findAll method is called otherwise findByCompanyId
            Page<Product> pageTuts;
            if (companyId == null)
                pageTuts = productRepository.findAll(pageable);
            else
                pageTuts = productRepository.findByCompanyId(companyId, pageable);

            return pageTuts.getContent();
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
