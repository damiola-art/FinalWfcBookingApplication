public class Review {
    private int rating;

    private String comments;

    public Review(String comment, int rating) {
    }

    public Review(int rating) {
    }


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}