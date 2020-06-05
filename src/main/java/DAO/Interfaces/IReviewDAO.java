package DAO.Interfaces;

import Entities.Review;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;


public interface IReviewDAO {
    void addReview(Review review);
    List<Review> readReviews() throws JsonProcessingException;
    List<Review> findReviewsId(int id) throws JsonProcessingException;
    List<Review> findReviewsRating(float rating) throws JsonProcessingException;
    void deleteReview(int id);
}
