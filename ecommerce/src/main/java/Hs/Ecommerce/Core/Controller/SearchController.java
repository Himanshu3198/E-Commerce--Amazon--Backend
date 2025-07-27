package Hs.Ecommerce.Core.Controller;

import Hs.Ecommerce.Core.DTO.Request.ProductSearchRequestDTO;
import Hs.Ecommerce.Core.DTO.Response.ProductResponseDTO;

import Hs.Ecommerce.Core.Service.Interface.ISearchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ecom/api/product/")
public class SearchController {

    @Autowired
    private ISearchService searchService;



    @PostMapping("/search")
    public ResponseEntity<Page<ProductResponseDTO>> searchProduct(@Valid @RequestBody ProductSearchRequestDTO dto){
        Page<ProductResponseDTO> searchRequestDTOPage = searchService.searchProduct(dto);
        return ResponseEntity.ok(searchRequestDTOPage);
    }

}
