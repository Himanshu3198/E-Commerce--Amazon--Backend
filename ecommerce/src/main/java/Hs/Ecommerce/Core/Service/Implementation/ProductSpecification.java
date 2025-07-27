package Hs.Ecommerce.Core.Service.Implementation;

import Hs.Ecommerce.Core.DTO.Request.ProductSearchRequestDTO;
import Hs.Ecommerce.Core.Entity.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductSpecification {

    public static Specification<Product> getProductSpecification(ProductSearchRequestDTO dto) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (dto.keyword() != null) {
                predicates.add(
                        cb.or(
                                cb.like(cb.lower(root.get("productName")), "%" + dto.keyword().toLowerCase() + "%"),
                                cb.like(cb.lower(root.get("description")), "%" + dto.keyword().toLowerCase() + "%")
                        )
                );
            }

            if (dto.category() != null)
                predicates.add(cb.equal(root.get("category"), dto.category()));

            if (dto.minPrice() != null)
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), dto.minPrice()));

            if (dto.maxPrice() != null)
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), dto.maxPrice()));

            if(dto.minRating() != null)
                predicates.add(cb.greaterThanOrEqualTo(root.get("rating"),dto.minRating()));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
