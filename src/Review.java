public class Review {
    private int rating;

    private String comment;

    public Review(String comment, int rating) {
        this.comment = comment;
        this.rating = rating;

    }

    public Review(int rating) {
        this.comment = "";
        this.rating = rating;
    }


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}