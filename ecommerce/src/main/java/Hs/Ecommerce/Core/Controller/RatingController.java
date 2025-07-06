package Hs.Ecommerce.Core.Controller;

import Hs.Ecommerce.Core.DTO.Request.RatingDTO;
import Hs.Ecommerce.Core.Entity.Rating;
import Hs.Ecommerce.Core.Exception.ResourceNotFoundException;
import Hs.Ecommerce.Core.Mapper.RatingMapper;
import Hs.Ecommerce.Core.Service.Interface.IRatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/ecom/product/rating")
public class RatingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RatingController.class);
    @Autowired
    private IRatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<String> addRating(@RequestBody RatingDTO ratingDTO){

        ratingService.addRating(ratingDTO.userId(), ratingDTO.productId(), ratingDTO.rating(), ratingDTO.review());
        LOGGER.info("Rating has been added for product: {}",ratingDTO.productId());
        return ResponseEntity.status(HttpStatus.CREATED).body("Rating has been added for product: "+ratingDTO.productId());
    }
    @PatchMapping("/update")
    public ResponseEntity<String> updateRating(@RequestParam Long ratingId,@RequestParam Long rating, @RequestParam String review){
        ratingService.updateRating(ratingId,rating,review);
        LOGGER.info("Rating has been updated for rating: {}",ratingId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Rating has been updated for rating: "+ratingId);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<Map<String, Object>>> AllRatingForProduct(@PathVariable Long productId){
        List<Rating> ratings = ratingService.findAllReview(productId);
        if(ratings.isEmpty() || ratings == null){
            throw new ResourceNotFoundException("Rating not found for productId"+productId);
        }
        return ResponseEntity.ok(ratings.stream().map(RatingMapper::toDTO).toList());
    }
}
