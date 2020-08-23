/*
 * Class match luggage
 */
package corendon.queries;

/**
 *
 * @author Nam Phan - Studentnummer: 500769669
 */
public class MatchLuggage {
    
    private int issuesId;
    private String typeLuggage;
    private String brandLuggage;
    private String destinationLuggage;
    private String flightnumberLuggage;

    /**
     *  All parameters contains information of the luggage to create a match with another luggage
     * @param issuesId
     * @param typeLuggage
     * @param brandLuggage
     * @param destinationLuggage
     * @param flightnumberLuggage
     */
    public MatchLuggage(int issuesId, String typeLuggage, String brandLuggage, String destinationLuggage, String flightnumberLuggage) {
        this.issuesId = issuesId;
        this.typeLuggage = typeLuggage;
        this.brandLuggage = brandLuggage;
        this.destinationLuggage = destinationLuggage;
        this.flightnumberLuggage = flightnumberLuggage;
    }

    /**
     *
     * @return issue id of the luggage
     */
    public int getIssuesId() {
        return issuesId;
    }

    /**
     *
     * @return type of the luggage
     */
    public String getTypeLuggage() {
        return typeLuggage;
    }

    /**
     *
     * @return brand of the luggage
     */
    public String getBrandLuggage() {
        return brandLuggage;
    }

    /**
     *
     * @return destination of the luggage
     */
    public String getDestinationLuggage() {
        return destinationLuggage;
    }

    /**
     *
     * @return flight number of the luggage
     */
    public String getFlightnumberLuggage() {
        return flightnumberLuggage;
    }
    
}
