package Hs.Ecommerce.Core.Service.Interface;

import Hs.Ecommerce.Core.DTO.Response.ProductResponseDTO;
import Hs.Ecommerce.Core.DTO.Request.ProductSearchRequestDTO;
import org.springframework.data.domain.Page;

public interface ISearchService {
    Page<ProductResponseDTO> searchProduct(ProductSearchRequestDTO dto);
}
