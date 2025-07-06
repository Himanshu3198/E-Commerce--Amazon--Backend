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

import java.lang.module.ResolutionException;
import java.util.List;

@Service
public class RatingServiceImpl implements IRatingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RatingServiceImpl.class);
    private final RatingRepository ratingRepository;
    private final IUserService userService;
    private final IInventoryManager inventoryManager;

    public RatingServiceImpl(RatingRepository ratingRepository, IUserService userService, IInventoryManager inventoryManager){
        this.ratingRepository = ratingRepository;
        this.userService = userService;
        this.inventoryManager = inventoryManager;
    }


    @Transactional
    @Override
    public Rating addRating(Long userId, Long productId, Long rating, String review) {
        try {
            User user = userService.getUserById(userId);
            Product product = inventoryManager.getProductById(productId);

            if (user == null) {
                throw new UserNotFoundException("User not found with id: " + userId);
            }
            if (product == null) {
                throw new ProductNotFoundException("Product not found with id: " + productId);
            }

            Rating userRating = new Rating()
                    .setRating(rating)
                    .setProduct(product)
                    .setReview(review)
                    .setUser(user);

            return ratingRepository.save(userRating);
        } catch (DataAccessException dae) {
            logAndThrow("Database exception occurred while adding the user review", dae);
            // logAndThrow likely throws, but to satisfy compiler:
            throw new RatingException("Unhandled database exception", dae);
        } catch (Exception e) {
            throw new RatingException("An unexpected error occurred while adding the user review", e);
        }
    }


    @Override
    public Rating updateRating(Long ratingId, Long rating, String review) {
        try {
            Rating userRating = ratingRepository.findById(ratingId)
                    .orElseThrow(()-> new ResolutionException("Rating not found with id: "+ratingId));
            userRating.setRating(rating);
            userRating.setReview(review);

            return  ratingRepository.save(userRating);
        }catch (DataAccessException dae) {
            logAndThrow("Database exception occurred while updating the user review", dae);
            // logAndThrow likely throws, but to satisfy compiler:
            throw new RatingException("Unhandled database exception", dae);
        } catch (Exception e) {
            throw new RatingException("An unexpected error occurred while adding the user review", e);
        }
    }
    @Override
    public List<Rating> findAllReview(Long productId){

        try{
            return ratingRepository.getAllRatingByProductId(productId);
        }catch (DataAccessException dae) {
            logAndThrow("Database exception occurred while updating the user review", dae);
            // logAndThrow likely throws, but to satisfy compiler:
            throw new RatingException("Unhandled database exception", dae);
        } catch (Exception e) {
            throw new RatingException("An unexpected error occurred while adding the user review", e);
        }
    }

    private void logAndThrow(String message, Exception ex) {
        LOGGER.error("{}: {}", message, ex.getMessage(), ex);
        throw new RuntimeException(message);
    }


}
