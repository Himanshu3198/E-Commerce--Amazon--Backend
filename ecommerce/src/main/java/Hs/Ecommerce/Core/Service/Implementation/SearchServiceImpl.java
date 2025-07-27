package Hs.Ecommerce.Core.Service.Implementation;

import Hs.Ecommerce.Core.DTO.Response.ProductResponseDTO;
import Hs.Ecommerce.Core.DTO.Request.ProductSearchRequestDTO;
import Hs.Ecommerce.Core.Entity.Product;
import Hs.Ecommerce.Core.Mapper.ProductMapper;
import Hs.Ecommerce.Core.Repository.ProductRepository;
import Hs.Ecommerce.Core.Service.Interface.ISearchService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public class SearchServiceImpl implements ISearchService {

    private final ProductRepository productRepository;

    public SearchServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public Page<ProductResponseDTO> searchProduct(ProductSearchRequestDTO dto) {

        Specification<Product> spec = ProductSpecification.getProductSpecification(dto);
        Sort sort = Sort.by(Sort.Direction.fromString(dto.sortDirection() != null ? dto.sortDirection() : "asc"), dto.sortBy() != null ? dto.sortBy() : "createdAt");
        int page = dto.page() != null ? dto.page() : 5;
        int size = dto.size() != null ? dto.size() : 15;

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> productPage;
        try {
            productPage = productRepository.findAll(spec, pageable);
        } catch (DataAccessException dae) {
            throw new RuntimeException("An error occurred while accessing the data!" + dae.getMessage());
        }

        return ProductMapper.toPageDTO(productPage);

    }
}
