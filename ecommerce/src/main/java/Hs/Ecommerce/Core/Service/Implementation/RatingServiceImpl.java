package Hs.Ecommerce.Core.Service.Implementation;

import Hs.Ecommerce.Core.Entity.Product;
import Hs.Ecommerce.Core.Entity.Rating;
import Hs.Ecommerce.Core.Entity.User;
import Hs.Ecommerce.Core.Exception.ProductNotFoundException;
import Hs.Ecommerce.Core.Exception.RatingException;
import Hs.Ecommerce.Core.Exception.UserNotFoundException;
import Hs.Ecommerce.Core.Repository.RatingRepository;
import Hs.Ecommerce.Core.Service.Interface.IInventoryManager;
import Hs.Ecommerce.Core.Service.Interface.IRatingService;
import Hs.Ecommerce.Core.Service.Interface.IUserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements IRatingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RatingServiceImpl.class);

    private final RatingRepository ratingRepository;
    private final IUserService userService;
    private final IInventoryManager inventoryManager;

    public RatingServiceImpl(RatingRepository ratingRepository,
                             IUserService userService,
                             IInventoryManager inventoryManager) {
        this.ratingRepository = ratingRepository;
        this.userService = userService;
        this.inventoryManager = inventoryManager;
    }

    @Transactional
    @Override
    public Rating addRating(Long userId, Long productId, Double rating, String review) {
        try {
            User user = userService.getUserById(userId);
            if (user == null) {
                throw new UserNotFoundException("User not found with id: " + userId);
            }

            Product product = inventoryManager.getProductById(productId);
            if (product == null) {
                throw new ProductNotFoundException("Product not found with id: " + productId);
            }

            Rating userRating = new Rating();
            userRating.setRating(rating);
            userRating.setProduct(product);
            userRating.setReview(review);
            userRating.setUser(user);

            return ratingRepository.save(userRating);

        } catch (DataAccessException dae) {
            logAndThrow("Database exception occurred while adding the user review", dae);
            throw new RatingException("Database exception occurred"); // for compiler
        } catch (Exception e) {
            throw new RatingException("Unexpected error while adding user review");
        }
    }

    @Transactional
    @Override
    public Rating updateRating(Long ratingId, Double rating, String review) {
        try {
            Rating existingRating = ratingRepository.findById(ratingId)
                    .orElseThrow(() -> new RatingException("Rating not found with id: " + ratingId));

            existingRating.setRating(rating);
            existingRating.setReview(review);

            return ratingRepository.save(existingRating);

        } catch (DataAccessException dae) {
            logAndThrow("Database exception occurred while updating the user review", dae);
            throw new RatingException("Database exception occurred");
        } catch (Exception e) {
            throw new RatingException("Unexpected error while updating user review");
        }
    }

    @Override
    public List<Rating> findAllReview(Long productId) {
        try {
            return ratingRepository.getAllRatingByProductId(productId);
        } catch (DataAccessException dae) {
            logAndThrow("Database exception occurred while retrieving reviews", dae);
            throw new RatingException("Database exception occurred");
        } catch (Exception e) {
            throw new RatingException("Unexpected error while retrieving reviews");
        }
    }

    private void logAndThrow(String message, Exception ex) {
        LOGGER.error("{}: {}", message, ex.getMessage(), ex);
        throw new RuntimeException(message, ex);
    }
}
