package Hs.Ecommerce.Core.DTO.Request;

public record ProductSearchRequestDTO(
        String keyword,
        String category,
        Double minPrice,
        Double maxPrice,
        Double minRating,
        String sortBy, //price,rating
        String sortDirection,
        Integer page,
        Integer size
) {
}
